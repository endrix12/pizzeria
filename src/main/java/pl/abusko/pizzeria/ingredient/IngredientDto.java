package pl.abusko.pizzeria.ingredient;

import lombok.Data;

@Data
public class IngredientDto {

    private Integer id;
    private String name;

    public Ingredient toEntity(){
        Ingredient ingredient = new Ingredient();
        ingredient.setId(this.id);
        ingredient.setName(this.name);
        return ingredient;
    }


}
