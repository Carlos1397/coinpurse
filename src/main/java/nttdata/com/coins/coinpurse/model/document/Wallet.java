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
@RedisHash("Wallet")
public class Wallet implements Serializable {
  @Id
  private int id;
  private TypeDocument typeDocument;
  private String numberCell;
  private String email;
}
