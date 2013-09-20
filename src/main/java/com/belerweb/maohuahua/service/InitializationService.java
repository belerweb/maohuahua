package com.belerweb.maohuahua.service;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belerweb.maohuahua.DataUpgrade;
import com.belerweb.maohuahua.dao.MongoDao;
import com.belerweb.maohuahua.model.InitializationConfig;

@Service
public class InitializationService implements InitializingBean {

  private static final String CONFIG_DB_HOST = "maohuahua.db.host";
  private static final String CONFIG_DB_NAME = "maohuahua.db.name";
  private static final String CONFIG_DB_USERNAME = "maohuahua.db.username";
  private static final String CONFIG_DB_PASSWORD = "maohuahua.db.password";

  @Autowired
  private MongoDao mongoDao;
  @Autowired
  private DataUpgrade dataUpgrade;

  @Override
  public void afterPropertiesSet() throws Exception {
    InitializationConfig config = new InitializationConfig();
    config.setHost(System.getProperty(CONFIG_DB_HOST, System.getenv(CONFIG_DB_HOST)));
    config.setDbName(System.getProperty(CONFIG_DB_NAME, System.getenv(CONFIG_DB_NAME)));
    config.setUsername(System.getProperty(CONFIG_DB_USERNAME, System.getenv(CONFIG_DB_USERNAME)));
    config.setPassword(System.getProperty(CONFIG_DB_PASSWORD, System.getenv(CONFIG_DB_PASSWORD)));
    mongoDao.init(config);

    dataUpgrade.upgrade();
  }

}
