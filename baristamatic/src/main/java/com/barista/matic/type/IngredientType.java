package com.barista.matic.type;

import java.math.BigDecimal;
import java.util.Arrays;

public enum IngredientType {
  COFFEE("Coffee", BigDecimal.valueOf(.75d)),
  DECAF_COFFEE("Decalf Coffee", BigDecimal.valueOf(.75d)),
  SUGAR("Sugar", BigDecimal.valueOf(.25d)),
  CREAM("Cream", BigDecimal.valueOf(.25d)),
  STEAMED_MILK("Steamed Milk", BigDecimal.valueOf(.35d)),
  FOAMED_MILK("Foamed Milk", BigDecimal.valueOf(.35d)),
  ESPRESSO("Espresso", BigDecimal.valueOf(1.10d)),
  COCOA("Cocoa", BigDecimal.valueOf(.90d)),
  WHIPPED_CREAM("Whipped Cream", BigDecimal.valueOf(1d));
  final String ingredientName;
  final BigDecimal unitCost;

  IngredientType(String ingredientName, BigDecimal unitCost) {
    this.ingredientName = ingredientName;
    this.unitCost = unitCost;
  }

  public String getIngredientName() {
    return ingredientName;
  }

  BigDecimal getUnitCost() {
    return unitCost;
  }

  public static IngredientType[] getSorted() {
    return Arrays.stream(values())
        .sorted((type1, type2) -> type1.ingredientName.compareTo(type2.ingredientName))
        .toArray(IngredientType[]::new);
  }
}
