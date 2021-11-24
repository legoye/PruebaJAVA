package com.test.repository;

import com.test.model.ProductDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository  extends JpaRepository<ProductDetailEntity, Long> {
}
