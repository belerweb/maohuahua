package com.belerweb.maohuahua.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户的网站
 */
public class Site {

  private String id;// 用户ID
  private String name;// 网站的名称
  private String title;// 网站的标题
  private String description;// 网站的描述
  private List<String> keywords = new ArrayList<>();// 关键词
  private String template;// 模板

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

  public List<String> getKeywords() {
    return keywords;
  }

  public void setKeywords(List<String> keywords) {
    this.keywords = keywords;
  }

  public String getTemplate() {
    return template;
  }

  public void setTemplate(String template) {
    this.template = template;
  }

}
