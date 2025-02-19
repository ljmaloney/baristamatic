package com.barista.matic.type;

import com.barista.matic.dto.IngredientDto;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public enum DrinkType {
  COFFEE(
      "Coffee",
      new IngredientDto(IngredientType.COFFEE, 3),
      new IngredientDto(IngredientType.SUGAR, 1),
      new IngredientDto(IngredientType.CREAM, 1)),
  DECAF_COFFEE(
      "Decaf Coffee",
      new IngredientDto(IngredientType.DECAF_COFFEE, 3),
      new IngredientDto(IngredientType.SUGAR, 1),
      new IngredientDto(IngredientType.CREAM, 1)),
  CAFFE_LATTE(
      "Caffe Latte",
      new IngredientDto(IngredientType.ESPRESSO, 2),
      new IngredientDto(IngredientType.STEAMED_MILK, 1)),
  CAFFE_AMERICANO("Caffe Americano",
          new IngredientDto(IngredientType.ESPRESSO, 3)),
  CAFFE_MOCHA(
      "Caffe Mocha",
      new IngredientDto(IngredientType.ESPRESSO, 1),
      new IngredientDto(IngredientType.COCOA, 1),
      new IngredientDto(IngredientType.STEAMED_MILK, 1),
      new IngredientDto(IngredientType.WHIPPED_CREAM, 1)),
  CAPPUCCINO(
      "Cappuccino",
      new IngredientDto(IngredientType.ESPRESSO, 2),
      new IngredientDto(IngredientType.STEAMED_MILK, 1),
      new IngredientDto(IngredientType.FOAMED_MILK, 1));
  final String drinkName;
  final IngredientDto[] recipeIngredient;
  final BigDecimal cost;

  DrinkType(String drinkName, IngredientDto... recipeIngredient) {
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

  public IngredientDto[] getRecipeIngredients() {
    return recipeIngredient;
  }

  public static List<DrinkType> getSorted() {
    return Arrays.stream(values())
        .sorted((drink1, drink2) -> drink1.drinkName.compareTo(drink2.drinkName))
        .toList();
  }
}
