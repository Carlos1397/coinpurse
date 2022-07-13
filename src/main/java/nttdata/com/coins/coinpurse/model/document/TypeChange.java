package nttdata.com.coins.coinpurse.model.document;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public enum TypeChange {
  COMPRA("COMPRA"), VENTA("VENTA");
  private String name;
}
