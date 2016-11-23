package jeu;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;


/**
 * Partie "vue" de l'architecture MVC ; gère l'affichage graphique et les évènements.
 * @author jdespret
 * 
 */
public class Vue implements Observer{
	/**
	 * Taille de la fenetre graphique a afficher.
	 */
	private final Dimension TAILLE_FENETRE = new Dimension(800,600);
	/**
	 * Position de la fenetre graphique sur le bureau.
	 */
	private final Point POSITION_FENETRE = new Point(600,200);
	/**
	 * Dimension des boutons de la fenetre.
	 */
	private final Dimension TAILLE_BOUTON = new Dimension(60,60);
	/**
	 * L'unique Modele de l'architecture.
	 */
	private Modele modele;
	/**
	 * L'unique Controleur de l'architecture.
	 */
	private Controleur controleur;
	/**
	 * Ensemble de boutons correspondant aux cartes affichees.
	 */
	private ArrayList<CarteGraphique> cartesAffichees;
	/**
	 * Fenetre contenant tous les elements graphiques de la Vue.
	 */
	private JFrame fenetre;
	/**
	 * "Panneau" Java comprenant les éléments graphiques.
	 */
	private JPanel panneau;
	/**
	 * Bouton controlant la distribution des cartes. Se desactive automatiquement a la fin de la distribution.
	 */
	private JButton boutonDistribuer;
	/**
	 * Texte affichant "Main du joueur" dans la Vue.
	 */
	private JLabel labelMain;
	
	public Vue() {
		modele = new Modele();
		modele.addObserver(this);
		controleur = new Controleur(modele);
		
		construireFenetre();
		construirePanneau();
		
		construireBoutons();
		construireLabels();
	}
	
	/**
	 * Construit la fenetre graphique.
	 */
	private void construireFenetre() {
		fenetre = new JFrame();
		fenetre.setSize(TAILLE_FENETRE);
		fenetre.setLocation(POSITION_FENETRE);
		fenetre.setTitle("Tarot S3 (Jules Despret, Pablo Gutierrez)");
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fenetre.setVisible(true);
	}
	
	/**
	 * Construit le panneau Swing qui contiendra l'ensemble des elements de la fenetre graphique.
	 */
	private void construirePanneau() {
		panneau = new JPanel();
		panneau.setBounds(0, 0, fenetre.getWidth(), fenetre.getHeight());
		panneau.setBackground(Color.GREEN);
		panneau.setVisible(true);
		
		fenetre.add(panneau);
	}
	
	/**
	 * Construit l'ensemble des boutons de la fenetre graphique.
	 */
	private void construireBoutons() {
		construireBoutonDistribuer();
	}
	
	/**
	 * Construit le bouton associe a la distribution des cartes.
	 */
	private void construireBoutonDistribuer() {
		boutonDistribuer = new JButton("Distribuer");
		boutonDistribuer.setLocation(10, 10);
		boutonDistribuer.setCursor(new Cursor(Cursor.HAND_CURSOR));
		boutonDistribuer.setSize(TAILLE_BOUTON);
		boutonDistribuer.setVisible(true);
		boutonDistribuer.setEnabled(true);
		
		boutonDistribuer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				if(evt.getSource() == boutonDistribuer) {
					if(!controleur.distribuerCartes()) {
						boutonDistribuer.setEnabled(false); // Si on ne peut plus distribuer, on desactive le bouton
					}
				}
			}
		});
		
		panneau.add(boutonDistribuer, BorderLayout.WEST);
	}
	
	/**
	 * Construit les textes fixes de la fenetre graphique.
	 */
	private void construireLabels() {
		construireLabelMain();
	}
	
	/**
	 * Construit le texte correspondant au label "Main du joueur".
	 */
	private void construireLabelMain() {
		labelMain = new JLabel();
		labelMain.setLocation(100, 50);
		labelMain.setText("Main du joueur :");
		labelMain.setVisible(true);
		labelMain.setEnabled(true);
		
		panneau.add(labelMain, BorderLayout.WEST);
	}

	@Override
	public void update(Observable o, Object arg) {
		cartesAffichees.clear();
		for(Carte c : modele.getMainJoueur1())
			cartesAffichees.add(new CarteGraphique(c));
		
		panneau.repaint();
		fenetre.repaint();
	}

}

