package directory.beans;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import springapp.web.Mail;

/**
 * il faut faire la verif du groupid et id dans le validator
 * le validateur d'url marche pas, flemme de check
 * 
 * bon la date elle me fait chier, j'ai presque reussi a faire marcher. le mieu c de faire en String je crois
 * ou pas
 * @author masliah yann
 *
 */
public class Person {

    @NotNull(message = "Un identifiant est obligatoire")
	private Long id;
    
    @NotNull(message = "Un identifiant est obligatoire")
    @Size(min = 1, message = "Le nom est obligatoire")
	private String lastName;
    
    @NotNull(message = "Un identifiant est obligatoire")
    @Size(min = 1, message = "Le nom est obligatoire")
	private String firstName;
    
	// un string pour debuter mais apres faut changer
    @DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date birthDate;
    
    @Override
	public String toString() {
		return "Person [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", birthDate=" + birthDate
				+ ", mail=" + mail + ", webSite=" + webSite + ", password=" + password + ", groupId=" + groupId + "]";
	}

	@Mail
	private String mail;

	//@URL
	private String webSite;
    
    @NotNull(message = "Un identifiant est obligatoire")
    @Size(min = 1, message = "Le nom est obligatoire")
	private String password;
	// un int ou un Group ?
    
    @Min(value = 1, message = "Le prix est trop bas")
	private Long groupId;

	public Person() {

	}

	public Person(long id, String lastName, String firstName, Date birthDate, String mail, String webSite,
			String password, long groupId) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.birthDate = birthDate;
		this.mail = mail;
		this.webSite = webSite;
		this.password = password;
		this.groupId = groupId;
	}

	/**
	 * renvoi toute les valeurs
	 * 
	 * @return
	 */
	public Object[] getAll() {
		return null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public void init() {
		id = -1L;
		lastName = "";
		firstName = "";
		//birthDate = "";
		mail = "";
		webSite = "";
		password = "";
		groupId = -1L;
	}
}
