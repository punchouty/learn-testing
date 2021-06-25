package com.racloop.learn.testing.api;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BrainIdControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private BrainIdRepository brainIdRepository;

    @BeforeEach
    void setUp() {
        BrainId brainId = BrainId.builder().id(1L).domain("common").keyToken("key-token-1").build();
        brainIdRepository.save(brainId);
        brainId = BrainId.builder().id(2L).domain("common").keyToken("key-token-2").build();
        brainIdRepository.save(brainId);
        brainId = BrainId.builder().id(3L).domain("telecom").keyToken("key-token-3").build();
        brainIdRepository.save(brainId);
    }

    @AfterEach
    void tearDown() {
        brainIdRepository.deleteAll();
    }

    @Test
    void testAll() {
        // given
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        // when
        ResponseEntity<String> response = testRestTemplate.exchange("/id", HttpMethod.GET, entity, String.class);

        // then
        System.out.println(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertEquals(3, (Integer) JsonPath.parse(response.getBody()).read("$..*.length()"));
        assertEquals("key-token-1", JsonPath.parse(response.getBody()).read("$[0].keyToken"));
        assertEquals("key-token-2", JsonPath.parse(response.getBody()).read("$[1].keyToken"));
        assertEquals("key-token-3", JsonPath.parse(response.getBody()).read("$[2].keyToken"));
    }

    @Test
    void searchDefault() {
        // given
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        // when
        ResponseEntity<String> response = testRestTemplate.exchange("/id/search", HttpMethod.GET, entity, String.class);

        // then
        System.out.println(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertEquals(2, (Integer) JsonPath.parse(response.getBody()).read("$..*.length()"));
        assertEquals("key-token-1", JsonPath.parse(response.getBody()).read("$[0].keyToken"));
        assertEquals("key-token-2", JsonPath.parse(response.getBody()).read("$[1].keyToken"));
    }

    @Test
    void searchDomain() {
        // given
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        // when
        ResponseEntity<String> response = testRestTemplate.exchange("/id/search?domain=telecom", HttpMethod.GET, entity, String.class);

        // then
        System.out.println(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertEquals(1, (Integer) JsonPath.parse(response.getBody()).read("$..*.length()"));
        assertEquals("key-token-3", JsonPath.parse(response.getBody()).read("$[0].keyToken"));
    }

    @Test
    void create() {
        // given
        BrainId brainId = BrainId.builder().id(100L).domain("common").keyToken("key-token100").build();
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", MediaType.APPLICATION_JSON.toString());
        HttpEntity<BrainId> entity = new HttpEntity<>(brainId, headers);

        // when
        ResponseEntity<String> response = testRestTemplate.exchange("/id", HttpMethod.POST, entity, String.class);

        // then
        System.out.println(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        assertEquals(4, brainIdRepository.findAll().size());
    }

    @Test
    void createFailWithoutId() {
        // given
        BrainId brainId = BrainId.builder().id(null).domain("telecom").keyToken("key-token100").build();
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", MediaType.APPLICATION_JSON.toString());
        HttpEntity<BrainId> entity = new HttpEntity<>(brainId, headers);

        // when
        ResponseEntity<String> response = testRestTemplate.exchange("/id", HttpMethod.POST, entity, String.class);

        // then
        System.out.println(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Id cannot be null", JsonPath.parse(response.getBody()).read("$.message"));
    }

    @Test
    void createFailValidation() {
        // given
        BrainId brainId = BrainId.builder().id(100L).domain(null).keyToken("key-token100").build();
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", MediaType.APPLICATION_JSON.toString());
        HttpEntity<BrainId> entity = new HttpEntity<>(brainId, headers);

        // when
        ResponseEntity<String> response = testRestTemplate.exchange("/id", HttpMethod.POST, entity, String.class);

        // then
        System.out.println(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("must not be blank", JsonPath.parse(response.getBody()).read("$.errors.domain"));
    }

    @Test
    void edit() {
        // given
        BrainId brainId = BrainId.builder().id(1L).domain("common").keyToken("key-token-changed").build();
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", MediaType.APPLICATION_JSON.toString());
        HttpEntity<BrainId> entity = new HttpEntity<>(brainId, headers);

        // when
        ResponseEntity<String> response = testRestTemplate.exchange("/id/edit/1", HttpMethod.PUT, entity, String.class);

        // then
        System.out.println(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaders().getContentType());
        Optional<BrainId> byId = brainIdRepository.findById(1L);
        assertEquals("key-token-changed", byId.get().getKeyToken());
    }

    @Test
    void delete() {
        // given
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        // when
        ResponseEntity<String> response = testRestTemplate.exchange("/id/1", HttpMethod.DELETE, entity, String.class);

        // then
        System.out.println(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, brainIdRepository.findAll().size());
    }
}