package Exception;

import Damier.Case;

public class SelectionneCaseVideException extends Exception {

	private Case selectionnee;
	
	public SelectionneCaseVideException(Case selectionnee) {
		super("Cette case est vide");
	}

	public Case getCase() {
		return selectionnee;
	}
}