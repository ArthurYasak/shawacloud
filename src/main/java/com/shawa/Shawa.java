package com.shawa;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.shawa.utils.ShawaUDRUtils;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@ToString(exclude = {"shawaOrder"/*, "ingredients"*/})
@Table("shawa")
public class Shawa {

    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private UUID id = Uuids.timeBased();

    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private Date createdAt = new Date();

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

//    @ManyToMany(/*mappedBy = "shawas", targetEntity = Ingredient.class*/)
//    @JoinTable(
//            name = "Ingredient_Ref",
//            joinColumns = @JoinColumn(name = "shawa", referencedColumnName = "id"),
//            inverseJoinColumns = @JoinColumn(name = "ingredient", referencedColumnName = "id"))

    @Size(min=1, message="You must choose at least 1 ingredient")
    @Column("ingredients")
    private List<IngredientUDT> ingredients = new ArrayList<>(); // todo: delete: When using a List, Hibernate removes all entities from the junction table and inserts the remaining ones. This can cause performance issues. We can easily avoid this problem by using Set.

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ShawaUDRUtils.toIngredientUDT(ingredient));
    }
}
