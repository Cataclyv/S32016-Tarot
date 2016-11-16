package jeu;

import java.util.Observable;
import java.util.ArrayList;

/**
 * Partie "modele" de l'architecture MVC ; contient les donnees de l'application.
 * @author jdespret
 * 
 */
public class Modele extends Observable{
	/**
	 * Collection contenant l'integralite des cartes utilisables dans le jeu.
	 */
	private ArrayList<Carte> cartes;
	/**
	 * Tableau contenant les 4 mains, chaque main contenant un certain nombre de cartes.
	 */
	private Carte[][] mains;
	
	/**
	 * Collection contenant les cartes du Chien.
	 */
	private ArrayList<Carte> chien;
	
	/**
	 * Constructeur par défaut de Modele.
	 */
	public Modele() {
		cartes = new ArrayList<Carte>();
		chien = new ArrayList<Carte>();
		
		mains = new Carte[4][24];
		
		construireJeu();
	}
	
	/**
	 * Initialise les 78 cartes du jeu de Tarot dans la collection de cartes.
	 */
	private void construireJeu() {
		cartes.clear();
		construireCouleur(CouleurCarte.pique);
		construireCouleur(CouleurCarte.coeur);
		construireCouleur(CouleurCarte.carreau);
		construireCouleur(CouleurCarte.trefle);
	}
	
	private void construireCouleur(CouleurCarte couleur) {
		for(int valeur=1 ; valeur<=14 ; valeur++) {
			cartes.add(new Carte(couleur, valeur, ""));
		}
	}
}
