package limited.stocktrader.stock;

import limited.stocktrader.customer.Customer;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class StockOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Stock stock;

    private OrderType type;

    private int quantity;

    private BigDecimal orderPrice;

    private BigDecimal salePrice;

    private LocalDateTime createdDate;

    public StockOrder() {}

    public Long getId() {
        return id;
    }
    public Stock getStock() {
        return stock;
    }

    public OrderType getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public static class Builder {
        private final StockOrder order;

        public Builder() {
            order = new StockOrder();
            order.createdDate = LocalDateTime.now();
        }

        public Builder customer(Customer customer) {
            order.customer = customer;
            return this;
        }

        public Builder stock(Stock stock) {
            order.stock = stock;
            return this;
        }

        public Builder type(OrderType type) {
            order.type = type;
            return this;
        }

        public Builder quantity(int quantity) {
            order.quantity = quantity;
            return this;
        }

        public Builder price(BigDecimal price) {
            order.orderPrice = price;
            return this;
        }

        public Builder salePrice(BigDecimal salePrice){
            order.salePrice = salePrice;
            return this;
        }

        public StockOrder build() {
            return order;
        }
    }
}

