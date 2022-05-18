package com.zhangyj.spring.business.circularreferences;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * OrderService、TradeService发生循环依赖，但可以启动成功，原因是spring的三级缓存
 * 配置：spring.main.allow-circular-references=true
 * @author mghio
 * @since 2021-07-17
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class OrderService {

  @Autowired
  private TradeService tradeService;

  public void testCreateOrder() {
    // omit business logic ...
  }

}
