package pl.grabowski.fastserwis.dto.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateClientRequest {
    @NotNull
    private String firstName;
    @NotNull
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
