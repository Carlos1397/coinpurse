package nttdata.com.coins.coinpurse.model.document;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Swap")
public class Swap implements Serializable {
  @Id
  private int id;
  private int idBuy;
  private int codUser;
  private final String codTransaction = UUID.randomUUID().toString();
}
