package com.hudela.genealogy.person;

import com.hudela.genealogy.configuration.orientdb.OrientDBProvider;
import com.hudela.genealogy.person.dto.Person;
import com.orientechnologies.orient.core.sql.OCommandSQL;
import com.tinkerpop.blueprints.Vertex;
import com.tinkerpop.blueprints.impls.orient.OrientEdge;
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

	public void addParent(Person child, Person parent) {
		OrientGraph graph = provider.getGraph();
		try {
			OrientVertex childVertex = graph.getVertex(child.getId());
			OrientVertex parentVertex = graph.getVertex(parent.getId());
			OrientEdge isParent = graph.addEdge("class:IsParent", childVertex, parentVertex, "isParent");
			graph.commit();
		} catch (Exception e) {
			graph.rollback();
		} finally {
			graph.shutdown();
		}
	}

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

	public void deletePerson(Person person) {
		OrientGraph graph = provider.getGraph();
		try {
			OrientVertex vertex = graph.getVertex(person.getId());
			graph.removeVertex(vertex);
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
				result.add(new Person(v.getId().toString(), v.getProperty("firstName"), v.getProperty("lastName")));
			}
		} catch (Exception e) {
			graph.rollback();
		} finally {
			graph.shutdown();
		}
		return result;
	}

	public List<Person> findLike(String nameQuery) {
		OrientGraph graph = provider.getGraph();
		List<Person> result = new ArrayList<>();
		try {
			String query = "select from person where firstName like '%" + nameQuery + "%' or lastName like '%" + nameQuery + "%';";
			for (Vertex v : (Iterable<Vertex>) graph.command(
					new OCommandSQL(query)).execute()) {
				result.add(new Person(v.getId().toString(), v.getProperty("firstName"), v.getProperty("lastName")));
			}
		} catch (Exception e) {
			graph.rollback();
		} finally {
			graph.shutdown();
		}
		return result;
	}
}
