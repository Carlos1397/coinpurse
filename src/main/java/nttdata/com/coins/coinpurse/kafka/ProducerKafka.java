package nttdata.com.coins.coinpurse.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nttdata.com.coins.coinpurse.model.document.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Data
public class ProducerKafka {

  @Autowired
  private KafkaTemplate<String, Object> kafkaTemplate;

  private  String kafkaTopic;

  public void send(String kafkaTopic,String key, Object ob) {
    kafkaTemplate.send(kafkaTopic,key, ob);
  }
}
