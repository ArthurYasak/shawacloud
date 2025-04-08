package com.shawa.web;

import com.shawa.IngredientUDT;
import com.shawa.data.IngredientRepository;
import jakarta.annotation.Nullable;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import com.shawa.Ingredient;

import java.util.Optional;

@Component
public class IngredientByIdConverter implements Converter<String, IngredientUDT> {

    private IngredientRepository ingredientRepo;

    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    @Override
    public IngredientUDT convert(String id) { // todo: @NotNull
        Ingredient ingredient = ingredientRepo.findById(id).orElse(null);
        return IngredientUDT.builder()
                .name(ingredient.getName()) // todo: may be NPE
                .type(ingredient.getType())
                .build();
    }
}
