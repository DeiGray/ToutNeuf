package Affichage;

import java.awt.Color;
import java.util.ArrayList;

import java.util.List;

import ShingShang.*;
import enigma.console.*;
import enigma.core.Enigma;

public class Console {

	private String damier;
	private List<TexteColore> texte ;
	private String messageDeSaisie;
	private PartieImpl partie;
	private static  enigma.console.Console console;
    
	public Console(PartieImpl partie) {
		texte = new ArrayList<TexteColore>();
	}
	
	public void afficher(){
		for(int i = 0; i<this.texte.size();i++){
			texte.get(i).afficher(console);
		}
	}
	
	public void addPartie(PartieImpl partie){
		this.partie = partie;
	}
	

	
	public void ajouteMessage(TexteColore message){
		texte.add(message);
	}
	
	public void afficherPrompt(){
		
	}

}
