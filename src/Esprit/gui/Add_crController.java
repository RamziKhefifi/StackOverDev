/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Esprit.gui;

import Esprit.entities.Compte_rendu;
import Esprit.services.Compte_renduCRUD;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class Add_crController implements Initializable {
    List<String> listFile;
     @FXML
    private Button btnUpload;
    private Button btnEnvoyer;
    private TextField tcomment;
    
      @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }   
    
     @FXML
    void FileChooser(ActionEvent event){
        
        FileChooser fc = new FileChooser();
        List<File> f = fc.showOpenMultipleDialog(null);
      for (File file: f)
          System.out.println(file.getAbsolutePath());
    
  
    }
    
    @FXML
    private void AjouterCommentaire(ActionEvent event){
        String rcomment = tcomment.getText();
        Compte_rendu cr = new Compte_rendu(rcomment);
        Compte_renduCRUD crc = new  Compte_renduCRUD();
        crc.ajouterCommentaire(cr) ;


    }
    }
    
