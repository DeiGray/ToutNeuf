package Deplacement;

import Damier.Case;
import Damier.Damier;

public class Glissement extends MouvementImpl {

	public Glissement(Case selectionnee, Case arrivee,Damier d) {
		super(selectionnee, arrivee,d);
		// TODO Auto-generated constructor stub
	}

	public boolean estLongueurPossible(){
		return this.determinerLongueur() <= this.selectionnee.getBushi().getLongueurGlisser();
	}
}
