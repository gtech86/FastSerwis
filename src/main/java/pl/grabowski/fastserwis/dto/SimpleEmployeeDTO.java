package pl.grabowski.fastserwis.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SimpleEmployeeDTO {

    public final Long employeeId;
    public final String firstName;
    public final String lastName;
    public final String phone;
    public final String mail;
}
