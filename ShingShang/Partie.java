package ShingShang;

import Affichage.InterfaceShingShang;
import Damier.Damier;

public abstract class Partie{

	protected Damier plateauDeJeu;
	protected InterfaceShingShang console;
	
	public abstract boolean estFinie();
	public abstract String unTour(Joueur j);
	public abstract void demarrer();
	public abstract String resultat(String s);
	public abstract String gagnant();
	public abstract Damier getDamier();
}
