package controleur;

public class Lieu {
	private int idLieu;
	private String nom, adresse, capacite, disponibilite;
	public Lieu(int idLieu, String nom, String adresse, String capacite, String disponibilite) {
		super();
		this.idLieu = idLieu;
		this.nom = nom;
		this.adresse = adresse;
		this.capacite = capacite;
		this.disponibilite = disponibilite;
	}
	
	public Lieu(String nom, String adresse, String capacite, String disponibilite) {
		super();
		this.idLieu = 0;
		this.nom = nom;
		this.adresse = adresse;
		this.capacite = capacite;
		this.disponibilite = disponibilite;
	}

	public int getIdLieu() {
		return idLieu;
	}

	public void setIdLieu(int idLieu) {
		this.idLieu = idLieu;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCapacite() {
		return capacite;
	}

	public void setCapacite(String capacite) {
		this.capacite = capacite;
	}

	public String getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(String disponibilite) {
		this.disponibilite = disponibilite;
	}
	
	
}
