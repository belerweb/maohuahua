package com.belerweb.maohuahua;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.belerweb.maohuahua.dao.MongoDao;
import com.belerweb.maohuahua.model.Site;
import com.belerweb.maohuahua.model.User;
import com.belerweb.maohuahua.model.Version;

@Component
public class DataUpgrade implements InitializingBean {

  @Autowired
  private MongoDao mongoDao;
  @Autowired
  private Version version;

  private void upgrade() {
    nullToV1();
  }

  private void nullToV1() {
    Version version = mongoDao.createQuery("Version").findObject(Version.class);
    if (version == null) {
      List<User> users = mongoDao.createQuery("User").findObjects(User.class).readAll();
      for (User user : users) {
        Site site = mongoDao.findById("Site", Site.class, user.getId());
        List<String> domains = site.getDomains();// new ArrayList<String>();
        if (domains == null || domains.isEmpty()) {
          domains = new ArrayList<>();
          domains.add(user.getSubDomain().replaceAll("[@\\.]", "-") + ".maohuahua.com");
          site.setDomains(domains);
          mongoDao.updateObject("Site", site.getId(), site);
        }
        mongoDao.createQuery("User").modify().unset("subDomain").update();
      }
    }
    mongoDao.createObject("Version", this.version);
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    upgrade();
  }
}
