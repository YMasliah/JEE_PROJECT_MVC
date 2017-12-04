package directory.beans;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * faut lire ca (7 ->7.3) 
 * http://docs.spring.io/spring/docs/4.3.11.RELEASE/spring-framework-reference/html/beans.html
 * 
 * https://docs.spring.io/spring/docs/4.3.11.RELEASE/spring-framework-reference/html/beans.html#beans-autowired-annotation-qualifiers
 * https://docs.spring.io/spring/docs/4.3.11.RELEASE/spring-framework-reference/html/beans.html#beans-annotation-config
 * 
 * demander a massat si c'est mieu de modifier ici
 * ou directement dans le fichier XML
 * persso je ne vois pas reelement la difference. 
 * peut etre le fichier xml ne necessite pas de recompiler ?
 * exemple : tp1 avant les callback
 * Edit : avec la classe @config et les @value sa doit etre la vrai methode
 */
public class Group {

    @NotNull(message = "Un identifiant est obligatoire")
    @Min(value = 1, message = "Le prix est trop bas")
	private Long id;
    
    @NotNull(message = "Un nom est obligatoire")
    @Size(min = 1, max = 100, message = "Entre 1 et 200 caract�res")
	private String name;
	
	public Group() {
		
	}
	
	public Group(long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public void init() {
		id = -1L;
		name = "";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Group other = (Group) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
    
}
