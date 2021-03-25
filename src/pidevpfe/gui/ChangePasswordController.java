/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevpfe.gui;

import java.net.URL;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javax.swing.JOptionPane;
import pidevpfe.tools.Myconnexion;

/**
 * FXML Controller class
 *
 * @author ramzuss
 */
public class ChangePasswordController implements Initializable {

   @FXML
    public PasswordField pfactuel;

    @FXML
    public PasswordField pfnouveau;

    @FXML
    public PasswordField pfconfirme;

    @FXML
    public LoginController lg;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }   
    
    public String cryptage(String input) throws Exception
    {
     String password =input;

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes());

        byte byteData[] = md.digest();

        //convertir le tableau de bits en une format hexadécimal - méthode 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        } 
       
        //convertir le tableau de bits en une format hexadécimal - méthode 2
        StringBuffer hexString = new StringBuffer();
     for (int i=0;i<byteData.length;i++) {
      String hex=Integer.toHexString(0xff & byteData[i]);
          if(hex.length()==1) hexString.append('0');
          hexString.append(hex);
     }
    return(hexString.toString());
    }
    
    public void changePassword(){
    Connection connection = Myconnexion.getInstance().getConnection();
        PreparedStatement stmt;

        try {
            stmt = connection.prepareStatement("UPDATE user SET password=?, confirm_password=? WHERE confirm_password=?");
           
            if  (pfnouveau.getText().length()<8) {

            JOptionPane.showMessageDialog(null, "verifier votre nouveau mot de passe");
             }
            else if  (!(pfnouveau.getText().equalsIgnoreCase(pfconfirme.getText()))) {

            JOptionPane.showMessageDialog(null, "verifier la confirmation du password");
             }
              
            else{
            stmt.setString(3, (pfactuel.getText()));
            
              
           
            stmt.setString(1, cryptage(pfnouveau.getText()));
            stmt.setString(2, pfconfirme.getText());
           
              int i = stmt.executeUpdate();

            System.out.println(i);
            JOptionPane.showMessageDialog(null, "votre mot de passe à été changé avec succès");

            }

        }catch (Exception e){
            e.printStackTrace();
        }
         
    }

  

}
