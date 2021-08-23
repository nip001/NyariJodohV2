package com.juaracoding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.juaracoding.config.JwtTokenUtil;
import com.juaracoding.entity.Jodoh;
import com.juaracoding.entity.UserEntity;
import com.juaracoding.service.JodohService;

@RestController
public class JodohController {

	@Autowired
	JodohService jodohService;
	
	@Autowired
	JwtTokenUtil tokenUtil;
	
	@GetMapping("/calon/")
	public List<Jodoh> searchCalonGender(@RequestHeader("Authorization") String header){
		String iduser = tokenUtil.getPayloadFromToken(header.substring(7), "iduser");
		return jodohService.searchCalon(Long.parseLong(iduser));
	}
	
	@PostMapping("/calon/save")
	public String addCalon(@RequestBody Jodoh jodoh, @RequestHeader("Authorization") String header) {
		String iduser = tokenUtil.getPayloadFromToken(header.substring(7), "iduser");
		UserEntity user = new UserEntity();
		user.setId(Long.parseLong(iduser));
		jodoh.setIduser(user);
		
		return jodohService.addCalon(jodoh);
	}
}
