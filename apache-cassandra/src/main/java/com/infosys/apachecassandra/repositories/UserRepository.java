package com.infosys.apachecassandra.repositories;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

import com.infosys.apachecassandra.entities.User;

@Repository
public interface UserRepository extends CassandraRepository<User, Long>{

	/* 
	 * Because There is only one primary key (id), Cassandra can't filter by anything else other than the primary 
	 * key. In order to get around this, we can allow filtering (as shown below), but it's not recommended as it 
	 * can produce unpredicted behavior. 
	 * 
	 * ALLOW FILTERING requires the whole table to be read and then goes on to filter out the  invalid records. 
	 * Cassandra’s read speed comes from querying the partition and clustering columns and using an index to locate 
	 * the partition data allowing them to be read without needing to read through the whole table.
	 * */
	@Query(allowFiltering = true)
	public User findUserByUsername(String username);
	
	@Query(allowFiltering = true)
	public User findUserByUsernameAndPassword(String username, String password);
}
