package com.test.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ProductRequest {

  private Long id;

  @NotEmpty
  private String name;

  private ProductDetailEntity productDetailEntity;

  @NotEmpty
  private String idShippingDetail;

  @NotNull
  private Long idSupplier;

  public ProductRequest() {
  }

  public ProductRequest(Long id, @NotEmpty String name,
      @NotEmpty ProductDetailEntity productDetailEntity,
      @NotEmpty String idShippingDetail,
      @NotEmpty Long idSupplier) {
    this.id = id;
    this.name = name;
    this.productDetailEntity = productDetailEntity;
    this.idShippingDetail = idShippingDetail;
    this.idSupplier = idSupplier;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ProductDetailEntity getProductDetailEntity() {
    return productDetailEntity;
  }

  public void setProductDetailEntity(ProductDetailEntity productDetailEntity) {
    this.productDetailEntity = productDetailEntity;
  }

  public String getIdShippingDetail() {
    return idShippingDetail;
  }

  public void setIdShippingDetail(String idShippingDetail) {
    this.idShippingDetail = idShippingDetail;
  }

  public Long getIdSupplier() {
    return idSupplier;
  }

  public void setIdSupplier(Long idSupplier) {
    this.idSupplier = idSupplier;
  }
}
