package pl.fastserwis.fastserwis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.fastserwis.fastserwis.model.RepairOrders;

@Repository
public interface RepairOrdersRepo extends CrudRepository<RepairOrders, Long> {

}
