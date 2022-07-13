package nttdata.com.coins.coinpurse.model.document;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public enum TypePayment {
  YANKI("YANKI"), TRANSFERENCIA("TRANSFERENCIA");
  private String name;
}
