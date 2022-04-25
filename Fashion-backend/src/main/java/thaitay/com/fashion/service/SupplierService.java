package thaitay.com.fashion.service;


import thaitay.com.fashion.entity.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierService {

    List<Supplier> findAllSupplier();
    Optional<Supplier> findSupplierById(Long supplierId);
    Supplier saveSupplier(Supplier supplier);
    void removeSupplier(Long supplierId);

}
