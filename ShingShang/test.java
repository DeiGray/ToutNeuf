package ShingShang;

import java.util.LinkedList;

import Affichage.*;
import Damier.Coordonnees;
import Entitees.Dragon;

import java.awt.Color;


import Exception.CaseDejaOccuperException;
import Exception.EnleverBushiCaseException;
import enigma.console.TextAttributes;

public class test {


	/**
	 * @param args
	 * @throws CaseDejaOccuperException 
	 */
	public static void main(String[] args) throws CaseDejaOccuperException {
		Classique partie = new Classique();
		InterfaceShingShang console = new InterfaceShingShang(partie);
		/*
		Joueur joueur1 = new Joueur(new Identite("Chocolat",1));
		Joueur joueur2 = new Joueur(new Identite("Caramel",2));
		Armee armee1 = new Armee(joueur1,console);
		joueur1.setArmee(armee1);
		Armee armee2 = new Armee(joueur2,console);
		joueur2.setArmee(armee2);
	
		System.out.println("Test Coordonnees");
		Case c1 = new Case(new Coordonnees (1,1));
		Case c2 = new Case(new Coordonnees (0,2));

		
		System.out.println("Test Case");
		try{
			c1.remplir(new Singe(armee,new Identite("ch",1)));
		}
		catch(CaseDejaOccuperException e){
			System.out.println("Case occupee!");
		}
	
		
		//console.saisie();
		

		console.ajouteMessage(new TexteColore("chocolat", new TextAttributes(Color.BLUE,Color.BLACK)));
		console.ajouteMessage(new TexteColore("chocolat2", new TextAttributes(Color.BLUE,Color.BLACK)));
		console.afficher();
		console.afficher();
		console.afficherErreur("ERREUR");*/
		
		try {
			partie.getJoueur1().getArmee().supprimeBushi(partie.getDamier().getCase(new Coordonnees(1,0)).enleverBushi());
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EnleverBushiCaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		console.ajouteMessage("test suppression\n");
		console.ajouteMessage("testPion encore qqpart :\ndans le plateau ="+partie.getDamier().getCase(new Coordonnees(1, 0))+"\ndans l'armee = "+partie.getJoueur1().getArmee().getTaille());
		console.afficher();
		
		

	
		
		

	}

}
