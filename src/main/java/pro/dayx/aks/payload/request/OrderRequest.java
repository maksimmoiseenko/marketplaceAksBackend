package pro.dayx.aks.payload.request;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderRequest {
    private String date;
    private String time;
    private Boolean paymentOnline;
    private String cardNumber;
    private Integer cardDateExpirationMonth;
    private Integer cardDateExpirationYear;
    private Integer cvv;
    private String paymentOfflineType;
    private Long addressId;
    private Long suggestionId;
    private String comment;
}
