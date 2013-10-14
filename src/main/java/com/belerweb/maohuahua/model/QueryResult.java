package com.belerweb.maohuahua.model;

import java.util.List;

public class QueryResult<T> {

  private int page;
  private int pageSize;
  private long total;
  private List<T> items;

  public QueryResult(int page, int pageSize) {
    this.page = page;
    this.pageSize = pageSize;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }

  public List<T> getItems() {
    return items;
  }

  public void setItems(List<T> items) {
    this.items = items;
  }

  public int getStart() {
    return page * pageSize;
  }

}
