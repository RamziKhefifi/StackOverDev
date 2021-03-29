/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevpfe.gui;

import java.io.IOException;
import pidevpfe.tools.Myconnexion;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import pidevpfe.entities.user;

/**
 * FXML Controller class
 *
 * @author ramzuss
 */

public class LoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    public TextField tfemail;

     @FXML
    private PasswordField tfpassword;

   
      @FXML
    private static TextField tfnom;

     
    @FXML
    private AccueilResponsableController acc;
    
     @FXML
    private Button button;

    
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
      
    } 
    
   
    
    
    
    
    
    PreparedStatement pst = null;
     ResultSet rs = null;
       public void Login () throws Exception{  
           
    Connection cnx = Myconnexion.getInstance().getConnection();
    String sql = "Select * from user where email = ? and password = ?  ";
        
    try {
            pst = cnx.prepareStatement(sql);
            pst.setString(1, tfemail.getText());
             pst.setString(2, cryptage(tfpassword.getText()));
            //String s = cbrole.getSelectionModel().getSelectedItem().toString();
            //pst.setString(3,s);
           
            
            rs = pst.executeQuery();
            
            if(rs.next()){ 
               
               
            String role =rs.getString("role");
                if(role.equals("responsable des stages"))
                {     
                    
             String fullname=rs.getString(3);
             String email=rs.getString(4);
   FXMLLoader loader=new FXMLLoader();
   loader.setLocation(getClass().getResource("accueilResponsable.fxml"));
        try {
            loader.load();
        } catch (Exception e) {
        }
        AccueilResponsableController acc=loader.getController();
        acc.seTtext(fullname,email);
        Parent p=loader.getRoot();
        Stage stage=new Stage();
        stage.setScene(new Scene(p));
        stage.show();
     
      button.getScene().getWindow().hide();  //pour fermer interface login

               
            }  
                 if(role.equals("Etudiant"))
                {     
                     String fullname=rs.getString(3);
             String email=rs.getString(4);
   FXMLLoader loader=new FXMLLoader();
   loader.setLocation(getClass().getResource("accueilResponsable.fxml"));
        try {
            loader.load();
        } catch (Exception e) {
        }
        AccueilResponsableController acc=loader.getController();
        acc.seTtext(fullname,email);
        Parent p=loader.getRoot();
        Stage stage=new Stage();
        stage.setScene(new Scene(p));
        stage.show();
     
      button.getScene().getWindow().hide();  //pour fermer interface login

                    
            }
            if(role.equals("Encadrant Académique "))
                {     
                     String fullname=rs.getString(3);
             String email=rs.getString(4);
   FXMLLoader loader=new FXMLLoader();
   loader.setLocation(getClass().getResource("accueilEncadrantAca.fxml"));
        try {
            loader.load();
        } catch (Exception e) {
        }
        AccueilEncadrantAcaController aea=loader.getController();
        aea.seTtext(fullname,email);
        Parent p=loader.getRoot();
        Stage stage=new Stage();
        stage.setScene(new Scene(p));
        stage.show();
     
      button.getScene().getWindow().hide();  //pour fermer interface login

                    
            }
                 
            } 
          
            else
                JOptionPane.showMessageDialog(null, "Invalide Username Or Password or role");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
  
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
      
 /*
     public static void sendMail(String recepient,String passwd) throws Exception {
        System.out.println("Preparing to send email");
        try{
        Properties properties = new Properties();

        //Enable authentication
        properties.put("mail.smtp.auth", "true");
        //Set TLS encryption enabled
        properties.put("mail.smtp.starttls.enable", "true");
        //Set SMTP host
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //Set smtp port
        properties.put("mail.smtp.port", "587");

        //Your gmail address
        String accountEmail = recepient;
        //Your gmail password
        String password = passwd;

        //Create a session with account credentials
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(accountEmail, password);
            }
        });

        //Prepare email message
        Message message = prepareMessage(session, accountEmail, recepient);
        

        //Send mail
       Transport.send(message);
        System.out.println("Message sent successfully");
        }catch (MessagingException ex) {
            Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Message prepareMessage(Session session, String accountEmail, String recepient) {
      
        Connection cnx = Myconnexion.getInstance().getConnection();
     PreparedStatement pst = null;
     ResultSet rs = null;
    
        ObservableList<user> userList = FXCollections.observableArrayList();
       
        String req = "select (user_name),(email),(confirm_password) from user where email=?";
        Statement st;
        
       
        try{
            pst = cnx.prepareStatement(req);
            pst.setString(1,accountEmail);
            
            rs = pst.executeQuery();
            user usr;
            while(rs.next()){
                usr = new user(rs.getString("user_name"),rs.getString("email"),rs.getString("confirm_password")); // depuis bd //
                userList.add(usr);
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress() );
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Password");
            message.setText("Bonjour "+" : \n" +userList);
            
            return message;
        } catch (MessagingException ex) {
            Logger.getLogger(userController.class.getName()).log(Level.SEVERE, null, ex);
        }
          return null;
    }

    
    public void forgot() throws Exception{
     
        Connection cnx = Myconnexion.getInstance().getConnection();
     PreparedStatement pst = null;
     ResultSet rs = null;
    
        ObservableList<user> userList = FXCollections.observableArrayList();
        String req = "select (confirm_password) from user where email=?";
        Statement st;
        
       
        try{
            pst = cnx.prepareStatement(req);
            pst.setString(1,this.tfemail.getText());
            rs = pst.executeQuery();
            user usr;
            while(rs.next()){
                usr = new user(rs.getString("user_name"),rs.getString("email"),rs.getString("confirm_password")); // depuis bd //
                userList.add(usr);
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
  sendMail(tfemail.getText(),rs.getString("confirm_password"));
    }
         
     */
    
        
   
}
