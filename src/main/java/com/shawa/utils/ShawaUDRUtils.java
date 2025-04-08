package com.shawa.utils;

import com.shawa.Ingredient;
import com.shawa.IngredientUDT;
import org.springframework.stereotype.Component;

@Component
public class ShawaUDRUtils {

    public static IngredientUDT toIngredientUDT(Ingredient ingredient) {
        return IngredientUDT.builder()
                .name(ingredient.getName())
                .type(ingredient.getType())
                .build();
    }
}
