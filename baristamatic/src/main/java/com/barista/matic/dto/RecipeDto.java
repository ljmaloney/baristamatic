package com.barista.matic.dto;

import com.barista.matic.type.IngredientType;

public record RecipeDto(IngredientType ingredientType, int quantity) {}
