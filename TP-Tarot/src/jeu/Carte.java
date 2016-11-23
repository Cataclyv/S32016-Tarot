package jeu;

import javax.swing.JButton;

/**
 * Partie logique et graphique de la carte ; possède une couleur, une valeur et le chemin d'une image.
 * @author jdespret
 *
 */
public final class Carte extends JButton {
	/**
	 * Couleur de la carte.
	 */
	private CouleurCarte couleur;
	/**
	 * Valeur de la carte.
	 */
	private int valeur;
	/**
	 * Chemin d'accès à l'image de la carte.
	 */
	private String chemin;
	/**
	 * Indique si la carte est retournee (face visible) ou non (face cachee).
	 */
	private boolean retourne;
	
	/**
	 * Constructeur paramétré de Carte.
	 * @param couleur
	 * @param valeur
	 * @param chemin
	 */
	public Carte(CouleurCarte couleur, int valeur, String chemin) {
		this.couleur = couleur;
		this.valeur = valeur;
		this.chemin = chemin;
		retourne = false;
	}
	
	/**
	 * Retourne la carte ; la Vue n'affichera pas une face cachee mais la vraie face de la carte.
	 */
	public void retourner() {
		retourne = true;
	}
}
