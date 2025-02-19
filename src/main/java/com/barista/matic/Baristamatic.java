package com.barista.matic;

import com.barista.matic.type.DrinkType;
import com.barista.matic.type.IngredientType;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Baristamatic {
  private final IngredientInventoryMap ingredientInventoryMap;
  private final List<DrinkType> drinkTypes;

  public Baristamatic() {
    ingredientInventoryMap = new IngredientInventoryMap(IngredientType.values());
    drinkTypes = DrinkType.getSorted();
  }

  void dispenseDrink(DrinkType drink) {
    Arrays.stream(drink.getRecipeIngredients())
        .forEach(
            recipeDto ->
                ingredientInventoryMap.decrementQuantity(
                    recipeDto.ingredientType(), recipeDto.quantity()));
    System.out.printf("Dispensing: %s", drink.getDrinkName()).println();
  }

  void outputInventory() {
    System.out.println("Inventory:");
    Arrays.stream(IngredientType.getSorted())
        .forEach(
            ingredientType ->
                System.out
                    .printf(
                        "%s,%d",
                        ingredientType.getIngredientName(),
                        ingredientInventoryMap.get(ingredientType))
                    .println());
  }

  void outputMenu() {
    System.out.println("Menu:");
    int itemNumber = 1;
    for (DrinkType drink : drinkTypes) {
      System.out
          .printf(
              "%d,%s,$%2.2f,%b",
              itemNumber++, drink.getDrinkName(), drink.getCost(), isAvailable(drink))
          .println();
    }
  }

  boolean isAvailable(DrinkType drink) {
    return Arrays.stream(drink.getRecipeIngredients())
        .filter(ingredientDto -> ingredientInventoryMap.isQuantityUnAvailable(ingredientDto.ingredientType(), ingredientDto.quantity()))
        .findFirst()
        .isEmpty();
  }

  static int getAsInteger(String input) {
    return Pattern.matches("\\d+", input) ? Integer.parseInt(input) : 0;
  }

  public static void main(String[] args) {
    Baristamatic baristamatic = new Baristamatic();
    Scanner sc = new Scanner(System.in);

    do {
      baristamatic.outputInventory();
      System.out.println();
      baristamatic.outputMenu();
      String input = sc.nextLine();

      if ("q".equals(input) || "Q".equals(input)) {
        break;
      } else if ("r".equals(input) || "R".equals(input)) {
        baristamatic.ingredientInventoryMap.restockAll();
      } else if (getAsInteger(input) > 0 && getAsInteger(input) <= baristamatic.drinkTypes.size()) {
        DrinkType drink = baristamatic.drinkTypes.get(getAsInteger(input) - 1);
        if (baristamatic.isAvailable(drink)) {
          baristamatic.dispenseDrink(drink);
        } else {
          System.out.printf("Out of stock: %s", drink.getDrinkName()).println();
        }
      } else {
        System.out.printf("Invalid selection: %s", input).println();
      }
    } while (true);
  }
}
