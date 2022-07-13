package nttdata.com.coins.coinpurse.configredis.repository;

import lombok.extern.slf4j.Slf4j;
import nttdata.com.coins.coinpurse.kafka.ProducerKafka;
import nttdata.com.coins.coinpurse.model.document.Movements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class MovementsRepositoryRedis {
  public static final String HASH_KEY = "Movements";


  @Autowired
  private ProducerKafka producerKafka;
  private final String kafkaTopic ="topicMovements";
  @Autowired
  private RedisTemplate template;

  public Movements save(Movements movements){
    log.info("SAVED: "+ movements.toString());
    if (movements.getTypeChange().getName().equals("COMPRA")){
      movements.setAmountOfFee(movements.getAmount()*2);
      producerKafka.send(kafkaTopic, movements.getTypePayment().getName(), movements);
      template.opsForHash().put(HASH_KEY, movements.getId(), movements);
    }else if (movements.getTypeChange().getName().equals("VENTA")){
      movements.setAmountOfFee(movements.getAmount()*4);
      producerKafka.send(kafkaTopic, movements.getTypePayment().getName(), movements);
      template.opsForHash().put(HASH_KEY, movements.getId(), movements);
    } else {
      return null;
    }
    return movements;
  }

  public List<Movements> findAll(){
    return template.opsForHash().values(HASH_KEY);
  }

  public Movements findBuyId(int id){
    log.info("llamado Movements findProductById() DESDE CACHÉ REDIS: "+template.opsForHash().get(HASH_KEY,id));
    return (Movements) template.opsForHash().get(HASH_KEY,id);
  }


  public String deleteBuy(int id){
    template.opsForHash().delete(HASH_KEY,id);
    return "BUY REMOVED !!";
  }
}
