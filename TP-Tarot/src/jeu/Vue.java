package jeu;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Observable;
import java.util.Observer;


/**
 * Partie "vue" de l'architecture MVC ; gère l'affichage graphique et les évènements.
 * @author jdespret
 * 
 */
public class Vue implements Observer{
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
	 * Fenetre contenant tous les elements graphiques de la Vue.
	 */
	private JFrame fenetre;
	/**
	 * Bouton controlant la distribution des cartes.
	 */
	private JButton boutonDistribuer;
	
	public Vue() {
		modele = new Modele();
		controleur = new Controleur(modele);
		
		fenetre = new JFrame();
		fenetre.setSize(800, 600);
		fenetre.setTitle("Tarot S3 (Jules Despret, Pablo Gutierrez)");
		fenetre.setVisible(true);
		
		boutonDistribuer = new JButton();
		boutonDistribuer.setLocation(100, 100);
		boutonDistribuer.setSize(TAILLE_BOUTON);
		boutonDistribuer.setText("Distribuer");
		boutonDistribuer.setVisible(true);
		boutonDistribuer.setEnabled(true);
		
		fenetre.add(boutonDistribuer);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
