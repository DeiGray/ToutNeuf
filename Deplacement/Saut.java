package Deplacement;

import java.util.LinkedList;
import java.util.List;

import Damier.Case;
import Damier.Damier;
import Entitees.Bushi;
import Exception.EnleverBushiCaseException;

public class Saut extends MouvementImpl {

	public Saut(Case selectionnee, Case arrivee,Damier d) {
		super(selectionnee, arrivee,d);
		// TODO Auto-generated constructor stub
	}

	public boolean estLongueurPossible(){
		return super.determinerLongueur() <= this.selectionnee.getBushi().getLongueurSaut();
	}
	
	public void effectuer(){
		List<Bushi> listSaute= this.bushiSautees();
		Bushi bushiSaute;
		
		int nbBushiSaute = listSaute.size();
		for (int i=0;i<nbBushiSaute;i++){
			bushiSaute = listSaute.get(i);
			if(super.estBushiEnnemie(bushiSaute)){
				try {
					bushiSaute.getArmee().supprimeBushi(d.getCase(bushiSaute).enleverBushi());
				} catch (NullPointerException | EnleverBushiCaseException e) {
					d.getConsole().afficherErreur(e.getMessage());
				}
			}
		}
		super.effectuer();
	}
	

	
}
