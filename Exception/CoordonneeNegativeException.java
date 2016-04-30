package Exception;

/**
 * Exception des erreurs de création de ccordonnées
 * @author Fabien
 * @version 1.0
 */

public class CoordonneeNegativeException extends Exception {

	public CoordonneeNegativeException() {
		super("Coordonnees impossible. Coordonnees autorisé uniquement positive.");
	}
}