package nttdata.com.coins.coinpurse.model.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Movements")
public class Movements implements Serializable {
  @Id
  private int id;
  private TypePayment typePayment;
  private double amount;
  private String idWallet;
  private TypeChange typeChange;
  private double amountOfFee;

}
