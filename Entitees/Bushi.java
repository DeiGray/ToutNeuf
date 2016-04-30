package Entitees;

import ShingShang.Armee;
import ShingShang.Identite;

/**
 * Symbolise un Bushi (pion sur un plateau)
 * 
 * @see Bushi
 * @author Dorian et Fabien
 * @version 1.1
 */

public abstract class Bushi implements Glisser,Sauter{

	private Armee armee;
	private Identite identite;
	private int TAILLE_PION;
	
	/**
	 * @return the identite
	 */
	public Identite getIdentite() {
		return identite;
	}

	/**
	 * @param identite the identite to set
	 */
	public void setIdentite(Identite identite) {
		this.identite = identite;
	}

	/**
	 * Renvoie la taille du pion
	 * 
	 * @return TAILLE_PION
	 */
	public int getTAILLE_PION() {
		return TAILLE_PION;
	}

	/**
	 * Modifie la valeur de TAILLE_PION
	 * 
	 * @param TAILLE_PION
	 */
	public void setTAILLE_PION(int TAILLE_PION) {
		this.TAILLE_PION = TAILLE_PION;
	}


	/**
	 * @return the armee
	 */
	public Armee getArmee() {
		return armee;
	}

	/**
	 * @param armee the armee to set
	 */
	public void setArmee(Armee armee) {
		this.armee = armee;
	}
	
	public void supprimer() {

	}
}