package limited.stocktrader.stock.service;

import limited.stocktrader.customer.Customer;
import limited.stocktrader.customer.PortfolioItem;
import limited.stocktrader.customer.repository.CustomerRepository;
import limited.stocktrader.customer.repository.PortfolioRepository;
import limited.stocktrader.stock.Stock;
import limited.stocktrader.stock.StockOrder;
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
    @Test
    public void testSell() throws Exception {
        // Given
        Customer customer = new Customer();
        customer.setBalance(new BigDecimal(1000));
        String stockSymbol = "AAPL";
        Stock mockStock = new Stock();
        mockStock.setPrice(new BigDecimal(100));

        StockOrder stockOrder = new StockOrder();

        PortfolioItem portfolioItem = new PortfolioItem.Builder()
                .customer(customer)
                .totalValue(new BigDecimal(2000))
                .stock(mockStock)
                .quantity(10)
                .build();

        when(customerRepository.findAll()).thenReturn(List.of(customer));
        when(portfolioRepository.findByStock(mockStock)).thenReturn(portfolioItem);
        when(stockRepository.findBySymbol(stockSymbol)).thenReturn(mockStock);
        when(stockOrderRepository.save(any())).thenReturn(stockOrder);

        // When
        stockService.sell("AAPL", new BigDecimal(90), 5);

        // Then
        assertEquals(5, portfolioItem.getQuantity());
        assertEquals(new BigDecimal(1500), customer.getBalance());
    }


    @Test
    public void testCantSellDueToSharePriceTooHigh() throws Exception {
        // Given
        Customer customer = new Customer();
        customer.setBalance(new BigDecimal(1000));
        String stockSymbol = "AAPL";
        Stock mockStock = new Stock();
        mockStock.setPrice(new BigDecimal(100));

        StockOrder stockOrder = new StockOrder();

        PortfolioItem portfolioItem = new PortfolioItem.Builder()
                .customer(customer)
                .totalValue(new BigDecimal(2000))
                .stock(mockStock)
                .quantity(100)
                .build();

        when(customerRepository.findAll()).thenReturn(List.of(customer));
        when(portfolioRepository.findByStock(mockStock)).thenReturn(portfolioItem);
        when(stockRepository.findBySymbol(stockSymbol)).thenReturn(mockStock);
        when(stockOrderRepository.save(any())).thenReturn(stockOrder);

        // When
        stockService.sell("AAPL", new BigDecimal(110), 5);

        // Then
        assertEquals(100, portfolioItem.getQuantity());
        assertEquals(new BigDecimal(1000), customer.getBalance());
    }
}