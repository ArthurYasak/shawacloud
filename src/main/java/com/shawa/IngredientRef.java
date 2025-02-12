package com.shawa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientRef {

    @Id  // todo: IngredientRefKey
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "ingredient_id", referencedColumnName = "id") // works without it  // todo: with composite key
    private Ingredient ingredient;

    @ManyToOne()
    @JoinColumn(name = "shawa_id", referencedColumnName = "id") // works without it // todo with composite key
    private Shawa shawa;

    private Integer weight;
}
