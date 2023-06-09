package com.james.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.james.demo.model.Alien;

public interface AlienRepo extends JpaRepository<Alien, Integer> {
	
	List<Alien> findByName(String name);
	List<Alien> findByAidGreaterThan(int aid);
	
	@Query("from Alien where tech=?1 order by name") //for custom query
	List<Alien>findByTechSorted(String tech);

}
