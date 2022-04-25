package thaitay.com.fashion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import thaitay.com.fashion.entity.Supplier;
import thaitay.com.fashion.service.SupplierService;


import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/supplier")
    public ResponseEntity<List<Supplier>> getAllSupplier() {
        List<Supplier> supplierList = supplierService.findAllSupplier();
        if (supplierList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(supplierList, HttpStatus.OK);
    }
    @GetMapping("/supplier/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> getSupplier(@PathVariable("id") Long id) {
        Optional<Supplier> supplier = supplierService.findSupplierById(id);
        if (!supplier.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(supplier, HttpStatus.OK);
    }
    @Transactional
    @PostMapping("/supplier")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<Supplier> createSupplier(@RequestBody Supplier supplier, UriComponentsBuilder ucBuilder) {
        supplierService.saveSupplier(supplier);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/supplier/{id}").buildAndExpand(supplier.getSupplierId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
    @Transactional
    @PutMapping("/supplier/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> updateSupplier(@PathVariable("id") Long id, @RequestBody Supplier supplier) {
        Optional<Supplier> currentSupplier = supplierService.findSupplierById(id);
        if (!currentSupplier.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        currentSupplier.get().setSupplierName(supplier.getSupplierName());
        supplierService.saveSupplier(currentSupplier.get());
        return new ResponseEntity<>(currentSupplier, HttpStatus.OK);
    }
    @Transactional
    @DeleteMapping("/supplier/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<?> removeSupplier(@PathVariable("id") Long id) {
        Optional<Supplier> supplier = supplierService.findSupplierById(id);
        if (!supplier.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        supplierService.removeSupplier(id);
        return new ResponseEntity<Supplier>(HttpStatus.NO_CONTENT);
    }


}
