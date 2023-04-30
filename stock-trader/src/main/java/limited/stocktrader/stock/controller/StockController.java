package limited.stocktrader.stock.controller;

import limited.stocktrader.stock.Stock;
import limited.stocktrader.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    private StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public void buyStock(@RequestParam String symbol, @RequestParam BigDecimal price, @RequestParam int quantity) throws Exception {
        stockService.buy(symbol, price, quantity);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/sell", method = RequestMethod.POST)
    public void sellStock(@RequestParam String symbol, @RequestParam BigDecimal price, @RequestParam int quantity) throws Exception {
        stockService.sell(symbol, price, quantity);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Stock> getAllStockOnExchange() {
        return stockService.getAllStockOnExchange();
    }

}
