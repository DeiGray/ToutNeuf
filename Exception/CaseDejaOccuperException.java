package Exception;

/**
 * Survient lors d'un ajout impossible de pion à une case.
 * @author Fabien
 * @version 1.0
 */

public class CaseDejaOccuperException extends Exception {
	
	public CaseDejaOccuperException(){
		super("La case que vous voulez remplir occupe deja un bushi");
	}

}
