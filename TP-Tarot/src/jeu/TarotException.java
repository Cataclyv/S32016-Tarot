package jeu;

/**
 * Gestion des exceptions propre au tarot : enregistre un message d'erreur quand l'exception est levee, l'affiche quand l'exception est remontee.
 * @author jdespret
 *
 */
public class TarotException extends Exception {
	private String message;
	
	public TarotException(String message) {
		this.message = message;
	}
	
	public void message() {
		System.err.println("ERREUR -> " + message);
	}
}
