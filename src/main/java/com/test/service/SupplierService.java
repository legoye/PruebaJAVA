package com.test.service;

import com.test.exception.RecordNotFoundException;
import com.test.model.SupplierEntity;
import com.test.repository.SupplierRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {

  @Autowired
  SupplierRepository supplierRepository;

  @Cacheable(value = "supplierCache")
  public SupplierEntity getSupplierById(Long id) throws RecordNotFoundException
  {
    Optional<SupplierEntity> supplier = supplierRepository.findById(id);

    if(supplier.isPresent()) {
      return supplier.get();
    } else {
      throw new RecordNotFoundException("No supplier record exist for given id");
    }
  }
}
