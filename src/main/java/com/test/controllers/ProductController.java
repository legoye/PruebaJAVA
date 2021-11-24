package com.test.controllers;

import com.test.exception.RecordNotFoundException;
import com.test.model.ProductResponse;
import com.test.model.ProductDetailEntity;
import com.test.model.ProductEntity;
import com.test.model.ProductRequest;
import com.test.service.ProductService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private ProductService service;

  @GetMapping
  public ResponseEntity<List<ProductResponse>> getAllProducts() {
    List<ProductResponse> list = service.getAllProducts();
    return new ResponseEntity<List<ProductResponse>>(list, new HttpHeaders(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ProductDetailEntity> geDetailProductById(@PathVariable("id") Long id)
      throws RecordNotFoundException {
    ProductDetailEntity entity = service.getDetailById(id);
    return new ResponseEntity<ProductDetailEntity>(entity, new HttpHeaders(), HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<ProductEntity> createProduct(@Valid @RequestBody ProductRequest productRequest){
    ProductEntity product = service.createProduct(productRequest);
    return new ResponseEntity<ProductEntity>(product, new HttpHeaders(), HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.PUT)
  public ResponseEntity<ProductEntity> updateProduct(@Valid @RequestBody ProductRequest productRequest)
      throws RecordNotFoundException {
    ProductEntity product = service.updateProduct(productRequest);
    return new ResponseEntity<ProductEntity>(product, new HttpHeaders(), HttpStatus.OK);
  }


}
