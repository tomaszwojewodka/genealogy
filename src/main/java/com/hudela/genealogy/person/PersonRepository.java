package com.hudela.genealogy.person;

import com.hudela.genealogy.configuration.orientdb.OrientDBProvider;
import com.hudela.genealogy.person.dto.Person;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class PersonRepository {
	@EJB
	private OrientDBProvider provider;

	public void createPerson(String firstName, String lastName) {
		OrientGraph graph = provider.getGraph();
		try {
			OrientVertex person = graph.addVertex("class:Person");
			person.setProperty("firstName", firstName);
			person.setProperty("lastName", lastName);

			graph.commit();
		} catch (Exception e) {
			graph.rollback();
		} finally {
			graph.shutdown();
		}
	}

	public List<Person> findAll() {
		OrientGraph graph = provider.getGraph();
		List<Person> result = new ArrayList<>();
		try {
			for (Vertex v : (Iterable<Vertex>) graph.command(
					new OCommandSQL("select from Person;")).execute()) {
				result.add(new Person(v.getProperty("firstName"), v.getProperty("lastName")));
			}
		} catch (Exception e) {
			graph.rollback();
		} finally {
			graph.shutdown();
		}
		return result;
	}
}
