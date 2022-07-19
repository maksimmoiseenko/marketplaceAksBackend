package pro.dayx.aks.payload.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SuggestionAndObjectRequest {
    private Double price;
    private String descriptionObject;
    private String descriptionSuggestion;
    private Integer amountOfDays;
    private String category;
    private String currency;
    private Integer left;
    private String name;
    private String presence;
    private String merchandiseOrService;
    private String unit;

    @Override
    public String toString() {
        String string = "{" +
                "\"category\":\"" + category +
                "\", \"currency\":\"" + currency +
                "\", \"name\":\"" + name +
                "\", \"presence\":\"" + presence +
                "\", \"merchandiseOrService\":\"" + merchandiseOrService +
                "\", \"unit\":\"" + unit +
                "\", \"price\":" + price +
                ",\"amountOfDays\":" + amountOfDays +
                ",\"left\":" + left + "}"
                ;
        if(descriptionObject == null)
            string+=(",\"descriptionObject\":" + descriptionObject);
        else
            string+=(",\"descriptionObject\":\"" + descriptionObject + "\"");
        if(descriptionSuggestion == null)
            string+=(",\"descriptionSuggestion\":" + descriptionSuggestion);
        else
            string+=(",\"descriptionSuggestion\":\"" + descriptionSuggestion + "\"");
        return string;
    }
}
