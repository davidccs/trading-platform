package limited.stocktrader.customer.controller;

import limited.stocktrader.customer.Customer;
import limited.stocktrader.customer.PortfolioItem;
import limited.stocktrader.customer.repository.CustomerRepository;
import limited.stocktrader.stock.StockOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerRepository customerRepository;


    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public Customer getCustomerDetails() {
        return customerRepository.findAll().get(0);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<StockOrder> getCustomerOrders() {
        return customerRepository.findAll().get(0).getStockOrders();
    }

    @RequestMapping(value = "/portfolio", method = RequestMethod.GET)
    public List<PortfolioItem> getCustomerPortfolio() {
        return customerRepository.findAll().get(0).getPortfolio();
    }

    // other methods as needed
}
