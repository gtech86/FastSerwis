package pl.grabowski.fastserwis.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.grabowski.fastserwis.dto.EmployeeCreateRequest;
import pl.grabowski.fastserwis.dto.EmployeeResponse;
import pl.grabowski.fastserwis.dto.EmployeeUpdateRequest;
import pl.grabowski.fastserwis.dto.SimpleEmployeeDTO;
import pl.grabowski.fastserwis.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper extends EntityMapper<Employee, SimpleEmployeeDTO>{
    @Mapping(target = "password", ignore = true)
        Employee toCreateEntity(EmployeeCreateRequest employeeCreateRequest);

    EmployeeResponse toCreateResponse(Employee employee);

    Employee toUpdateEntity(EmployeeUpdateRequest employeeUpdateRequest);

    EmployeeCreateRequest toCreateEmployeeRequest(Employee employee);
}
