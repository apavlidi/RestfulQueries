package com.apavlidi.domain;

public enum Filter {
  PAGE_SIZE("pageSize"),
  PAGE("page"),
  SEARCH("q"),
  SELECT("select"),
  SORT("sort");

  private String code;

  Filter(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }

}
