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
	public Carte(CouleurCarte couleur, int valeur) {
		this.couleur = couleur;
		this.valeur = valeur;

		retourne = false;
		
		chemin = "img/";
		if(couleur != CouleurCarte.Excuse && couleur != CouleurCarte.Atout)
			chemin += valeur + couleur.toString() + ".jpg";
		else if(couleur != CouleurCarte.Excuse && couleur == CouleurCarte.Atout)
			chemin += valeur + ".jpg";
		else if(couleur == CouleurCarte.Excuse && couleur != CouleurCarte.Atout)
			chemin += "excuse.jpg";
	}
	
	/**
	 * Retourne le chemin necessaire pour acceder au fichier contenant l'image
	 * @return
	 */
	public String getChemin() {
		return chemin;
	}
}
