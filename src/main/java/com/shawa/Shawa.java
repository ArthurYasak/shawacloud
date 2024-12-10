package com.shawa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Data
@ToString(exclude = "shawaOrder")
public class Shawa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // todo: without it to check is id not generate using sql script
    private Long id;

    private Date createdAt = new Date();

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    @NotNull
    @ManyToMany
    @JoinTable(
            name = "Ingredient_Ref",
            joinColumns = @JoinColumn(name = "shawa", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient", referencedColumnName = "id"))
    @Size(min=1, message="You must choose at least 1 ingredient")
    private List<Ingredient> ingredients = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "SHAWA_ORDER")
    private ShawaOrder shawaOrder;

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }
}
