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

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ",\"userEntity\":" + userEntity +
                ",\"suggestionEntity\":" + suggestionEntity +
                '}';
    }
    public OrderEntity(UserEntity client, SuggestionEntity suggestion){
        this.userEntity = client;
        this.suggestionEntity = suggestion;
    }
}
