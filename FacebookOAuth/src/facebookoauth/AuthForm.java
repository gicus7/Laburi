/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facebookoauth;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.scope.FacebookPermissions;
import com.restfb.scope.ScopeBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.awt.event.MouseEvent;

import static facebookoauth.Alignment.centerTheWindow;

/**
 * @author olegb
 */
public class AuthForm extends javax.swing.JFrame {

    MouseEvent getPositionEvent;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LoginButton;
    private javax.swing.JLabel MessageToSend;
    private javax.swing.JPasswordField PasswordField;
    private javax.swing.JPanel SendPanel;
    private javax.swing.JLabel SendTo;
    private javax.swing.JTextField UsernameTextField;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanelCloseWindow;
    private javax.swing.JPanel jPanelDragWindow;

    /**
     * Creates new form AuthForm
     */
    public AuthForm() {
        initComponents();

        UsernameTextField.setText("");
        PasswordField.setText("");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AuthForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AuthForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AuthForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AuthForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AuthForm().setVisible(true);
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelDragWindow = new javax.swing.JPanel();
        jPanelCloseWindow = new javax.swing.JPanel();
        SendPanel = new javax.swing.JPanel();
        UsernameTextField = new javax.swing.JTextField();
        MessageToSend = new javax.swing.JLabel();
        SendTo = new javax.swing.JLabel();
        LoginButton = new javax.swing.JButton();
        PasswordField = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelDragWindow.setBackground(new java.awt.Color(192, 192, 192));
        jPanelDragWindow.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanelDragWindowMouseDragged(evt);
            }
        });
        jPanelDragWindow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanelDragWindowMousePressed(evt);
            }
        });

        jPanelCloseWindow.setBackground(new java.awt.Color(255, 51, 51));
        jPanelCloseWindow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanelCloseWindowMousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCloseWindowLayout = new javax.swing.GroupLayout(jPanelCloseWindow);
        jPanelCloseWindow.setLayout(jPanelCloseWindowLayout);
        jPanelCloseWindowLayout.setHorizontalGroup(
                jPanelCloseWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 33, Short.MAX_VALUE)
        );
        jPanelCloseWindowLayout.setVerticalGroup(
                jPanelCloseWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelDragWindowLayout = new javax.swing.GroupLayout(jPanelDragWindow);
        jPanelDragWindow.setLayout(jPanelDragWindowLayout);
        jPanelDragWindowLayout.setHorizontalGroup(
                jPanelDragWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelDragWindowLayout.createSequentialGroup()
                                .addGap(0, 307, Short.MAX_VALUE)
                                .addComponent(jPanelCloseWindow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanelDragWindowLayout.setVerticalGroup(
                jPanelDragWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanelCloseWindow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanelDragWindow, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 30));

        SendPanel.setBackground(new java.awt.Color(102, 0, 204));
        SendPanel.setPreferredSize(new java.awt.Dimension(800, 500));
        SendPanel.setLayout(null);
        SendPanel.add(UsernameTextField);
        UsernameTextField.setBounds(80, 130, 180, 30);

        MessageToSend.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        MessageToSend.setForeground(new java.awt.Color(240, 240, 240));
        MessageToSend.setText("Password");
        SendPanel.add(MessageToSend);
        MessageToSend.setBounds(130, 170, 110, 30);

        SendTo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        SendTo.setForeground(new java.awt.Color(240, 240, 240));
        SendTo.setText("Username");
        SendPanel.add(SendTo);
        SendTo.setBounds(130, 90, 110, 30);

        LoginButton.setText("Login");
        LoginButton.setToolTipText("");
        LoginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LoginButtonMouseClicked(evt);
            }
        });
        SendPanel.add(LoginButton);
        LoginButton.setBounds(130, 260, 79, 21);
        SendPanel.add(PasswordField);
        PasswordField.setBounds(80, 210, 180, 30);
        SendPanel.add(jLabel2);
        jLabel2.setBounds(610, 390, 50, 50);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(240, 240, 240));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("SingIn");
        SendPanel.add(jLabel3);
        jLabel3.setBounds(40, 20, 260, 56);

        getContentPane().add(SendPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 340, 310));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanelCloseWindowMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelCloseWindowMousePressed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jPanelCloseWindowMousePressed

    private void jPanelDragWindowMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelDragWindowMouseDragged
        // Set new location
        setLocation(evt.getXOnScreen() - getPositionEvent.getX(),
                evt.getYOnScreen() - getPositionEvent.getY());
    }//GEN-LAST:event_jPanelDragWindowMouseDragged

    private void jPanelDragWindowMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelDragWindowMousePressed
        // Get current position
        getPositionEvent = evt;
    }//GEN-LAST:event_jPanelDragWindowMousePressed

    private void LoginButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoginButtonMouseClicked
        String username = UsernameTextField.getText();
        String password = new String(PasswordField.getPassword());
        String appID = "3756977847707176";
        String domain = "https://google.com/";
        ScopeBuilder permissions = new ScopeBuilder();
        permissions.addPermission(FacebookPermissions.PUBLIC_PROFILE);
        permissions.addPermission(FacebookPermissions.PAGES_SHOW_LIST);
        FacebookClient facebookClient = new DefaultFacebookClient(Version.VERSION_7_0);
        String oauthUrl = facebookClient.getLoginDialogUrl(appID, domain, permissions);
        System.setProperty("webdriver.Chrome.driver", System.getProperty("user.dir") + "/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(oauthUrl + "&response_type=token");
        WebElement usernameField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("pass"));
        WebElement loginButton = driver.findElement(By.id("loginbutton"));
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
        String accessTockenPattern = "^(https?:\\/\\/)?(www\\.)?google.com\\/#access_token=.*";
        while (!driver.getCurrentUrl().matches(accessTockenPattern)) {
        }
        String responseUrl = driver.getCurrentUrl();
        String accessToken = responseUrl.substring(responseUrl.indexOf("=") + 1, responseUrl.indexOf("&"));
        MainForm mainWindow = new MainForm(accessToken);
        centerTheWindow(mainWindow);
        mainWindow.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_LoginButtonMouseClicked
    // End of variables declaration//GEN-END:variables
}
