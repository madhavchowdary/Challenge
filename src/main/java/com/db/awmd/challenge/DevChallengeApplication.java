package com.db.awmd.challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.db.awmd.challenge.repository.TransferRepositoryImpl;

@SpringBootApplication
public class DevChallengeApplication {
	
	 @Autowired
	 TransferRepositoryImpl repository;

  public static void main(String[] args) {
    SpringApplication.run(DevChallengeApplication.class, args) ;
    
  }
}
