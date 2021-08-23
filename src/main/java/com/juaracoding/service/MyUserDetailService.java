package com.juaracoding.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.juaracoding.entity.UserEntity;
import com.juaracoding.repository.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService{

	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub	// TODO Auto-generated method stub
		UserEntity user = userRepo.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("User tidak ditemukan dengan username : "+username);
		}
		
		return new User(user.getUsername(),user.getPassword(), new ArrayList<>());
	}
	
	public List<UserEntity> getAllUser(){
		return userRepo.findAll();
	}

	public UserEntity getUserByUsername(String username){
		return userRepo.findByUsername(username);
	}
	
	public String save(UserEntity user){
		userRepo.save(user);
		return "Berhasil menambahkan";
	}
	
	public List<UserEntity> findByGender(String gender){
		return userRepo.findByGender(gender);
	}

}
