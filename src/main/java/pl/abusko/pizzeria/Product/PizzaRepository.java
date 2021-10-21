package pl.abusko.pizzeria.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza,Integer> {

    Boolean existsPizzaByName(String name);

}
