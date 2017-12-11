package directory.beans;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Master 2 ISL 2017/2018
 * 
 * Java Bean de la table group de la base de donnée
 * 
 * @author MASLIAH Yann
 * @author TIGRARA Redouane
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
