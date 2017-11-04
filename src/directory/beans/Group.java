package directory.beans;

import java.util.Collection;

public class Group {

	private int id;
	private String name;
	private Collection<Person> persons;

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
	
	public Group() {
		
	}
	
	public Group(int id, String name, Collection<Person> persons) {
		this.id = id;
		this.name = name;
		this.persons = persons;
	}
	
	public void init() {
		id = -1;
		name = "";
	}
	
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((persons == null) ? 0 : persons.hashCode());
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
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (persons == null) {
			if (other.persons != null)
				return false;
		} else if (!persons.equals(other.persons))
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Person> getPersons() {
		return persons;
	}

	public void setPersons(Collection<Person> persons) {
		this.persons = persons;
	}
}
