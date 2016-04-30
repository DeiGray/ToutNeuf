package Deplacement;

import Entitees.Bushi;

public interface Mouvement {

	public Bushi effectuer();
	public boolean estLongueurPossible();
}
