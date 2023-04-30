package limited.stocktrader.customer.service;

import limited.stocktrader.customer.Customer;
import limited.stocktrader.customer.PortfolioItem;
import limited.stocktrader.customer.repository.CustomerRepository;
import limited.stocktrader.stock.StockOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerDetails(long userId) {
        Optional<Customer> customer = customerRepository.findById(userId);
        return customer.orElse(null);
    }

    public List<StockOrder> getCustomerOrders(long userId) {
        return customerRepository.findById(userId).map(Customer::getStockOrders).orElse(null);
    }
    public List<PortfolioItem> getCustomerPortfolio(long userId) {
        return customerRepository.findById(userId).map(Customer::getPortfolio).orElse(null);
    }


}
