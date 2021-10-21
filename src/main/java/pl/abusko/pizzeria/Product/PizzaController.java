package pl.abusko.pizzeria.Product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class PizzaController {

    PizzaService pizzaService;

    @GetMapping
    public String showPizzas(Model model){
        model.addAttribute("pizzaList", pizzaService.getAllPizzas() );
        return "home";

    }

    @PostMapping("add")
    public String addPizza(@ModelAttribute PizzaDto pizzaDto, Model model) {
        Boolean existsPizza = pizzaService.existsPizzaByName(pizzaDto.getName());
        if (existsPizza) {
            model.addAttribute("newPizzaDto", pizzaDto);
            model.addAttribute("pizzaExists",Boolean.TRUE);
            return "register";
        }
        else{
            pizzaService.addPizza(pizzaDto.toEntity());
            return "home";
        }
    }
}
