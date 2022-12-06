package com.example.UserService.controller;

import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.UserService.model.Idtophone;
import com.example.UserService.model.Phonetoid;
import com.example.UserService.repository.IdtophoneRepository;
import com.example.UserService.repository.PhonetoidRepository;

@RestController
@RequestMapping("/api")
public class CassandraController {

	@Autowired
	private IdtophoneRepository idtophoneRepository;
	
	@Autowired
	private PhonetoidRepository phonetoidRepository;
	
	@GetMapping("/id-to-phone")
	public ResponseEntity<List<Idtophone>> getPhoneById(@RequestParam(required = false) String id){
		List<Idtophone> list = new ArrayList<Idtophone>();
//		list = idtophoneRepository.findById(id).stream().toList();
		list = idtophoneRepository.findById(id).stream().collect(Collectors.toList());
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@PostMapping("/id-to-phone")
	public ResponseEntity<Idtophone> createPhone(@RequestBody Idtophone idToPhone){
		idtophoneRepository.save(idToPhone);
		return new ResponseEntity<>(idToPhone, HttpStatus.CREATED);
	}
	
	@GetMapping("/phone-to-id")
	public ResponseEntity<List<Phonetoid>> getIdByPhone(@RequestParam(required = false) String phonenumber){
		List<Phonetoid> list = new ArrayList<Phonetoid>();
//		Optional<Phonetoid> sbc = phonetoidRepository.findById(phonenumber);
		list = phonetoidRepository.findById(phonenumber).stream().collect(Collectors.toList());
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@PostMapping("/phone-to-id")
	public ResponseEntity<Phonetoid> createPhone(@RequestBody Phonetoid phoneToId){
		phonetoidRepository.save(phoneToId);
		return new ResponseEntity<>(phoneToId, HttpStatus.CREATED);
	}
	
}
