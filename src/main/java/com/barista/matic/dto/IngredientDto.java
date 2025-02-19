package com.barista.matic.dto;

import com.barista.matic.type.IngredientType;

public record IngredientDto(IngredientType ingredientType, int quantity) {}
