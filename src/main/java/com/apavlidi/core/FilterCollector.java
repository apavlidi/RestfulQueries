package com.apavlidi.core;

import static com.apavlidi.domain.Filter.PAGE;
import static com.apavlidi.domain.Filter.PAGE_SIZE;
import static com.apavlidi.domain.Filter.SEARCH;
import static com.apavlidi.domain.Filter.SELECT;
import static com.apavlidi.domain.Filter.SORT;

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
    if (filters.containsKey(SEARCH.getCode())) {
      restApiQueries.put(SEARCH.getCode(), filters.get(SEARCH.getCode()));
    }
  }

  private static void collectPageSizeFilter(Map<String, String> restApiQueries,
      Map<String, String> filters) {
    if (filters.containsKey(PAGE_SIZE.getCode())) {
      restApiQueries.put(PAGE_SIZE.getCode(), filters.get(PAGE_SIZE.getCode()));
    }
  }

  private static void collectSelectFilter(Map<String, String> restApiQueries,
      Map<String, String> filters) {
    if (filters.containsKey(SELECT.getCode())) {
      restApiQueries.put(SELECT.getCode(), filters.get(SELECT.getCode()));
    }
  }

  private static void collectPageFilter(Map<String, String> restApiQueries,
      Map<String, String> filters) {
    if (filters.containsKey(PAGE.getCode())) {
      restApiQueries.put(PAGE.getCode(), filters.get(PAGE.getCode()));
    }
  }

  private static void collectSortFilter(Map<String, String> restApiQueries,
      Map<String, String> filters) {
    if (filters.containsKey(SORT.getCode())) {
      restApiQueries.put(SORT.getCode(), filters.get(SORT.getCode()));
    }
  }


}
