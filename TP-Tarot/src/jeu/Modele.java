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
	 * Liste comprenant 4 sous-listes correspondant aux 4 mains
	 */
	private ArrayList<ArrayList<Carte> > mains;
	
	/**
	 * Collection contenant les cartes du Chien.
	 */
	private ArrayList<Carte> chien;
	/**
	 * Nombre de cartes maximum par main, doit etre atteint a la fin de toutes les distributions.
	 */
	private final int NB_CARTES_PAR_MAIN = 18;
	/**
	 * Nombre de cartes a tirer a chaque distribution.
	 */
	private final int NB_CARTES_A_TIRER = 3;

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
		for(int valeur = 1 ; valeur < 21 ; valeur++) {
			cartes.add(new Carte(couleur, valeur, couleur.toString()+valeur));
		}
	}
	
	/**
	 * Retire des cartes du jeu et les places dans la main du joueur courant
	 * @param tour -> a qui donner les cartes
	 * @return TRUE si les cartes ont bien ete distribuees, FAUX sinon (cas ou tout a ete distribue)
	 */
	public boolean tirerCartes(int tour) {
		if(mains.get(tour).size() < NB_CARTES_PAR_MAIN) {
			int i;
			for(i=0 ; i<NB_CARTES_A_TIRER ; ++i) {
				mains.get(tour).add(cartes.get(i));
			}
			for(i=0 ; i<NB_CARTES_A_TIRER ; ++i) {
				cartes.remove(0);
			}
			setChanged();
			notifyObservers();
			return true;
		}
		return false;
	}
}