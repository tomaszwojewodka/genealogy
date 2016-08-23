package it.streamlining.genealogy.person.view;

import it.streamlining.genealogy.person.PersonRepository;
import it.streamlining.genealogy.person.dto.Person;

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
	private Person selectedParent;

	public MenuView() {
	}

	public void addParent() {
		repository.addParent(selectedPerson, selectedParent);
		addMessage(String.format("Added parent %s to %s", selectedParent, selectedPerson));
		selectedParent = null;
		selectedPerson = null;
	}

	public void delete() {
		repository.deletePerson(selectedPerson);
		addMessage("Deleted " + selectedPerson.getFirstName() + " " + selectedPerson.getLastName());
		selectedPerson = null;
	}

	private void addMessage(String summary) {
		FacesContext.getCurrentInstance().addMessage("msg",
				new FacesMessage(summary));
	}

	public Person getSelectedPerson() {
		return selectedPerson;
	}

	public void setSelectedPerson(Person selectedPerson) {
		this.selectedPerson = selectedPerson;
	}

	public Person getSelectedParent() {
		return selectedParent;
	}

	public void setSelectedParent(Person selectedParent) {
		this.selectedParent = selectedParent;
	}
}
