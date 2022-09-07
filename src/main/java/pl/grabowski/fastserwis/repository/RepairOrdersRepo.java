package pl.grabowski.fastserwis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.grabowski.fastserwis.model.RepairOrders;

@Repository
public interface RepairOrdersRepo extends CrudRepository<RepairOrders, Long> {

}
