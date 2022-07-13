package nttdata.com.coins.coinpurse.model.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Getter
@NoArgsConstructor
public enum TypeDocument {
  DNI("DNI"), CEX("CEX"), PASSPORT("PASSPORT");
  private String name;
}
