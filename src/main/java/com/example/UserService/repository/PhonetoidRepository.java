package com.example.UserService.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.example.UserService.model.Phonetoid;

public interface PhonetoidRepository extends CassandraRepository<Phonetoid, String> {
	
	List<Phonetoid> getByPhonenumber(String phonenumber);
	
}
