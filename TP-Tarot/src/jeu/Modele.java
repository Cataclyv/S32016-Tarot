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

		try {
			construireMains();
		}
		catch(TarotException e) {
			e.message();
		}

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
	 * @throws TarotException
	 */
	private void construireMains() throws TarotException {
		mains = new ArrayList<ArrayList<Carte> >(4);

		for(int i=0 ; i<4 ; ++i)
			mains.add(new ArrayList<Carte>());

		if(mains.size() != 4)
			throw new TarotException("Il y a " + mains.size() + " mains au lieu de 4");
	}

	/**
	 * Initialise les 78 cartes du jeu de Tarot dans la collection de cartes.
	 */
	private void construireJeu() {
		cartes.clear();

		try {
			construireCouleur(CouleurCarte.pique, 14);
			construireCouleur(CouleurCarte.coeur, 14);
			construireCouleur(CouleurCarte.carreau, 14);
			construireCouleur(CouleurCarte.trefle, 14);
			construireCouleur(CouleurCarte.atout, 21);
			construireCouleur(CouleurCarte.excuse, 1);
		}
		catch(TarotException e) {
			e.message();
		}
		
		melangerCartes();
	}

	/**
	 * Insere autant de cartes de la couleur spécifiée que precise dans le jeu de cartes total.
	 * @param couleur
	 * @param nbCartes
	 * @throws TarotException
	 */
	private void construireCouleur(CouleurCarte couleur, int nbCartes) throws TarotException {
		int nbCartesAjoutees = 0;
		int tailleInitiale = cartes.size();

		for(int valeur=1 ; valeur<=nbCartes ; valeur++) {
			cartes.add(new Carte(couleur, valeur, couleur.toString()+valeur));
			nbCartesAjoutees++;
		}

		if(cartes.size() != tailleInitiale + nbCartesAjoutees)
			throw new TarotException("Il y a " + nbCartesAjoutees + " cartes \"" + couleur.toString() + "\" ajoutees et non " + nbCartes);
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
	 * Retire des cartes du jeu et les places dans la main du joueur courant.
	 * @param tour -> a qui donner les cartes
	 * @return TRUE si les cartes ont bien ete distribuees, FAUX sinon (cas ou tout a ete distribue)
	 * @throws TarotException
	 */
	public boolean tirerCartes(int tour) throws TarotException {
		int tailleInitialeMain = mains.get(tour).size();
		int tailleInitialeCartes = cartes.size();
		
		if(cartes.size() > 0) {
			int i;
			for(i=0 ; i<NB_CARTES_A_TIRER ; ++i) {
				mains.get(tour).add(cartes.get(i));
			}
			for(i=0 ; i<NB_CARTES_A_TIRER ; ++i) {
				cartes.remove(0);
			}
			
			if(mains.get(tour).size() != tailleInitialeMain + NB_CARTES_A_TIRER) {
				int nbCartesPrevu = tailleInitialeMain + NB_CARTES_A_TIRER;
				throw new TarotException("Le joueur n°" + tour + " a une main de " + mains.get(tour).size() + " cartes et non " + nbCartesPrevu);
			}
			if(cartes.size() != tailleInitialeCartes - NB_CARTES_A_TIRER) {
				int nbCartesPrevu = cartes.size() - NB_CARTES_A_TIRER;
				throw new TarotException("Le joueur n°" + tour + " a une main de " + cartes.size() + " cartes et non " + nbCartesPrevu);
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
	
	/**
	 * Permet a la Vue de recuperer les cartes dans la main du joueur 1.
	 * @return Une collection de cartes correspondant a la main du joueur 1.
	 */
	public ArrayList<Carte> getMainJoueur1() {
		return mains.get(0);
	}
}