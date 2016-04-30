package Damier;

import Entitees.Bushi;
import Entitees.Dragon;
import ShingShang.Joueur;

/**
 * Type de case affilié à un joueur.
 * @author Fabien
 * @version 1.0
 */
public class Portail extends Case {
	private Joueur possesseur;

	public Portail(Coordonnees coordonnees,Joueur possesseur) {
		super(coordonnees);
		this.possesseur = possesseur;
		// TODO Auto-generated constructor stub
	}
	
	public Portail(Coordonnees coordonnees,Joueur possesseur,Bushi occupant) {
		super(coordonnees,occupant);
		this.possesseur = possesseur;
		// TODO Auto-generated constructor stub
	}
	
	public void setPossesseur(Joueur possesseur) {
		this.possesseur = possesseur;
	}

	public boolean estOccupee(){
		return this.possesseur.getIdentite().equals(super.getBushi().getArmee().getPossesseur().getIdentite()) && this.getBushi() instanceof Dragon;
	}

	/**
	 * @return the possesseur
	 */
	public Joueur getPossesseur() {
		return possesseur;
	}	
}
