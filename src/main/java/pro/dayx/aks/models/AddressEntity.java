package pro.dayx.aks.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pro.dayx.aks.security.services.UserEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    @JsonIgnore
    @OneToMany(mappedBy = "address", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<OrderEntity> orders = new ArrayList<>();
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private UserEntity client;

    public AddressEntity(String locality, String street, String houseNumber, String building, Integer entrance, Integer floor, Integer apartment, String firstname, String lastname, String email, String phone, String comment) {
        this.locality = locality;
        this.street = street;
        this.houseNumber = houseNumber;
        this.building = building;
        this.entrance = entrance;
        this.floor = floor;
        this.apartment = apartment;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"locality\":\"" + locality +
                "\", \"street\":\"" + street +
                "\", \"houseNumber\":\"" + houseNumber +
                "\", \"building\":\"" + building +
                "\", \"entrance\":" + entrance +
                ", \"floor\":" + floor +
                ", \"apartment\":" + apartment +
                ", \"firstname\":\"" + firstname +
                "\", \"lastname\":\"" + lastname +
                "\", \"email\":\"" + email +
                "\", \"phone\":\"" + phone +
                "\", \"comment\":\"" + comment +
                "\"}";
    }
}
