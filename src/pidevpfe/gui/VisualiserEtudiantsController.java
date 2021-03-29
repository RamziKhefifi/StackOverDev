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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static javax.swing.UIManager.getString;
import pidevpfe.entities.Etudiant;
import pidevpfe.entities.user;
import pidevpfe.tools.Myconnexion;

/**
 * FXML Controller class
 *
 * @author ramzuss
 */
public class VisualiserEtudiantsController implements Initializable {

    @FXML
    private TableView<user> tvea;
    @FXML
    private TableColumn<user,String> coluser;
    @FXML
    private TableColumn<user,String> colname;
    @FXML
    private TableColumn<?, ?> colemail;
    @FXML
    private TableColumn<?, ?> coldate;
    @FXML
    private Label email;

    /**
     * Initializes the controller class.
     */
    
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      showUser();
    }    
   
    public void seTtext(String email){
    
      this.email.setText(email);
      

    }
       PreparedStatement st=null;
        ResultSet rs=null;
   public ObservableList<user> getuserList(){
        ObservableList<user> userList = FXCollections.observableArrayList();
        Connection cnx = Myconnexion.getInstance().getConnection();
        String req = "select * from user where email=?";
       
        
        try{
             
         
               st = cnx.prepareStatement(req);
            st.setString(1, email.getText());
           
            //String s = cbrole.getSelectionModel().getSelectedItem().toString();
            //pst.setString(3,s);
           
            
            rs = st.executeQuery();
            
           
           user usr;
                
            
            
            while(rs.next()){
                System.err.println("ssss");
                usr = new user(rs.getString("full_name")); // depuis bd //
                
                userList.add(usr);
                
            }
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return userList;
        
    }
    
     public void showUser(){
        ObservableList<user> list = getuserList();


        
        
         

coluser.setCellValueFactory(new PropertyValueFactory<user, String>("full_name"));
        
         //classe user.java

        tvea.setItems(list);
       

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*
    PreparedStatement pst = null;
     ResultSet rs = null;
    public void visualiser() {
     Connection cnx = Myconnexion.getInstance().getConnection();
    String sql = "SELECT affectation.nom_etudiant FROM `affectation` left JOIN `user`  on affectation.nom_encadrant_academique=user.full_name where email=? ";
     try {
            pst = cnx.prepareStatement(sql);
            pst.setString(1, email.getText());
             
            
            rs = pst.executeQuery();
            
            if(rs.next()){ 
               
               
            
                        
             coluser.setText("hahahahaha");
            }
   
       
     
      //button.getScene().getWindow().hide();  //pour fermer interface login

               
            
    
    
    
    
    }
     catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
}
    
*/
}
    

   
