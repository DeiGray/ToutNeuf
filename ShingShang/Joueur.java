package ShingShang;

import Damier.Case;
import Entitees.Bushi;
import Exception.SelectionneCaseVideException;

/**
 * Défini un joueur
 * 
 * @see Deplacements
 * @author Dorian
 * @version 1.0
 */

public class Joueur extends ActionTour{
		
		private Identite identitee;
		private Armee armee;
		private boolean abandon;

		/**
		 * Permet de récupérer le nom d'un Bushi
		 * 
		 * @param identitée
		 */
		public Joueur(Identite identitee) {
			this.identitee = identitee;
			this.armee = null;
			this.abandon = false;
		}

		/**
		 * @return the abandon
		 */
		public boolean estAbandon() {
			return abandon;
		}

		/**
		 * @param abandon the abandon to set
		 */
		public void setAbandon(boolean abandon) {
			this.abandon = abandon;
		}

		/**
		 * Permet de récupérer l'armée d'un joueur
		 * 
		 * @return armée
		 */
		public Armee getArmee() {
			return armee;
		}

		/**
		 * @param armee the armee to set
		 */
		public void setArmee(Armee armee) {
			this.armee = armee;
		}


		/**
		 * @return the identitee
		 */
		public Identite getIdentite() {
			return identitee;
		}
	}