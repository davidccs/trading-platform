package limited.stocktrader.stock.service;

import limited.stocktrader.customer.Customer;
import limited.stocktrader.customer.PortfolioItem;
import limited.stocktrader.customer.repository.CustomerRepository;
import limited.stocktrader.customer.repository.PortfolioRepository;
import limited.stocktrader.stock.ExecutedType;
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
    public void buy(String symbol, BigDecimal customerPrice, int quantity) throws Exception {
        Stock stock = getStockBySymbol(symbol);
        BigDecimal stockPrice = stock.getPrice();
        Customer customer = customerRepository.findAll().get(0);

        BigDecimal costBasedOnStockPriceAndQuantity = new BigDecimal(String.valueOf(customerPrice)).multiply(BigDecimal.valueOf(quantity));
        if (customer.getBalance().compareTo(costBasedOnStockPriceAndQuantity) < 0) {
            throw new Exception("Customer does not have the required funds");
        }

        StockOrder stockOrder = addStockOrderForCustomer(quantity, stock, customerPrice, null, customer, OrderType.BUY, ExecutedType.PENDING);

        if (stockPrice.compareTo(customerPrice) <= 0) {
            customer.setBalance(customer.getBalance().subtract(costBasedOnStockPriceAndQuantity));
            System.out.println("New customer balance is " + customer.getBalance());
            addStockToPortfolio(customer, stock, quantity);
            customerRepository.save(customer);

            stockOrder.setExecuted(ExecutedType.FULFILLED);
            stockOrderRepository.save(stockOrder);        }
    }

    @Transactional
    public void sell(String symbol, BigDecimal customerPrice, int quantity) throws Exception {
        Customer customer = customerRepository.findAll().get(0);
        Stock stock = getStockBySymbol(symbol);
        BigDecimal stockPrice = stock.getPrice();

        // check if customer has the stock in portfolio
        PortfolioItem portfolioItem = portfolioRepository.findByStock(stock);

        if (portfolioItem == null) {
            throw new Exception("Customer does not have the stock: " + stock.getName());
        }
        if (quantity > portfolioItem.getQuantity()) {
            throw new Exception("Customer does not have the required stock to sell: " + stock.getName());
        }

        StockOrder stockOrder = addStockOrderForCustomer(quantity, stock, stockPrice, customerPrice, customer, OrderType.SELL, ExecutedType.PENDING);

        if (stock.getPrice().compareTo(customerPrice) > 0) {
            portfolioItem.setQuantity(portfolioItem.getQuantity() - quantity);
            portfolioItem.setTotalValue(portfolioItem.getTotalValue().subtract(customerPrice.multiply(new BigDecimal(quantity))));

            if (portfolioItem.getQuantity() <= 0) {
                portfolioRepository.delete(portfolioItem);
            } else {
                portfolioRepository.save(portfolioItem);
            }

            BigDecimal newCustomerBalance = customer.getBalance().add(stock.getPrice().multiply(new BigDecimal(quantity)));
            customer.setBalance(newCustomerBalance);
            customerRepository.save(customer);

            stockOrder.setExecuted(ExecutedType.FULFILLED);
            stockOrderRepository.save(stockOrder);
        }
    }


    private StockOrder addStockOrderForCustomer(int quantity, Stock stock, BigDecimal stockPrice, BigDecimal salePrice, Customer customer, OrderType orderType, ExecutedType executed) {
        StockOrder stockOrder = new StockOrder.Builder()
                .customer(customer)
                .stock(stock)
                .type(orderType)
                .executedType(executed)
                .quantity(quantity)
                .salePrice(salePrice)
                .price(stockPrice)
                .build();
        stockOrderRepository.save(stockOrder);

        return stockOrder;
    }

    private void addStockToPortfolio(Customer customer,
                                     Stock stock,
                                     int quantity) {
        PortfolioItem portfolioItem = portfolioRepository.findByStock(stock);

        if (portfolioItem == null) {
            PortfolioItem newItem = new PortfolioItem.Builder()
                    .customer(customer)
                    .totalValue(stock.getPrice().multiply(new BigDecimal(quantity)))
                    .stock(stock)
                    .quantity(quantity)
                    .build();
            portfolioRepository.save(newItem);
        } else {
            int totalStock = portfolioItem.getQuantity() + quantity;
            portfolioItem.setQuantity(totalStock);
            portfolioItem.setTotalValue(portfolioItem.getTotalValue().add(stock.getPrice().multiply(new BigDecimal(quantity))));
            portfolioRepository.save(portfolioItem);
        }
    }


    @Transactional
    public List<Stock> getAllStockOnExchange() {
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
