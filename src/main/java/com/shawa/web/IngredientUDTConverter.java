package com.shawa.web;

import com.shawa.Ingredient;
import com.shawa.IngredientUDT;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class IngredientUDTConverter implements Converter<IngredientUDT, Ingredient> {

    @Override
    public Ingredient convert(IngredientUDT ingredientUDT) {
        return Ingredient.builder()
                .name(ingredientUDT.getName())
                .type(ingredientUDT.getType())
                .build();
    }
}
