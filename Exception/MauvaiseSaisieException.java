package Exception;

public class MauvaiseSaisieException extends Exception {

	public MauvaiseSaisieException(String message) {
		super("Erreur de Saisie : "+message);
	}

}
