package pl.abusko.pizzeria.Product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PizzaDto {

    Integer id;
    String name;
    BigDecimal priceForSMALL;
    BigDecimal priceForMEDIUM;
    BigDecimal priceForBIG;

    public Pizza toEntity(){
        Pizza pizza = new Pizza();
        pizza.setId(this.id);
        pizza.setName(this.name);
        pizza.setPriceForSMALL(this.priceForSMALL);
        pizza.setPriceForMEDIUM(this.priceForMEDIUM);
        pizza.setPriceForBIG(this.priceForBIG);
        return pizza;
    }
}
