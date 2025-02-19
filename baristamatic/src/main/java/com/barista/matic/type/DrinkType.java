package com.barista.matic.type;

import com.barista.matic.dto.RecipeDto;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public enum DrinkType {
  COFFEE(
      "Coffee",
      new RecipeDto(IngredientType.COFFEE, 3),
      new RecipeDto(IngredientType.SUGAR, 1),
      new RecipeDto(IngredientType.CREAM, 1)),
  DECAF_COFFEE(
      "Decaf Coffee",
      new RecipeDto(IngredientType.DECAF_COFFEE, 3),
      new RecipeDto(IngredientType.SUGAR, 1),
      new RecipeDto(IngredientType.CREAM, 1)),
  CAFFE_LATTE(
      "Caffe Latte",
      new RecipeDto(IngredientType.ESPRESSO, 2),
      new RecipeDto(IngredientType.STEAMED_MILK, 1)),
  CAFFE_AMERICANO("Caffe Americano", new RecipeDto(IngredientType.ESPRESSO, 3)),
  CAFFE_MOCHA(
      "Caffe Mocha",
      new RecipeDto(IngredientType.ESPRESSO, 1),
      new RecipeDto(IngredientType.COCOA, 1),
      new RecipeDto(IngredientType.STEAMED_MILK, 1),
      new RecipeDto(IngredientType.WHIPPED_CREAM, 1)),
  CAPPUCCINO(
      "Cappuccino",
      new RecipeDto(IngredientType.ESPRESSO, 2),
      new RecipeDto(IngredientType.STEAMED_MILK, 1),
      new RecipeDto(IngredientType.FOAMED_MILK, 1));
  final String drinkName;
  final RecipeDto[] recipeIngredient;
  final BigDecimal cost;

  DrinkType(String drinkName, RecipeDto... recipeIngredient) {
    this.drinkName = drinkName;
    this.recipeIngredient = recipeIngredient;
    this.cost =
        Arrays.stream(recipeIngredient)
            .map(
                ingredient ->
                    BigDecimal.valueOf(ingredient.quantity())
                        .multiply(ingredient.ingredientType().getUnitCost()))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public BigDecimal getCost() {
    return cost;
  }

  public String getDrinkName() {
    return drinkName;
  }

  public RecipeDto[] getRecipeIngredients() {
    return recipeIngredient;
  }

  public static List<DrinkType> getSorted() {
    return Arrays.stream(values())
        .sorted((drink1, drink2) -> drink1.drinkName.compareTo(drink2.drinkName))
        .toList();
  }
}
