package ShingShang;

public abstract class PartieImpl implements Partie {

	protected Damier plateauDeJeu;
	
	 public abstract boolean estFinie();
	 
	 public abstract String unTour(Joueur j);
	 
	 public abstract void demarrer();
	 
	 public abstract String resultat(String s);
	 
	 public abstract String gagnant();
}
