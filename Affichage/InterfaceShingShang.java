package Affichage;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Damier.Case;
import Damier.Coordonnees;
import Damier.Portail;
import Entitees.Dragon;
import Entitees.Lion;
import Entitees.Singe;
import ShingShang.*;
import enigma.console.*;
import enigma.core.Enigma;
import java.awt.Color;

public class InterfaceShingShang {
	

	private List<TexteColore> texte ;
	private final String textePrompt="prompt>>";
	private Partie partie;
	private static  Console console;
    
	public InterfaceShingShang(Partie partie)
	{
		this.partie = partie;
		texte = new ArrayList<TexteColore>();
	}
	
	public void afficher(){
		this.clearEcran();
		console = Enigma.getConsole("ShingShang");
		this.afficherDamier();
		new TexteColore("\n\n",new TextAttributes(Color.black,Color.WHITE)).afficher(console);
		titrePartieEcran("Instruction");
		for(int i = 0; i<this.texte.size();i++){
			texte.get(i).afficher(console);
		}
	}
	
	public void clearEcran()
	{
		for(int i=0;i<50;i++)
			System.out.print("\n");
	}
	
	public void reset(){
		if(this.texte.size() >0){
			this.texte.clear();
		}
	}
	
	public String saisie(){
		Scanner sc;
		String s;
		sc = new Scanner(System.in);
		s= sc.nextLine();
		sc.close();
		return s;
	}

	
	public void ajouteMessage(TexteColore messageColore){
		texte.add(messageColore);
		this.afficher();
		
	}
	
	public void ajouteMessage(String message){
		texte.add(new TexteColore(message,new TextAttributes(Color.WHITE,Color.BLACK)));
		this.afficher();
		
	}
	
	public void afficherPrompt(){
		ajouteMessage(new TexteColore("\n"+textePrompt,new TextAttributes(Color.MAGENTA,Color.BLACK)));

	}
	
	public void afficherErreur(String messageDErreur){
		ajouteMessage(new TexteColore("\n"+messageDErreur+"\nAppuyer sur entrée pour continuer.",new TextAttributes(Color.YELLOW,Color.BLACK)));
		this.afficherPrompt();
		this.saisie();	
	}
	
	public void titrePartieEcran(String intitule){
		for(int i=0;i<this.partie.getDamier().getDimension();i++)
		{
			new TexteColore(" ",new TextAttributes(Color.black,Color.WHITE)).afficher(console);
		}
						
		new TexteColore(" "+intitule+" ",new TextAttributes(Color.black,Color.WHITE)).afficher(console);
		for(int i=0;i<this.partie.getDamier().getDimension();i++)
		{
			new TexteColore(" ",new TextAttributes(Color.black,Color.WHITE)).afficher(console);
		}
		new TexteColore("  \n",new TextAttributes(Color.black,Color.WHITE)).afficher(console);
	}
	
	public void afficherDamier(){
		
		
		//titre
		titrePartieEcran("Damier");
		//repere abscisse
		if(this.partie.getDamier().getDimension() <=10){
			new TexteColore("    A B C D E F G H I J\n   ",new TextAttributes(Color.orange,Color.BLACK)).afficher(console);
			new TexteColore("+",new TextAttributes(Color.lightGray,Color.BLACK)).afficher(console);
			for(int i=0;i<this.partie.getDamier().getDimension();i++){
				new TexteColore("-+",new TextAttributes(Color.lightGray,Color.BLACK)).afficher(console);
			}new TexteColore("\n",new TextAttributes(Color.blue,Color.BLACK)).afficher(console);
		}
		else
		{
			new TexteColore("    ",new TextAttributes(Color.blue,Color.BLACK)).afficher(console);
			//ligne1
			for(int i=0;i<this.partie.getDamier().getDimension();i++){
				new TexteColore((int)i/10+" ",new TextAttributes(Color.orange,Color.BLACK)).afficher(console);
			}
			new TexteColore("\n    ",new TextAttributes(Color.blue,Color.BLACK)).afficher(console);
			//ligne2
			for(int i=0;i<this.partie.getDamier().getDimension();i++){
				new TexteColore(i%10+" ",new TextAttributes(Color.orange,Color.BLACK)).afficher(console);
			}
			new TexteColore("\n   +",new TextAttributes(Color.lightGray,Color.BLACK)).afficher(console);
			for(int i=0;i<this.partie.getDamier().getDimension();i++){
				if(i==this.partie.getDamier().getDimension()-1)
					new TexteColore("+",new TextAttributes(Color.lightGray,Color.BLACK)).afficher(console);
				else
					new TexteColore("-+",new TextAttributes(Color.lightGray,Color.BLACK)).afficher(console);
			}
			new TexteColore("\n",new TextAttributes(Color.blue,Color.BLACK)).afficher(console);
			
		}
		
		for (int i=0;i<this.partie.getDamier().getDimension();i++){
			//repere ordonnee
			if(i<10){
				new TexteColore(i+"",new TextAttributes(Color.orange,Color.BLACK)).afficher(console);
				new TexteColore("  |",new TextAttributes(Color.lightGray,Color.BLACK)).afficher(console);
			}
			else{
				new TexteColore(i+"",new TextAttributes(Color.orange,Color.BLACK)).afficher(console);
				new TexteColore(" |",new TextAttributes(Color.lightGray,Color.BLACK)).afficher(console);
			}
			//case et pion
			for (int j=0;j<this.partie.getDamier().getDimension();j++){
				if(j==this.partie.getDamier().getDimension())
					new TexteColore(" ",new TextAttributes(Color.blue,Color.BLACK)).afficher(console);
				else if(this.partie.getDamier().getCase(new Coordonnees(j,i)) == null)
					new TexteColore(" ",new TextAttributes(Color.blue,Color.BLACK)).afficher(console);
				else if(this.partie.getDamier().getCase(new Coordonnees(j,i)).getBushi() != null)
				{ 
					if(this.partie.getDamier().getCase(new Coordonnees(j,i)).getBushi().getArmee().getPossesseur().getIdentite().getNumero() == 1)
					{
						if(this.partie.getDamier().getCase(new Coordonnees(j,i)).getBushi() instanceof Dragon)
							new TexteColore("D",new TextAttributes(Color.blue,Color.BLACK)).afficher(console);
						else if(this.partie.getDamier().getCase(new Coordonnees(j,i)).getBushi() instanceof Lion)
							new TexteColore("L",new TextAttributes(Color.blue,Color.BLACK)).afficher(console);
						else if(this.partie.getDamier().getCase(new Coordonnees(j,i)).getBushi() instanceof Singe)
							new TexteColore("S",new TextAttributes(Color.blue,Color.BLACK)).afficher(console);
					}else{
						if(this.partie.getDamier().getCase(new Coordonnees(j,i)).getBushi() instanceof Dragon)
							new TexteColore("D",new TextAttributes(Color.red,Color.BLACK)).afficher(console);
						else if(this.partie.getDamier().getCase(new Coordonnees(j,i)).getBushi() instanceof Lion)
							new TexteColore("L",new TextAttributes(Color.red,Color.BLACK)).afficher(console);
						else if(this.partie.getDamier().getCase(new Coordonnees(j,i)).getBushi() instanceof Singe)
							new TexteColore("S",new TextAttributes(Color.red,Color.BLACK)).afficher(console);
					}
				}
				
				else if(this.partie.getDamier().getCase(new Coordonnees(j,i)) instanceof Portail){
					((Portail) this.partie.getDamier().getCase(new Coordonnees(j,i))).getPossesseur().getIdentite();
					if(((Portail) this.partie.getDamier().getCase(new Coordonnees(j,i))).getPossesseur().getIdentite().getNumero() == 1)
						new TexteColore("P",new TextAttributes(Color.blue,Color.BLACK)).afficher(console);
					else
						new TexteColore("P",new TextAttributes(Color.red,Color.BLACK)).afficher(console);
				}
				else if(this.partie.getDamier().getCase(new Coordonnees(j,i)) instanceof Case)
					new TexteColore("~",new TextAttributes(Color.gray,Color.BLACK)).afficher(console);
				if(j!= this.partie.getDamier().getDimension()-1)
					new TexteColore(" ",new TextAttributes(Color.blue,Color.BLACK)).afficher(console);
			}
			new TexteColore("|\n",new TextAttributes(Color.lightGray,Color.BLACK)).afficher(console);
		}
		new TexteColore("   +",new TextAttributes(Color.lightGray,Color.BLACK)).afficher(console);
		for(int i=0;i<this.partie.getDamier().getDimension();i++){
			new TexteColore("-+",new TextAttributes(Color.lightGray,Color.BLACK)).afficher(console);
		}
	}
	
	

}
