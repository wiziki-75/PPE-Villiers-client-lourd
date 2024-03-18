package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Evenement;
import controleur.Lieu;
import controleur.User;

public class Modele {
	private static BDD uneBDD = new BDD("root", "", "localhost","gestionevenements");

	public static ArrayList<User> selectAllUser (String filtre){
		String requete="";
		if(filtre.equals("")) {
			requete = "SELECT * FROM User;";
		} else {
			requete = "SELECT * FROM User WHERE nom LIKE '%"
					+ filtre + "%' OR prenom LIKE '%" + filtre
					+ "%' OR courriel = '%" + filtre + "%';";
		}
		
		ArrayList<User> lesUsers = new ArrayList<User>();
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			ResultSet desRes = unStat.executeQuery(requete);
			while(desRes.next()) {
				User unUser = new User (
						desRes.getInt("idUtilisateur"), desRes.getString("nom"),
						desRes.getString("prenom"), desRes.getString("courriel"),
						desRes.getString("motdepasse"), desRes.getString("role"),
						desRes.getBoolean("resetMDP")
						);
				lesUsers.add(unUser);
			}
			unStat.close();
			uneBDD.seDeConnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete );
		}
		return lesUsers;
	}
	
	public static User selectWhereUser(String courriel, String Motdepasse) {
		User unUser = null;
		String requete = "SELECT * FROM User WHERE courriel='" + courriel + "' AND  motdepasse='" + Motdepasse + "';";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(requete);
			if(unResultat.next()) {
				unUser = new User (
						unResultat.getInt("idUtilisateur"), unResultat.getString("nom"),
						unResultat.getString("prenom"), unResultat.getString("courriel"),
						unResultat.getString("motdepasse"), unResultat.getString("role"),
						unResultat.getBoolean("resetMDP")
						);
			}
			unStat.close();
			uneBDD.seDeConnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
		return unUser;
	}

	
	
	
	public static void updateUser(User unUser) {
		// TODO Auto-generated method stub
		
	}

	public static ArrayList<Evenement> selectAllEvenement(String filtre) {
		String requete = "";
		if(filtre.equals("")) {
			requete = "SELECT * FROM evenement";
		} else {
			requete = "SELECT * FROM evenement WHERE nom LIKE '%"
					+ filtre + "%' OR type LIKE '%" + filtre + "%';";
		}
		
		ArrayList<Evenement> lesEvenements = new ArrayList<Evenement>();
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			ResultSet desRes = unStat.executeQuery(requete); 
			while (desRes.next()) {
				Evenement unEvenement = new Evenement (
						desRes.getInt("idEvenement"), desRes.getInt("organisateurId"), 
						desRes.getInt("lieuId"), desRes.getString("nom"),
						desRes.getString("description"), desRes.getString("type"),
						desRes.getString("statut"), desRes.getDate("date")
						);
				lesEvenements.add(unEvenement);
			}
			unStat.close();
			uneBDD.seDeConnecter();	
			
		} catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete );
		}
		
		return lesEvenements;
	}

	public static Evenement selectWhereEvenement(String nom) {
		Evenement unEvenement = null;
		String requete = "SELECT * FROM evenement WHERE nom = '" + nom + "';";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			ResultSet desRes = unStat.executeQuery(requete); 
			if (desRes.next()) {
				unEvenement = new Evenement (
						desRes.getInt("idEvenement"), desRes.getInt("organisateurId"), 
						desRes.getInt("lieuId"), desRes.getString("nom"),
						desRes.getString("description"), desRes.getString("type"),
						desRes.getString("statut"), desRes.getDate("date")
						);
			}
			unStat.close();
			uneBDD.seDeConnecter();	
		} catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete );
			System.out.println(exp);
		}
		return unEvenement;
	}

	public static void insertEvenement(Evenement unEvenement) {
		// TODO Auto-generated method stub
		
	}

	public static void updateEvenement(Evenement unEvenement) {
		// TODO Auto-generated method stub
		
	}

	public static ArrayList<Lieu> selectAllLieu(String filtre) {
	    String requete = ""; 
	    if (filtre.equals("")) {
	        requete = "select * from lieu;"; // Assurez-vous que votre syntaxe SQL est correcte
	    } else {
	        requete = "select * from lieu where nom like '%" + filtre + "%' or adresse like '%" + filtre 
	                + "%' or capacite like '%" + filtre + "%' or disponibilite like '%" + filtre + "%';"; 
	    }
	    
	    ArrayList<Lieu> lesLieux = new ArrayList<Lieu>(); 
	    try {
	        uneBDD.seConnecter();
	        Statement unStat = uneBDD.getMaConnexion().createStatement();
	        ResultSet desRes = unStat.executeQuery(requete); 
	        while (desRes.next()) {
	            Lieu unLieu = new Lieu(
	                    desRes.getInt("idLieu"), desRes.getString("nom"), 
	                    desRes.getString("adresse"), desRes.getString("capacite"),
	                    desRes.getString("disponibilite")
	                    );
	            lesLieux.add(unLieu);
	        }
	        unStat.close();
	        uneBDD.seDeConnecter();
	    }
	    catch (SQLException exp) {
	        System.out.println("Erreur de requete : " + requete);
	    }   
	    return lesLieux;
	}


	public static Lieu selectWhereLieu(String nom, String adresse, String capacite, String disponibilite) {
		Lieu unLieu = null;
		String requete = "SELECT * FROM lieu WHERE nom = '" + nom
				+ "' AND adresse = '" + adresse 
				+ "' AND capacite = '" + capacite
				+ "' AND disponibilite = '" + disponibilite + "';";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			ResultSet desRes = unStat.executeQuery(requete);
			if(desRes.next()) {
				unLieu = new Lieu (
					desRes.getInt("idLieu"), desRes.getString("nom"),
					desRes.getString("adresse"), desRes.getString("capacite"),
					desRes.getString("disponibilite")
				);
			}
		} catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete );
			System.out.println(exp);
		}
		
		return unLieu;
	}

	public static void insertLieu(Lieu unLieu) {
		String requete = "INSERT INTO lieu VALUES(NULL, '"
				+ unLieu.getNom() + "', '"
				+ unLieu.getAdresse() + "', '"
				+ unLieu.getCapacite() + "', '"
				+ unLieu.getDisponibilite() + "');";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			unStat.execute(requete);
			unStat.close();
			uneBDD.seDeConnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
			System.out.println(exp);
		}
	}

	public static void updateLieu(Lieu unLieu) {
		// TODO Auto-generated method stub
		
	}
	
	public static void deleteLieu(int idLieu) {
		String requete = "DELETE FROM lieu WHERE idLieu = " + idLieu + ";";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close();
			uneBDD.seDeConnecter();	
		} catch (SQLException exp)
			{
				System.out.println("Erreur de requete : " + requete );
			}
	}
}
