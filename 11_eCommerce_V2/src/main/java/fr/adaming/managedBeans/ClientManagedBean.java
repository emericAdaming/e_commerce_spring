package fr.adaming.managedBeans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpSession;
import javax.swing.ImageIcon;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import fr.adaming.modele.Client;
import fr.adaming.modele.LigneCommande;
import fr.adaming.service.IClientService;

@ManagedBean(name="clientMB")
@RequestScoped
public class ClientManagedBean implements Serializable {

	@ManagedProperty(value="#{clService}")
	private IClientService clientService;

	private Client client;
	
	private List<LigneCommande> listeLignes;
	private HttpSession maSession;

	// Constructeur
	
	public ClientManagedBean() {
		this.client = new Client();
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}
	
	// Setter pour l'injection dépendance
	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}
	
	// Getters & Setters

	public Client getClient() {
		return client;
	}

	public List<LigneCommande> getListeLignes() {
		return listeLignes;
	}

	public void setListeLignes(List<LigneCommande> listeLignes) {
		this.listeLignes = listeLignes;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	// Méthodes métiers
	
	public String ajouterClient(){
		this.client=clientService.ajouterClient(this.client);
		return "ajoutClient";
	}
	
	public String connexionClient(){
		this.client=clientService.isExist(this.client);
		maSession.setAttribute("clientSession", this.client);
		return "accueil";
	}
	
	public String seDeconnecter(){
		System.out.println("DEconnexion client");
		this.client=null;
		//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adminSession", null);
		// Metre à jour la liste dans la session
		maSession.setAttribute("clientSession", null);
		//permet de refresh accueil
		return "deconnection"; 
	}
	
	
//**********************************************************************************************************	
//****************************** Mail & Pdf ****************************************************************
	
	class GMailAuthenticator extends Authenticator {
	     String user;
	     String pw;
	     public GMailAuthenticator (String username, String password)
	     {
	        super();
	        this.user = username;
	        this.pw = password;
	     }
	    public PasswordAuthentication getPasswordAuthentication()
	    {
	       return new PasswordAuthentication(user, pw);
	    }
	}
	
	public void sendMail(){
		//Recup infos clients
		this.client=(Client) maSession.getAttribute("client");
		try {
		System.out.println("!!!!!!!!!!!!!Creation pdf!!!!!!!!!!!!!!!!!!!!");
		creationPDF();
		System.out.println("!!!!!!!!!!!!!!!!!!! ENVOIE MAIL !!!!!!!!!!!!!!!!!!!!!!!!");
		// Creation protocole
	    Properties props = new Properties();
	    props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.setProperty("mail.smtp.starttls.enable", "true");
		System.out.println("!!!!!!!!!!!!! Debut creation session !!!!!!!!!!!!!!!!!!!");
		// 1 -> Création de la session
		Session session = Session.getInstance(props, new GMailAuthenticator("ecommerce44000@gmail.com", "adaming44000"));
		System.out.println("!!!!!!!!! Session cree !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		try {

			Message message = new MimeMessage(session);
			System.out.println("!!!!!!!! Message session cree !!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			message.setFrom(new InternetAddress("ecommerce44000@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(client.getEmail()));
			System.out.println("!!!!!!!! Destinataire et envoteur done !!!! !!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			message.setSubject("Recapitulatif de votre commande");

			System.out.println("!!!!!!!!!!!!!! Piece jointe !!!!!!!!!!!!!!!!!!!!!!!!!!");
			MimeMultipart mp = new MimeMultipart();
			MimeBodyPart mbp1 = new MimeBodyPart();
			MimeBodyPart mbp2 = new MimeBodyPart();
				mbp1.attachFile(new File("C:/Users/emegu/Documents/Test4.pdf"));
				mbp2.setText("Bonjour "+client.getNomClient()+"  !!!!!," +
					"\n\n Nous vous remercions de votre commande, veuillez trouvez en piece jointe le recapitulatif de vos achats !!"+
					"\n\n Cordialment! \n l'equipe ecommerce");
				/*ByteArrayDataSource ds = new ByteArrayDataSource(); 
				mbp1.setDataHandler(new DataHandler(ds));
				mbp1.setFileName("Test.pdf");*/
		    mp.addBodyPart(mbp1);
		    mp.addBodyPart(mbp2);
			message.setContent(mp);
			System.out.println("!!!!!!!! Debut transport !!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
		
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		}
		
	}
	

	
	public void creationPDF() throws DocumentException, IOException {
		String montantTotal=(String) maSession.getAttribute("montantTotal");
		//Recup liste commande
		listeLignes=(List<LigneCommande>) maSession.getAttribute("listeLignes");
				
		String chemin= "C:/Users/emegu/Documents/Test4.pdf"; 

	     //Rectangle pagesize = new Rectangle(216f, 720f);
		 Document document = new Document();

		 PdfWriter.getInstance(document, new FileOutputStream(chemin));

		 document.open();
		 //Image icon = Image.getInstance("C:/Users/emegu/Pictures/chauve.jpg");
		 document.add(new Paragraph("Merci de votre commande \n\n" ));
		 document.add(new Paragraph( "\nVoici le recapitulatif de vos achats:!\n\n\n\n\n\n\n\n\n\n"));
		     int i=0;
		     for(LigneCommande element:listeLignes){	
		        	i++;
					String s="Article n°"+i+": "+element.getProduit().getDesignation()+"\t \t prix:"+element.getPrix()+"€          ";
					Paragraph p=new Paragraph(s);
					p.setAlignment(Element.ALIGN_RIGHT);
				//	p.setAlignment(Element.);
					document.add(p);
					Image img = Image.getInstance(element.getProduit().getPhoto());
		        	img.scaleAbsolute(180,150);
		        	System.out.println("1-Informations positions img:"+ Float.toString(img.getAbsoluteY()));
		        	//img.setAbsolutePosition(img.getAbsoluteX()+100, img.getAbsoluteY()-500);
		        	System.out.println("2-Informations positions img:"+Float.toString(img.getAbsoluteY()));
		        	/*img.setAbsolutePosition(30f,
				            (200f*i)-100f);*/
					document.add(img);
					
		        }
		  document.add(new Paragraph("\n Votre montant est de "+montantTotal+" €"));

		  document.close();
		
		
	}
	
}