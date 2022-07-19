package pro.dayx.aks.models;

import pro.dayx.aks.security.services.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "suggestion_entity", schema = "public")
public class SuggestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private UserEntity userEntity;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "object_id")
    private MenuObjectEntity menuObjectEntity;
    @Column
    private double price;
    @Column
    private String currency;
    @Column
    private String presence;
    @Column
    private String unit;
    @Column
    private String description;
    @Column
    private Integer amountOfDays;
    @Column(name = "left_amount")
    private Integer left;
    @OneToMany(mappedBy = "suggestionEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderEntity> orderList = new ArrayList<>();
    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"userEntity\":" + userEntity +
                ", \"menuObjectEntity\":" + menuObjectEntity +
                ", \"price\":" + price +
                ", \"currency\":\"" + currency +
                "\", \"presence\":\"" + presence +
                "\", \"unit\":\"" + unit +
                "\",\"amountOfDays\":" + amountOfDays +
                ",\"description\":\"" + description +
                "\",\"left\":" + left +
                '}';
    }

    public SuggestionEntity(UserEntity userEntity, MenuObjectEntity menuObjectEntity, double price, String currency, String presence, String unit, String description, Integer amountOfDays, Integer left) {
        this.userEntity = userEntity;
        this.menuObjectEntity = menuObjectEntity;
        this.price = price;
        this.currency = currency;
        this.presence = presence;
        this.unit = unit;
        this.description = description;
        this.amountOfDays = amountOfDays;
        this.left = left;
    }
}
