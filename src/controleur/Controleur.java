package controleur;

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
	
	public static void deleteUser(int idclasse) {
		// TODO Auto-generated method stub
		
	}
	
	public static void insertUser(User unUser) {
		// TODO Auto-generated method stub
		
	}
	
	// Evenement
	
	public static ArrayList<Evenement> selectAllEvenement(String filtre) {
		return Modele.selectAllEvenement(filtre);
	}
	
	public static Evenement selectWhereEvenement(String nom) {
		return Modele.selectWhereEvenement(nom);
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
	
	public static void deleteLieu(int idLieu) {
		Modele.deleteLieu(idLieu);
	}


}
