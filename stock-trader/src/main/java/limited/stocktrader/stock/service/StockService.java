package limited.stocktrader.stock.service;

import limited.stocktrader.customer.Customer;
import limited.stocktrader.customer.PortfolioItem;
import limited.stocktrader.customer.repository.CustomerRepository;
import limited.stocktrader.customer.repository.PortfolioRepository;
import limited.stocktrader.stock.OrderType;
import limited.stocktrader.stock.Stock;
import limited.stocktrader.stock.StockOrder;
import limited.stocktrader.stock.repository.StockOrderRepository;
import limited.stocktrader.stock.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class StockService {

    private final StockRepository stockRepository;
    private final StockOrderRepository stockOrderRepository;
    private final CustomerRepository customerRepository;
    private final PortfolioRepository portfolioRepository;

    @Autowired
    public StockService(StockRepository stockRepository,
                        StockOrderRepository stockOrderRepository,
                        CustomerRepository customerRepository,
                        PortfolioRepository portfolioRepository) {
        this.stockRepository = stockRepository;
        this.stockOrderRepository = stockOrderRepository;
        this.customerRepository = customerRepository;
        this.portfolioRepository = portfolioRepository;
    }

    @Transactional
    public void buy(String symbol, int quantity) throws Exception {
        Stock stock = getStockBySymbol(symbol);
        BigDecimal stockPrice = stock.getPrice();
        Customer customer = customerRepository.findAll().get(0);

        BigDecimal costBasedOnStockPriceAndQuantity = new BigDecimal(String.valueOf(stock.getPrice())).multiply(BigDecimal.valueOf(quantity));
        if (customer.getBalance().compareTo(costBasedOnStockPriceAndQuantity) < 0) {
            throw new Exception("Customer does not have the required funds");
        }
        customer.setBalance(customer.getBalance().subtract(costBasedOnStockPriceAndQuantity));

        System.out.println("New customer balance is " + customer.getBalance());

        addStockOrderForCustomer(quantity, stock, stockPrice, customer);
        addStockToPortfolio(customer, stock, quantity);

    }

    private void addStockOrderForCustomer(int quantity, Stock stock, BigDecimal stockPrice, Customer customer) {
        StockOrder stockOrder = new StockOrder.Builder()
                .customer(customer)
                .stock(stock)
                .type(OrderType.BUY)
                .quantity(quantity)
                .price(stockPrice)
                .build();
        stockOrderRepository.save(stockOrder);
    }

    private void addStockToPortfolio(Customer customer,
                             Stock stock,
                             int quantity) {
        PortfolioItem newItem = new PortfolioItem.Builder()
                .customer(customer)
                .stock(stock)
                .quantity(quantity)
                .build();
        portfolioRepository.save(newItem);
    }

    @Transactional
    public void sell(String symbol, int quantity) {
        Customer customer = customerRepository.findAll().get(0);
        Stock stock = getStockBySymbol(symbol);
        // check if customer has the stock

//        StockOrder customerStock = getStockInPortfolio(symbol);

        // sell the stock based on price.
//        customer.getStockInPortfolio(stock);

    }

    @Transactional
    public List<Stock> getCurrentStock() {
        return stockRepository.findAll();
    }

    private Stock getStockBySymbol(String symbol) {
        Stock retrievedStock = stockRepository.findBySymbol(symbol);
        if (retrievedStock == null) {
            throw new EntityNotFoundException("Stock not found for symbol: " + symbol);
        }
        return retrievedStock;
    }

}
