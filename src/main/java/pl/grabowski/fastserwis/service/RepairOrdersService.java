package pl.grabowski.fastserwis.service;


import pl.grabowski.fastserwis.dto.RepairOrdersExtendedResponse;
import pl.grabowski.fastserwis.dto.RepairOrdersSimpleResponse;

import java.util.List;

public interface RepairOrdersService {
    List<RepairOrdersSimpleResponse> getSimpleRepairOrders();
    List<RepairOrdersExtendedResponse> getExtendedRepairOrders();
}
