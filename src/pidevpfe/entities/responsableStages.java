/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevpfe.entities;

/**
 *
 * @author ramzuss
 */
public class responsableStages extends user{
    private String nom_faculte; 
    public responsableStages(String user_name, String full_name, String email, String password, String confirm_p, String role, String adress) {
        super(user_name, full_name, email, password, confirm_p, role, adress);
    }

    public responsableStages(String nom_faculte, String user_name, String full_name, String email, String password, String confirm_p, String role, String adress) {
        super(user_name, full_name, email, password, confirm_p, role, adress);
        this.nom_faculte = nom_faculte;
    }

    
    
    public String getNom_faculte() {
        return nom_faculte;
    }

    public void setNom_faculte(String nom_faculte) {
        this.nom_faculte = nom_faculte;
    }

    @Override
    public String toString() {
        return "responsableStages{"+super.toString() + "nom_faculte=" + nom_faculte + '}';
    }
    
    
}
