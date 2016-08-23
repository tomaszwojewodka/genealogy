package it.streamlining.genealogy.configuration.orientdb;

import com.tinkerpop.blueprints.impls.orient.OrientGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class OrientDBProvider {
	private static final String USER = "genealogy";
	private static final String PASSWORD = "Ra3fF3cGKG1AL7DFTmkr";

	private OrientGraphFactory factory;

	@PostConstruct
	public void startup() {
		factory = new OrientGraphFactory("remote:127.0.0.1/genealogy", USER, PASSWORD).setupPool(1, 10);
	}

	@PreDestroy
	public void shutdown() {
		factory.close();
	}

	public OrientGraph getGraph() {
		// TODO handle pool availability
		return factory.getTx();
	}
}
