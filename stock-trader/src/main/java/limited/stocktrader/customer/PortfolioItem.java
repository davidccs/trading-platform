package limited.stocktrader.customer;

import limited.stocktrader.stock.Stock;

import javax.persistence.*;

@Entity
public class PortfolioItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Stock stock;

    private int quantity;

    public Stock getStock() {
        return stock;
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
    }

    public static class Builder {
        private Stock stock;

        private Customer customer;

        private int quantity;

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
