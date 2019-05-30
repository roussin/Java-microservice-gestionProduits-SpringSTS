package com.ecommerce.microcommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

// @JsonIgnoreProperties(value={"prixAchat", "id", "etc"}) : pour regrouper et cacher plusieurs champs à la fois
// Déclaration de ce Bean comme étant une entité
@Entity
public class Product {

	// Attributs ----------------------------------
	// L'id est la clef unique et auto incrémenté
	@Id
	@GeneratedValue
	private int id;
	@Length(min=3, max=20, message="Nom trop long ou trop court") // accepte en argument un minimum et un maximum, et vérifie si la longueur de la chaîne est conforme
	private String nom;
	@Min(value=1, message="Le prix doit être supérieur à 0") // définit la valeur minimale
	private int prix;
	// @JsonIgnore n'afficher la valeur de prixAchat
	// @JsonIgnore
	private int prixAchat;
	// --------------------------------------------
	// Constructeur par défaut --------------------
	public Product() {}
	// --------------------------------------------
	// Constructeur pour les testes ---------------
	public Product(int id, String nom, int prix, int prixAchat) {
		super();
		this.id = id;
		this.nom = nom;
		this.prix = prix;
		this.prixAchat = prixAchat;
	}
	// --------------------------------------------
	// Getters & Setters --------------------------
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public int getPrix() {
		return prix;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	public int getPrixAchat() {
		return prixAchat;
	}
	public void setPrixAchat(int prixAchat) {
		this.prixAchat = prixAchat;
	}
	// --------------------------------------------
	// Pour la sérialisation
	@Override
	public String toString() {
		return "Product {"+
			"id=" + id +
			", nom='" + nom + '\''+
			", prix=" + prix + 
			", prixAchat=" + prixAchat +
			'}';
	}
	// -------------------------------------------- 
}
