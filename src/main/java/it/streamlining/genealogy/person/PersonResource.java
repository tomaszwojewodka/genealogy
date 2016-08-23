package it.streamlining.genealogy.person;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Stateless
@Path("person")
public class PersonResource {
	@EJB
	private PersonRepository personRepository;

	// FIXME not a real REST service as only GET is used for all the actions for the sake of simplicity
	@GET
	@Path("create")
	public String createPerson() {
		personRepository.createPerson("Tester2", "Testowalski2");
		return "";
	}
}
