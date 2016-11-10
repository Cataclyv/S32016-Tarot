package jeu;

/**
 * Partie logique de la carte ; possède une couleur, une valeur et le chemin d'une image.
 * @author jdespret
 *
 */
public final class Carte {
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
	 * Constructeur paramétré de Carte.
	 * @param couleur
	 * @param valeur
	 * @param chemin
	 */
	public Carte(CouleurCarte couleur, int valeur, String chemin) {
		this.couleur = couleur;
		this.valeur = valeur;
		this.chemin = chemin;
	}
}
