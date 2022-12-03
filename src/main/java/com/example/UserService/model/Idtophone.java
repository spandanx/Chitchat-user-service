package com.example.UserService.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Idtophone {
	
	@PrimaryKey
	private String id;
	
	private String phonenumber;
}
