package com.zhangyj.spring.business.circularreferences;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mghio
 * @since 2021-07-17
 */
@Slf4j
@Service
public class TradeService {

  @Autowired
  private OrderService orderService;

  public void testCreateTrade() { 
    // omit business logic ...
   }

}
