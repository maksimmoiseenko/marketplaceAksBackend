package pro.dayx.aks.models;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data

@Table(name = "menu_type", schema = "public")
public class MenuTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @Column(name = "icon", nullable = false)
    private String icon;
    @Column(name = "sort_order")
    private int sortOrder;
    @Column(name = "filter_by_event_type")
    private String filterByEventType;
}
