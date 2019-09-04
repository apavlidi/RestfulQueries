package com.apavlidi.core;

import java.util.Map;
import org.springframework.data.mongodb.core.query.Query;

public class RestFullAPI {

  public static Map<String, String> collectRestApiParams(Map<String, String> filters) {
    return FilterCollector.collectRestApiParams(filters);
  }

  public static void applyRestApiQueries(Query query, Map<String, String> restApiQueries) {
    FilterApplier.applyRestApiQueries(query, restApiQueries);
  }

}
