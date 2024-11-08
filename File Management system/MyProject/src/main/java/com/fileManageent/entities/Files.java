package com.fileManageent.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Files {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	@Lob
	@Column(columnDefinition = "LONGBLOB")
	private byte[] UserFile;
	
	private int SecretCode;
	
	private double size;
	
	private LocalDate date;
	
	private String Filename;
	
	public String getFilename() {
		return Filename;
	}

	public void setFilename(String filename) {
		Filename = filename;
	}

	@ManyToOne
	private User user;
	
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getUserFile() {
		return UserFile;
	}

	public void setUserFile(byte[] userFile) {
		UserFile = userFile;
	}

	public int getSecretCode() {
		return SecretCode;
	}

	public void setSecretCode(int secretCode) {
		SecretCode = secretCode;
	}
	
}
