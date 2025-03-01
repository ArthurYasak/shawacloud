package com.shawa;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@Table("ingredient")
public class Ingredient {

    @PrimaryKey
    private final String id;

    private final String name;

    private final Type type;

//    @ManyToMany(mappedBy = "ingredients"/*,targetEntity = Shawa.class, fetch = FetchType.EAGER*/)
//    @JoinTable(
//            name = "Ingredient_Ref",
//            joinColumns = @JoinColumn(name = "ingredient", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "shawa", referencedColumnName = "id"))
//    private Set<Shawa> shawas; // When using a List, Hibernate removes all entities from the junction table and inserts the remaining ones. This can cause performance issues. We can easily avoid this problem by using Set.

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
