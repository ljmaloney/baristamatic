package com.barista.matic;

import com.barista.matic.type.IngredientType;
import java.util.Arrays;
import java.util.HashMap;

public class IngredientInventoryMap extends HashMap<IngredientType, Integer> {

  public static final int DEFAULT_RESTOCK_QUANTITY = 10;

  public IngredientInventoryMap(IngredientType... ingredients) {
    super();
    stock(DEFAULT_RESTOCK_QUANTITY, ingredients);
  }

  public void decrementQuantity(IngredientType ingredient, int quantity) {
    int originalQuantity = get(ingredient);
    if (quantity > originalQuantity) {
      throw new IllegalArgumentException(
          "Quantity must be less than or equal to original quantity");
    }
    put(ingredient, originalQuantity - quantity);
  }

  public boolean isQuantityUnAvailable(IngredientType ingredient, int quantity) {
    return isQuantityEmpty(ingredient) || get(ingredient) < quantity;
  }

  public boolean isQuantityEmpty(IngredientType ingredient) {
    return get(ingredient) == null || 0 == get(ingredient);
  }

  public void restockAll() {
    restockAll(DEFAULT_RESTOCK_QUANTITY);
  }

  public void restockAll(Integer quantity) {
    keySet().forEach(key -> put(key, quantity));
  }

  public void stock(int quantity, IngredientType... ingredients) {
    Arrays.stream(ingredients).forEach(ingredient -> put(ingredient, quantity));
  }
}
