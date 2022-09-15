package pl.grabowski.fastserwis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.grabowski.fastserwis.dto.RepairOrdersSimpleResponse;
import pl.grabowski.fastserwis.model.RepairOrders;

import java.util.List;

@Repository
public interface RepairOrdersRepo extends CrudRepository<RepairOrders, Long> {
   /* @Query(value = "select ca.categoryName, em.lastName, ro.orderDate, ro.expectedEndDate, ro.faultDescription, st.statusName " +
            "from RepairOrders ro join ro.devices dv join ro.employees em join dv.categories ca join ro.status st")*/
    @Query(value = "select ro.Order_id as orderId, " +
            "ca.Category_name as categoryName, " +
            "em.Last_name as lastName, " +
            "ro.Order_date as orderDate, " +
            "ro.Expected_end_date as expectedEndDate, " +
            "ro.Fault_description as faultDescription, " +
            "st.Status_name as statusName from Repair_orders ro " +
            "join Devices dv on ro.Device_id = dv.Device_id " +
            "join Employees em on ro.Employee_id = em.Employee_id " +
            "join Categories ca on dv.Category_id = ca.Category_id " +
            "join Status st on ro.Status_id = st.Status_id order by Expected_end_date", nativeQuery = true)
    List<RepairOrdersSimpleResponse> getSimpleRepairOrders();
}
