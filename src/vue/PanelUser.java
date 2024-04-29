package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

import controleur.User;
import controleur.Controleur;
import controleur.Tableau; 

@SuppressWarnings("serial")
public class PanelUser extends PanelPrincipal implements ActionListener{
	private JTextField txtNom = new  JTextField(); 
	private JTextField txtPrenom = new  JTextField(); 
	private JTextField txtCourriel = new  JTextField(); 
	private JTextField txtMotdepasse = new JTextField();
	private JComboBox<String> cbxRole = new JComboBox<>(new String[] {"Participant", "Organisateur"});
	
	private JButton btAnnuler = new JButton("Annuler"); 
	private JButton btEnregistrer = new JButton("Enregistrer");
	
	private JPanel panelForm = new JPanel(); 
	
	private JTable tableUsers ; 
	private JScrollPane uneScroll ; 
	private Tableau unTableau ; 
	
	private JPanel panelFiltre = new JPanel(); 
	private JTextField txtFiltre = new JTextField(); 
	private JButton btFiltrer = new JButton("Filtrer"); 
	
	private JLabel nbUser = new JLabel(); 
	private int nb = 0;

	public PanelUser() {
		super("Gestion des utilisateurs");
		
		this.panelForm.setBounds(20, 90, 250, 250);
		this.panelForm.setBackground(Color.white); 
		this.panelForm.setLayout(new GridLayout(8,2));
		this.panelForm.add(new JLabel("Nom :")); 
		this.panelForm.add(this.txtNom); 
		this.panelForm.add(new JLabel("Prenom :")); 
		this.panelForm.add(this.txtPrenom); 
		this.panelForm.add(new JLabel("Courriel :")); 
		this.panelForm.add(this.txtCourriel); 
		this.panelForm.add(new JLabel("Mot de passe :")); 
		this.panelForm.add(this.txtMotdepasse); 
		this.panelForm.add(new JLabel("Role :")); 
		this.panelForm.add(this.cbxRole);
		this.panelForm.add(this.btAnnuler); 
		this.panelForm.add(this.btEnregistrer);
		this.add(this.panelForm);
		
		//construction du panel filtre 
		this.panelFiltre.setBounds(360, 80, 460, 30);
		this.panelFiltre.setBackground(Color.white); 
		this.panelFiltre.setLayout(new GridLayout(1,3));
		this.panelFiltre.add(new JLabel("Filtrer par :")); 
		this.panelFiltre.add(this.txtFiltre); 
		this.panelFiltre.add(this.btFiltrer);
		this.add(this.panelFiltre);
		
		String entetes[] = {"ID", "Nom", "Prenom", "Courriel", "Role"};
		this.unTableau = new Tableau(this.obtenirDonnees(""), entetes);
		this.tableUsers = new JTable(this.unTableau) ; 
		this.uneScroll = new JScrollPane(this.tableUsers);
		this.uneScroll.setBounds(360, 130, 460, 200);
		this.add(this.uneScroll);
		
		this.tableUsers.getTableHeader().setReorderingAllowed(false);
		
		this.nbUser.setBounds(400, 380, 250, 20);
		this.add(this.nbUser); 
		this.nb = this.unTableau.getRowCount(); 
		this.nbUser.setText("Nombre d'utilisateur : " + nb);
		
		this.tableUsers.addMouseListener(new MouseListener() {
			
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
				int numLigne, idUser ; 
				if (e.getClickCount() >=2 ) {
					numLigne = tableUsers.getSelectedRow(); 
					idUser = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString()); 
					Controleur.deleteUser(idUser);
					unTableau.supprimerLigne(numLigne);
				}
			}
		});
		
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		this.btFiltrer.addActionListener(this);
	}
	
	public Object[][] obtenirDonnees(String filtre) {
	    ArrayList<User> lesUsers = Controleur.selectAllUser(filtre);
	    if (lesUsers == null) {
	        return new Object[0][5];
	    }
	    Object[][] matrice = new Object[lesUsers.size()][5];
	    int i = 0;
	    for (User unUser : lesUsers) {
	        matrice[i][0] = unUser.getIdUtilisateur();
	        matrice[i][1] = unUser.getNom();
	        matrice[i][2] = unUser.getPrenom();
	        matrice[i][3] = unUser.getCourriel();
	        matrice[i][4] = unUser.getRole();
	        i++;
	    }
	    return matrice;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btAnnuler) {
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtCourriel.setText("");
			this.txtMotdepasse.setText("");
			this.cbxRole.setSelectedIndex(0);
		} else if(e.getSource() == this.btEnregistrer) {
			String nom = this.txtNom.getText();
			String prenom = this.txtPrenom.getText();
			String courriel = this.txtCourriel.getText();
			String motdepasse = this.txtMotdepasse.getText();
			String role = (String) this.cbxRole.getSelectedItem();
			
			if(nom.isEmpty() || prenom.isEmpty() || courriel.isEmpty() || motdepasse.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
			} else {
				User unUser = new User(nom, prenom, courriel, motdepasse, role);
				
				Controleur.insertUser(unUser);
				
				unUser = Controleur.selectWhereUser(courriel, motdepasse);
				
				Object ligne[] = {unUser.getIdUtilisateur(), nom, prenom, courriel, role};
				this.unTableau.ajouterLigne(ligne);
				this.nb = this.unTableau.getRowCount(); 
				this.nbUser.setText("Nombre de d'évènements : " + nb);
				
				JOptionPane.showMessageDialog(this, "Insertion réussie de l'utilisateur.");
				this.txtNom.setText("");
				this.txtPrenom.setText("");
				this.txtCourriel.setText("");
				this.txtMotdepasse.setText("");
				this.cbxRole.setSelectedIndex(0);
			}
		} else if(e.getSource() == this.btFiltrer) {
			 String filtre = this.txtFiltre.getText(); 
			 
			 Object matrice[][] = this.obtenirDonnees(filtre);
			 
			 this.unTableau.setDonnees(matrice);
		}
	}

}
