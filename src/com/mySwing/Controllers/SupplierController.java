package com.mySwing.Controllers;

import java.util.ArrayList;
import java.util.List;

import com.mySwin.Models.Supplier;
import com.mySwing.Services.SupplierService;

public class SupplierController implements SupplierService {

    private List<Supplier> suppliers;

    public SupplierController() {
        // Initialize suppliers or fetch them from a data source
        this.suppliers = new ArrayList<>();
    }

    @Override
    public void addSupplier(Supplier supplier) {
        // Placeholder logic to add a supplier
        suppliers.add(supplier);
        System.out.println("Supplier added: " + supplier);
    }

    @Override
    public void updateSupplier(Supplier supplier) {
        // Placeholder logic to update a supplier
        for (Supplier existingSupplier : suppliers) {
            if (existingSupplier.getSupplierId() == supplier.getSupplierId()) {
                existingSupplier.setSupplierName(supplier.getSupplierName());
                existingSupplier.setContactInformation(supplier.getContactInformation());
                existingSupplier.setEmail(supplier.getEmail());
                System.out.println("Supplier updated: " + supplier);
                return;
            }
        }
        System.out.println("Supplier not found for update: " + supplier);
    }

    @Override
    public void deleteSupplier(int supplierId) {
        // Placeholder logic to delete a supplier
        suppliers.removeIf(supplier -> supplier.getSupplierId() == supplierId);
        System.out.println("Supplier deleted with ID: " + supplierId);
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        // Placeholder logic to get all suppliers
        System.out.println("Getting all suppliers");
        return new ArrayList<>(suppliers);
    }

    @Override
    public Supplier getSupplierById(int supplierId) {
        // Placeholder logic to get a supplier by ID
        for (Supplier supplier : suppliers) {
            if (supplier.getSupplierId() == supplierId) {
                System.out.println("Getting supplier by ID: " + supplierId + " - " + supplier);
                return supplier;
            }
        }
        System.out.println("Supplier not found for ID: " + supplierId);
        return null;
    }
}
