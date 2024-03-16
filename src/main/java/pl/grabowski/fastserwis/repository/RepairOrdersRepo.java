package pl.grabowski.fastserwis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.grabowski.fastserwis.dto.order.RepairOrderExtendedResponse;
import pl.grabowski.fastserwis.dto.order.RepairOrdersSimpleDto;
import pl.grabowski.fastserwis.model.RepairOrder;

import java.util.List;

@Repository
public interface RepairOrdersRepo extends AbstractRepository<RepairOrder> {
    /* @Query(value = "select ca.categoryName, em.lastName, ro.orderDate, ro.expectedEndDate, ro.faultDescription, st.statusName " +
             "from RepairOrders ro join ro.devices dv join ro.employees em join dv.categories ca join ro.status st")*/
    @Query(value = "select new pl.grabowski.fastserwis.dto.order.RepairOrdersSimpleDto(" +
            "ro.orderId, ca.categoryName, cl.lastName, ro.orderDate, ro.expectedEndDate, ro.faultDescription," +
            "dv.serialNumber, st.statusName) from RepairOrder ro join ro.status st join ro.devices dv join dv.client cl  join dv.category ca " +
            "where st.statusName not like 'Zamknięte' order by ro.expectedEndDate asc")
    List<RepairOrdersSimpleDto> getSimpleRepairOrders();


    @Query(value = "select ro.order_id as orderId, " +
            "ro.order_date as orderDate, " +
            "ro.end_date as endDate, " +
            "ro.expected_end_date as expectedEndDate, " +
            "ro.fault_description as faultDescription, " +
            "ro.repair_description as repairDescription, " +
            "ro.charger as charger, " +
            "ro.repair_price as repairPrice, " +
            "ro.parts_price as partPrice, " +
            "os.type_name as orderType, " +
            "st.status_name as statusName," +
            "dv.device_id as deviceId," +
            "dv.producer as deviceProducer," +
            "dv.model as deviceModel," +
            "dv.serial_number as deviceSerialNumber," +
            "em.first_name as employeeFirstName," +
            "em.last_name as employeeLastName," +
            "em.phone as employeePhone," +
            "cl.client_id as clientId," +
            "cl.first_name as clientFirstName," +
            "cl.last_name as clientLastName," +
            "cl.phone as clientPhone from repair_orders ro " +
            "join devices dv on ro.device_id = dv.device_id " +
            "join employees em on ro.employee_id = em.employee_id " +
            "join categories ca on dv.category_id = ca.category_id " +
            "join clients cl on ro.client_id = cl.client_id " +
            "join status st on ro.status_id = st.status_id " +
            "join order_types os on ro.order_type_id = os.order_type_id where ro.order_id = :orderId", nativeQuery = true)
    RepairOrderExtendedResponse getExtendedRepairOrders(Long orderId);

    @Query(value = "select new pl.grabowski.fastserwis.dto.order.RepairOrdersSimpleDto(" +
            "ro.orderId, ca.categoryName, cl.lastName, ro.orderDate, ro.expectedEndDate, ro.faultDescription," +
            "dv.serialNumber, st.statusName) from RepairOrder ro join ro.status st join ro.devices dv join dv.client cl join dv.category ca " +
            "WHERE st.statusName = :status")
    List<RepairOrdersSimpleDto> getRepairOrdersByStatus(@Param("status") String statusName);
}