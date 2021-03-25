/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevpfe.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ramzuss
 */
public class AccueilResponsableController implements Initializable {

   @FXML
    private Label laccueil;

    @FXML
    private Label lgereruser;
    
    @FXML
    private Label lgererprofile;
    
    @FXML
    private Label laffecter;
    
    @FXML
    private Label changepassword;
    
    @FXML
    private TextField tnom;
    
    @FXML
    private Button btn;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      
   
    
   
    public void changepassword() throws IOException{
    
     changepassword.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("changePassword.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }
    
     public void gererUser() throws IOException{
    
     lgereruser.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("GererUser.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }
     
     public void affecterEncadrant() throws IOException{
    
     laffecter.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("AffecterEncadrant.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
    }
     
     public void gererProfile() throws IOException{
    
     lgererprofile.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("GererProfile.fxml"));
                Stage mainStage = new Stage();
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
                mainStage.show();
                
    }
}
