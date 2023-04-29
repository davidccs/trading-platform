package limited.stocktrader.customer;

import limited.stocktrader.stock.Stock;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class PortfolioItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Stock stock;

    private BigDecimal totalValue;

    private int quantity;

    public Stock getStock() {
        return stock;
    }

    public BigDecimal getTotalValue() {
        return this.totalValue;
    }

    public void setTotalValue(BigDecimal totalValue) {
        this.totalValue = totalValue;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private PortfolioItem() {
    }

    private PortfolioItem(Builder builder) {
        this.stock = builder.stock;
        this.quantity = builder.quantity;
        this.customer = builder.customer;
        this.totalValue = builder.totalValue;
    }

    public static class Builder {
        private Stock stock;

        private BigDecimal totalValue;

        private Customer customer;

        private int quantity;

        public Builder totalValue(BigDecimal totalValue) {
            this.totalValue = totalValue;
            return this;
        }

        public Builder stock(Stock stock) {
            this.stock = stock;
            return this;
        }

        public Builder customer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public PortfolioItem build() {
            return new PortfolioItem(this);
        }
    }
}
