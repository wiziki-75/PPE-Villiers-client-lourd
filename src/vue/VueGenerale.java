package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import controleur.Villiers;
import controleur.User;

@SuppressWarnings("serial")
public class VueGenerale extends JFrame implements ActionListener{
	
	private JPanel panelMenu = new JPanel(); 
	private JButton btProfil = new JButton("Profil");
	private JButton btUsers = new JButton("Utilisateurs");
	private JButton btEvenements = new JButton("Evenements");
	private JButton btLieux = new JButton("Lieux");
	private JButton btQuitter = new JButton("Quitter");
	
	private PanelProfil unPanelProfil;
	private PanelUser unPanelUser = new PanelUser();
	private PanelEvenement unPanelEvenement = new PanelEvenement();
	private PanelLieu unPanelLieu = new PanelLieu();
	
	public VueGenerale(User unUser) {
		unPanelProfil = new PanelProfil(unUser);
		
		this.setTitle("Application admin 0.1.0-beta.2");
		this.setResizable(false);
		this.setBounds(100, 100, 900, 600);
		this.getContentPane().setBackground(Color.white);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//construction du panel Menu 
		this.panelMenu.setBounds(50, 10, 800, 30);
		this.panelMenu.setBackground(Color.white);
		this.panelMenu.setLayout(new GridLayout(1, 6));
		this.panelMenu.add(this.btProfil);
		this.panelMenu.add(this.btUsers); 
		this.panelMenu.add(this.btEvenements); 
		this.panelMenu.add(this.btLieux); 
		this.panelMenu.add(this.btQuitter); 
		this.add(this.panelMenu);
		
		//rendre les boutons ecoutables 
		this.btProfil.addActionListener(this);
		this.btUsers.addActionListener(this);
		this.btEvenements.addActionListener(this);
		this.btLieux.addActionListener(this);
		this.btQuitter.addActionListener(this);
		
		//ajout les panels dans la vue 
		this.add(this.unPanelProfil);
		this.add(this.unPanelUser);
		this.add(this.unPanelEvenement);
		this.add(this.unPanelLieu);
		
		this.setVisible(true);
	}
	
	public void afficherPanel (int choix) {
		this.unPanelProfil.setVisible(false);
		this.unPanelUser.setVisible(false);
		this.unPanelEvenement.setVisible(false);
		this.unPanelLieu.setVisible(false);
		
		switch(choix) {
		case 1 : this.unPanelProfil.setVisible(true);break;
		case 2 : this.unPanelUser.setVisible(true);break;
		case 3 : this.unPanelEvenement.setVisible(true);break;
		case 4 : this.unPanelLieu.setVisible(true);break;
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btQuitter) {
			Villiers.rendreVisibleVueGenerale(false, null);
			Villiers.rendreVisibleVueConnexion(true);
		} else if(e.getSource() == this.btProfil) {
			this.afficherPanel(1);
		} else if(e.getSource() == this.btUsers) {
			this.afficherPanel(2);
		} else if(e.getSource() == this.btEvenements) {
			this.afficherPanel(3);
		} else if(e.getSource() == this.btLieux) {
			this.afficherPanel(4);
		}
	}
}
