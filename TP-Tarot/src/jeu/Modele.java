package jeu;

import java.util.Observable;
import java.util.ArrayList;

import java.util.Random;

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
	 * Liste comprenant 4 sous-listes correspondant aux 4 mains
	 */
	private ArrayList<ArrayList<Carte> > mains;
	
	/**
	 * Collection contenant les cartes du Chien.
	 */
	private ArrayList<Carte> chien;
	/**
	 * Nombre de cartes a tirer a chaque distribution.
	 */
	private final int NB_CARTES_A_TIRER = 3;
	/**
	 * Nombre de cartes que le Chien doit accueillir.
	 */
	private final int TAILLE_CHIEN = 6;

	/**
	 * Constructeur par défaut de Modele.
	 */
	public Modele() {
		construireTas();
		construireMains();
		construireJeu();
	}
	
	/**
	 * Construit la liste de cartes totale et le chien.
	 */
	private void construireTas() {
		cartes = new ArrayList<Carte>();
		chien = new ArrayList<Carte>();
	}
	
	/**
	 * Construit les 4 mains des 4 joueurs.
	 */
	private void construireMains() {
		mains = new ArrayList<ArrayList<Carte> >(4);
		
		for(int i=0 ; i<4 ; ++i)
			mains.add(new ArrayList<Carte>());
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
		construireAtouts();
		construireExcuse();
		melangerCartes();
	}
	
	/**
	 * Insere 14 cartes de la couleur spécifiée dans le jeu de cartes total.
	 * @param couleur
	 */
	private void construireCouleur(CouleurCarte couleur) {
		for(int valeur=1 ; valeur<=14 ; valeur++) {
			cartes.add(new Carte(couleur, valeur, couleur.toString()+valeur));
		}
	}
	
	/**
	 * Insere les 21 atouts dans le jeu de cartes total.
	 */
	private void construireAtouts() {
		CouleurCarte couleur = CouleurCarte.atout;
		for(int valeur = 1 ; valeur <= 21 ; valeur++) {
			cartes.add(new Carte(couleur, valeur, couleur.toString()+valeur));
		}
	}
	
	/**
	 * Ajoute l'Excuse au jeu de cartes total.
	 */
	private void construireExcuse() {
		cartes.add(new Carte(CouleurCarte.excuse, 0, "excuse"));
	}
	
	/**
	 * Melange aleatoirement le jeu de cartes total.
	 */
	private void melangerCartes() {
		ArrayList<Carte> paquetMelange = new ArrayList<Carte>();
		int index = 0;
		Random aleatoire = new Random();
		while(!cartes.isEmpty()) {
			index = aleatoire.nextInt(cartes.size());
			paquetMelange.add(cartes.get(index));
			cartes.remove(index);
		}
		cartes = paquetMelange;
	}
	
	/**
	 * Retire des cartes du jeu et les places dans la main du joueur courant
	 * @param tour -> a qui donner les cartes
	 * @return TRUE si les cartes ont bien ete distribuees, FAUX sinon (cas ou tout a ete distribue)
	 */
	public boolean tirerCartes(int tour) {
		if(cartes.size() > 0) {
			int i;
			for(i=0 ; i<NB_CARTES_A_TIRER ; ++i) {
				mains.get(tour).add(cartes.get(i));
			}
			for(i=0 ; i<NB_CARTES_A_TIRER ; ++i) {
				cartes.remove(0);
			}
			if(chien.size() < TAILLE_CHIEN) {
				chien.add(cartes.get(0));
				cartes.remove(0);
			}
			setChanged();
			notifyObservers();
			return true;
		}
		return false;
	}
}