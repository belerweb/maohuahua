package com.belerweb.maohuahua.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.belerweb.maohuahua.model.UserImage;
import com.belerweb.maohuahua.service.ImageService;

@Controller
public class PictureController extends ControllerHelper {

  private static final List<String> ALLOWED_EXTENSIONS = Arrays.asList(new String[] {"jpg", "png",
      "gif"});
  private static final List<String> ALLOWED_CONTENT_TYPE = Arrays.asList(new String[] {
      "image/jpeg", "image/png", "image/gif"});

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
   * 上传图片
   */
  @RequestMapping(method = RequestMethod.POST, value = "/picture/upload")
  public Object upload(@RequestParam String date, @RequestParam MultipartFile file) {
    String name = file.getOriginalFilename();
    String extension = name.substring(name.lastIndexOf(".") + 1).toLowerCase();
    if (!ALLOWED_EXTENSIONS.contains(extension)) {
      return error("只支持 jpg/png/gif 文件。");
    }

    String contentType = file.getContentType().toLowerCase();
    if (!ALLOWED_CONTENT_TYPE.contains(contentType)) {
      return error("不支持的文件。");
    }

    UserImage image = new UserImage();
    image.setId(UUID.randomUUID().toString());

    try {
      name = new String(name.getBytes("ISO8859-1"));
      image.setName(name);
      image.setTitle(name);
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      return error(e.getMessage());
    }

    image.setExtension(extension);
    image.setContentType(contentType);
    // image.setDescription(description);
    // image.setTags(tags);
    try {
      image.setData(file.getBytes());
    } catch (IOException e) {
      e.printStackTrace();
      return error(e.getMessage());
    }
    image.setSize(file.getSize());
    try {
      image.setDate(DateUtils.parseDate(date, new String[] {"yyyyMMdd"}));
    } catch (ParseException e) {
      e.printStackTrace();
      return error(e.getMessage());
    }

    Date current = new Date();
    image.setCreated(current);
    image.setModified(current);
    image.setUserId(getUser().getId());
    imageService.addUserImage(image);
    image.setData(null);
    return json(image);
  }

  /**
   * 我的图片
   */
  @RequestMapping("/picture")
  public Object picture(Model model) {
    return "/v1/picture";
  }

  /**
   * 查看图片
   */
  @RequestMapping(value = "/image/user/{imageId}.{extention:jpg|png|gif}", method = RequestMethod.GET)
  public Object image(@PathVariable String imageId, @PathVariable String extention,
      @RequestHeader(value = "If-Modified-Since", required = false) String ifModifiedSince) {
    if (ifModifiedSince != null) {
      return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    UserImage image = imageService.getUserImage(imageId);
    if (!extention.equals(image.getExtension())) {
      return new ResponseEntity<byte[]>(null, null, HttpStatus.NOT_FOUND);
    }

    HttpHeaders headers = new HttpHeaders();
    headers.setDate(System.currentTimeMillis());
    headers.setLastModified(image.getModified().getTime());
    headers.setExpires(System.currentTimeMillis() + 31536000000L);
    headers.setCacheControl("max-age=31536000000");
    headers.setContentType(MediaType.valueOf(image.getContentType()));
    return new ResponseEntity<byte[]>(image.getData(), headers, HttpStatus.OK);
  }
}
