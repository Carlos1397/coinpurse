package nttdata.com.coins.coinpurse.configredis.controller;


import lombok.extern.slf4j.Slf4j;
import nttdata.com.coins.coinpurse.configredis.repository.MovementsRepositoryRedis;
import nttdata.com.coins.coinpurse.model.document.Movements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/movement")
@EnableCaching
public class MovementsRedisController {
  @Autowired
  private MovementsRepositoryRedis movementsRepositoryRedis;


  @PostMapping
  public Movements save(@RequestBody Movements movements) {
    Movements movements1 = movementsRepositoryRedis.save(movements);
    log.info(movements1.toString());
    return movements;
  }

  @GetMapping
  public List<Movements> getAllBuys() {
    return movementsRepositoryRedis.findAll();
  }

  @GetMapping("/{id}")
  @Cacheable(key = "#id",value = "Movement",unless = "#result.id > 1")
  public Movements findWallet(@PathVariable int id) {
    return movementsRepositoryRedis.findBuyId(id);
  }

  @DeleteMapping("/{id}")
  @CacheEvict(key = "#id",value = "Movement")
  public String remove(@PathVariable int id) {
    return movementsRepositoryRedis.deleteBuy(id);
  }
}
