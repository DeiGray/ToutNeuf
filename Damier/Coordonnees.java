package Damier;

import Exception.CoordonneeNegativeException;

/**
 * Donne des coordonnées de repère pour chaque Case
 * @see Case
 * @author Fabien
 * @version 1.0
 */


public class Coordonnees {
	private int abscisse;
	private int ordonnee;
	
	/**
	 * Creer un ensemble d'abscisse et d'ordonnées
	 * @param abscisse
	 * @param ordonnee
	 */
	public Coordonnees(int abscisse, int ordonnee) {
		try{
			if (abscisse < 0 || ordonnee < 0)
				throw new CoordonneeNegativeException();
			this.abscisse = abscisse;
			this.ordonnee = ordonnee;
		}
		catch(CoordonneeNegativeException e){
			System.out.println("abscisse = "+abscisse+"ordonnee = "+ordonnee+e.getMessage());
		}
	}
	
	/**
	 * @return abscisse
	 */
	public int getAbscisse() {
		return this.abscisse;
	}
	
	/**
	 * 
	 * @param abscisse
	 */
	public void setAbscisse(int abscisse) {
		this.abscisse = abscisse;
	}
	
	/**
	 * 
	 * @return ordonnee
	 */
	public int getOrdonnee() {
		return this.ordonnee;
	}
	
	/**
	 * 
	 * @param ordonnee
	 */
	public void setOrdonnee(int ordonnee) {
		this.ordonnee = ordonnee;
	}
	
}
