package jeu;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Bouton affichant une carte. Utilise dans la Vue pour afficher des cartes ; le joueur peut cliquer dessus, et elles peuvent etre desactivees (pendant l'ecart)
 * @author jdespret
 *
 */
public class CarteGraphique extends JButton {
	/**
	 * Carte associe au bouton.
	 */
	private Carte carte;
	/**
	 * Taille d'une carte graphique.
	 */
	private final Dimension TAILLE_CARTE = new Dimension(100,140);
	
	public CarteGraphique(Point position) {
		setSize(TAILLE_CARTE);
		this.setLocation(position);
		
		setVisible(true);
		setEnabled(false);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setIcon(new ImageIcon("img/cache.jpg"));
	}
	
	/**
	 * Assigne une carte logique a cette carte graphique.
	 * @param carte
	 */
	public void setCarte(Carte carte) {
		this.carte = carte;
	}
	
	/**
	 * Retourne la carte associee au bouton.
	 * @return
	 */
	public Carte getCarte() {
		return carte;
	}
	
	/**
	 * Revele la carte auparavant retournee, en remplacant son image de dos par son image de face.
	 */
	public void retourner() {
		setIcon(new ImageIcon(carte.getChemin()));
	}
}