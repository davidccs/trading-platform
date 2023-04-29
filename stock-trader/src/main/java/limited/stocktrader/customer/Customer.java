package limited.stocktrader.customer;

import limited.stocktrader.stock.Stock;
import limited.stocktrader.stock.StockOrder;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String username;

    private BigDecimal balance;

    @OneToMany(mappedBy = "customer")
    private List<StockOrder> stockOrdersHistory;
    @OneToMany(mappedBy = "customer")
    private List<PortfolioItem> portfolio;

    public Customer() {
    }

    public Customer(String username) {
        this.username = username;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<PortfolioItem> getPortfolio() {
        return this.portfolio;
    }

    public List<StockOrder> getStockOrders() {
        return this.stockOrdersHistory;
    }
}
