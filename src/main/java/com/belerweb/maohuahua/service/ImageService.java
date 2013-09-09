package com.belerweb.maohuahua.service;

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

}
