package ShingShang;
import java.util.ArrayList;

import Affichage.InterfaceShingShang;
import Entitees.Bushi;
import Entitees.Dragon;
import Entitees.Lion;
import Entitees.Singe;

import java.lang.NullPointerException;

/**
 * Représente l'ensemble des Bushis d'un Joueur.
 * 
 * @author fabou
 * @version 1.0
 */
public class Armee {

	private Joueur possesseur;
	public final int NOMBRE_BUSHI;
	public final int NOMBRE_SINGE;
	public final int NOMBRE_LION;
	public final int NOMBRE_DRAGON = 2;
	private ArrayList<Bushi> bushis;
	private InterfaceShingShang console;
	
	
	/**
	 * Crée une armée de Bushi sous la forme d'une ArrayList.
	 * Armée composé de 12 Bushi.
	 * @see ArrayList
	 * @param possesseur
	 */
	public Armee(Joueur possesseur,InterfaceShingShang console) {
		this.console = console;
		this.possesseur = possesseur;
		this.NOMBRE_BUSHI=12;
		this.bushis = new ArrayList<Bushi>(NOMBRE_BUSHI);
		this.NOMBRE_SINGE = this.NOMBRE_BUSHI/2;
		this.NOMBRE_LION = NOMBRE_BUSHI-NOMBRE_SINGE-NOMBRE_DRAGON;
		this.ajouterDragon();
		this.ajouterLion();
		this.ajouterSinge();
	}
	
	public Armee(Joueur possesseur,int nombreBushi) {
		this.possesseur = possesseur;
		if(nombreBushi %2 != 0)
			nombreBushi++;
		if(nombreBushi<8)
			nombreBushi=8;
		this.NOMBRE_BUSHI=nombreBushi;
		this.bushis = new ArrayList<Bushi>(NOMBRE_BUSHI);
		this.NOMBRE_SINGE = this.NOMBRE_BUSHI/2;
		this.NOMBRE_LION = NOMBRE_BUSHI-NOMBRE_SINGE-NOMBRE_DRAGON;
		this.ajouterDragon();
		this.ajouterLion();
		this.ajouterSinge();
	}
	
	/**
	 * ajoute les singes dans l'armee.
	 */
	private void ajouterSinge(){
		for (int i=0;i<NOMBRE_BUSHI/2;i++){
			try{
				this.ajouterBushi(new Singe(this,new Identite("Singe"+i,i)));
			}
			catch(NullPointerException e){
				console.afficherErreur("Ajout de Singe rater.");
			}
		}
	}
	/**
	 * ajoute les Dragons dans l'armee.
	 */
	private void ajouterDragon(){
		for (int i=0;i<2;i++){
			try{
				this.ajouterBushi(new Dragon(this,new Identite("Dragon"+i,i)));
			}
			catch(NullPointerException e){
				console.afficherErreur("Ajout de Singe rater.");
			}
		}
	}
	/**
	 * ajoute les Lions dans l'armée.
	 */
	private void ajouterLion(){
		for (int i=0;i<NOMBRE_LION;i++){
			try{
				this.ajouterBushi(new Lion(this,new Identite("Lion"+i,i)));
			}
			catch(NullPointerException e){
				console.afficherErreur("Ajout de Singe rater.");
			}
		}
	}
	
	/**
	 * Ajoute un bushi à l'armée
	 * @param bushi
	 * @throws NullPointerException
	 */
	public void ajouterBushi(Bushi bushi) throws NullPointerException{
		try{
			if (bushi == null)
				throw new NullPointerException();
			this.bushis.add(bushi);
		}
		catch(NullPointerException e){
			console.afficherErreur("Erreur : ajout de bushi impossible. "+e.getMessage());
		}
	}
	
	public boolean supprimeBushi(Bushi bushi) throws NullPointerException{
		try{
			if(bushi == null)
				throw new NullPointerException();
			return this.bushis.remove(bushi);
		}
		catch(NullPointerException e){
			console.afficherErreur("Vous tentez de supprimé un bushi inexistant."+e.getMessage());
			return false;
		}
	}
	/**
	 * Donne le bushi d'identite et de type
	 * @param bushi
	 * @param id
	 */
	public Bushi getBushi(Class<? extends Bushi> classBushi ,int numero) throws NullPointerException{
		for(int i=0;i<this.NOMBRE_BUSHI;i++){
			if(classBushi == Dragon.class && numero == this.bushis.get(i).getIdentite().getNumero())
				return this.bushis.get(i);
			if(classBushi == Lion.class && numero == this.bushis.get(i).getIdentite().getNumero())
				return this.bushis.get(i);
			if(classBushi == Singe.class && numero == this.bushis.get(i).getIdentite().getNumero())
				return this.bushis.get(i);	
		}
		throw new NullPointerException("impossible d'obtenir ce bushi dans l'armee :"+this);
	}
	
	public Bushi getBushi(int indice){
				return this.bushis.get(indice);
	}
	

	
	public boolean aucunDragon(){
		for (int i=0;i<2;i++){
			if(this.bushis.get(i) instanceof Dragon)
				return false;
		}
		return true;
	}
	
	public boolean estVide(){
		return this.bushis.isEmpty();
	}
	
	public int getTaille(){
		return this.bushis.size();
	}

	/**
	 * @return the possesseur
	 */
	public Joueur getPossesseur() {
		return possesseur;
	}

	/**
	 * @return the nOMBRE_PION
	 */
	public int getNOMBRE_BUSHI() {
		return NOMBRE_BUSHI;
		
	}
	
	public String toString(){
		return this.bushis.toString();
	}
}
