package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import controleur.Controleur;
import controleur.Lieu;
import controleur.Tableau;

@SuppressWarnings("serial")
public class PanelLieu extends PanelPrincipal implements ActionListener{
	private JTextField txtNom = new  JTextField(); 
	private JTextField txtAdresse = new  JTextField(); 
	private JTextField txtCapacite = new  JTextField(); 
	private JComboBox<String> cbxDispo = new JComboBox<>(new String[]{"disponible", "réservé", "indisponible"}); 
	
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JPanel panelForm = new JPanel(); 
	
	private JTable tableLieux ; 
	private JScrollPane uneScroll ; 
	private Tableau unTableau ; 
	
	private JPanel panelFiltre = new JPanel(); 
	private JTextField txtFiltre = new JTextField(); 
	private JButton btFiltrer = new JButton("Filtrer"); 
	
	private JLabel nbLieux = new JLabel(); 
	private int nb = 0;
	
	public PanelLieu() {
		super("Gestion des lieux");
		
		//construire le panel formulaire : saisie de la classe. 
		this.panelForm.setBounds(20, 90, 250, 250);
		this.panelForm.setBackground(Color.white); 
		this.panelForm.setLayout(new GridLayout(5,2));
		this.panelForm.add(new JLabel("Nom :")); 
		this.panelForm.add(this.txtNom); 
		this.panelForm.add(new JLabel("Adresse :")); 
		this.panelForm.add(this.txtAdresse); 
		this.panelForm.add(new JLabel("Capacité :")); 
		this.panelForm.add(this.txtCapacite);
		this.panelForm.add(new JLabel("Disponibilité :")); 
		this.panelForm.add(this.cbxDispo);
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
		String entetes [] = {"ID Lieu", "Nom","Adresse", "Capacité", "Disponibilité"};
		this.unTableau= new Tableau (this.obtenirDonnees(""), entetes);
		this.tableLieux = new JTable(this.unTableau) ; 
		this.uneScroll = new JScrollPane(this.tableLieux);
		this.uneScroll.setBounds(360, 130, 460, 200);
		this.add(this.uneScroll);
		
		//interdire l'ordre des colonnes 
		this.tableLieux.getTableHeader().setReorderingAllowed(false);
		
		//construction du Label Nb 
		this.nbLieux.setBounds(400, 380, 250, 20);
		this.add(this.nbLieux); 
		this.nb = this.unTableau.getRowCount(); 
		this.nbLieux.setText("Nombre de lieux : " + nb);
		
		//suppression et modification 
		//suppression et modification 
		this.tableLieux.addMouseListener(new MouseListener() {
			
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
				int numLigne, idLieu ; 
				if (e.getClickCount() >=2 ) {
					numLigne = tableLieux.getSelectedRow(); 
					idLieu = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());  
					String nomLieu = unTableau.getValueAt(numLigne, 1).toString();
					
					int response = JOptionPane.showConfirmDialog(null, 
						"Êtes-vous sûr de vouloir supprimer le lieu " + nomLieu + " ?",
						"Confirmation de suppression",
						JOptionPane.YES_NO_OPTION, 
				        JOptionPane.QUESTION_MESSAGE);
					if(response == JOptionPane.YES_OPTION) {
						try {
							Controleur.deleteLieu(idLieu);
							unTableau.supprimerLigne(numLigne);
						} catch (SQLException ex) {
			                System.err.println("Erreur SQL capturée: " + ex.getMessage());
			                JOptionPane.showMessageDialog(null, "Vous ne pouvez pas supprimer ce lieu car il est sûrement attribuer à un évènement.");
			            } catch (Exception ex) {
			                System.err.println("Erreur générale capturée: " + ex.getMessage());
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
	    ArrayList<Lieu> lesLieux = Controleur.selectAllLieu(filtre); 
	    if (lesLieux == null) {
	        return new Object[0][5]; // Retourner une matrice vide si lesLieux est null
	    }
	    Object [][] matrice = new Object [lesLieux.size()][5];
	    int i = 0; 
	    for (Lieu unLieu : lesLieux) {
	        matrice [i][0] = unLieu.getIdLieu(); 
	        matrice [i][1] = unLieu.getNom();
	        matrice [i][2] = unLieu.getAdresse();
	        matrice [i][3] = unLieu.getCapacite(); 
	        matrice [i][4] = unLieu.getDisponibilite(); 
	        i++;
	    }
	    return matrice; 
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == this.btAnnuler) {
			 this.txtNom.setText("");
			 this.txtAdresse.setText("");
			 this.txtCapacite.setText("");
			 this.cbxDispo.setSelectedIndex(0);
		 }
		 else if (e.getSource() == this.btEnregistrer) {
			 String nom = this.txtNom.getText(); 
			 String adresse = this.txtAdresse.getText(); 
			 String capacite = this.txtCapacite.getText(); 
			 String disponibilite = (String) this.cbxDispo.getSelectedItem();
			 
			 if(nom.isEmpty() || adresse.isEmpty()) {
				 JOptionPane.showMessageDialog(this, "Vérifiez que le nom et adresse soient bien remplis.");
			 } else if(capacite.isEmpty()) {
				 JOptionPane.showMessageDialog(this, "Saisissez, la capacite.");
			 } else {
				 //instancier une classe 
				 Lieu unLieu = new Lieu (nom, adresse, capacite, disponibilite);
				 
				 //insertion dans la classe dans la base de données 
				 Controleur.insertLieu (unLieu); 
				 
				 //recuperation de l'id de la classe inseree auprès de Mysql 
				 unLieu = Controleur.selectWhereLieu(nom, adresse, capacite, disponibilite); 
				 
				 //actualiser l'affichage dans le tableau 
				 Object ligne[]= {unLieu.getIdLieu(), nom, adresse, capacite, disponibilite};
				 this.unTableau.ajouterLigne(ligne);
				 this.nb = this.unTableau.getRowCount(); 
				 this.nbLieux.setText("Nombre de lieux : " + nb);
				 
				 JOptionPane.showMessageDialog(this, "Insertion réussie du lieu.");
				 this.txtNom.setText("");
				 this.txtAdresse.setText("");
				 this.txtCapacite.setText("");
				 this.cbxDispo.setSelectedIndex(0);
			 }
		 }
		 else if (e.getSource() == this.btFiltrer) {
			 String filtre = this.txtFiltre.getText(); 
			 
			 //recuperation des données de la bdd avec le filtre 
			 Object matrice[][] = this.obtenirDonnees(filtre);
			 
			 //actualisation de l'affichage 
			 this.unTableau.setDonnees(matrice);
		 }
		
	}
}
