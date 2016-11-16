package jeu;

/**
 * Partie "Contrôleur" de l'architecture MVC ; fait le lien entre la vue et le modèle.
 * @author jdespret
 *
 */
public class Controleur {
	/**
	 * Unique modele de l'application, dans le controleur.
	 */
	private Modele modele;
	
	public Controleur(Modele modele) {
		this.modele = modele;
	}

}
