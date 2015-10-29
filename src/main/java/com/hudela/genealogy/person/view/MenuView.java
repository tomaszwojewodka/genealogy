package com.hudela.genealogy.person.view;

import com.hudela.genealogy.person.PersonRepository;
import com.hudela.genealogy.person.dto.Person;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean(name = "menuView")
@ViewScoped
public class MenuView implements Serializable {
	@EJB
	private PersonRepository repository;

	private Person selectedPerson;

	public MenuView() {
	}

	public void delete() {
		FacesContext.getCurrentInstance().addMessage("msg",
				new FacesMessage("Deleted " + selectedPerson.getFirstName() + " " + selectedPerson.getLastName()));
		repository.deletePerson(selectedPerson);
		selectedPerson = null;
	}

	public Person getSelectedPerson() {
		return selectedPerson;
	}

	public void setSelectedPerson(Person selectedPerson) {
		this.selectedPerson = selectedPerson;
	}
}
