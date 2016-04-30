/**
 * 
 */
package Damier;

/**
 * Les methodes de cette interface sont des indicateur de position des différents élément d'un damier de ShingShang.
 * @author Fabien
 * @version 1.0
 */

public interface PositionElement {

	public boolean estPositionPortail(Coordonnees coordonnees);
	public boolean estPositionCase(Coordonnees coordonnees);
	public boolean estHorsDamier(Coordonnees coordonnees);
}
