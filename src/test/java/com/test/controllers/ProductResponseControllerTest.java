package com.test.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import com.test.model.ProductResponse;
import com.test.model.ProductDetailEntity;
import java.net.URL;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductResponseControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void getALLProductsTest() throws Exception {
    ResponseEntity<ProductResponse[]> response = restTemplate.getForEntity(
        new URL("http://localhost:" + port + "/prod/products").toString(), ProductResponse[].class);
    assertTrue(response.hasBody());
    assertEquals(HttpStatus.OK,response.getStatusCode());
  }

  @Test
  public void geDetailProductById() throws Exception{
    ResponseEntity<ProductDetailEntity> response = restTemplate.getForEntity(
        new URL("http://localhost:" + port + "/prod/products/1").toString(), ProductDetailEntity.class);
    assertTrue(response.hasBody());
    assertEquals(HttpStatus.OK,response.getStatusCode());
  }

}