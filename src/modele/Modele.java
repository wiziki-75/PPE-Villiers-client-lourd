package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import controleur.Evenement;
import controleur.Lieu;
import controleur.User;

public class Modele {
	private static BDD uneBDD = new BDD("root", "", "localhost","gestionevenements");
	
	//========================================================================================================================================
	//User
	//========================================================================================================================================


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

	
	public static void insertUser(User unUser) {
	    String requete = "INSERT INTO user (nom, prenom, courriel, motdepasse, resetMDP, role) VALUES (?, ?, ?, ?, 0, ?)";
	    try {
	        uneBDD.seConnecter();
	        PreparedStatement unStat = (PreparedStatement) uneBDD.getMaConnexion().prepareStatement(requete);
	        unStat.setString(1, unUser.getNom());
	        unStat.setString(2, unUser.getPrenom());
	        unStat.setString(3, unUser.getCourriel());
	        unStat.setString(4, unUser.getMotdepasse());
	        // Notez que le resetMDP est fixé à 0 par défaut selon votre requête, donc pas besoin de le spécifier ici
	        unStat.setString(5, unUser.getRole());
	        unStat.executeUpdate();
	        unStat.close();
	        uneBDD.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur de requete : " + requete);
	        System.out.println(exp);
	    }
	}

	
	public static void deleteUser(int idUtilisateur) throws SQLException {
	    String requete = "DELETE FROM user WHERE idUtilisateur = ?";
	    try {
	        uneBDD.seConnecter();
	        PreparedStatement unStat = (PreparedStatement) uneBDD.getMaConnexion().prepareStatement(requete);
	        unStat.setInt(1, idUtilisateur);
	        unStat.executeUpdate();
	        unStat.close();
	        uneBDD.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur de requete : " + requete);
	        throw new SQLException("Vous ne pouvez pas cette utilisateur car il est sûrement attribué à un évènement.", exp);
	    }
	}

	
	public static void updateUser(User unUser) {
		// TODO Auto-generated method stub
		
	}
	
	//========================================================================================================================================
	//Evenement
	//========================================================================================================================================

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
						desRes.getInt("idEvenement"), 
						desRes.getInt("organisateurId"), 
						desRes.getInt("lieuId"), 
						desRes.getString("nom"),
						desRes.getString("description"), desRes.getString("type"),
						desRes.getString("statut"), desRes.getString("date")
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

	public static Evenement selectWhereEvenement(String nom, String description, String type, String status, String date) {
	    Evenement unEvenement = null;
	    String requete = "SELECT * FROM evenement WHERE nom = ? AND description = ? AND type = ? AND statut = ? AND date = ?";
	    try {
	        uneBDD.seConnecter();
	        PreparedStatement unStat = (PreparedStatement) uneBDD.getMaConnexion().prepareStatement(requete);
	        unStat.setString(1, nom);
	        unStat.setString(2, description);
	        unStat.setString(3, type);
	        unStat.setString(4, status);
	        unStat.setString(5, date);
	        ResultSet desRes = unStat.executeQuery();
	        if (desRes.next()) {
	            unEvenement = new Evenement (
	                    desRes.getInt("idEvenement"), 
	                    desRes.getInt("organisateurId"), 
	                    desRes.getInt("lieuId"), 
	                    nom, // Utilisation de la variable nom
	                    description, // Utilisation de la variable description
	                    type, // Utilisation de la variable type
	                    status, // Utilisation de la variable status
	                    date // Utilisation de la variable date
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
	    String requete = "INSERT INTO evenement (organisateurId, lieuId, nom, description, type, statut, date) VALUES (?, ?, ?, ?, ?, ?, ?)";
	    try {
	        uneBDD.seConnecter();
	        PreparedStatement unStat = (PreparedStatement) uneBDD.getMaConnexion().prepareStatement(requete);
	        unStat.setInt(1, unEvenement.getOrganisateurId());
	        unStat.setInt(2, unEvenement.getLieuId());
	        unStat.setString(3, unEvenement.getNom());
	        unStat.setString(4, unEvenement.getDescription());
	        unStat.setString(5, unEvenement.getType());
	        unStat.setString(6, unEvenement.getStatut());
	        unStat.setString(7, unEvenement.getDate());
	        unStat.executeUpdate();
	        unStat.close();
	        uneBDD.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur de requete : " + requete);
	        System.out.println(exp);
	    }
	}


	public static void updateEvenement(Evenement unEvenement) {
		// TODO Auto-generated method stub
		
	}
	
	public static void deleteEvenement(int idEvenement) {
	    String requete = "DELETE FROM evenement WHERE idEvenement = ?";
	    try {
	        uneBDD.seConnecter();
	        PreparedStatement unStat = (PreparedStatement) uneBDD.getMaConnexion().prepareStatement(requete);
	        unStat.setInt(1, idEvenement);
	        unStat.executeUpdate();
	        unStat.close();
	        uneBDD.seDeConnecter();
	    } catch (SQLException exp) {
	        System.out.println("Erreur de requete : " + requete);
	        System.out.println(exp);
	    }
	}
	
	//========================================================================================================================================
	//Lieu
	//========================================================================================================================================

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
	
	public static void deleteLieu(int idLieu) throws SQLException {
		String requete = "DELETE FROM lieu WHERE idLieu = " + idLieu + ";";
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement(); 
			unStat.execute(requete);
			unStat.close();
			uneBDD.seDeConnecter();	
		} catch (SQLException exp){
				System.out.println("Erreur de requete : " + requete );
				throw new SQLException("Vous ne pouvez pas supprimer ce lieu car il est sûrement attribué à un évènement.", exp);
			}
	}
	
	
	//========================================================================================================================================
	//SELECT CUSTOM
	//========================================================================================================================================

	
    public static String selectUserNameById(int idUtilisateur) {
        String nomUtilisateur = null; // Initialisation à null au cas où l'utilisateur n'est pas trouvé
        String requete = "SELECT nom FROM user WHERE idUtilisateur = ?;"; // Utilisation d'un paramètre

        try {
            uneBDD.seConnecter();
            // Utilisation de PreparedStatement au lieu de Statement pour sécuriser la requête
            PreparedStatement unPStat = (PreparedStatement) uneBDD.getMaConnexion().prepareStatement(requete);
            unPStat.setInt(1, idUtilisateur); // Assignation de la valeur au paramètre de la requête
            ResultSet unResultat = unPStat.executeQuery();
            if (unResultat.next()) {
                nomUtilisateur = unResultat.getString("nom"); // Récupération du nom de l'utilisateur
            }
            unPStat.close();
            uneBDD.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur de requête : " + requete);
            exp.printStackTrace();
        }
        return nomUtilisateur; // Retourne le nom de l'utilisateur ou null
    }
    
    public static String selectLieuNameById(int idLieu) {
        String nomLieu = null; // Initialisation à null au cas où le lieu n'est pas trouvé
        String requete = "SELECT nom FROM lieu WHERE idLieu = ?;"; // Utilisation d'un paramètre

        try {
            uneBDD.seConnecter();
            // Utilisation de PreparedStatement au lieu de Statement pour sécuriser la requête
            PreparedStatement unPStat = (PreparedStatement) uneBDD.getMaConnexion().prepareStatement(requete);
            unPStat.setInt(1, idLieu); // Assignation de la valeur au paramètre de la requête
            ResultSet unResultat = unPStat.executeQuery();
            if (unResultat.next()) {
                nomLieu = unResultat.getString("nom"); // Récupération du nom du lieu
            }
            unPStat.close();
            uneBDD.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur de requête : " + requete);
            exp.printStackTrace();
        }
        return nomLieu; // Retourne le nom du lieu ou null
    }
    
    public static ArrayList<String> selectOrganisateurs() {
        ArrayList<String> organisateurs = new ArrayList<>();
        String requete = "SELECT nom FROM User WHERE role = 'organisateur';";
        try {
            uneBDD.seConnecter();
            PreparedStatement unPStat = (PreparedStatement) uneBDD.getMaConnexion().prepareStatement(requete);
            ResultSet unResultat = unPStat.executeQuery();
            while (unResultat.next()) {
                String nom = unResultat.getString("nom");
                organisateurs.add(nom);
            }
            unPStat.close();
            uneBDD.seDeConnecter();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return organisateurs;
    }
    
    public static ArrayList<String> selectLieuxDispo() {
        ArrayList<String> lieux = new ArrayList<>();
        String requete = "SELECT nom FROM Lieu WHERE disponibilite = 'disponible';";
        try {
            uneBDD.seConnecter();
            PreparedStatement unPStat = (PreparedStatement) uneBDD.getMaConnexion().prepareStatement(requete);
            ResultSet unResultat = unPStat.executeQuery();
            while (unResultat.next()) {
                String nom = unResultat.getString("nom");
                lieux.add(nom);
            }
            unPStat.close();
            uneBDD.seDeConnecter();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lieux;
    }
    
    public static Integer selectOrganisateurIdByNom(String nom) {
        Integer idOrganisateur = null; // Initialisation à null au cas où l'organisateur n'est pas trouvé
        String requete = "SELECT idOrganisateur FROM organisateur WHERE nom = ?;"; // Utilisation d'un paramètre

        try {
            uneBDD.seConnecter();
            // Utilisation de PreparedStatement au lieu de Statement pour sécuriser la requête
            PreparedStatement unPStat = (PreparedStatement) uneBDD.getMaConnexion().prepareStatement(requete);
            unPStat.setString(1, nom); // Assignation de la valeur au paramètre de la requête
            ResultSet unResultat = unPStat.executeQuery();
            if (unResultat.next()) {
                idOrganisateur = unResultat.getInt("idOrganisateur"); // Récupération de l'ID de l'organisateur
            }
            unResultat.close();
            unPStat.close();
            uneBDD.seDeConnecter();
        } catch (SQLException exp) {
            System.out.println("Erreur de requête : " + requete);
            exp.printStackTrace();
        }
        return idOrganisateur; // Retourne l'ID de l'organisateur ou null
    }

    public static Integer selectLieuIdByNom(String nom) {
    	Integer idLieu = null;
        String requete = "SELECT idLieu FROM lieu WHERE nom = ?;";
        try {
            uneBDD.seConnecter();
            // Auto-closeable try-with-resources for PreparedStatement and ResultSet
            try (PreparedStatement unPStat = (PreparedStatement) uneBDD.getMaConnexion().prepareStatement(requete)) {
                unPStat.setString(1, nom); // Assignation de la valeur au paramètre de la requête
                try (ResultSet unResultat = unPStat.executeQuery()) {
                    if (unResultat.next()) {
                    	idLieu = unResultat.getInt("idLieu");
                    	return idLieu;// Récupération de l'ID du lieu
                    }
                }
            } finally {
                uneBDD.seDeConnecter();
            }
        } catch (SQLException exp) {
            System.out.println("Erreur de requête : " + requete);
            exp.printStackTrace();
        }
        return null; // Retourne null si le lieu n'est pas trouvé
    }



}
