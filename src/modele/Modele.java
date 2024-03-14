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
		// TODO Auto-generated method stub
		return null;
	}

	public static Evenement selectWhereEvenement(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void insertEvenement(Evenement unEvenement) {
		// TODO Auto-generated method stub
		
	}

	public static void updateEvenement(Evenement unEvenement) {
		// TODO Auto-generated method stub
		
	}

	public static ArrayList<Lieu> selectAllLieu(String filtre) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Lieu selectWhereLieu(String nom) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void insertLieu(Lieu unLieu) {
		// TODO Auto-generated method stub
		
	}

	public static void updateLieu(Lieu unLieu) {
		// TODO Auto-generated method stub
		
	}
}
