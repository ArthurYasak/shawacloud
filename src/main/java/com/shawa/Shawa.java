package com.shawa;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.shawa.utils.ShawaUDRUtils;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
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
//@ToString(exclude = {"shawaOrder"/*, "ingredients"*/})
@Table("shawas")
public class Shawa {

    @PrimaryKeyColumn(type = PrimaryKeyType.PARTITIONED)
    private UUID id = Uuids.timeBased();

    @PrimaryKeyColumn(type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private Date createdAt = new Date();

    @NotNull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    // UDT - user defined type
    @Size(min=1, message="You must choose at least 1 ingredient")
    @Column("ingredients")
    private List<IngredientUDT> ingredients = new ArrayList<>(); // for delete: When using a List, Hibernate removes all entities from the junction table and inserts the remaining ones. This can cause performance issues. We can easily avoid this problem by using Set.

    public void addIngredient(Ingredient ingredient) { // TODO: Что с конвертацией??
        this.ingredients.add(ShawaUDRUtils.toIngredientUDT(ingredient));
    }
}
