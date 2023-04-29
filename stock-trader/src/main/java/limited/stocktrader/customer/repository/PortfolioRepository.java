package limited.stocktrader.customer.repository;

import limited.stocktrader.customer.PortfolioItem;
import limited.stocktrader.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioRepository extends JpaRepository<PortfolioItem, Long> {
    PortfolioItem findByStock(Stock stock);

}
