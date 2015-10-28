package com.hudela.genealogy.person.view;

import com.hudela.genealogy.person.PersonRepository;
import com.hudela.genealogy.person.dto.Person;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "personView")
@RequestScoped
public class PersonView implements Serializable {

	@EJB
	private PersonRepository repository;

	private String firstName;
	private String lastName;

	public PersonView() {
	}

	public void save() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Added " + firstName + " " + lastName));
		repository.createPerson(firstName, lastName);
		firstName = null;
		lastName = null;
	}

	public List<Person> getPeople() {
		return repository.findAll();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
