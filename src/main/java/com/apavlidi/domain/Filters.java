package com.apavlidi.domain;

public enum Filters {
  PAGE_SIZE("pageSize"),
  PAGE("page"),
  SEARCH("q"),
  SELECT("select"),
  SORT("sort");

  private String code;

  Filters(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }

}
