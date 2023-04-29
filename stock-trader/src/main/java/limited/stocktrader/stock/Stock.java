package limited.stocktrader.stock;

import limited.stocktrader.customer.Customer;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;

    private String name;

    private BigDecimal price;
    public Stock() {}

    public Stock(String symbol, String name, BigDecimal price) {
        this.symbol = symbol;
        this.name = name;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}