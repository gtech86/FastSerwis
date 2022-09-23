package pl.grabowski.fastserwis.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.grabowski.fastserwis.dto.RepairOrdersExtendedResponse;
import pl.grabowski.fastserwis.dto.RepairOrdersSimpleResponse;
import pl.grabowski.fastserwis.model.RepairOrders;
import pl.grabowski.fastserwis.repository.RepairOrdersRepo;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RepairOrdersService {
    private final RepairOrdersRepo ordersRepo;


    public List<RepairOrdersSimpleResponse> getSimpleRepairOrders() {
        return ordersRepo.getSimpleRepairOrders();
    }

    public RepairOrdersExtendedResponse getExtendedRepairOrders() {

        return null;
    }

    public Optional<RepairOrders> getOrderById(Long orderId) {
        return ordersRepo.findById(orderId);
    }
}
