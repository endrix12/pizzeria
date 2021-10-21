package pl.abusko.pizzeria.ingredient;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.abusko.pizzeria.User.User;

import java.util.List;

@Controller
@AllArgsConstructor
public class IngredientController {

    IngredientService ingredientService;

    @GetMapping("ingredients")
    public String getIngredient(Model model){
        List<Ingredient> ingredientList = ingredientService.getIngredients();
        model.addAttribute("ingredientList",ingredientList);
        return "ingredients";

    }

    @GetMapping("ingredient/add")
    public String addIngredient(Model model){
        model.addAttribute("ingredientDto", new IngredientDto());
        model.addAttribute("ingredientExists",Boolean.FALSE);
        return "add-ingredient";
    }

    @PostMapping("ingredient/add")
    public String addIngredient(@ModelAttribute IngredientDto ingredientDto,Model model){
        Boolean existsIngredient = ingredientService.existsIngredientByName(ingredientDto.getName());
        if (existsIngredient) {
            model.addAttribute("ingredientDto", ingredientDto);
            model.addAttribute("ingredientExists",Boolean.TRUE);
            return "add-ingredient";
        }
        else {
            ingredientService.addIngredient(ingredientDto.toEntity());
            return "redirect:/ingredients";
        }
    }

    @GetMapping("ingredient/delete/{id}")
    public String deleteUser(@PathVariable(name="id") Integer id){
        ingredientService.deleteIngredientById(id);
        return "redirect:/ingredients";
    }

    @GetMapping("ingredient/update/{id}")
    public String updateUser(@PathVariable(name="id") Integer id, Model model){
        Ingredient ingredient = ingredientService.findIngredientById(id);
        model.addAttribute("ingredientDto",ingredient.toDto());
        return "add-ingredient";
    }
}
