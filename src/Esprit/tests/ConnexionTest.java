package Esprit.tests;

import Esprit.entities.Compte_rendu;
import Esprit.services.Compte_renduCRUD;
import Esprit.tools.MyConnection;

/**
 *
 * @author Fayechi
 */
public class ConnexionTest {

    public static void main(String[] args) {
        MyConnection mc= MyConnection.getInstance();
        Compte_renduCRUD crr = new Compte_renduCRUD();
        Compte_rendu cr1 = new Compte_rendu("bonsoir");
        crr.ajouterCommentaire(cr1);
    }
    
}
