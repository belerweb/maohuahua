package com.belerweb.maohuahua.controller;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.belerweb.maohuahua.model.User;
import com.belerweb.maohuahua.model.UserImage;
import com.belerweb.maohuahua.service.ImageService;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.rs.PutPolicy;

@Controller
public class PictureController extends ControllerHelper {

  static final List<String> ALLOWED_EXTENSIONS = Arrays.asList(new String[] {"jpg", "png", "gif"});
  static final List<String> ALLOWED_CONTENT_TYPE = Arrays.asList(new String[] {"image/jpeg",
      "image/png", "image/gif"});
  static final String QINIU_AK = "qiniu.ak";
  static final String QINIU_SK = "qiniu.sk";

  @Autowired
  private ImageService imageService;

  /**
   * 上传图片
   */
  @RequestMapping(method = RequestMethod.GET, value = "/picture/upload")
  public Object upload() {
    return "/v1/picture/upload";
  }

  /**
   * 上传图片的Token
   */
  @RequestMapping("/picture/upload/token")
  public Object upload(@RequestParam String date, @RequestParam String name) {
    Date _date = null;
    try {
      _date = DateUtils.parseDate(date, new String[] {"yyyyMMdd"});
    } catch (ParseException e) {
      e.printStackTrace();
      return error(e.getMessage());
    }
    String accessKey = System.getProperty(QINIU_AK, System.getenv(QINIU_AK));
    String secretKey = System.getProperty(QINIU_SK, System.getenv(QINIU_SK));
    Mac mac = new Mac(accessKey, secretKey);
    String userId = getUser().getId();
    String imageId = UUID.randomUUID().toString();
    String extension = name.substring(name.lastIndexOf(".")).toLowerCase();
    String key = "u/" + userId + "/p/" + date + "/" + imageId + extension;
    PutPolicy putPolicy = new PutPolicy("maohuahua:" + key);
    putPolicy.endUser = userId;
    putPolicy.callbackUrl = "http://maohuahua.com/picture/upload";
    putPolicy.callbackBody =
        "token=$(x:token)&uid=$(x:uid)&imageId=$(x:id)" + "&etag=$(etag)&fname=$(fname)"
            + "&fsize=$(fsize)&mimeType=$(mimeType)" + "&imageInfo=$(imageInfo)&exif=$(exif)"
            + "&width=$(imageInfo.width)&height=$(imageInfo.height)";
    try {
      String token = putPolicy.token(mac);
      Map<String, String> result = new HashMap<>();
      result.put("key", key);
      result.put("token", token);
      result.put("x:id", imageId);
      result.put("x:uid", userId);
      String imageToken = RandomStringUtils.randomAlphanumeric(16);
      result.put("x:token", imageToken);

      UserImage image = new UserImage();
      image.setId(imageId);
      image.setName(name);
      image.setTitle(name);

      image.setExtension(extension.substring(1));
      image.setDate(_date);
      Date current = new Date();
      image.setCreated(current);
      image.setModified(current);
      image.setUserId(userId);
      image.setToken(imageToken);
      image.setQiniuKey(key);
      imageService.addUserImage(image);

      return json(result);
    } catch (Exception e) {
      e.printStackTrace();
      return error(e.getMessage());
    }
  }

  /**
   * 七牛上传图片回调
   */
  @RequestMapping(method = RequestMethod.POST, value = "/picture/upload")
  public Object upload(@RequestParam String token, @RequestParam String uid,
      @RequestParam String imageId, @RequestParam String etag, @RequestParam String fname,
      @RequestParam long fsize, @RequestParam String mimeType, @RequestParam String imageInfo,
      @RequestParam int width, @RequestParam int height, @RequestParam String exif,
      HttpServletRequest request) {
    UserImage image = imageService.getUserImage(imageId);
    if (!token.equals(image.getToken())) {
      return illegal();
    }

    image.setUploaded(Boolean.TRUE);
    image.setSize(fsize);
    image.setContentType(mimeType);
    image.setWidth(width);
    image.setHeight(height);
    imageService.updateImage(image);
    return json(image);
  }

  /**
   * 我的图片
   */
  @RequestMapping("/picture")
  public Object picture(Model model) {
    model.addAttribute("imgs", imageService.getUserImages(getUser().getId()));
    return "/v1/picture/my";
  }

  /**
   * 更改图片信息
   */
  @RequestMapping(method = RequestMethod.POST, value = "/picture/update")
  public Object update(@RequestParam String pk, @RequestParam String name,
      @RequestParam String value) {
    String userId = getUser().getId();
    if ("title".equals(name)) {
      if (StringUtils.isBlank(value)) {
        return error("必须输入标题");
      }

      imageService.updateImage(userId, pk, name, value);
      return ok();
    }
    if ("description".equals(name)) {
      imageService.updateImage(userId, pk, name, value);
      return ok();
    }
    return illegal();
  }


  /**
   * 查看单张图片
   */
  @RequestMapping("/p/{imageId}.html")
  public Object image(HttpServletRequest request, @PathVariable String imageId, Model model) {
    User user = retrieveSiteOwner(request);
    if (user == null) {
      return notFound();
    }

    UserImage image = imageService.getUserImage(imageId);
    if (image == null || !image.getUploaded()) {
      return notFound();
    }

    model.addAttribute("site", userService.getUserSite(user.getId()));
    model.addAttribute("owner", user);
    model.addAttribute("img", image);
    return "/v2/picture";
  }

}
