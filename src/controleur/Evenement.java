package controleur;

public class Evenement {
	private int idEvenement, organisateurId, lieuId;
	private String nom, description, type, statut;
	private String date;
	public Evenement(int idEvenement, int organisateurId, int lieuId, String nom, String description, String type,String statut, String date) {
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
	
	public Evenement(int organisateurId, int lieuId, String nom, String description, String type,String statut, String date) {
		super();
		this.idEvenement = 0;
		this.organisateurId = organisateurId;
		this.lieuId = lieuId;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	
}
