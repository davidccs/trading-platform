package limited.stocktrader.customer.controller;

import limited.stocktrader.customer.Customer;
import limited.stocktrader.customer.PortfolioItem;
import limited.stocktrader.customer.repository.CustomerRepository;
import limited.stocktrader.stock.StockOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerRepository customerRepository;


    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public Customer getCustomerDetails(@RequestParam long userId) {
        Optional<Customer> customer = customerRepository.findById(userId);
        return customer.orElse(null);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<StockOrder> getCustomerOrders(@RequestParam long userId) {
        return customerRepository.findById(userId).map(Customer::getStockOrders).orElse(null);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/portfolio", method = RequestMethod.GET)
    public List<PortfolioItem> getCustomerPortfolio(@RequestParam long userId) {
        return customerRepository.findById(userId).map(Customer::getPortfolio).orElse(null);
    }

    // other methods as needed
}
