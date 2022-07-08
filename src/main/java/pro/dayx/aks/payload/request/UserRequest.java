package pro.dayx.aks.payload.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserRequest {
   private String firstname;
   private String lastname;
   private String patronymic;
   private String sex;

   @Override
   public String toString() {
      return "UserRequest{" +
              "firstname='" + firstname + '\'' +
              ", lastname='" + lastname + '\'' +
              ", patronymic='" + patronymic + '\'' +
              ", sex='" + sex + '\'' +
              '}';
   }
}
