package com.apavlidi.core;

import static com.apavlidi.domain.Filters.PAGE;
import static com.apavlidi.domain.Filters.PAGE_SIZE;
import static com.apavlidi.domain.Filters.SEARCH;
import static com.apavlidi.domain.Filters.SELECT;
import static com.apavlidi.domain.Filters.SORT;

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

  public static final String FIELD_DECIMETER = ",";

  public static void applyRestApiQueries(Query query, Map<String, String> restApiQueries) {
    applySortQueryParam(query, restApiQueries);
    applySelectQueryParam(query, restApiQueries);
    applySearchQueryParam(query, restApiQueries);
    applyPageQueryParam(query, restApiQueries);
    applyPageSizeQueryParam(query, restApiQueries);
  }

  public static void applyPageQueryParam(Query query, Map<String, String> restApiQueries) {
    try {
      if (restApiQueries.get(PAGE.getCode()) != null) {
        String pageQueryParam = restApiQueries.get(PAGE.getCode());
        long skip = Long.parseLong(pageQueryParam);
        if (skip < 0) {
          throw new NumberFormatException();
        }
        query.skip(skip);
      }
    } catch (NumberFormatException pageParamExc) {
      throw new WrongQueryParam();
    }
  }

  public static void applyPageSizeQueryParam(Query query, Map<String, String> restApiQueries) {
    try {
      if (restApiQueries.get(PAGE_SIZE.getCode()) != null) {
        String pageSizeQueryParam = restApiQueries.get(PAGE_SIZE.getCode());
        int limit = Integer.parseInt(pageSizeQueryParam);
        if (limit < 0) {
          throw new NumberFormatException();
        }
        query.limit(limit);
      }
    } catch (NumberFormatException pageParamExc) {
      throw new WrongQueryParam();
    }
  }

  public static void applySearchQueryParam(Query query, Map<String, String> restApiQueries) {
    try {
      if (restApiQueries.get(SEARCH.getCode()) != null) {
        JSONObject jsonCriteria = new JSONObject(restApiQueries.get(SEARCH.getCode()));
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

  public static void applySelectQueryParam(Query query, Map<String, String> restApiQueries) {
    if (restApiQueries.get(SELECT.getCode()) != null) {
      String[] selectedFields = restApiQueries.get(SELECT.getCode()).split(FIELD_DECIMETER);
      for (String selectedField : selectedFields) {
        query.fields().include(selectedField);
      }
    }
  }

  public static void applySortQueryParam(Query query, Map<String, String> restApiQueries) {
    try {
      if (restApiQueries.get(SORT.getCode()) != null) {
        String sortQueryParam = restApiQueries.get(SORT.getCode());
        Direction sortDir =
            sortQueryParam.substring(0, 1).equals("-") ? Direction.DESC : Direction.ASC;
        String sortBy = sortDir.equals(Direction.DESC) ? sortQueryParam.substring(1)
            : sortQueryParam;
        query.with(new Sort(sortDir, sortBy));
      }
    } catch (IndexOutOfBoundsException subStringException) {
      throw new WrongQueryParam();
    }
  }
}
