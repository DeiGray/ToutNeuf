package ShingShang;
import java.util.Scanner;

import Affichage.InterfaceShingShang;
import Damier.Damier;
import Exception.MauvaiseSaisieException;

/**
 * Créer une partie de shingSgang classique
 * @author fabou
 * @version 1.0
 */
public class Classique  extends Partie{

	private Joueur joueur1;
	private Joueur joueur2;
	private Joueur joueurQuiCommence;
	private boolean quitter;
	private InterfaceShingShang console;
	
	//private Statistique stats;
	
	/**
	 * Instancie joueurs, plateau de jeu et Bushis d'une partie classique.
	 * Le joueurQuiCommence est juste le joueur qui a démarrer la partie.
	 */
	public Classique() {
		this.console = new InterfaceShingShang(this);
		this.quitter = false;
		
		plateauDeJeu = new Damier(10,"Partie Classique",console);
		this.joueur1 = new Joueur(new Identite("Joueur1",1));
		this.joueur2 = new Joueur(new Identite("Joueur2",2));
		this.joueur1.setArmee(new Armee(this.joueur1,this.console));
		this.joueur2.setArmee(new Armee(this.joueur2,this.console));
		
		plateauDeJeu.attribuerPortail(this.joueur1);
		plateauDeJeu.attribuerPortail(this.joueur2);

		plateauDeJeu.placerArmee(this.joueur1);
		plateauDeJeu.placerArmee(this.joueur2);
		
		this.joueurQuiCommence = this.demanderQuiCommence();
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @param joueurQuiCommence the joueurQuiCommence to set
	 */
	public void setJoueurQuiCommence(Joueur joueurQuiCommence) {
		this.joueurQuiCommence = joueurQuiCommence;
	}


	public void demarrer(){
		String s="";
		if(this.joueurQuiCommence == joueur1)
		{
			while(!this.estFinie()){
				if(!this.estFinie())	
					s=unTour(joueur1);
				if(!this.estFinie())
					s=unTour(joueur2);
			}
			this.resultat(s);
		}
		else
		{
			while(!this.estFinie()){
				if(!this.estFinie())
					s=unTour(this.joueur2);
				if(!this.estFinie())	
					s=unTour(this.joueur1);
			}
			this.resultat(s);
		}
	}


	/**
	 * @return the joueurQuiCommence
	 */
	public Joueur getJoueurQuiCommence() {
		return joueurQuiCommence;
	}

/**
 * Represente l'ensemble des choix que peux faire un joueur lors de son tours de jeu.
 * @param j
 * @return
 */
	public String unTour(Joueur j){
		String s="";
		 switch (choixAction(j, this.console)){
		 case 1:
			 j.deplacerUnBushi(j,plateauDeJeu,console);
			 break;
		 case 2:
			 s+=j.passer(j);
			 break;
		 case 3:
			 s+=j.abandonner(j);
			 break;
		 case 4:
		//	 sauverContinuer();
			 break;
		 case 5:
		//	 sauverQuitter();
			 break;
		 case 6:
			 s+=j.quitterSansSauver(j,this.quitter);
			 break;
		 default:
			 s+="erreur choixAction";
			 break;
		 }
		 return s;
	}
	
	/**
	 * Controle du choix de l'action d'un joueur lors de son tour de jeux.
	 * @param j
	 * @return
	 */
	
	public static int choixAction(Joueur j,InterfaceShingShang console){
		 try{
			 console.reset();
			 console.ajouteMessage("Que voulez-vous faire ?\n1. Deplacer Bushi          4. Sauver et Continuer\n2. Passer le tour          5. Sauver et Quitter\n3. Abandonner          6. Quitter sans Sauver\n"+"Saisir le numéro d'action voulue. (entre 1 et 6)");
			 Scanner sc = new Scanner(System.in);
			 String saisie;
			 console.afficherPrompt();
			 saisie = console.saisie();;
			 if(saisie.length() != 1 || (saisie.charAt(0) != '1' && saisie.charAt(0)!= '2' && saisie.charAt(0) != '3' 
				 && saisie.charAt(0)!= '4' && saisie.charAt(0) != '5' && saisie.charAt(0)!= '6'))
				 throw new MauvaiseSaisieException("Vous devez un chiffre entre 1 et 6.");
			 return (int)saisie.charAt(0)-'0';
		 }
		 catch(MauvaiseSaisieException e){
			 String saisie;
			 Scanner sc = new Scanner(System.in);
			 do{
				 console.afficherErreur(e.getMessage());
				 console.ajouteMessage("Que voulez-vous faire ?\n1. Deplacer Bushi          4. Sauver et Continuer\n2. Passer le tour          5. Sauver et Quitter\n3. Abandonner          6. Quitter sans Sauver\n\nSaisir le numéro d'action voulue. (entre 1 et 6)");
				 console.afficherPrompt();
				 saisie = console.saisie();;
			 }while(saisie.length() != 1 || (saisie.charAt(0) != '1' && saisie.charAt(0)!= '2' && saisie.charAt(0) != '3' 
					 && saisie.charAt(0)!= '4' && saisie.charAt(0) != '5' && saisie.charAt(0)!= '6'));
			 return (int)saisie.charAt(0)-'0';
		 }
	}
	
	
	/**
	 * Retourne true si une partie a réunie les conditions pour s'arrêter, sinon false.
	 */
	public boolean estFinie(){
		if (this.quitter)
			return true;
		if(joueur1.estAbandon())
			return true;
		if(this.joueur1.getArmee().aucunDragon() || this.joueur2.getArmee().aucunDragon())
			return true;
		if(plateauDeJeu.portailsTousOccuppee(this.joueur1) || plateauDeJeu.portailsTousOccuppee(this.joueur2))
			return true;
		return false;
	}//ajouter d'autre critere pour abandon

	/**
	 * Retourne la phrase de fin annonçant quelle joueur a gagné. 
	 * @return
	 */
	public String gagnant(){
		if(this.joueur1.getArmee().aucunDragon() || plateauDeJeu.portailsTousOccuppee(this.joueur1))
			return joueur2.getIdentite().toString()+" a gagné !\n";
		if(this.joueur2.getArmee().aucunDragon() || plateauDeJeu.portailsTousOccuppee(this.joueur2))
			return joueur1.getIdentite().toString()+" a gagné !\n";
		return "Erreur trouver gagnant.\n";
		
	}
	
	/**
	 * Affiche/Retourne les resultats d'une partie suite à sa fin.
	 * @param s
	 * @return
	 */
	 public String resultat(String s){
		 
		 s+="Fin de partie.";
		//s+=stats.toString()+'\n';
		 this.console.reset();
		 console.ajouteMessage(s);
		 return s;
		 
	 }

	 /**
	  * Demande au joueur de saisir lequels des deux joueurs commence, ou si ils veulent un choix aléatoire.
	  * @return
	  */
	 public Joueur demanderQuiCommence(){
		 Scanner sc = new Scanner(System.in);
		 String saisie;
		 try{
			 this.console.reset();
			 this.console.ajouteMessage("Qui commence ?\n1. Joueur 1\n2. Joueur2\n3. Aleatoire\nSaisir le num�ro d'action voulue. (1,2 ou 3)");
			 this.console.afficherPrompt();
			 saisie = console.saisie();
			 
			 if(saisie.length() != 1 || (saisie.charAt(0) != '1' && saisie.charAt(0) !='2' && saisie.charAt(0) != '3'))
				 throw new MauvaiseSaisieException("Vous devez saisir 1,2 ou 3");
		 }
		 catch(MauvaiseSaisieException e){
			 do{
				 this.console.reset();
				 this.console.afficherErreur(e.getMessage());
				 this.console.ajouteMessage("Qui commence ?\n1. Joueur 1\n2. Joueur2\n3. Aleatoire\nSaisir le num�ro d'action voulue. (1,2 ou 3)");
				 this.console.afficherPrompt();
				 saisie = console.saisie();;
			 }while(saisie.length() != 1 || (saisie.charAt(0) != '1' && saisie.charAt(0) !='2' && saisie.charAt(0) != '3'));
		 }

			 if(saisie.charAt(0) == '1')
				 return this.joueur1;
			 if(saisie.charAt(0) == '2')
				 return this.joueur2;
			 else
			 { 
				 double n;
				 n=(Math.random()*2-1);
				 if((int) n == 1)
					 return joueur1;
				 else
					 return joueur2;
			 }
	 }
			 	
	
	/**
	 * @return the plateauDeJeu
	 */
	public Damier getPlateauDeJeu() {
		return plateauDeJeu;
	}

	/**
	 * @param plateauDeJeu the plateauDeJeu to set
	 */
	public void setPlateauDeJeu(Damier plateauDeJeu) {
		plateauDeJeu = plateauDeJeu;
	}

	/**
	 * @return the joueur1
	 */
	public Joueur getJoueur1() {
		return joueur1;
	}

	/**
	 * @param joueur1 the joueur1 to set
	 */
	public void setJoueur1(Joueur joueur1) {
		this.joueur1 = joueur1;
	}

	/**
	 * @return the joueur2
	 */
	public Joueur getJoueur2() {
		return joueur2;
	}

	/**
	 * @param joueur2 the joueur2 to set
	 */
	public void setJoueur2(Joueur joueur2) {
		this.joueur2 = joueur2;
	}


	@Override
	public Damier getDamier() {
		return this.plateauDeJeu;
		// TODO Auto-generated method stub
	}
}
