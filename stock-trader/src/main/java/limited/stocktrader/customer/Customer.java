package limited.stocktrader.customer;

import limited.stocktrader.stock.StockOrder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String username;

    private BigDecimal balance;

    private int portflioCount;

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

    public Long getCustomerId() {
        return customerId;
    }

    public String getUsername() {
        return username;
    }

    public int getPortfolioCount() {
        return portfolio.size();
    }

    public List<PortfolioItem> getPortfolio() {
        return this.portfolio;
    }

    public List<StockOrder> getStockOrders() {
        return this.stockOrdersHistory;
    }
}
