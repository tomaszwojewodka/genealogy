package com.hudela.genealogy.person.view;

import com.hudela.genealogy.person.PersonRepository;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean(name = "personView")
@RequestScoped
public class PersonView implements Serializable {

	@EJB
	private PersonRepository personRepository;

	private String firstName;
	private String lastName;

	public PersonView() {
	}

	public void save() {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Welcome " + firstName + " " + lastName));
		personRepository.createPerson(firstName, lastName);
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
