package pl.grabowski.fastserwis.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@RequiredArgsConstructor
public class CreateClientRequest {
    @NotNull
    @Size(min=2, max=25)
    private String firstName;

    @NotNull
    @Size(min=2, max=35)
    private String lastName;

    @NotNull
    @Size(min=2, max=40)
    private String street;

    @NotNull
    private Integer streetNumber;

    private Integer flatNumber;

    @NotNull
    private String postalCode;

    private String city;

    @NotNull
    @Size(min=9, max=15)
    private String phone;

    @Email
    private String mail;
}
