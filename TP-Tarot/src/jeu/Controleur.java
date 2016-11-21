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
	/**
	 * Identifiant correspondant a qui faire la prochaine distribution.
	 */
	private int tour;
	
	public Controleur(Modele modele) {
		this.modele = modele;
		tour = 0;
	}
	
	public boolean distribuerCartes() {
		boolean resultat = modele.tirerCartes(tour);
		++tour;
		if(tour >= 3)
			tour = 0;
		return resultat;
	}

}
