package com.juaracoding.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.juaracoding.config.JwtTokenUtil;
import com.juaracoding.entity.UserEntity;
import com.juaracoding.service.MyUserDetailService;
import com.juaracoding.utility.FileUtility;

@RestController
public class UserController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil tokenUtil;

	@Autowired
	private MyUserDetailService uService;

	@GetMapping("/getalluser")
	public ResponseEntity<?> getAllUser() {
		return ResponseEntity.ok(uService.getAllUser());
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody UserEntity dataUser) throws Exception{
		authenticate(dataUser.getUsername(), dataUser.getPassword());
		
		final UserDetails userDetails = uService.loadUserByUsername(dataUser.getUsername());
		UserEntity user = uService.getUserByUsername(dataUser.getUsername());
		final String token = tokenUtil.generateToken(userDetails, user.getJeniskelamin(), user.getId());

		return ResponseEntity.ok(token);
	}
	
	
	@PostMapping("/register")
	public ResponseEntity<?> createUser(@RequestParam(value = "file") MultipartFile images, @ModelAttribute(value="data") String dataJSON) throws IOException{
		String filename = StringUtils.cleanPath(images.getOriginalFilename());
		
		String uploadDir="src/main/java/user-photos/";
		FileUtility.saveFile(uploadDir, filename, images);
		UserEntity user = new Gson().fromJson(dataJSON, UserEntity.class);
		user.setImage(filename) ;

		BCryptPasswordEncoder passwordEncode = new BCryptPasswordEncoder();
		user.setPassword(passwordEncode.encode(user.getPassword()));
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.APPLICATION_JSON)
				.body(this.uService.save(user));
	}
	
	@GetMapping("/gender")
	public List<UserEntity> searchCalonGender(@RequestHeader("Authorization") String header) {
		String gender = tokenUtil.getPayloadFromToken(header.substring(7),"jeniskelamin");
		String genderTarget="";
		if(gender.equalsIgnoreCase("male")) {
			genderTarget="female";
		}else {
			genderTarget="male";
		}
		return uService.findByGender(genderTarget);
	}
	
	@GetMapping(value = "/image/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getImageWithMediaType(@PathVariable String name) throws IOException{
		final InputStream in = getClass().getResourceAsStream("/user-photos/"+name);
		return IOUtils.toByteArray(in);
		
	}
	
	private void authenticate(String username, String password) throws Exception{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			// TODO: handle exception
			throw new Exception("USER_DISABLED",e);
		} catch (BadCredentialsException e) {
			// TODO: handle exception
			throw new Exception("INVALID_CREDENTIALS",e);
		}
	}
}
