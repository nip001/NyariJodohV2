package com.juaracoding.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.juaracoding.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

//	@Query(value="SELECT * from user where username=?1 and nohp=?2",nativeQuery = true)
//	UserEntity findByLogin(String username, String nohp);

	@Query(value="SELECT * from user where jeniskelamin=?1",nativeQuery = true)
	List<UserEntity> findByGender(String gender);

	UserEntity findByUsername(String username);
}
