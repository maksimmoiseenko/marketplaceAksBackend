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
}
