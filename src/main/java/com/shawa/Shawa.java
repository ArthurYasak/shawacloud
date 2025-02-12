package com.shawa;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.Set;

@Entity
@Table
@Data
@ToString(exclude = {"shawaOrder"/*, "ingredients"*/})
public class Shawa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // todo: without it to check is id not generate using sql script
    private Long id;

    private Date createdAt = new Date();

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

//    @ManyToMany(/*mappedBy = "shawas",*/ targetEntity = Ingredient.class)
//    @JoinTable(
//            name = "Ingredient_Ref",
//            joinColumns = @JoinColumn(name = "shawa", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "ingredient", referencedColumnName = "id"))
//    private Set<Ingredient> ingredients/* = new ArrayList<>()*/; // When using a List, Hibernate removes all entities from the junction table and inserts the remaining ones. This can cause performance issues. We can easily avoid this problem by using Set.

    @NotNull
    @OneToMany(mappedBy = "shawa"/*, fetch = FetchType.EAGER*/, cascade = CascadeType.PERSIST)
    @Size(min=1, message="You must choose at least 1 ingredient")
    private Set<IngredientRef> ingredientRefs;

    @ManyToOne()
    @JoinColumn(name = "shawa_order")
    private ShawaOrder shawaOrder;

//    public void addIngredientRefs(IngredientRef ingredientRef) {
//        this.ingredientRefs.add(ingredientRef);
//        ingredient.getShawas().add(this);
//    }
}
