package com.belerweb.maohuahua.service;

import java.util.UUID;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.belerweb.maohuahua.dao.MongoDao;
import com.belerweb.maohuahua.model.Site;
import com.belerweb.maohuahua.model.User;

@Service
public class UserService implements UserDetailsService {

  @Autowired
  private MongoDao mongoDao;

  @Autowired
  private FreeMarkerConfigurer freeMarkerConfigurer;

  public User getUser(String property, Object value) {
    return mongoDao.createQuery("User").eq(property, value).findObject(User.class);
  }

  public User getUser(String id) {
    return mongoDao.findById("User", User.class, id);
  }

  public User signup(String with, String account) {
    User user = new User();
    user.setId(UUID.randomUUID().toString());
    if ("email".equals(with)) {
      user.setEmail(account);
      user.setNickname(account.substring(0, account.indexOf("@")));
      user.setAvatar(new Md5PasswordEncoder().encodePassword(account, null));
    }
    if ("mobile".equals(with)) {
      user.setMobile(account);
      user.setNickname(account);
      user.setAvatar(null);
    }
    String password = RandomStringUtils.randomNumeric(6);
    user.setPassword(new ShaPasswordEncoder(256).encodePassword(password, null));
    user.setNeedChangePwd(true);
    user = mongoDao.createObject("User", user);
    user.setPassword(password);

    // 保存用户的网站配置
    Site site = new Site();
    site.setId(user.getId());
    site.setName("猫画画");
    site.setTitle(account + "的网站");
    site.setDescription("猫画画，用心纪录你画画的每一天！");
    site.setTemplate("v2");
    site.getDomains().add(account.replaceAll("[@\\.]", "-") + ".maohuahua.com");
    mongoDao.createObject("Site", site);
    return user;
  }

  public Site getUserSite(String userId) {
    return mongoDao.findById("Site", Site.class, userId);
  }

  public Site getUserSiteByDomain(String domain) {
    return mongoDao.createQuery("Site").in("domains", domain).findObject(Site.class);
  }

  public void updateUserSite(String userId, String property, Object value) {
    mongoDao.createQuery("Site").eq("_id", userId).modify().set(property, value).update();
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    String property = null;
    if (username.matches("^\\d+$")) {
      property = "mobile";
    } else if (username.contains("@")) {
      property = "email";
    } else {
      property = "username";
    };

    User user = mongoDao.createQuery("User").eq(property, username).findObject(User.class);
    if (user == null) {
      throw new UsernameNotFoundException("用户不存在");
    }
    return new User.UserWrapper(username, user);
  }
}
