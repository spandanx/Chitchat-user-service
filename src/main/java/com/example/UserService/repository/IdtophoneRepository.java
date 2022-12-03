package com.example.UserService.repository;

import java.util.Optional;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.example.UserService.model.Idtophone;

public interface IdtophoneRepository extends CassandraRepository<Idtophone, String> {
	
	Optional<Idtophone> findById(String id);
	
}
