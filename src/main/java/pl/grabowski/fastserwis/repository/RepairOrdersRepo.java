package pl.grabowski.fastserwis.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.grabowski.fastserwis.dto.RepairOrderExtendedResponse;
import pl.grabowski.fastserwis.dto.RepairOrdersSimpleResponse;
import pl.grabowski.fastserwis.model.RepairOrders;
import pl.grabowski.fastserwis.model.Status;

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
    @Query(value = "select ro.Order_id as orderId, " +
            "ro.Order_date as orderDate, " +
            "ro.End_date as endDate, " +
            "ro.Expected_end_date as expectedEndDate, " +
            "ro.Fault_description as faultDescription, " +
            "ro.Repair_description as repairDescription, " +
            "ro.Charger as charger, " +
            "ro.Repair_price as repairPrice, " +
            "ro.Parts_price as partPrice, " +
            "os.Type_name as orderType, " +
            "st.Status_name as statusName," +
            "dv.Device_id as deviceId," +
            "dv.Producer as deviceProducer," +
            "dv.Model as deviceModel," +
            "dv.Serial_number as deviceSerialNumber," +
            "em.First_name as employeeFirstName," +
            "em.Last_name as employeeLastName," +
            "em.Phone as employeePhone," +
            "cl.Client_id as clientId," +
            "cl.First_name as clientFirstName," +
            "cl.Last_name as clientLastName," +
            "cl.Phone as clientPhone from Repair_orders ro " +
            "join Devices dv on ro.Device_id = dv.Device_id " +
            "join Employees em on ro.Employee_id = em.Employee_id " +
            "join Categories ca on dv.Category_id = ca.Category_id " +
            "join Clients cl on ro.Client_id = cl.Client_id " +
            "join Status st on ro.Status_id = st.Status_id " +
            "join Order_types os on ro.Order_type_id = os.Order_type_id where ro.Order_id = :orderId", nativeQuery = true)
    RepairOrderExtendedResponse getExtendedRepairOrders(Long orderId);

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
            "join Status st on ro.Status_id = st.Status_id where st.Status_name = :status", nativeQuery = true)
    List<RepairOrdersSimpleResponse> getRepairOrdersByStatus(@Param("status") String statusName);
}