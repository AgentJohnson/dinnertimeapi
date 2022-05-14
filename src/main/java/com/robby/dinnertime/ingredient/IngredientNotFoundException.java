package com.robby.dinnertime.ingredient;

class IngredientNotFoundException extends RuntimeException {

	  IngredientNotFoundException(Long id) {
	    super("Could not find employee " + id);
	  }
	}
