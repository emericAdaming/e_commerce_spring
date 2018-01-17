package fr.adaming.modele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="admins")
public class Admin {

	// Attributs
	
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int id;
		private String mail;
		private String mdp;
		
		// Constructeurs
		
		public Admin() {
			super();
		}

		public Admin(String mail, String mdp) {
			super();
			this.mail = mail;
			this.mdp = mdp;
		}

		public Admin(int id, String mail, String mdp) {
			super();
			this.id = id;
			this.mail = mail;
			this.mdp = mdp;
		}

		// Getters & Setters
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getMail() {
			return mail;
		}

		public void setMail(String mail) {
			this.mail = mail;
		}

		public String getMdp() {
			return mdp;
		}

		public void setMdp(String mdp) {
			this.mdp = mdp;
		}
		
		
		
		
		
		
		
		
		
		
	
}
