package com.juaracoding.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.juaracoding.entity.Jodoh;
import com.juaracoding.entity.UserEntity;

public interface JodohRepository extends JpaRepository<Jodoh, Long>{
//	List<Jodoh> findByIdUser(long iduser);
	
	@Query(value="SELECT * FROM JODOH INNER JOIN user ON user.id = jodoh.idjodoh WHERE jodoh.iduser = ?1", nativeQuery = true)
	List<Jodoh> findByTarget(long iduser);
}
