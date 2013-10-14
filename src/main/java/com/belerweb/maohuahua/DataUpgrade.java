package com.belerweb.maohuahua;

import java.util.ArrayList;
import java.util.Date;
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
    Version version = mongoDao.createQuery("Version").findObject(Version.class);
    version = nullToV1(version);
    version = v1ToV2(version);
    mongoDao.createQuery("Version").modify().delete();
    mongoDao.createObject("Version", this.version);
  }

  private Version nullToV1(Version version) {
    if (version == null) {
      version = new Version();
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

    version.setId(1);
    return version;
  }

  private Version v1ToV2(Version version) {
    if (version.getId() == 1) {
      List<User> users = mongoDao.createQuery("User").findObjects(User.class).readAll();
      for (User user : users) {
        if (user.getCreated() == null || user.getModified() == null) {
          user.setCreated(new Date());
          user.setModified(user.getCreated());
          mongoDao.updateObject("User", user.getId(), user);
        }
      }
    }
    version.setId(1);
    return version;
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    upgrade();
  }
}
