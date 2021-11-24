package com.test.model;

public class ShippingDetail {

  private String id;
  private String price;
  private String company;
  private String paymentType;


  public ShippingDetail() {
  }

  public ShippingDetail(String id, String price, String company, String paymentType) {
    this.id = id;
    this.price = price;
    this.company = company;
    this.paymentType = paymentType;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(String paymentType) {
    this.paymentType = paymentType;
  }

}
