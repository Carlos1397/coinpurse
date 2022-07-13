package nttdata.com.coins.coinpurse.configredis.controller;

import lombok.extern.slf4j.Slf4j;
import nttdata.com.coins.coinpurse.configredis.repository.SwapRepositoryRedis;
import nttdata.com.coins.coinpurse.model.document.Swap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/v1/swap")
@EnableCaching
public class SwapRedisController {
  @Autowired
  private SwapRepositoryRedis swapRepositoryRedis;


  @PostMapping
  public Swap save(@RequestBody Swap swap) {
    Swap swap1= swapRepositoryRedis.save(swap);
    log.info(swap1.toString());
    return swap ;
  }

  @GetMapping
  public List<Swap> getAllBuys() {
    return swapRepositoryRedis.findAll();
  }

  @GetMapping("/{id}")
  @Cacheable(key = "#id",value = "Wallet",unless = "#result.id > 1")
  public Swap findWallet(@PathVariable int id) {
    return swapRepositoryRedis.findByIdSwap(id);
  }

  @DeleteMapping("/{id}")
  @CacheEvict(key = "#id",value = "Wallet")
  public String remove(@PathVariable int id) {
    return swapRepositoryRedis.deleteSwap(id);
  }
}
