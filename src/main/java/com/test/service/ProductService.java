package com.test.service;

import com.test.exception.RecordNotFoundException;
import com.test.model.ProductResponse;
import com.test.model.ProductDetailEntity;
import com.test.model.ProductEntity;
import com.test.model.ProductRequest;
import com.test.model.ShippingDetail;
import com.test.model.SupplierEntity;
import com.test.repository.ProductDetailRepository;
import com.test.repository.ProductRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  ProductRepository repository;

  @Autowired
  ProductDetailRepository productDetailRepository;

  @Autowired
  SupplierService supplierService;

  public List<ProductResponse> getAllProducts()
  {
    List<ProductEntity> productEntityList = repository.findAll();
    if(productEntityList.size() > 0) {
      List<ProductResponse> productResponses =
          productEntityList.stream().map( e -> {
            try {
              return productEntityToProduct(e);
            } catch (RecordNotFoundException recordNotFoundException) {
              recordNotFoundException.printStackTrace();
            }
            return null;
          }).collect(Collectors.toList());
      return productResponses;
    } else {
      return new ArrayList<ProductResponse>();
    }
  }

  private ProductResponse productEntityToProduct(ProductEntity productEntity)
      throws RecordNotFoundException {
    ProductResponse productResponse = new ProductResponse();
    productResponse.setId(productEntity.getId());
    productResponse.setName(productEntity.getName());
    productResponse.setProductDetailEntity(productEntity.getProductDetailEntity());
    productResponse.setShippingDetail(shippingDetailsByProduct(productEntity.getIdShippingDetail()));
    productResponse.setSupplierEntity(getSupplierById(productEntity.getIdSupplier()));
    return productResponse;
  }

  private ShippingDetail shippingDetailsByProduct(String idShippingDetail ){
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
    return restTemplate.exchange("https://retoolapi.dev/G0X55T/product_ship/"+idShippingDetail, HttpMethod.GET, entity, ShippingDetail.class).getBody();
  }

  private SupplierEntity getSupplierById(Long idSupplier ) throws RecordNotFoundException {
    return supplierService.getSupplierById(idSupplier);
  }

  public ProductDetailEntity getDetailById(Long id) throws RecordNotFoundException {
    Optional<ProductDetailEntity> detail = productDetailRepository.findById(id);
    if(detail.isPresent()) {
      return detail.get();
    } else {
      throw new RecordNotFoundException("No employee record exist for given id");
    }
  }

  public ProductEntity createProduct(ProductRequest productRequest) {
      return repository.save(productRequestToProductEntity(productRequest));
  }

  public ProductEntity updateProduct(ProductRequest productRequest) {
    Optional<ProductEntity> productEntity = repository.findById(productRequest.getId());
    if(productEntity.isPresent()) {
      ProductEntity newEntity = productEntity.get();
      newEntity.setName(productRequest.getName());
      newEntity.setIdShippingDetail(productRequest.getIdShippingDetail());
      newEntity.setIdSupplier(productRequest.getIdSupplier());
      newEntity.setIdShippingDetail(productRequest.getIdShippingDetail());
      newEntity.setProductDetailEntity(productRequest.getProductDetailEntity());
      newEntity = repository.save(newEntity);
      return newEntity;
    }
    return null;
  }

  private ProductEntity productRequestToProductEntity(ProductRequest productRequest){
      ProductEntity productEntity = new ProductEntity();
      productEntity.setName(productRequest.getName());
      productEntity.setIdShippingDetail(productRequest.getIdShippingDetail());
      productEntity.setIdSupplier(productRequest.getIdSupplier());
      productEntity.setIdShippingDetail(productRequest.getIdShippingDetail());
      productEntity.setProductDetailEntity(productRequest.getProductDetailEntity());
    return productEntity;
  }

}
