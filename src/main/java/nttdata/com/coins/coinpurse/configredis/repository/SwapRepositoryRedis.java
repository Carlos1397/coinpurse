package nttdata.com.coins.coinpurse.configredis.repository;

import lombok.extern.slf4j.Slf4j;
import nttdata.com.coins.coinpurse.kafka.ProducerKafka;
import nttdata.com.coins.coinpurse.model.document.Swap;
import nttdata.com.coins.coinpurse.model.document.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class SwapRepositoryRedis {
  public static final String HASH_KEY = "Swap";

  @Autowired
  private ProducerKafka producerKafka;

  private final String kafkaTopic ="topicSwap";
  @Autowired
  private RedisTemplate template;

  public Swap save(Swap swap){
    log.info("SAVED: "+swap.toString());
    producerKafka.send(kafkaTopic,swap.getCodTransaction(),swap);
    template.opsForHash().put(HASH_KEY,swap.getId(),swap);
    return swap;
  }

  public List<Swap> findAll(){
    return template.opsForHash().values(HASH_KEY);
  }

  public Swap findByIdSwap(int id){
    log.info("llamado Swap findProductById() DESDE CACHÉ REDIS: "+template.opsForHash().get(HASH_KEY,id));
    return (Swap) template.opsForHash().get(HASH_KEY,id);
  }


  public String deleteSwap(int id){
    template.opsForHash().delete(HASH_KEY,id);
    return "Swap REMOVED !!";
  }
}
