package limited.stocktrader.stock.repository;

import limited.stocktrader.stock.StockOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockOrderRepository extends JpaRepository<StockOrder, Long> {
}
