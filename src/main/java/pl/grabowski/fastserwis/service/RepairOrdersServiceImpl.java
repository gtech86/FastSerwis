package pl.grabowski.fastserwis.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.grabowski.fastserwis.dto.RepairOrdersExtendedResponse;
import pl.grabowski.fastserwis.dto.RepairOrdersSimpleResponse;
import pl.grabowski.fastserwis.repository.RepairOrdersRepo;

import java.util.List;

@Service
@AllArgsConstructor
public class RepairOrdersServiceImpl implements RepairOrdersService {

    private final RepairOrdersRepo ordersRepo;

    @Override
    public List<RepairOrdersSimpleResponse> getSimpleRepairOrders() {
        return ordersRepo.getSimpleRepairOrders();
    }

    @Override
    public List<RepairOrdersExtendedResponse> getExtendedRepairOrders() {
        return null;
    }
}
