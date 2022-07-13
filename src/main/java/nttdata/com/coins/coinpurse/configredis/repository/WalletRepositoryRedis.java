package nttdata.com.coins.coinpurse.configredis.repository;

import lombok.extern.slf4j.Slf4j;
import nttdata.com.coins.coinpurse.kafka.ProducerKafka;
import nttdata.com.coins.coinpurse.model.document.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class WalletRepositoryRedis {
  public static final String HASH_KEY = "Wallet";

  @Autowired
  private ProducerKafka producerKafka;

  private final String kafkaTopic ="topicWallet";
  @Autowired
  private RedisTemplate template;

  public Wallet save(Wallet wallet){
    log.info("SAVED: "+wallet.toString());
    producerKafka.send(kafkaTopic,wallet.getTypeDocument().getName(),wallet);
    template.opsForHash().put(HASH_KEY,wallet.getId(),wallet);
    return wallet;
  }

  public List<Wallet> findAll(){
    return template.opsForHash().values(HASH_KEY);
  }

  public Wallet findWalletyId(int id){
    log.info("llamado Wallet findProductById() DESDE CACHÉ REDIS: "+template.opsForHash().get(HASH_KEY,id));
    return (Wallet) template.opsForHash().get(HASH_KEY,id);
  }


  public String deleteWallet(int id){
    template.opsForHash().delete(HASH_KEY,id);
    return "WALLET REMOVED !!";
  }
}
