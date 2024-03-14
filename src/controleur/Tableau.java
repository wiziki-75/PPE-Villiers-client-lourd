package controleur;

import javax.swing.table.AbstractTableModel;

public class Tableau extends AbstractTableModel
{
	private Object [][] donnees ; //matrice des données 
	private String entetes []; //les colonnes
	
	public Tableau(Object[][] donnees, String[] entetes) {
		super();
		this.donnees = donnees;
		this.entetes = entetes;
	}
	@Override
	public int getRowCount() {
		return this.donnees.length;
	}
	@Override
	public int getColumnCount() {
		return this.entetes.length;
	}
	@Override
	public Object getValueAt(int ligne, int colonne) {
		return this.donnees [ligne][colonne];
	}
	@Override
	public String getColumnName(int colonne) {
		return this.entetes[colonne];
	}
	public void ajouterLigne (Object ligne[]) {
		Object matrice [][] = new Object [this.getRowCount()+1][this.getColumnCount()];
		int i = 0; 
		for (i =0; i < this.getRowCount(); i++) {
			matrice [i] = donnees[i];
		}
		matrice [this.getRowCount()]= ligne ; 
		//actualisation des données 
		this.donnees = matrice ; 
		this.fireTableDataChanged();
	}
	public void supprimerLigne(int numLigne) {
		Object matrice [][] = new Object [this.getRowCount()-1][this.getColumnCount()];
		int i = 0, j = 0; 
		for (i =0; i < this.getRowCount(); i++) {
			if (i != numLigne) {
					matrice [j] = donnees[i];
					j++;
				}	
		}
		//actualisation des données 
		this.donnees = matrice ; 
		this.fireTableDataChanged();
	}
	public void modifierLigne (int numLigne, Object ligne[]) {
		Object matrice [][] = new Object [this.getRowCount()][this.getColumnCount()];
		int i = 0, j = 0; 
		for (i =0; i < this.getRowCount(); i++) {
			if (i != numLigne) {
					matrice [j] = donnees[i];
					j++;
				}else {
					matrice [j] = ligne; 
					j++;
				}
		}
		//actualisation des données 
		this.donnees = matrice ; 
		this.fireTableDataChanged();
	}
	
	public void setDonnees (Object matrice [][]) {
		this.donnees = matrice ; 
		this.fireTableDataChanged();//actualisation des données
	}
	
	
	
}
