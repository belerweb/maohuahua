package com.belerweb.maohuahua.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.belerweb.maohuahua.dao.MongoDao;
import com.belerweb.maohuahua.model.UserImage;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;

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
    mongoDao.ensureIndex("UserImage", "date", true, false, false);
    DBCursor objects =
        mongoDao.createQuery("UserImage").eq("userId", userId).addSort("date", -1)
            .findObjects(new BasicDBObject("data", 0));
    List<UserImage> result = new ArrayList<>();
    while (objects.hasNext()) {
      result.add(mongoDao.map(objects.next(), UserImage.class));
    }
    return result;
  }
}
