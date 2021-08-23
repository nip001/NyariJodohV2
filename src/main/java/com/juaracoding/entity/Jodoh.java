package com.juaracoding.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="jodoh")
public class Jodoh {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="iduser", referencedColumnName = "id")
	private UserEntity iduser;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="idjodoh", referencedColumnName = "id")
	private UserEntity idjodoh;
}
