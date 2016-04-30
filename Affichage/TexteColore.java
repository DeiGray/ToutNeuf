package Affichage;

import enigma.console.*;
import java.awt.Color;

public class TexteColore {

	private String texte;
	private TextAttributes couleurs;
	public TexteColore(String texte, TextAttributes couleurs) {
		this.texte = texte;
		this.couleurs = couleurs;
	}
	
	public void afficher(Console console){
		 console.setTextAttributes(couleurs);
		 System.out.print(texte);
		 console.setTextAttributes(new TextAttributes(Color.WHITE,Color.BLACK));
	}


}
