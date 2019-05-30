package com.ecommerce.microcommerce.dao;

import com.ecommerce.microcommerce.model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/*
 * Création de l'implémentation Dao
 * pour Communication avec la base de données
 * - Méthodes déjà autogénéré par JpaRepository<param1, param2> 
 * - param1 : l'entité concerné
 * - param2 : le type de son id
 * @Repository : Auto-génération  des opérations CRUD (Create, Read, Upadate, Delete)
 */

//Spring Data peut générer la requête SQL automatiquement en partant du nom de la méthode

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
	
	Product findById(int id );
	// Retourner une liste de produits dont le prix est supérieur à un chiffre donné
	// findBy = indique que l'opération à exécuter est un SELECT
	// Prix = fournit le nom de la propriété sur laquelle le SELECT s'applique
	// GreaterThan = définit une condition "plus grand que"
	// prixLimit = paramètre qui définit la valeur à appliquer à la condition
	// requête SQL => SELECT * FROM product where prix > [un chiffre]
	
	List<Product> findByPrixGreaterThan(int prixLimit);
	
	// En passant par une requête en JPQL manuelle @Query(requête) associé à une méthode qui va retourner une liste de produit
	@Query("SELECT id, nom, prix FROM Product p WHERE p.prix > :prixLimit")
	List<Product> chercherUnProduit(@Param("prixLimit") int prix);
}
