package controleur;

public class User {
	private int idUtilisateur;
	private String nom, prenom, courriel, motdepasse, role;
	private boolean resetMDP;
	
	public User(int idUtilisateur, String nom, String prenom, String courriel, String motdepasse, String role, boolean resetMDP) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.nom = nom;
		this.prenom = prenom;
		this.courriel = courriel;
		this.motdepasse = motdepasse;
		this.role = role;
		this.resetMDP = resetMDP;
	}
	
	public User(String nom, String prenom, String courriel, String motdepasse, String role) {
		super();
		this.idUtilisateur = 0;
		this.nom = nom;
		this.prenom = prenom;
		this.courriel = courriel;
		this.motdepasse = motdepasse;
		this.role = role;
		this.resetMDP = false;
	}


	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getCourriel() {
		return courriel;
	}

	public void setCourriel(String courriel) {
		this.courriel = courriel;
	}

	public String getMotdepasse() {
		return motdepasse;
	}

	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isResetMDP() {
		return resetMDP;
	}

	public void setResetMDP(boolean resetMDP) {
		this.resetMDP = resetMDP;
	}
	
	
}
