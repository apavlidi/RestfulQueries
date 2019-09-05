package com.apavlidi.core;

import java.util.Map;

public class RestFullAPI {

  public static Map<String, String> collectRestApiParams(Map<String, String> filters) {
    return FilterCollector.collectRestApiParams(filters);
  }

}
