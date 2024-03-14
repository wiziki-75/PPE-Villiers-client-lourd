package controleur;

import java.util.Date;

public class Evenement {
	private int idEvenement, organisateurId, lieuId;
	private String nom, description, type, statut;
	private Date date;
	public Evenement(int idEvenement, int organisateurId, int lieuId, String nom, String description, String type,String statut, Date date) {
		super();
		this.idEvenement = idEvenement;
		this.organisateurId = organisateurId;
		this.lieuId = lieuId;
		this.nom = nom;
		this.description = description;
		this.type = type;
		this.statut = statut;
		this.date = date;
	}
	
	public Evenement(String nom, String description, String type,String statut, Date date) {
		super();
		this.idEvenement = 0;
		this.organisateurId = 0;
		this.lieuId = 0;
		this.nom = nom;
		this.description = description;
		this.type = type;
		this.statut = statut;
		this.date = date;
	}

	public int getIdEvenement() {
		return idEvenement;
	}

	public void setIdEvenement(int idEvenement) {
		this.idEvenement = idEvenement;
	}

	public int getOrganisateurId() {
		return organisateurId;
	}

	public void setOrganisateurId(int organisateurId) {
		this.organisateurId = organisateurId;
	}

	public int getLieuId() {
		return lieuId;
	}

	public void setLieuId(int lieuId) {
		this.lieuId = lieuId;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
