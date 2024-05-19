package controleur;

import java.sql.SQLException;
import java.util.ArrayList;

import modele.Modele;

public class Controleur {
	
	// User
	
	public static ArrayList<User> selectAllUser (String filtre){
		return Modele.selectAllUser(filtre);
	}
	
	public static User selectWhereUser (String courriel, String Motdepasse) {
		return Modele.selectWhereUser(courriel, Motdepasse);
	}

	public static void updateUser(User unUser) {
		Modele.updateUser(unUser);
	}
	
	public static void deleteUser(int idUser) {
		Modele.deleteUser(idUser);
	}
	
	public static void insertUser(User unUser) {
		Modele.insertUser(unUser);
		
	}
	
	// Evenement
	
	public static ArrayList<Evenement> selectAllEvenement(String filtre) {
		return Modele.selectAllEvenement(filtre);
	}
	
	public static Evenement selectWhereEvenement(String nom, String description, String type, String status, String date) {
		return Modele.selectWhereEvenement(nom, description, type, status, date);
	}
	
	public static void insertEvenement(Evenement unEvenement) {
		Modele.insertEvenement(unEvenement);
	}
	
	public static void updateEvenement(Evenement unEvenement) {
		Modele.updateEvenement(unEvenement);
	}
	
	public static void deleteEvenement(int idEvenement) {
		Modele.deleteEvenement(idEvenement);
	}
	
	// Lieu
	
	public static ArrayList<Lieu> selectAllLieu(String filtre) {
		return Modele.selectAllLieu(filtre);
	}
	
	public static Lieu selectWhereLieu(String nom, String adresse, String capacite, String disponiblite) {
		return Modele.selectWhereLieu(nom, adresse, capacite, disponiblite);
	}
	
	public static void insertLieu(Lieu unLieu) {
		Modele.insertLieu(unLieu);
	}
	
	public static void updateLieu(Lieu unLieu) {
		Modele.updateLieu(unLieu);
	}
	
	public static void deleteLieu(int idLieu) throws SQLException {
		Modele.deleteLieu(idLieu);
	}
	
	public static String selectUserNameById(int idUtilisateur) {
		return Modele.selectUserNameById(idUtilisateur);
	}
	
	public static String selectLieuNameById(int idLieu) {
		return Modele.selectLieuNameById(idLieu);
	}
	
	public static ArrayList<String> selectOrganisateurs() {
		return Modele.selectOrganisateurs();
	}
	
	public static ArrayList<String> selectLieuxDispo() {
		return Modele.selectLieuxDispo();
	}
	
	public static Integer selectOrganisateurIdByNom(String nom) {
		return Modele.selectOrganisateurIdByNom(nom);
	}
	
	public static Integer selectLieuIdByNom(String nom) {
		return Modele.selectLieuIdByNom(nom);
	}

}
