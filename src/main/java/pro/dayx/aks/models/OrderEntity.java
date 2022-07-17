package pro.dayx.aks.models;

import pro.dayx.aks.security.services.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_entity")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "suggestion_id")
    private SuggestionEntity suggestionEntity;
    private String date;
    private String time;
    private Boolean paymentOnline;
    private String cardNumber;
    private Integer cardDateExpirationMonth;
    private Integer cardDateExpirationYear;
    private Integer cvv;
    private String paymentOfflineType;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="address_id")
    private AddressEntity address;
    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ",\"userEntity\":" + userEntity +
                ",\"suggestionEntity\":" + suggestionEntity +
                ",\"date\":\"" + date + "\"" +
                ",\"time\":\"" + time + "\"" +
                ",\"paymentOnline\":" + paymentOnline +
                ",\"paymentOfflineType\":\"" + paymentOfflineType + "\"" +
                ",\"cardNumber\":\"" + cardNumber + "\"" +
                ",\"cardDateExpirationMonth\":" + cardDateExpirationMonth +
                ",\"cardDateExpirationYear\":" + cardDateExpirationYear +
                ",\"cvv\":" + cvv +
                ",\"address\":" + address +
                '}';
    }


}
