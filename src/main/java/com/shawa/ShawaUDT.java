package com.shawa;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@UserDefinedType("shawa")
public class ShawaUDT {

    private String name;

    private List<IngredientUDT> ingredients = new ArrayList<>();
}
