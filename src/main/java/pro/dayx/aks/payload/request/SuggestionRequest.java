package pro.dayx.aks.payload.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SuggestionRequest {
    private Double price;
    private String description;
    private Integer amountOfDays;
    private String currency;
    private Integer left;
    private String presence;
    private String unit;
    //private String description;
}
