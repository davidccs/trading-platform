package limited.stocktrader.stock.controller;

import limited.stocktrader.stock.Stock;
import limited.stocktrader.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    private StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public void buyStock(@RequestParam String symbol, @RequestParam int quantity) throws Exception {
        stockService.buy(symbol, quantity);
    }

//    @RequestMapping(value = "/sell", method = RequestMethod.POST)
//    public void sellStock(@RequestParam String symbol, @RequestParam int quantity) {
//        stockService.sell(symbol, quantity);
//    }

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public List<Stock> currentStock() {
        return stockService.getCurrentStock();
    }


    // other methods as needed
}
