package pl.grabowski.fastserwis.dto;

import lombok.Data;


@Data
public class EmployeeCreateRequest {
    private Long employeeId;

    private String firstName;

    private String lastName;

    private String username;

    private String password;
    private String password2;

    private String phone;

    private String mail;

    private Boolean isAdmin;
}
