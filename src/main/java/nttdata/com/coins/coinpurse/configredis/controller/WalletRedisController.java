package nttdata.com.coins.coinpurse.configredis.controller;

import lombok.extern.slf4j.Slf4j;
import nttdata.com.coins.coinpurse.configredis.repository.WalletRepositoryRedis;
import nttdata.com.coins.coinpurse.model.document.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/wallet")
@EnableCaching
public class WalletRedisController {
  @Autowired
  private WalletRepositoryRedis walletRepositoryRedis;


  @PostMapping
  public Wallet save(@RequestBody Wallet wallet) {
    Wallet wallet1= walletRepositoryRedis.save(wallet);
    log.info(wallet1.toString());
    return wallet1 ;
  }

  @GetMapping
  public List<Wallet> getAllWallets() {
    return walletRepositoryRedis.findAll();
  }

  @GetMapping("/{id}")
  @Cacheable(key = "#id",value = "Wallet",unless = "#result.id > 1")
  public Wallet findWallet(@PathVariable int id) {
    return walletRepositoryRedis.findWalletyId(id);
  }

  @DeleteMapping("/{id}")
  @CacheEvict(key = "#id",value = "Wallet")
  public String remove(@PathVariable int id) {
    return walletRepositoryRedis.deleteWallet(id);
  }
}
