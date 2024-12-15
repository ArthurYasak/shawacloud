package com.shawa;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Persistable;

import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
//@ToString(exclude = "shawas")
public class Ingredient {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final String id;

    private final String name;

    @Enumerated(value = EnumType.STRING)
    private final Type type;

    @ManyToMany(/*mappedBy = "ingredients",*/ targetEntity = Shawa.class, fetch = FetchType.EAGER)
    @JoinTable(
            name = "Ingredient_Ref",
            joinColumns = @JoinColumn(name = "ingredient", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "shawa", referencedColumnName = "id"))
    private List<Shawa> shawas;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
