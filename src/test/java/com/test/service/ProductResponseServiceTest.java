package com.test.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


import com.test.exception.RecordNotFoundException;
import com.test.model.ProductResponse;
import com.test.model.ProductDetailEntity;
import com.test.model.ProductEntity;
import com.test.model.ShippingDetail;
import com.test.repository.ProductDetailRepository;
import com.test.repository.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
public class ProductResponseServiceTest {

  @InjectMocks
  private ProductService productService;

  @Mock
  private ProductRepository productRepository;

  @Mock
  private RestTemplate restTemplate;

  @Mock
  private ProductDetailRepository productDetailRepository;

  @Mock
  private SupplierService supplierService;

  @Before
  public void setup() throws  Exception{
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void getALLProductTest() throws RecordNotFoundException {
    when(productRepository.findAll()).thenReturn(getDummyList());
    ShippingDetail shippingDetail = new ShippingDetail();
    ResponseEntity<ShippingDetail> serviceResponse = new ResponseEntity<ShippingDetail>(shippingDetail,
        HttpStatus.OK);
    when(restTemplate.exchange(
        ArgumentMatchers.anyString(),
        ArgumentMatchers.any(HttpMethod.class),
        ArgumentMatchers.any(),
        ArgumentMatchers.<Class<ShippingDetail>>any()))
        .thenReturn(serviceResponse);
    List<ProductResponse> productResponses = productService.getAllProducts();
    assertFalse(productResponses.isEmpty());
    assertEquals(2, productResponses.size());
  }

  @Test
  public void getDetailProductByIdTest() throws RecordNotFoundException {
    when(productDetailRepository.findById(anyLong())).thenReturn(getDummyDetail());
    ProductDetailEntity productDetailEntity =  productService.getDetailById(1L);
    assertNotNull(productDetailEntity);
  }

  private List<ProductEntity> getDummyList(){
    List<ProductEntity> products = new ArrayList<>();
    ProductEntity product1 = new ProductEntity();
    product1.setName("testProduct1");
    product1.setId(1L);
    products.add(product1);
    ProductEntity product2= new ProductEntity();
    product1.setName("testProduct2");
    product1.setId(2L);
    products.add(product2);
    return products;
  }

  private Optional<ProductDetailEntity> getDummyDetail(){
    ProductDetailEntity productDetailEntity = new ProductDetailEntity();
    productDetailEntity.setId(1L);
    productDetailEntity.setBrand("TestBrand");
    productDetailEntity.setLocation("TestLocation");
    productDetailEntity.setType("TestType");
    return Optional.of(productDetailEntity);
  }

}