package Deplacement;

import java.util.LinkedList;

import Damier.Case;
import Damier.Coordonnees;
import Damier.Damier;
import Entitees.Bushi;
import Exception.CaseDejaOccuperException;
import Exception.EnleverBushiCaseException;

/**
 * 
 * @author CrazySpark
 * @version 1.0
 * @see Case, Damier
 */
public  class MouvementImpl implements Mouvement{
	
	protected Case selectionnee;
	protected Case arrivee;
	protected Damier d;

	public MouvementImpl(Case selectionnee, Case arrivee, Damier d) {
		this.selectionnee = selectionnee;
		this.arrivee = arrivee;
		this.d = d;
	}

	/**
	 * 
	 * @param selectionnee
	 * @param arrivee
	 * @return
	 */
	public double determinerLongueur(){
		return Math.sqrt(Math.abs(Math.pow((this.selectionnee.getCoordonnees().getOrdonnee() - this.arrivee.getCoordonnees().getOrdonnee()),2) + Math.pow(this.selectionnee.getCoordonnees().getAbscisse() - this.arrivee.getCoordonnees().getAbscisse(),2)));
	}
	
	/**
	 * Retourne si le deplacement est autoris� par les r�gles
	 * @return
	 */
	public boolean estAutorisee(){
		MouvementImpl deplacement = this.estDeType();
		
		if(this.estDirectionPossible()){
			if(deplacement instanceof Saut){
				return ((Saut)deplacement).estLongueurPossible();
			}
			else if(deplacement instanceof Glissement)
			{
				return ((Glissement)deplacement).estLongueurPossible();
			}
		}
		return false;
	}
	
	/**
	 * Donne le type enfant auquelle le deplacement correspond en fonction de selectionnee et arrivee
	 * @return
	 */
	public MouvementImpl estDeType(){
		LinkedList<Bushi> listDeBushi = this.bushiSautees();
		Bushi bushiSaute;
		if(this.selectionnee == null)
			return null;
		if(this.selectionnee.getBushi() == null)
			return null;
		if(listDeBushi == null)
			return new Glissement(this.selectionnee,this.arrivee,this.d);
		if(listDeBushi.size() == 0)
			return new Glissement(this.selectionnee,this.arrivee,this.d);
		for (int i= 0;i<listDeBushi.size();i++){
			bushiSaute = (Bushi) listDeBushi.get(i);
			if(this.estBushiEnnemie(bushiSaute))
				return new SautEnnemie(this.selectionnee,this.arrivee,d);
		}
		return new SautAmie(this.selectionnee,this.arrivee,this.d);
	}
	
	public boolean estBushiEnnemie(Bushi b){
		return !b.getArmee().getPossesseur().getIdentite().equals(selectionnee.getBushi().getArmee().getPossesseur().getIdentite());
	}
	
	/**
	 * Retourne true si la direction de la case "selectionnee" � la case "arrivee" est effectuable par un bushi.
	 * @param selectionnee
	 * @param arrivee
	 * @return
	 */
	public boolean estDirectionPossible(){
		//test l'angle polaire entre selectionne est arrivee. Si correspond � un multiple de 45, l'angle est valide
		for(int i=1;i<=8;i++){
			if (i*45 == Math.abs(getDirection(this.selectionnee,this.arrivee)))
				return true;
		}
		return false;
	}
	
	/**
	 * Retourne la direction formee par les case selectionnee et arrivee (en degree).
	 * @param selectionnee
	 * @param arrivee
	 * @return
	 */
	public static double getDirection(Case selectionnee,Case arrivee){
		return Math.toDegrees((Math.atan2(arrivee.getCoordonnees().getOrdonnee() - selectionnee.getCoordonnees().getOrdonnee(),arrivee.getCoordonnees().getAbscisse() - selectionnee.getCoordonnees().getAbscisse())));
	}
	
	/**
	 * Determine true le bushi dans la case "selectionnee" peut se deplacer jusque la case "arrivee"
	 * @param selectionnee
	 * @param arrivee
	 * @return
	 */
	public boolean estLongueurPossible(){
		return  false;
	}
	
	
	/**
	 * Retourne une liste contenant l'ensemble des bushis sautee entre la case selectionnee et la case arrivee.
	 * @see LinkedList
	 * @param d
	 * @param selectionnee
	 * @param arrivee
	 * @return
	 */
	public LinkedList<Bushi> bushiSautees(){
		LinkedList<Bushi> bushiSautees = new LinkedList<Bushi>();
		int i = selectionnee.getCoordonnees().getOrdonnee();
		int j = selectionnee.getCoordonnees().getAbscisse();
		
		while ( i != arrivee.getCoordonnees().getOrdonnee()){
			while(j != arrivee.getCoordonnees().getAbscisse()){
				if(new MouvementImpl(selectionnee,d.getCase(new Coordonnees(j,i)),d).estDirectionPossible()  && getDirection(selectionnee ,d.getCase(new Coordonnees(j,i))) == getDirection(selectionnee,arrivee))
					bushiSautees.add(d.getCase(new Coordonnees(j,i)).getBushi());
				if(selectionnee.getCoordonnees().getAbscisse() < arrivee.getCoordonnees().getAbscisse())
					j++;
				else
					j--;
			}
			if(new MouvementImpl(selectionnee,d.getCase(new Coordonnees(j,i)),d).estDirectionPossible() && getDirection(selectionnee ,d.getCase(new Coordonnees(j,i))) == getDirection(selectionnee,arrivee))
				bushiSautees.add(d.getCase(new Coordonnees(j,i)).getBushi());
			if(selectionnee.getCoordonnees().getOrdonnee() < arrivee.getCoordonnees().getOrdonnee() )
				i++;
			else
				i--;
			j = selectionnee.getCoordonnees().getAbscisse();
		}
		
		return bushiSautees;
	}

	
	@Override
	public void effectuer() {
		try{
			this.arrivee.remplir(selectionnee.enleverBushi());
		}
		catch(CaseDejaOccuperException e){
			d.getConsole().afficherErreur(e.getMessage());
		}
		catch(EnleverBushiCaseException e){
			d.getConsole().afficherErreur(e.getMessage());
		}
	}
	
}