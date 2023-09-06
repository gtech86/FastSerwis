package pl.grabowski.fastserwis.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.grabowski.fastserwis.dto.RepairOrderExtendedResponse;
import pl.grabowski.fastserwis.dto.RepairOrdersSimpleDto;
import pl.grabowski.fastserwis.model.RepairOrders;
import pl.grabowski.fastserwis.repository.RepairOrdersRepo;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RepairOrdersService {
    private final RepairOrdersRepo ordersRepo;

    public List<RepairOrdersSimpleDto> getSimpleRepairOrders() {
        return ordersRepo.getSimpleRepairOrders();
    }

    public Optional<RepairOrderExtendedResponse> getExtendedRepairOrders(Long orderId) {
        var order = getOrderById(orderId);
        if(order.isPresent()){
            return Optional.of(ordersRepo.getExtendedRepairOrders(orderId));
        }
        return Optional.empty();
    }

    public Optional<RepairOrders> getOrderById(Long orderId) {
        return ordersRepo.findById(orderId);
    }

}
