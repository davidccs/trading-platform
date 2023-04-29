package limited.stocktrader.customer.config;

import limited.stocktrader.customer.Customer;
import limited.stocktrader.customer.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class CustomerConfig {


    /**
     * Helper to set up customer during startup.
     */
    @Bean
    CommandLineRunner commandLineRunnerForCustomer(CustomerRepository customerRepository) {
        return args -> {
            Customer customer = new Customer("David");
            customer.setBalance(new BigDecimal(10000.00));
            customerRepository.save(customer);
        };
    }
}
