package jeu;

import javax.swing.JButton;

/**
 * Bouton affichant une carte. Utilise dans la Vue pour afficher des cartes ; le joueur peut cliquer dessus, et elles peuvent etre desactivees (pendant l'ecart)
 * @author jdespret
 *
 */
public class CarteGraphique extends JButton{
	/**
	 * Carte associe au bouton.
	 */
	private Carte carte;
	/**
	 * Definit si la carte est face cachee ou retournee.
	 */
	private boolean retourne;
	
	public CarteGraphique(Carte carte) {
		this.carte = carte;
		retourne = false;
	}
	
	/**
	 * Retourne la carte associee au bouton.
	 * @return
	 */
	public Carte getCarte() {
		return carte;
	}
}