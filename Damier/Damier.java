package Damier;

import Exception.CaseDejaOccuperException;
import Exception.DimensionDamierErroneeException;
import ShingShang.Armee;
import ShingShang.Joueur;

import java.lang.NullPointerException;

import Affichage.InterfaceShingShang;
import Entitees.Dragon;
/**
 * Symbolise le damier contenant des cases pour jouer au Jeu.
 * @author Fabien
 * @version 1.0
 */
public class Damier implements PositionElement{
	private String nom;
	private int dimension;
	private Case[][] cases;
	private InterfaceShingShang console;

	/**
	 *  La construction d'un damier se base sur ses dimensions. 
	 *  Le remplissage est donc modulable. 
	 *  Chaque partie du damier construite est donc dépendant des dimensions de ce dernier. 
	 *  Si les dimensions dépass
	 */

	/**
	 * Construit un damier d'une partie de ShingShang.
	 * Le damier s'adapte à la dimension (max 100)
	 * 
	 * @param dimension
	 * @param nom
	 */
	public Damier(int dimension, String nom, InterfaceShingShang console) {
		try {
			if(dimension < 0 || dimension > 100)
				throw new DimensionDamierErroneeException();
			this.dimension = dimension;
		}
		catch(DimensionDamierErroneeException e){
			if (this.dimension < 0)
				this.dimension = -dimension;
			if(this.dimension > 100)
				this.dimension = 100;
		}
		finally{
			this.console = console;
			this.nom = nom;
			this.cases = new Case[dimension][dimension];
			
			for(int i=0;i<dimension;i++){
				for(int j=0;j<dimension;j++){
						if(estPositionPortail(new Coordonnees(j,i)))
							cases[i][j] = new Portail(new Coordonnees(j,i),null);
						if(estPositionCase (new Coordonnees(j,i)))
							cases[i][j] = new Case(new Coordonnees(j,i));
				}
			}
		}
	}
	
	public void attribuerPortail(Joueur joueur){
		for(int i=0;i<dimension;i++){
			for(int j=0;j<dimension;j++){
				if(joueur.getIdentite().getNumero() == 1 && estPositionPortail(new Coordonnees(j,i)) && i==1)
						((Portail)cases[i][j]).setPossesseur(joueur);
				else if(joueur.getIdentite().getNumero() %2 == 0 && estPositionPortail(new Coordonnees(j,i)) && i==this.dimension-2)
					((Portail)cases[i][j]).setPossesseur(joueur);
			}
		}
	}
	
	
	/**
	 * Renvoie true si les coordonnees données sont celle d'un portail.
	 * Le nombre de portail dépend des dimensions du damier.
	 * @see PositionElement
	 * @param coordonnees
	 * @return boolean
	 */
	public boolean estPositionPortail(Coordonnees coordonnees){
		if(this.dimension %2 == 0)
			return (coordonnees.getOrdonnee()==this.dimension-2 || coordonnees.getOrdonnee() == 1) && coordonnees.getAbscisse() <= (int) ((this.dimension)/2) && coordonnees.getAbscisse() >= (int)((this.dimension)/2 -1);
		else
			return (coordonnees.getOrdonnee() == 1 || coordonnees.getOrdonnee() == this.dimension-2)&&(coordonnees.getAbscisse() == (int) ((this.dimension-1)/2 +1) || coordonnees.getAbscisse() == (int)((this.dimension-1)/2-1));
	}
	/**
	 * Renvoie true si les coordonnees données sont celle d'une case hors-damier (du fait de la forme du damier), 
	 * en fonction des dimension du damier.
	 * @param coordonnees
	 * @return boolean
	 */
	public boolean estHorsDamier(Coordonnees coordonnees){
		if (this.dimension %2 == 0)
		{
			return (coordonnees.getAbscisse() == 0 || coordonnees.getAbscisse() == dimension-1) && (coordonnees.getOrdonnee() <(int)((this.dimension)/2)-1 || coordonnees.getOrdonnee() > (int)((this.dimension)/2));
		}
		else
		{
			return (coordonnees.getAbscisse() == 0 || coordonnees.getAbscisse() == dimension-1) && (coordonnees.getOrdonnee() < (int)((this.dimension)/2)-1 || coordonnees.getOrdonnee() > (int)((this.dimension)/2 +1));
		}
	}
	
	/**
	 * Renvoie true si les coordonnees données sont celle d'un portail, 
	 * en fonction des dimension du damier.
	 * @param coordonnees
	 * @return boolean
	 */
	public boolean estPositionCase(Coordonnees coordonnees){
		
		if(estPositionPortail(coordonnees))
			return false;
		else if(estHorsDamier(coordonnees))
			return false;
		else
			return true;
	}
	
	
	/**
	 * Place tous les pions de l'armée d'un joueur.
	 * Si le nombre de joueur est supérieur à 3, les joueurs sont répartie 
	 * entre numéro impair et pair, et controleront la même armée
	 * @param j
	 * @see Armee chocolat
	 */
	public void placerArmee(Joueur joueur){
		if (joueur.getIdentite().getNumero() %2 == 0)
		{
			try{
				this.cases[this.dimension-1][1].remplir(joueur.getArmee().getBushi(Dragon.class,0));
				this.cases[this.dimension-1][this.dimension-2].remplir(joueur.getArmee().getBushi(Dragon.class,1));
				
				int compteurNombrePion=2,k=2;
				
				while(compteurNombrePion < joueur.getArmee().getTaille()){
					for(int i=0;i<this.dimension;i++){
						for (int j=0;j<dimension;j++){
							if((this.dimension-1-j+i) == (this.dimension-1)*2-k && this.cases[i][j] != null)
							{
								if(this.cases[i][j].estVide() && compteurNombrePion < joueur.getArmee().getTaille())
								{	
									this.cases[i][j].remplir(joueur.getArmee().getBushi(compteurNombrePion));
									compteurNombrePion++;
								}
							}
							
							if((i+j) == (this.dimension-1)*2-k && this.cases[i][j] != null)
							{
								if(this.cases[i][j].estVide() && compteurNombrePion < joueur.getArmee().getTaille())
								{	
									this.cases[i][j].remplir(joueur.getArmee().getBushi(compteurNombrePion));
									compteurNombrePion++;
								}
							}
						}
					}
					k++;
				}
			}
			catch(CaseDejaOccuperException | NullPointerException  e){
				console.afficherErreur("Erreur au placement de l'armee"+e.getMessage());
			}
		}else{
			try{
				this.cases[0][1].remplir(joueur.getArmee().getBushi(Dragon.class,0));
				this.cases[0][this.dimension-2].remplir(joueur.getArmee().getBushi(Dragon.class,1));
				
				int compteurNombrePion=2,k=2;
				
				while(compteurNombrePion < joueur.getArmee().getTaille()){
					for (int i=0;i<this.dimension;i++){
						for (int j=0;j<dimension;j++){
							if((this.dimension-1-j+i) == k && this.cases[i][j] != null)
							{
								if(this.cases[i][j].estVide() && compteurNombrePion < joueur.getArmee().getTaille())
								{	
									this.cases[i][j].remplir(joueur.getArmee().getBushi(compteurNombrePion));
									compteurNombrePion++;
								}
							}
							
							if((i+j) == k && this.cases[i][j] != null)
							{
								if(this.cases[i][j].estVide() && compteurNombrePion < joueur.getArmee().getTaille())
								{	
									this.cases[i][j].remplir(joueur.getArmee().getBushi(compteurNombrePion));
									compteurNombrePion++;
								}
							}
						}
					}
					k++;
				}
			}
			catch(CaseDejaOccuperException | NullPointerException  e){
				console.afficherErreur("Erreur au placement de l'armee"+e.getMessage());
			}
		}
	}
	
	/**
	 * Retourne les coordonnees d'une case
	 * @see Coordonnees
	 * @param c
	 * @return
	 */
	public Case getCase(Coordonnees c){
		return cases[c.getOrdonnee()][c.getAbscisse()];
	}
	
	/**
	 * Retourne le nom du damier.
	 * @return
	 */
	public String getNom() {
		return nom;
	}

	/**this.dimension-1
	 * Retourne les dimensions du damier. 
	 * @return
	 */
	public int getDimension() {
		return dimension;
	}
	
	
	/**
	 * Retourne le nombre de Portail d'un joueur occupee par un ennemie 
	 * @param j
	 * @return int
	 */
	public int portailsOccuppee(Joueur joueur){
		int compteurPortailOccuppee=0;
		for(int i=0;i<this.getDimension();i++){
			for(int j=0;j<this.getDimension();j++){
				if(this.getCase(new Coordonnees(j,i)) instanceof Portail)
				{
					Portail portail = (Portail) this.getCase(new Coordonnees(j,i));
					if(!portail.estOccupee())
						compteurPortailOccuppee++;
							
				}
			}
		}
		return compteurPortailOccuppee;
	}
	
	/**
	 * Retourne si tous les portails de ce joueur sont occupée par un ennemie;
	 * @param joueur
	 * @return boolean
	 */
	public boolean portailsTousOccuppee(Joueur joueur){
		return this.portailsOccuppee(joueur) == 2;
	}
	
/* ANCIEN DAMIER	
	public String toString(){
		String s="";
		
		
		//repere abscisse
		if(this.dimension <=10){
			s+="    A B C D E F G H I J\n   +";
			for(int i=0;i<this.dimension;i++){
				s+="-+";
			}s+="\n";
			}
		else
		{
			s+="    ";
			//ligne1
			for(int i=0;i<this.dimension;i++){
				s+=(int)i/10+" ";
			}
			s+="\n    ";
			//ligne2
			for(int i=0;i<this.dimension;i++){
				s+=i%10+" ";
			}
			s+="\n   +";
			for(int i=0;i<this.dimension;i++){
				if(i==this.dimension-1)
					s+="+";
				else
					s+="-+";
			}
			s+="\n";
			
		}
		
		for (int i=0;i<this.dimension;i++){
			//repere ordonnee
			if(i<10)
				s+=i+"  |";
			else
				s+=i+" |";
			//case
			for (int j=0;j<this.dimension;j++){
				if(j==this.dimension)
					s+=" ";
				else if(cases[i][j] == null)
					s+=" ";
				else if(cases[i][j].getBushi() instanceof Dragon)
					s+="D";
				else if(this.cases[i][j].getBushi() instanceof Lion)
					s+="L";
				else if(this.cases[i][j].getBushi() instanceof Singe)
					s+="S";
				else if(cases[i][j] instanceof Portail)
					s+="P";
				else if(cases[i][j] instanceof Case)
					s+="~";
				if(j!= this.dimension-1)
					s+=" ";
			}
			s+="|\n";
		}
		s+="   +";
		for(int i=0;i<this.dimension;i++){
			s+="-+";
		}
		return s;
	}*/
}