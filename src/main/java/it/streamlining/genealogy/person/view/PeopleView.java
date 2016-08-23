package it.streamlining.genealogy.person.view;

import it.streamlining.genealogy.person.PersonRepository;
import it.streamlining.genealogy.person.dto.Person;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "peopleView")
@RequestScoped
public class PeopleView implements Serializable {
	@EJB
	private PersonRepository repository;

	public List<Person> getPeople() {
		return repository.findAll();
	}

	public List<Person> getPeopleLike(String name) {
		return repository.findLike(name);
	}
}
