/**
 * 
 */
package ShingShang;

import java.util.Scanner;

import Affichage.InterfaceShingShang;
import Damier.Case;
import Damier.Coordonnees;
import Damier.Damier;
import Deplacement.MouvementImpl;
import Entitees.Bushi;

import java.io.*;

import Exception.CaseDejaOccuperException;
import Exception.MauvaiseSaisieException;
import Exception.SelectionneCaseVideException;

import java.lang.NullPointerException;

/**
 * @author Fabien
 *
 */
public abstract class ActionTour{
	
	/**
	 * permet au joueur de deplacer ses pions.
	 * 
	 */
	public void deplacerUnBushi(Joueur j,Damier d,InterfaceShingShang console){
		
		Bushi dejaJouer; //correspond au bushi qui a ete utiliser pour effectuer le dernier saut ennemie.
		MouvementImpl deplacementIndeterminee;
		MouvementImpl deplacementDeterminee;
		Case selectionnee= new Case(new Coordonnees(0,0));
		Case arrivee = new Case(new Coordonnees(0,0));
		
		try{
			this.selectionCaseDeplacement(selectionnee,arrivee,d,console);
		}
		catch(SelectionneCaseVideException e){
			console.afficherErreur(e.getMessage()+"\n"+e.getCase().toString()+"\nRedemaragge de la selection");
			deplacerUnBushi(j,d,console);
		}
		catch(CaseDejaOccuperException e){
			console.afficherErreur(e.getMessage()+"\nRedemaragge de la selection");
			deplacerUnBushi(j,d,console);
		}
		deplacementIndeterminee = new MouvementImpl(selectionnee,arrivee,d);
		deplacementDeterminee = deplacementIndeterminee.estDeType();

	}
	
	/**
	 * Saisie des case pour le deplacement du joueur
	 * @param selectionnee
	 * @param arrivee
	 */
	public void selectionCaseDeplacement(Case selectionnee,Case arrivee,Damier d,InterfaceShingShang console)
			throws SelectionneCaseVideException,CaseDejaOccuperException{
		
		String selectionneeString = saisieCaseDeplacement(d,console);
		String arriveeString = saisieCaseDeplacement(d,console);
		
		
		int cpt=0;
		for(int i=0 ;d.getDimension()>=Math.pow(10,i);i++)
			cpt=i;
		
		if(d.getDimension() <= 10){
			selectionnee = d.getCase(new Coordonnees((int)selectionneeString.charAt(0)-'A',(int)selectionneeString.charAt(1)));
			arrivee = d.getCase(new Coordonnees((int)arriveeString.charAt(0)-'A',(int)arriveeString.charAt(1)));
		}
		else
		{
			selectionnee = d.getCase(new Coordonnees((int)selectionneeString.charAt(0),(int)selectionneeString.charAt(1)));
			arrivee = d.getCase(new Coordonnees((int)arriveeString.charAt(0),(int)arriveeString.charAt(1)));
		}
		
		if(selectionnee == null)
			throw new SelectionneCaseVideException(selectionnee);
		if(arrivee == null)
			throw new SelectionneCaseVideException(arrivee);
		if(arrivee.estVide())
			throw new CaseDejaOccuperException();
	}
	
	public String saisieCaseDeplacement(Damier d,InterfaceShingShang console){ 
		String saisie="";
		try{
			 console.clearEcran();
			 console.ajouteMessage("Saisir les coordonnees du pion (ex: \"B0\")");
			 console.afficherPrompt();
			 saisie = console.saisie();;

			 if(!estSaisieCoordonneeValide(saisie,d))
				 throw new MauvaiseSaisieException("Coordonnees saisie inexistante.");
		 }
		 catch(MauvaiseSaisieException e){
			 Scanner sc2 = new Scanner(System.in);
			 do{
				 console.afficherErreur(e.getMessage());
				 saisie = sc2.nextLine();
			 }while(!estSaisieCoordonneeValide(saisie,d));
			 sc2.close();
		 }
		return saisie;
	}//a adapter au dimension du damier

	public static boolean estSaisieCoordonneeValide(String s,Damier d){
		s=s.toUpperCase();
		int cpt=0;
		for(int i=0 ;d.getDimension()>=Math.pow(10,i);i++)
			cpt=i;
		System.out.println("cpt= "+cpt);
		
		if(d.getDimension() <= 10)
			return s.length() == 2 && s.charAt(0) >= 'A' && s.charAt(0) <= 'J' && s.charAt(1) >= '0' && s.charAt(1) <= '9';
		else
		{
			if(s.length() != cpt+1)
				return false;
			for(int i=0;i<s.length();i++){
				if(s.charAt(i) <= '0' && s.charAt(i) >= '9')
					return false;
			}
			return true;
		}
	}//a adapter au dimension du damier
	
	
	/**
	 * Permet au joueur de passer son tour de jeux
	 * @param j
	 * @return
	 */
	public String passer(Joueur j){
		return j.getIdentite().toString()+" passe son tour";
	}
	
	/**
	 * Permet au joueur d'abandonner la partie.
	 * @param j
	 * @return
	 */
	public String abandonner(Joueur j){
		j.setAbandon(true);
		return j.getIdentite().toString()+" a abandonner";
	}
	
	/**
	 * Permet d'arreter une partie en cours sans la sauvegarder.
	 * @param j
	 * @return
	 */
	public String quitterSansSauver(Joueur j,boolean quitter){
		quitter=true;
		return j.getIdentite().toString()+"a quitter la partie sans sauvegarder.\n";
	}
	
}
