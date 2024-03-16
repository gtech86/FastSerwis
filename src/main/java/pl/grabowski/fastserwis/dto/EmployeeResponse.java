package pl.grabowski.fastserwis.dto;

import lombok.Data;


@Data
public class EmployeeResponse {
    private Long employeeId;

    private String firstName;

    private String lastName;

    private String username;

    private String phone;

    private String mail;
    private Boolean isAdmin;

}
