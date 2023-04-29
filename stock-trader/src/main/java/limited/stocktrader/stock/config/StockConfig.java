package limited.stocktrader.stock.config;

import limited.stocktrader.stock.Stock;
import limited.stocktrader.stock.repository.StockRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class StockConfig {

    /**
     * Helper to set up stocks during startup.
     */
    @Bean
    CommandLineRunner commandLineRunnerForStock(StockRepository stockRepository) {
        return args -> {
            Stock appleStock = new Stock("AAPL", "Apple", new BigDecimal(70.32));

            Stock googleStock = new Stock("GOOG", "Google", new BigDecimal(100.44));

            Stock citiStock = new Stock("C", "CitiGroup", new BigDecimal(123.88));

            Stock amazonStock = new Stock("AMZN", "Amazon", new BigDecimal(66.55));

            Stock accentureStock = new Stock("ACN", "Accenture", new BigDecimal(552.21));

            stockRepository.saveAll(List.of(appleStock,
                    googleStock,
                    citiStock,
                    amazonStock,
                    accentureStock));
        };
    }
}
