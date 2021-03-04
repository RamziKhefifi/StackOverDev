/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Esprit.services;
import Esprit.entities.Compte_rendu;
import Esprit.tools.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 *
 * @author ASUS
 */
public class Compte_renduCRUD {
    private Connection cnx;
    private PreparedStatement ste;
    
    public Compte_renduCRUD() {
        cnx = MyConnection.getInstance().getConnection();
    }
    
    public void ajouterCommentaire(Compte_rendu cr){
        String req ="INSERT INTO compte_rendu (commentaire) values (?)";
        try {
            ste = cnx.prepareStatement(req);
            ste.setString(1, cr.getCommentaire());
            ste.executeUpdate();
            System.out.println("Commentaire ajouté");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }
        
    }

 
        
    }
    


