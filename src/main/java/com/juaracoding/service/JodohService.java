package com.juaracoding.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juaracoding.entity.Jodoh;
import com.juaracoding.repository.JodohRepository;

@Service
public class JodohService {

	@Autowired
	JodohRepository jodohRepo;
	
	public List<Jodoh> searchCalon(long iduser){
		return jodohRepo.findByTarget(iduser);
	}
	
	public String addCalon(Jodoh jodoh){
		jodohRepo.save(jodoh);
		return "Berhasil menambahkan calon";
	}
	
}
