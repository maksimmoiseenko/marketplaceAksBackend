package pro.dayx.aks.payload.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressRequest {
    private String locality;
    private String street;
    private String houseNumber;
    private String building;
    private Integer entrance;
    private Integer floor;
    private Integer apartment;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String comment;
}
