package thaitay.com.fashion.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thaitay.com.fashion.entity.Supplier;
import thaitay.com.fashion.repository.SupplierRepository;
import thaitay.com.fashion.service.SupplierService;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;
    @Override
    public List<Supplier> findAllSupplier() {
        return supplierRepository.findAll();
    }

    @Override
    public Optional<Supplier> findSupplierById(Long supplierId) {
        return supplierRepository.findById(supplierId);
    }

    @Override
    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public void removeSupplier(Long supplierId) {
        supplierRepository.deleteById(supplierId);
    }
}
