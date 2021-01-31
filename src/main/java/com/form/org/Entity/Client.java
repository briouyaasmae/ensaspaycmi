package com.form.org.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

public class Client {
	@Id
    @Field(value = "identifiant")
	public String identifiant;
    @Field(value = "nom")
	public String nom;
    @Field(value = "prenom")
	public String prenom;
    @Indexed(unique = true)
    @Field(value = "phone")
	public String phone;
    @Field(value = "solde")
	public double solde;
	public String getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	public Client(String identifiant, String nom, String prenom, String phone, double solde) {
		super();
		this.identifiant = identifiant;
		this.nom = nom;
		this.prenom = prenom;
		this.phone = phone;
		this.solde = solde;
	}
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
