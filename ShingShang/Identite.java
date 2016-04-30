package ShingShang;

/**
 * Chaque joueur possède une identité. Les Bushis possède l'identité de leurs détenteurs.
 * @author Fabien
 * @version 1.0
 */
public class Identite {

	private String nom;
	private int numero;
	public Identite(String nom,int numero) {
		this.nom = nom;
		this.numero = numero;
		// TODO Auto-generated constructor stub
	}
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		this.numero = numero;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Identite other = (Identite) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (numero != other.numero)
			return false;
		return true;
	}
	public String toString(){
		return this.nom+",  numero "+this.numero;
	}

}
