/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevpfe.gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pidevpfe.entities.Etudiant;
import pidevpfe.entities.user;
import static pidevpfe.gui.userController.sendMail;
import pidevpfe.tools.Myconnexion;

/**
 * FXML Controller class
 *
 * @author ramzuss
 */
public class AffecterEncadrantController implements Initializable {

  @FXML
    private ComboBox cbetudiant;

    @FXML
    private ComboBox cbencadrant;
    @FXML
    private TextField tfpro;

  @FXML
    private Button btajouter;

    @FXML
    private Button btmodifier;

    @FXML
    private Button btsupprimer;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showEtudiant();
        showEncadrant();
      
    }    
    
   public ObservableList<user> getEtudiantList(){
        ObservableList<user> userList = FXCollections.observableArrayList();
       
        PreparedStatement pst = null;
     ResultSet rs = null;
       
     Connection cnx = Myconnexion.getInstance().getConnection();
        
       String req = "SELECT full_name FROM user WHERE user.role=? EXCEPT SELECT nom_etudiant FROM affectation ";
        
        try{
           pst = cnx.prepareStatement(req);
            pst.setString(1, "etudiant");
            rs = pst.executeQuery();
             user usr;  
            while(rs.next()){
                usr = new user(rs.getString("full_name")); // depuis bd //
                userList.add(usr);   
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return userList;
        
    }
    
    
     public void showEtudiant(){
           
 ObservableList<user> etudiant = getEtudiantList();      
cbetudiant.setItems(etudiant);
        
     }

     public ObservableList<user> getEncadrantList(){
        ObservableList<user> userList = FXCollections.observableArrayList();
       
        PreparedStatement pst = null;
     ResultSet rs = null;
       
     Connection cnx = Myconnexion.getInstance().getConnection();
        
       String req = "SELECT full_name FROM user WHERE user.role=? except SELECT nom_encadrant_academique FROM affectation  GROUP by nom_encadrant_academique  HAVING count(nom_encadrant_academique) >=3";
        
        try{
           pst = cnx.prepareStatement(req);
            pst.setString(1, "Encadrant Académique ");
            rs = pst.executeQuery();
             user usr;  
            while(rs.next()){
                usr = new user(rs.getString("full_name")); // depuis bd //
                userList.add(usr);   
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return userList;
        
    }
    
    
     public void showEncadrant(){
           
 ObservableList<user> encadrant = getEncadrantList();      
cbencadrant.setItems(encadrant);
        
     }

     public void affecter(){
      Connection connection =Myconnexion.getInstance().getConnection();
        PreparedStatement stm;
        try{
             String e = cbetudiant.getSelectionModel().getSelectedItem().toString();
             String a = cbencadrant.getSelectionModel().getSelectedItem().toString();
            stm = connection.prepareStatement("insert into affectation(nom_etudiant,nom_encadrant_academique,nom_encadrant_entreprise) values(?,?,?)");
             stm.setString(1,e);
            stm.setString(2,a);
            stm.setString(3,tfpro.getText());

        int i = stm.executeUpdate();
            System.out.println(i);
        }
        catch (Exception e){
            e.printStackTrace();
        }
     
     }

    
      
    
     
     
     

}
     