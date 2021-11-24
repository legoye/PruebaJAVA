package com.test.model;

public class ProductResponse {

  private Long id;

  private String name;

  private ProductDetailEntity productDetailEntity;

  private ShippingDetail shippingDetail;

  private SupplierEntity supplierEntity;

  public ProductResponse() {}

  public ProductResponse(Long id, String name, ProductDetailEntity productDetailEntity,
      ShippingDetail shippingDetail, SupplierEntity supplierEntity) {
    this.id = id;
    this.name = name;
    this.productDetailEntity = productDetailEntity;
    this.shippingDetail = shippingDetail;
    this.supplierEntity = supplierEntity;
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

  public ShippingDetail getShippingDetail() {
    return shippingDetail;
  }

  public void setShippingDetail(ShippingDetail shippingDetail) {
    this.shippingDetail = shippingDetail;
  }

  public SupplierEntity getSupplierEntity() {
    return supplierEntity;
  }

  public void setSupplierEntity(SupplierEntity supplierEntity) {
    this.supplierEntity = supplierEntity;
  }
}
