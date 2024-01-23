package com.mySwing.Services;

import java.util.List;

import com.mySwin.Models.Supplier;

public interface SupplierService {
    void addSupplier(Supplier supplier);
    void updateSupplier(Supplier supplier);
    void deleteSupplier(int supplierId);
    List<Supplier> getAllSuppliers();
    Supplier getSupplierById(int supplierId);
}

