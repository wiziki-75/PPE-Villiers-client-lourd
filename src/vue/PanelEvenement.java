package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Controleur;
import controleur.Evenement;
import controleur.Tableau;

@SuppressWarnings("serial")
public class PanelEvenement extends PanelPrincipal implements ActionListener{
	private JTextField txtNom = new  JTextField(); 
	private JTextField txtDate = new  JTextField();
	private JTextField txtDescription = new  JTextField();
	private JComboBox<String> cbxType = new JComboBox<>(new String[] {"Concert", "Educatif", "Communautaire"});
	private JComboBox<String> cbxStatus = new JComboBox<>(new String[] {"en_attente", "confirmé", "annulé"});
	private JComboBox<String> cbxOrganisateur = new JComboBox<>();
	private JComboBox<String> cbxLieu = new JComboBox<>();
	
	public void remplirCbxOrganisateur() {
	    ArrayList<String> organisateurs = Controleur.selectOrganisateurs();
	    for (String nom : organisateurs) {
	        cbxOrganisateur.addItem(nom);
	    }
	}
	
	public void selectLieuxDispo() {
	    ArrayList<String> lieux = Controleur.selectLieuxDispo();
	    for (String nom : lieux) {
	        cbxLieu.addItem(nom);
	    }
	}

	
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JPanel panelForm = new JPanel(); 
	
	private JTable tableEvenements ; 
	private JScrollPane uneScroll ; 
	private Tableau unTableau ; 
	
	private JPanel panelFiltre = new JPanel(); 
	private JTextField txtFiltre = new JTextField(); 
	private JButton btFiltrer = new JButton("Filtrer"); 
	
	private JLabel nbEvenements = new JLabel(); 
	private int nb = 0;
	
	public PanelEvenement() {
		super("Gestion des évènements");
		
		remplirCbxOrganisateur();
		selectLieuxDispo();
		
		//construire le panel formulaire : saisie de la classe. 
		this.panelForm.setBounds(20, 90, 250, 250);
		this.panelForm.setBackground(Color.white); 
		this.panelForm.setLayout(new GridLayout(8,2));
		this.panelForm.add(new JLabel("Nom :")); 
		this.panelForm.add(this.txtNom); 
		this.panelForm.add(new JLabel("Description :")); 
		this.panelForm.add(this.txtDescription); 
		this.panelForm.add(new JLabel("Date : (Y-M-D H:M:S)")); 
		this.panelForm.add(this.txtDate); 
		this.panelForm.add(new JLabel("Type :")); 
		this.panelForm.add(this.cbxType);
		this.panelForm.add(new JLabel("Status :")); 
		this.panelForm.add(this.cbxStatus);
		this.panelForm.add(new JLabel("Organisateur :")); 
		this.panelForm.add(this.cbxOrganisateur);
		this.panelForm.add(new JLabel("Lieu :")); 
		this.panelForm.add(this.cbxLieu);
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btEnregistrer);
		//on ajoute le formulaire à la fenetre 
		this.add(this.panelForm);
		
		//construction du panel filtre 
		this.panelFiltre.setBounds(360, 80, 460, 30);
		this.panelFiltre.setBackground(Color.white); 
		this.panelFiltre.setLayout(new GridLayout(1,3));
		this.panelFiltre.add(new JLabel("Filtrer par :")); 
		this.panelFiltre.add(this.txtFiltre); 
		this.panelFiltre.add(this.btFiltrer);
		this.add(this.panelFiltre);
		
		//construction de la table 
		String entetes [] = {"ID", "Nom","Date", "Type", "Status", "Organisateur", "Lieu"};
		this.unTableau= new Tableau (this.obtenirDonnees(""), entetes);
		this.tableEvenements = new JTable(this.unTableau) ; 
		this.uneScroll = new JScrollPane(this.tableEvenements);
		this.uneScroll.setBounds(360, 130, 460, 200);
		this.add(this.uneScroll);
		
		//interdire l'ordre des colonnes 
		this.tableEvenements.getTableHeader().setReorderingAllowed(false);
		
		//construction du Label Nb 
		this.nbEvenements.setBounds(400, 380, 250, 20);
		this.add(this.nbEvenements); 
		this.nb = this.unTableau.getRowCount(); 
		this.nbEvenements.setText("Nombre d'évènements : " + nb);
		
		//suppression et modification 
		this.tableEvenements.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int numLigne, idEvenement ; 
				
				if (e.getClickCount() >=2 ) {
					numLigne = tableEvenements.getSelectedRow(); 
					idEvenement = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString()); 
					String nomEvent = unTableau.getValueAt(numLigne, 1).toString();
					
				    int response = JOptionPane.showConfirmDialog(null, 
				    		"Êtes-vous sûr de vouloir supprimer l'évènement " + nomEvent + " ?", 
					        "Confirmation de suppression", 
					        JOptionPane.YES_NO_OPTION, 
					        JOptionPane.QUESTION_MESSAGE);
					    
					if (response == JOptionPane.YES_OPTION) {
						try {
							Controleur.deleteEvenement(idEvenement);
							unTableau.supprimerLigne(numLigne);
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "Une erreur s'est produite : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
						} 
						
				    }
				}
			}
		});
		
		//rendre les boutons ecoutables 
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btFiltrer.addActionListener(this);
	}
	
	public Object [][] obtenirDonnees (String filtre){
	    // transformer l'ArrayList en une matrice [][] 
	    ArrayList<Evenement> lesEvenements = Controleur.selectAllEvenement(filtre); 
	    if (lesEvenements == null) {
	        return new Object[0][7]; // Retourner une matrice vide si lesLieux est null
	    }
	    Object [][] matrice = new Object [lesEvenements.size()][7];
	    int i = 0; 
	    for (Evenement unEvenement : lesEvenements) {
	        matrice [i][0] = unEvenement.getIdEvenement(); 
	        matrice [i][1] = unEvenement.getNom();
	        matrice [i][2] = unEvenement.getDate();
	        matrice [i][3] = unEvenement.getType(); 
	        matrice [i][4] = unEvenement.getStatut(); 
	        matrice [i][5] = Controleur.selectUserNameById(unEvenement.getOrganisateurId()); 
	        matrice [i][6] = Controleur.selectLieuNameById(unEvenement.getLieuId()); 
	        i++;
	    }
	    return matrice; 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btAnnuler) {
			this.txtNom.setText("");
			this.txtDescription.setText("");
			this.txtDate.setText("");
			this.cbxLieu.setSelectedIndex(0);
			this.cbxOrganisateur.setSelectedIndex(0);
			this.cbxStatus.setSelectedIndex(0);
			this.cbxType.setSelectedIndex(0);
		} else if(e.getSource() == this.btEnregistrer) {
			String nom = this.txtNom.getText();
			String description = this.txtDescription.getText();
			String date = this.txtDate.getText();
			String type = (String) this.cbxType.getSelectedItem();
			String status = (String) this.cbxStatus.getSelectedItem();
			String organisateur = (String) this.cbxOrganisateur.getSelectedItem();
			String lieu = (String) this.cbxLieu.getSelectedItem();
			
			if(nom.isEmpty() || description.isEmpty() || date.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
			} else {
				int organisateurId = Controleur.selectOrganisateurIdByNom(organisateur);
				int lieuId = Controleur.selectLieuIdByNom(lieu);
				
				Evenement unEvenement = new Evenement(organisateurId, lieuId, nom, description, type, status, date);
				
				Controleur.insertEvenement(unEvenement);
				
				unEvenement = Controleur.selectWhereEvenement(nom, description, type, status, date);
				
				Object ligne[] = {unEvenement.getIdEvenement(), nom, date, type, status, organisateur, lieu};
				this.unTableau.ajouterLigne(ligne);
				this.nb = this.unTableau.getRowCount(); 
				this.nbEvenements.setText("Nombre de d'évènements : " + nb);
				
				JOptionPane.showMessageDialog(this, "Insertion réussie du lieu.");
				this.txtNom.setText("");
				this.txtDescription.setText("");
				this.txtDate.setText("");
				this.cbxLieu.setSelectedIndex(0);
				this.cbxOrganisateur.setSelectedIndex(0);
				this.cbxStatus.setSelectedIndex(0);
				this.cbxType.setSelectedIndex(0);
			}
		} else if(e.getSource() == this.btFiltrer) {
			 String filtre = this.txtFiltre.getText(); 
			 
			 //recuperation des données de la bdd avec le filtre 
			 Object matrice[][] = this.obtenirDonnees(filtre);
			 
			 //actualisation de l'affichage 
			 this.unTableau.setDonnees(matrice);
		}
	}

}
