package com.example.gestionbibliotheque;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GestionbibliothequeApplication {
    public static void main(String[] args){
        SpringApplication.run(GestionbibliothequeApplication.class, args);}
}
