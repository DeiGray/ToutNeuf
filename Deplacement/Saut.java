package Deplacement;

import Damier.Case;
import Damier.Damier;

public class Saut extends MouvementImpl {

	public Saut(Case selectionnee, Case arrivee,Damier d) {
		super(selectionnee, arrivee,d);
		// TODO Auto-generated constructor stub
	}

	public boolean estLongueurPossible(){
		return super.determinerLongueur() <= this.selectionnee.getBushi().getLongueurSaut();
	}
}
