package com.belerweb.maohuahua.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 用户的图片
 */
public class UserImage {

  private String id;
  private String name;// 原始文件名
  private String extension;// 扩展名
  private String contentType;
  private String title;// 标题
  private String description;// 描述
  private List<String> tags = new ArrayList<>();
  private Long size;
  private Integer width;
  private Integer height;
  private String userId;// 用户
  private Date date;// 图片日期
  private Date created;
  private Date modified;
  private Boolean uploaded = Boolean.FALSE;

  private String token;// 判断七牛回调是否合法
  private String qiniuKey;// 七牛文件Key

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getExtension() {
    return extension;
  }

  public void setExtension(String extension) {
    this.extension = extension;
  }

  public String getContentType() {
    return contentType;
  }

  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  public Long getSize() {
    return size;
  }

  public void setSize(Long size) {
    this.size = size;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }

  public Date getModified() {
    return modified;
  }

  public void setModified(Date modified) {
    this.modified = modified;
  }

  public Boolean getUploaded() {
    return uploaded;
  }

  public void setUploaded(Boolean uploaded) {
    this.uploaded = uploaded;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getQiniuKey() {
    return qiniuKey;
  }

  public void setQiniuKey(String qiniuKey) {
    this.qiniuKey = qiniuKey;
  }

  public Integer getWidth() {
    return width;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }

  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
  }

}
