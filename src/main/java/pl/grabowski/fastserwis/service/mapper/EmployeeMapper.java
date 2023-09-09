package pl.grabowski.fastserwis.service.mapper;

import org.mapstruct.Mapper;
import pl.grabowski.fastserwis.dto.SimpleEmployeeDTO;
import pl.grabowski.fastserwis.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper extends EntityMapper<Employee, SimpleEmployeeDTO>{

}
