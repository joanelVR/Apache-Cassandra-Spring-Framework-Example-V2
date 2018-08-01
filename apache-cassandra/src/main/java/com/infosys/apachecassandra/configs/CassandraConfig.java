package com.infosys.apachecassandra.configs;

import java.util.List;
import java.util.Arrays;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(basePackages="com.infosys.apachecassandra.repositories")
public class CassandraConfig extends AbstractCassandraConfiguration {

	//Name of the keyspace (or schema) to use
	public static final String KEYSPACE = "tester2";
	
	//Makes sure that a schema is created if non exists (Look at application.properties file)
	@Override
	public SchemaAction getSchemaAction() {
		return SchemaAction.CREATE_IF_NOT_EXISTS;
	}
	
	//Creates the Keyspace when spring runs (Optional: This can be created inside Cassandra 
	//                                                 and the method needs to be removed if a keyspace exists)
	@Override
	protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
		CreateKeyspaceSpecification specification = CreateKeyspaceSpecification.createKeyspace(KEYSPACE);
		return Arrays.asList(specification);
	}
	
	//Drops the Keyspace when spring stops (optional: mostly used for testing and can also be done inside cassandra)
	@Override
	protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
		return Arrays.asList(DropKeyspaceSpecification.dropKeyspace(KEYSPACE));
	}
	
	//Tells Cassandra where the entities are
	@Override
	public String[] getEntityBasePackages() {
		return new String[] {"com.infosys.apachecassandra.entities"};
	}
	
	@Override
	protected String getKeyspaceName() {
		// TODO Auto-generated method stub
		return KEYSPACE;
	}
}
