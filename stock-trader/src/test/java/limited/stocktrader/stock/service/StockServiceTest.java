package limited.stocktrader.stock.service;

import limited.stocktrader.customer.Customer;
import limited.stocktrader.customer.PortfolioItem;
import limited.stocktrader.customer.repository.CustomerRepository;
import limited.stocktrader.customer.repository.PortfolioRepository;
import limited.stocktrader.stock.Stock;
import limited.stocktrader.stock.repository.StockOrderRepository;
import limited.stocktrader.stock.repository.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class StockServiceTest {
    @Mock
    private StockRepository stockRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private StockOrderRepository stockOrderRepository;

    @Mock
    private PortfolioRepository portfolioRepository;

    @InjectMocks
    private StockService stockService;

    @BeforeEach
    public void init() {
        initMocks(this);
    }

    @Test
    public void testBuyWithEnoughFunds() throws Exception {
        // Given
        Stock mockStock = new Stock();
        String stockSymbol = "AAPL";
        mockStock.setSymbol(stockSymbol);
        mockStock.setPrice(BigDecimal.valueOf(90));
        when(stockRepository.findBySymbol(anyString())).thenReturn(mockStock);

        Customer mockCustomer = new Customer();
        mockCustomer.setBalance(BigDecimal.valueOf(1000));
        when(customerRepository.findAll()).thenReturn(List.of(mockCustomer));

        // When
        stockService.buy(stockSymbol, BigDecimal.valueOf(90), 10);

        // Then
        assertEquals(BigDecimal.valueOf(100), mockCustomer.getBalance());
    }

    @Test
    public void testBuyWithNotEnoughFunds() throws Exception {
        // Given
        Stock mockStock = new Stock();
        String stockSymbol = "AAPL";
        mockStock.setSymbol(stockSymbol);
        mockStock.setPrice(BigDecimal.valueOf(100));
        when(stockRepository.findBySymbol(stockSymbol)).thenReturn(mockStock);

        Customer mockCustomer = new Customer();
        mockCustomer.setBalance(BigDecimal.valueOf(500));
        when(customerRepository.findAll()).thenReturn(List.of(mockCustomer));

        // When - Then
        assertThrows(Exception.class, () -> stockService.buy(stockSymbol, BigDecimal.valueOf(95), 10));
    }
}