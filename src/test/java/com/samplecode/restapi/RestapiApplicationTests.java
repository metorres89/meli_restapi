package com.samplecode.restapi;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestapiApplicationTests 
{
  @LocalServerPort
  int randomServerPort;
  static RestTemplate restTemplate;
  static HttpHeaders headers;
  
	//@Test
	//void contextLoads() {
	//}

  @BeforeAll
  public static void runBeforeAllTestMethods() {
      restTemplate = new RestTemplate();
      headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
  }

  @Test
  public void testMutantSuccess() throws URISyntaxException 
  {
    final String baseUrl = "http://localhost:" + randomServerPort + "/mutant/";
    URI uri = new URI(baseUrl);
    JSONObject sequence = new JSONObject();
    List<String> dnaSeq = Arrays.asList(
      "ATGCGA",
      "CAGTGC",
      "TTATGT",
      "AGAAGG",
      "CCCCTA",
      "TCACTG"
    );
    sequence.appendField("dna", dnaSeq);
    HttpEntity<String> request = new HttpEntity<String>(sequence.toString(), headers);
    ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
    Assert.isTrue(200 == result.getStatusCode().value(), "if DNA is mutant status code must be 200 OK!");
  }

  @Test
  public void testHumanSuccess() throws URISyntaxException 
  {
    final String baseUrl = "http://localhost:" + randomServerPort + "/mutant/";
    URI uri = new URI(baseUrl);
    JSONObject sequence = new JSONObject();
    List<String> dnaSeq = Arrays.asList(
      "TTGCGA",
      "CCCTGC",
      "TTGTCT",
      "AGAAGG",
      "CTCTTA",
      "TCACTG"
    );
    sequence.appendField("dna", dnaSeq);
    HttpEntity<String> request = new HttpEntity<String>(sequence.toString(), headers);

    RestClientResponseException exception = assertThrows(RestClientResponseException.class, () -> {
      restTemplate.postForEntity(uri, request, String.class);
    });

    Assert.isTrue(403 == exception.getRawStatusCode(), "if DNA is human, status code must be 403 forbidden!");
  }

  @Test
  public void testStatsSuccess() throws URISyntaxException 
  {
    final String baseUrl = "http://localhost:" + randomServerPort + "/stats/";
    URI uri = new URI(baseUrl);
    
    ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);

    Assert.isTrue(200 == response.getStatusCodeValue(), "/stats/ should return 200 ok!");
  }
}
