package com.hudela.genealogy.person;

import com.hudela.genealogy.configuration.orientdb.OrientDBProvider;
import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientVertex;

import javax.ejb.EJB;
import javax.ejb.Stateless;

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
}
