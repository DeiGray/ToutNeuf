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
public class Lion extends Bushi {

	/**
	 * @param
	 */
	public Lion(Armee armee,Identite identite) {
		super.setTAILLE_PION(2);
		super.setArmee(armee);
		super.setIdentite(identite);
	}
	public int getLongueurSaut(){
		return 1;
	}
	
	public int getLongueurGlisser(){
		return 1;
	}
}