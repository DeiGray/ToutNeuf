package Damier;

import Entitees.Bushi;
import Exception.CaseDejaOccuperException;

/**
 * Symbolise une case d'un damier pouvant contenir un Bushi
 * @see Damier
 * @see Bushi
 * @author Fabien
 * @version 1.0
 */
public class Case {

	private Coordonnees coordonnees;
	private Bushi occupant;
	
	/**
	 * @param coordonnees
	 */
	public Case(Coordonnees coordonnees) {
		this.coordonnees = coordonnees;
		this.occupant = null;
	}
	
	public Case(Coordonnees coordonnees,Bushi occupant) {
		this.coordonnees = coordonnees;
		this.occupant = occupant;
	}

	/**
	 * 
	 * @return true si la case est vide, sinon false
	 */
	public boolean estVide () {
		return this.occupant == null; 
	}

	/**
	 * Enleve et retourne le bushi de la case.
	 * @return Bushi
	 */
	public Bushi enleverBushi () {
		if(!this.estVide())
		{
			Bushi tmp = this.occupant;
			this.occupant = null;
			return tmp;
		}
	else
		return null;
	}

	/**
	 * 
	 * @return Les coordonnées de cette case.
	 */
	public Coordonnees getCoordonnees() {
		return coordonnees;
	}
	
	/**
	 * Remplis une case avec un bushi. Si elle est pleine, renvoie une exception CaseDejaOccuperException
	 * @param nouveau
	 * @throws CaseDejaOccuperException
	 */
	public void remplir(Bushi nouveau) throws CaseDejaOccuperException{
			if(!this.estVide())
				throw new CaseDejaOccuperException();
			else
				this.occupant = nouveau;
	}
	
	/**
	 * @return le bushi de cette case
	 */
	public Bushi getBushi() {
		return this.occupant;
	}

	@Override
	public String toString() {
			return "Case de coordonnees ["+this.coordonnees.getAbscisse()+","+this.coordonnees.getOrdonnee()+"]";
	}

}