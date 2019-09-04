package com.apavlidi.core;

import java.util.HashMap;
import java.util.Map;

public class FilterCollector {

  public static Map<String, String> collectRestApiParams(Map<String, String> filters) {
    Map<String, String> restApiQueries = new HashMap<>();

    collectSortFilter(restApiQueries, filters);
    collectPageFilter(restApiQueries, filters);
    collectPageSizeFilter(restApiQueries, filters);
    collectQFilter(restApiQueries, filters);
    collectSelectFilter(restApiQueries, filters);

    return restApiQueries;
  }

  private static void collectQFilter(Map<String, String> restApiQueries,
      Map<String, String> filters) {
    if (filters.containsKey("q")) {
      restApiQueries.put("q", filters.get("q"));
    }
  }

  private static void collectPageSizeFilter(Map<String, String> restApiQueries,
      Map<String, String> filters) {
    if (filters.containsKey("pageSize")) {
      restApiQueries.put("pageSize", filters.get("pageSize"));
    }
  }

  private static void collectSelectFilter(Map<String, String> restApiQueries,
      Map<String, String> filters) {
    if (filters.containsKey("select")) {
      restApiQueries.put("select", filters.get("select"));
    }
  }

  private static void collectPageFilter(Map<String, String> restApiQueries,
      Map<String, String> filters) {
    if (filters.containsKey("page")) {
      restApiQueries.put("page", filters.get("page"));
    }
  }

  private static void collectSortFilter(Map<String, String> restApiQueries,
      Map<String, String> filters) {
    if (filters.containsKey("sort")) {
      restApiQueries.put("sort", filters.get("sort"));
    }
  }


}
