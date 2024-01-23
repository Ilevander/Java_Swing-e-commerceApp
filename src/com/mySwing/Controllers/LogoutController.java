package com.mySwing.Controllers;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.mySwing.Views.LoginView;

public class LogoutController {

    private JFrame currentFrame;

    public LogoutController(JFrame currentFrame) {
        this.currentFrame = currentFrame;
    }

    public void logout() {
        int choice = JOptionPane.showConfirmDialog(currentFrame, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            openLoginView();
        }
    }

    private void openLoginView() {
        SwingUtilities.invokeLater(() -> {
            LoginView loginView = new LoginView();
            loginView.setVisible(true);
            currentFrame.dispose();
        });
    }
}
