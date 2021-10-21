package pl.abusko.pizzeria.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.abusko.pizzeria.ingredient.Ingredient;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    BigDecimal priceForSMALL;
    BigDecimal priceForMEDIUM;
    BigDecimal priceForBIG;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER )
    @JoinTable(
            name = "pizza_ingredient",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    Set<Ingredient> ingridientsSet = new HashSet<>();

    public Pizza(String name) {
        this.name =name;
    }

    public void addIngredient(Ingredient ingredient){
        this.ingridientsSet.add(ingredient);
    }




}
