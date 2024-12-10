package com.shawa;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class IngredientRef {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ingredient")
    public Ingredient ingredient;

    @ManyToOne
    @JoinColumn(name = "shawa")
    public Shawa shawa;
}
