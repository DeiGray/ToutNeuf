package Entitees;

import ShingShang.Armee;
import ShingShang.Identite;

/**
 * Spécialise le bushi en Dragon
 * @see Bushi
 * @author Dorian
 * @version 1.0
 */

public class Singe extends Bushi {

	/**
	 * @param 
	 */
 public Singe(Armee armee,Identite identite) {
	 super.setTAILLE_PION(1);
	 super.setArmee(armee);
	 super.setIdentite(identite);
 }

	@Override
	public int getLongueurSaut() {
		return 1;
	}

	@Override
	public int getLongueurGlisser() {
		return 2;
	}

}