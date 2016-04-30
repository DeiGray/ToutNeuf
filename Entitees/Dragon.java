package Entitees;

import ShingShang.Armee;
import ShingShang.Identite;

/**
 * Spécialise le bushi en Dragon
 * 
 * @see Bushi
 * @author Dorian
 * @version 1.0
 */
public class Dragon extends Bushi {

	/**
	 * Crée un dragon
	 * @param identité du possesseur du pion
	 */
	public Dragon(Armee armee,Identite identite) {
		super.setTAILLE_PION(3);
		super.setArmee(armee);
		super.setIdentite(identite);
	}
	
	public int getLongueurSaut(){
		return 1;
	}
	
	public int getLongueurGlisser(){
		return 0;
	}
}
