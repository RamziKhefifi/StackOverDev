/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Esprit.entities;

/**
 *
 * @author ASUS
 */
public class Compte_rendu {
    private int id_cr;
    private byte[] fichier;
    private boolean validation_cr;
    private String commentaire;
    
        public Compte_rendu(){
    }
    
  public Compte_rendu( String commentaire) {
        this.commentaire = commentaire;
    }



    public int getId_cr() {
        return id_cr;
    }

    public void setId_cr(int id_cr) {
        this.id_cr = id_cr;
    }

    public byte[] getFichier() {
        return fichier;
    }

    public void setFichier(byte[] fichier) {
        this.fichier = fichier;
    }

    public boolean isValidation_cr() {
        return validation_cr;
    }

    public void setValidation_cr(boolean validation_cr) {
        this.validation_cr = validation_cr;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "Compte_rendu{" + "id_cr=" + id_cr + ", fichier=" + fichier + ", validation_cr=" + validation_cr + ", commentaire=" + commentaire + '}';
    }
    

  
  
}
