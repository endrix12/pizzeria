package pl.abusko.pizzeria.Product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PizzaService{

    private final PizzaRepository pizzaRepository;

public List<Pizza> getAllPizzas(){
    return pizzaRepository.findAll();
}

public void addPizza(Pizza pizza){
    pizzaRepository.save(pizza);
}

    public Boolean  existsPizzaByName(String name){
        return pizzaRepository.existsPizzaByName(name);
    }


}
