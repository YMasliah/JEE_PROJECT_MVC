package directory.beans;

import java.util.Date;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import springapp.web.Mail;
//import springapp.web.Group;

/**
 * @author masliah yann
 * 
 *         il faut faire la verif du groupid et id dans le validator le
 *         validateur d'url marche pas, flemme de check
 * 
 *         bon la date elle me fait chier, j'ai presque reussi a faire marcher.
 *         le mieu c de faire en String je crois ou pas
 */
public class Person {

	@NotNull(message = "Un identifiant est obligatoire")
	private Long id;

	@NotNull(message = "** Le nom est obligatoire.")
	@Size(min = 1, message = "** Le nom n'est pas valide (Minimum 2 caractères)")
	private String lastName;

	@NotNull(message = "** le prénom est obligatoire")
	@Size(min = 1, message = "** Le prénom n'est pas valide (Minimum 2 caractères)")
	private String firstName;

	@Mail
	private String email;

	// @URL
	private String webSite;

	// un string pour debuter mais apres faut changer
	// faut definir un format, soit yyyy-MM-dd soit l'autre
	// par default sur la base de donnée c'est yyyy-MM-dd
	@Past (message="Only the past is valid")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;


	private String password;
	// un int ou un Group ?


//	@Group
//	@NotNull(message = "Un nom VALIDE du group est obligatoire")
	private Long groupId;

	public Person() {

	}

	public Person(long id, String lastName, String firstName, String mail, String webSite, Date birthDate,
			String password, Long groupId) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = mail;
		this.webSite = webSite;
		this.birthDate = birthDate;
		this.password = password;
		this.groupId = groupId;
	}

	public void init() {
		id = -1L;
		lastName = "";
		firstName = "";
		// birthDate = "";
		email = "";
		webSite = "";
		password = "";
		groupId = null;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", lastName=" + lastName + ", firstName=" + firstName + ", email=" + email
				+ ", webSite=" + webSite + ", birthDate=" + birthDate + ", password=" + password + ", groupId="
				+ groupId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((webSite == null) ? 0 : webSite.hashCode());
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
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (groupId == null) {
			if (other.groupId != null)
				return false;
		} else if (!groupId.equals(other.groupId))
			return false;
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
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (webSite == null) {
			if (other.webSite != null)
				return false;
		} else if (!webSite.equals(other.webSite))
			return false;
		return true;
	}

//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Person other = (Person) obj;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		if (lastName == null) {
//			if (other.lastName != null)
//				return false;
//		} else if (!lastName.equals(other.lastName))
//			return false;
//		return true;
//	}

}
