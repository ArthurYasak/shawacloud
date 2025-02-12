package com.shawa;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Entity
@Table
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@ToString(exclude = "ingredientRefs")
public class Ingredient {

    @Id
    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final String id;

    private final String name;

    @Enumerated(value = EnumType.STRING)
    private final Type type;

//    @ManyToMany(/*mappedBy = "ingredients", */targetEntity = Shawa.class, fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "Ingredient_Ref",
//            joinColumns = @JoinColumn(name = "ingredient", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "shawa", referencedColumnName = "id"))
//    private Set<Shawa> shawas; // When using a List, Hibernate removes all entities from the junction table and inserts the remaining ones. This can cause performance issues. We can easily avoid this problem by using Set.

    @OneToMany(mappedBy = "ingredient"/*, fetch = FetchType.EAGER*/, cascade = CascadeType.PERSIST)
    private Set<IngredientRef> ingredientRefs;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
