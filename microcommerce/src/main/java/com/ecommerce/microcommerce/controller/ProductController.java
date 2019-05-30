package com.ecommerce.microcommerce.controller;

import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.exceptions.ProduitIntrouvableException;
import com.ecommerce.microcommerce.model.Product;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


/*
 * - Le controlleur s'occupera de répondre aux requêtes
 *   CRUD du microservice et de faire les opérations necessaires.
 * - L'annotation @RestController permet d'indiquer a spring que 
 *   c'est dans ce controlleur que se trouvera nos méthodes (la réponse
 *   de la requête sera au format JSON)
 * - L'annotation @PathVariable indique à spring que la méthode doit recevoir un paramètre 
 * - L'annotation @RequestBody
 */

@Api(description="Gestion des produits.")
@RestController 
public class ProductController {
 
	@Autowired
	private ProductDao productDao;
	
	/**
	 * listProduits() retourne tous les produits
	 * @return productDao
	 */
	@ApiOperation(value="Récupèrer tous les produits.")
	@GetMapping(value = "/Produits")
	public List<Product> listProduits() {
		return productDao.findAll();
	}
	
	/**
	 * - Permet d'aller chercher un produit par son id
	 * - L'annotation @GetMapping permet d'indiquer que c'est la méthode afficherUnProduit()
	 *   répond à l'URI Produits/{id} (appelé uniquement pour une requête de type Get
	 * - Générer une exception lorsque le produit n'est pas trouvé
	 * @param id 
	 * @return product
	 */
	@ApiOperation(value="Récupèrer un produit selon son {id}")
	@GetMapping(value="/Produits/{id}")
	public Product afficherUnProduit(@PathVariable int id) {
		
		/* 
		 * - Retourner le produit trouvé dans la base de données en fonction de l'id reçu
		 * - On retourne le produit
		 */
		Product produit =  productDao.findById(id);
		
		if(produit==null) throw new ProduitIntrouvableException("Le produit avec l'id " + id + " est INTROUVABLE!!!");
		
		return produit;
	}
	
	/**
	 * ajouterProduit() Permet de créer un produit
	 * - ResponseEntity : classe qui hérite de la classe HttpEntity qui est responsable de construire la réponse HTTP
	 *   Elle permet de définir le status de réponse.
	 * - L'annotation @valid indique au controlleur que le produit récu de l'utilisateur est à valider
	 * @param product
	 */
	@ApiOperation(value="Ajouter un produit")
	@PostMapping(value = "/Produits")
	public ResponseEntity<Void> ajouterProduit(@Valid @RequestBody Product product) {
		Product product1 = productDao.save(product);
		
		// Test produit bien ajouté 
		
		// Si le produit n'a pas été ajouté (appel de build afin de construire la réponse et l'envoyer)
		if (product == null) {
			return ResponseEntity.noContent().build();
		}
		/* Si le produit a été ajouté
		 * en plus du code 201 on ajoute l'URI vers cette nouvelle ressource crééée afin
		 * d'être conforme avec le protocole HTTP
		 * Constitution de l'URL dun nouveau produit à rajouter
		 * ServletUriComponentsBuilder : Crée un lien à partir d'une requêt
		 * - fromCurrentRequest() : Méthode qui permet de créer à partir de la requête courante
		 * - path("/{id}") : on rajoute à l'URI l'id du produit ajouté
		 * - buildAndExpand("product1.getID") : remplace l'id par son contenu en lui passant l'id nouvellement créé.
		 * - toUri() : transformation en URI
		 */
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(product1.getId())
				.toUri();
		
		/* Retourner ResonseEntity avec le code created() qui prend en paramètre une Uri
		 * Build pour construire la	réponse
		 */
		return ResponseEntity.created(location).build();
	}
	
	/**
	 * testeDeRequetes() cherche tous les produits supérieur à un prix limite
	 * @param prixLimit : le prix minimal
	 * @return une liste de produit
	 */
	//@GetMapping(value="/Test/Produits/{prixLimit}")
	//public List<Product> testeDeRequetes(@PathVariable int prixLimit) {
	//	return productDao.findByPrixGreaterThan(prixLimit);
	//}
	
	/**
	 * testeDeRequetes() cherche tous les produits supérieur à un prix limite
	 * @param prixLimit : le prix minimal
	 * @return une liste de produit
	 */
	@GetMapping(value="/Test/Produits/{prixLimit}")
	public List<Product> testeDeRequetes(@PathVariable int prixLimit) {
		return productDao.chercherUnProduit(prixLimit);
	}
	
	/**
	 * suprimerProduit() supprime le produit d'un id passé à paramètre
	 * @param id
	 * @return void
	 */
	@ApiOperation(value="Suprimer un produit selon son {id}")
	@DeleteMapping (value="/Produits/{id}")
	public void supprimerProduit(@PathVariable int id) {
		productDao.delete(id);
	}
	
	/**
	 * updateProduit() met à jour un produit
	 * @param product
	 */
	@ApiOperation(value="Modifier un produit")
	@PutMapping (value="/Produits")
	public void updateProduit(@RequestBody Product product) {
		productDao.save(product);
	}
}
