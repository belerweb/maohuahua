package com.belerweb.maohuahua.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belerweb.maohuahua.dao.MongoDao;
import com.belerweb.maohuahua.model.UserImage;

@Service
public class ImageService {

  @Autowired
  private MongoDao mongoDao;

  public void addUserImage(UserImage image) {
    mongoDao.createObject("UserImage", image);
  }

  public UserImage getUserImage(String imageId) {
    return mongoDao.findById("UserImage", UserImage.class, imageId);
  }

  public List<UserImage> getUserImages(String userId) {
    // mongoDao.ensureIndex("UserImage", "date", true, false, false);
    return mongoDao.createQuery("UserImage").eq("userId", userId).eq("uploaded", Boolean.TRUE)
        .addSort("date", -1).findObjects(UserImage.class).readAll();
  }

  public void updateImage(String userId, String imageId, String property, Object value) {
    mongoDao.createQuery("UserImage").eq("userId", userId).eq("_id", imageId).modify()
        .set(property, value).set("modified", new Date()).update();
  }

  public void updateImage(UserImage image) {
    mongoDao.updateObject("UserImage", image.getId(), image);
  }

}
