package com.shawa.data;

import org.springframework.data.repository.CrudRepository;
import com.shawa.Ingredient;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, String> {
}
