package pro.dayx.aks.models;

import lombok.*;

import javax.persistence.*;


import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(name = "menu_object", schema = "public")
public class MenuObjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    @JoinColumn(name = "type_id", referencedColumnName = "id", nullable = false)
    private MenuTypeEntity menuTypeEntity;
//    @Column(name = "image_url", nullable = false)
//    private String imageUrl;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "description")
    private String description;
    @Column
    private String merchandiseOrService;


    public MenuObjectEntity(MenuTypeEntity menuTypeEntity, String name, String description, String merchandiseOrService) {
        this.menuTypeEntity = menuTypeEntity;
        this.name = name;
        this.description = description;
        this.merchandiseOrService = merchandiseOrService;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                //", \"menuTypeEntity\":" + menuTypeEntity +
                //", \"imageUrl\":\"" + imageUrl + "\"" +
                ", \"name\":\"" + name + "\"" +
                ", \"description\":\"" + description + "\"" +
                ", \"merchandiseOrService\":\"" + merchandiseOrService + "\"" +
                '}';
    }

    @OneToMany(mappedBy = "menuObjectEntity",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SuggestionEntity> suggestionEntities = new ArrayList<>();

}
