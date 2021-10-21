package pl.abusko.pizzeria.ingredient;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class IngredientService {

    IngredientRepository ingredientRepository;

    public void addIngredient(Ingredient ingredient){
        ingredientRepository.save(ingredient);
    }

    public List<Ingredient> getIngredients(){
      return   ingredientRepository.findAll();
    }


    public void deleteIngredientById(Integer id) {
        ingredientRepository.deleteById(id);
    }

    public Ingredient findIngredientById(Integer id) {
        return ingredientRepository.findById(id).orElseGet(()->null);
    }

    public Boolean existsIngredientByName(String name) {
        return ingredientRepository.existsIngredientByName(name);
    }
}
