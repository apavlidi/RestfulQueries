package com.apavlidi.core;

import com.apavlidi.exceptions.WrongQueryParam;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class FilterApplier {

  public static void applyRestApiQueries(Query query, Map<String, String> restApiQueries) {
    applySortQueryParam(query, restApiQueries);
    applySelectQueryParam(query, restApiQueries);
    applySearchQueryParam(query, restApiQueries);
    applyPageQueryParam(query, restApiQueries);
    applyPageSizeQueryParam(query, restApiQueries);
  }

  private static void applyPageQueryParam(Query query, Map<String, String> restApiQueries) {
    try {
      if (restApiQueries.get("page") != null) {
        String pageQueryParam = restApiQueries.get("page");
        query.skip(Long.parseLong(pageQueryParam));
      }
    } catch (NumberFormatException pageParamExc) {
      throw new WrongQueryParam();
    }
  }

  private static void applyPageSizeQueryParam(Query query, Map<String, String> restApiQueries) {
    try {
      if (restApiQueries.get("pageSize") != null) {
        String pageSizeQueryParam = restApiQueries.get("pageSize");
        query.limit(Integer.parseInt(pageSizeQueryParam));
      }
    } catch (NumberFormatException pageParamExc) {
      throw new WrongQueryParam();
    }
  }

  private static void applySearchQueryParam(Query query, Map<String, String> restApiQueries) {
    try {
      if (restApiQueries.get("q") != null) {
        JSONObject jsonCriteria = new JSONObject(restApiQueries.get("q"));
        JSONArray keys = jsonCriteria.names();
        for (int i = 0; i < keys.length(); ++i) {
          String field = keys.getString(i);
          String value = jsonCriteria.getString(field);
          query.addCriteria(Criteria.where(field).is(value));
        }
      }
    } catch (JSONException e) {
      throw new WrongQueryParam();
    }
  }

  private static void applySelectQueryParam(Query query, Map<String, String> restApiQueries) {
    if (restApiQueries.get("select") != null) {
      String[] selectedFields = restApiQueries.get("select").split(",");
      for (String selectedField : selectedFields) {
        query.fields().include(selectedField);
      }
    }
  }

  private static void applySortQueryParam(Query query, Map<String, String> restApiQueries) {
    try {
      if (restApiQueries.get("sort") != null) {
        String sortQueryParam = restApiQueries.get("sort");
        Direction sortDir =
            sortQueryParam.substring(0, 1).equals("-") ? Direction.DESC : Direction.ASC;
        String sortBy = sortQueryParam.substring(1);
        query.with(new Sort(sortDir, sortBy));
      }
    } catch (IndexOutOfBoundsException subStringException) {
      throw new WrongQueryParam();
    }
  }
}
