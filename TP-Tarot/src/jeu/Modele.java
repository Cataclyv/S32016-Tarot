package jeu;

import java.util.Observable;
import java.util.ArrayList;

/**
 * Partie "mod�le" de l'architecture MVC ; contient les donn�es de l'application.
 * @author jdespret
 * 
 */
public class Modele extends Observable{
	/**
	 * Collection contenant l'int�gralit� des cartes utilisables dans le jeu.
	 */
	private ArrayList<Carte> cartes;
	
	/**
	 * Collecion contenant les cartes dans la main du joueur.
	 */
	private ArrayList<Carte> mainJoueur;
	
	/**
	 * Collection contenant les cartes du Chien.
	 */
	private ArrayList<Carte> chien;
	
	/**
	 * Constructeur par défaut de Modele.
	 */
	public Modele() {
		cartes = new ArrayList<Carte>();
		mainJoueur = new ArrayList<Carte>();
		chien = new ArrayList<Carte>();
		
		construireJeu();
	}
	
	/**
	 * Initialise les 78 cartes du jeu de Tarot dans la collection de cartes.
	 */
	private void construireJeu() {
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
