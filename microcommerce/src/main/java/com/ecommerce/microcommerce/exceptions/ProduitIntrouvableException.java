package com.ecommerce.microcommerce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // définit le code de statut associé à l'exception
public class ProduitIntrouvableException extends RuntimeException {

	public ProduitIntrouvableException(String s) {
		super(s);
	}
}
