package com.test.model;

import io.swagger.annotations.ApiModelProperty;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="PRODUCT")
public class ProductEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name="name")
  private String name;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "product_detail_id", referencedColumnName = "id")
  private ProductDetailEntity productDetailEntity;

  @Column(name="id_shipping_detail")
  private String idShippingDetail;

  @Column(name="id_supplier")
  private Long idSupplier;

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
