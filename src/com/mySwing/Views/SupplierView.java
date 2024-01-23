package com.mySwing.Views;

import com.mySwin.Models.Supplier;
import com.mySwing.Controllers.SupplierController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SupplierView extends JFrame {

    private static final long serialVersionUID = 1L;
    private SupplierController supplierController;

    public SupplierView() {
        this.supplierController = new SupplierController();
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Supplier Management");

        // Supplier Panel
        JPanel supplierPanel = new JPanel();
        JButton addSupplierButton = new JButton("Add Supplier");
        JButton updateSupplierButton = new JButton("Update Supplier");
        JButton deleteSupplierButton = new JButton("Delete Supplier");
        JButton getAllSuppliersButton = new JButton("Get All Suppliers");

        addSupplierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Use a dialog box to get user input for supplier details
                // Example: Supplier supplier = showSupplierInputDialog();
                // supplierController.addSupplier(supplier);
                JOptionPane.showMessageDialog(null, "Add Supplier functionality not implemented.");
            }
        });

        updateSupplierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Use a dialog box to get user input for updated supplier details
                // Example: Supplier updatedSupplier = showUpdatedSupplierInputDialog();
                // supplierController.updateSupplier(updatedSupplier);
                JOptionPane.showMessageDialog(null, "Update Supplier functionality not implemented.");
            }
        });

        deleteSupplierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Use a dialog box to get user input for supplier ID to delete
                // Example: int supplierId = showSupplierIdInputDialog();
                // supplierController.deleteSupplier(supplierId);
                JOptionPane.showMessageDialog(null, "Delete Supplier functionality not implemented.");
            }
        });

        getAllSuppliersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Call the controller to get all suppliers
                    List<Supplier> suppliers = supplierController.getAllSuppliers();
                    // Update the UI with the suppliers
                    // Example: updateSuppliersUI(suppliers);
                    JOptionPane.showMessageDialog(null, "Suppliers: " + suppliers);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error getting suppliers: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        supplierPanel.add(addSupplierButton);
        supplierPanel.add(updateSupplierButton);
        supplierPanel.add(deleteSupplierButton);
        supplierPanel.add(getAllSuppliersButton);

        // Add supplierPanel to the frame
        add(supplierPanel);

        pack();
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SupplierView().setVisible(true);
            }
        });
    }
}
