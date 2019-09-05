package com.apavlidi.resftullAPI;

import static com.apavlidi.core.FilterApplier.applyPageQueryParam;
import static com.apavlidi.core.FilterApplier.applyPageSizeQueryParam;
import static com.apavlidi.core.FilterApplier.applySelectQueryParam;
import static com.apavlidi.core.FilterApplier.applySortQueryParam;
import static com.apavlidi.domain.Filter.PAGE;
import static com.apavlidi.domain.Filter.PAGE_SIZE;
import static com.apavlidi.domain.Filter.SELECT;
import static com.apavlidi.domain.Filter.SORT;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.apavlidi.exceptions.WrongQueryParam;
import java.util.HashMap;
import java.util.Map;
import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.Test;
import org.springframework.data.mongodb.core.query.Query;

public class FilterApplierTest {

  @Test
  public void applyPageQueryParam_shouldApplySkipParamOnTheQuery() {
    Query query = new Query();
    Map<String, String> restApiQueries = new HashMap<>();
    restApiQueries.put(PAGE.getCode(), "1");
    applyPageQueryParam(query, restApiQueries);

    assertThat(query.getSkip(), is(1L));

    query = new Query();
    restApiQueries = new HashMap<>();
    restApiQueries.put(PAGE.getCode(), "5");
    applyPageQueryParam(query, restApiQueries);
    assertThat(query.getSkip(), is(5L));
  }

  @Test
  public void applyPageQueryParam_with_negative_value_should_throw_WrongQueryParam() {
    Query query = new Query();
    Map<String, String> restApiQueries = new HashMap<>();
    restApiQueries.put(PAGE.getCode(), "-1");

    assertThrows(WrongQueryParam.class, () -> applyPageQueryParam(query, restApiQueries));
  }

  @Test
  public void applyPageSizeQueryParam_shouldApplyLimitParamOnTheQuery() {
    Query query = new Query();
    Map<String, String> restApiQueries = new HashMap<>();
    restApiQueries.put(PAGE_SIZE.getCode(), "1");
    applyPageSizeQueryParam(query, restApiQueries);

    assertThat(query.getLimit(), is(1));

    query = new Query();
    restApiQueries = new HashMap<>();
    restApiQueries.put(PAGE_SIZE.getCode(), "5");
    applyPageSizeQueryParam(query, restApiQueries);
    assertThat(query.getLimit(), is(5));
  }

  @Test
  public void applyPageSizeQueryParam_with_negative_value_should_throw_WrongQueryParam() {
    Query query = new Query();
    Map<String, String> restApiQueries = new HashMap<>();
    restApiQueries.put(PAGE_SIZE.getCode(), "-1");
    assertThrows(WrongQueryParam.class, () -> applyPageSizeQueryParam(query, restApiQueries));
  }

  @Test
  public void applySelectQueryParam_shouldApplyLimitParamOnTheQuery() {
    Query query = new Query();
    Map<String, String> restApiQueries = new HashMap<>();
    restApiQueries.put(SELECT.getCode(), "username");
    applySelectQueryParam(query, restApiQueries);

    assertThat(query.fields().getFieldsObject().size(), is(1));
    assertThat(query.fields().getFieldsObject(), IsMapContaining.hasKey("username"));
    assertThat(query.fields().getFieldsObject().get("username"), is(1));

    query = new Query();
    restApiQueries = new HashMap<>();
    restApiQueries.put(SELECT.getCode(), "username,firstName,lastName");
    applySelectQueryParam(query, restApiQueries);

    assertThat(query.fields().getFieldsObject().size(), is(3));
    assertThat(query.fields().getFieldsObject(), IsMapContaining.hasKey("username"));
    assertThat(query.fields().getFieldsObject(), IsMapContaining.hasKey("firstName"));
    assertThat(query.fields().getFieldsObject(), IsMapContaining.hasKey("lastName"));
    assertThat(query.fields().getFieldsObject().get("username"), is(1));
    assertThat(query.fields().getFieldsObject().get("firstName"), is(1));
    assertThat(query.fields().getFieldsObject().get("lastName"), is(1));
  }

  @Test
  public void applySortQueryParam_ShouldApplyAscendingOrderByDefault() {
    Query query = new Query();
    Map<String, String> restApiQueries = new HashMap<>();
    restApiQueries.put(SORT.getCode(), "date");
    applySortQueryParam(query, restApiQueries);

    assertThat(query.getSortObject().size(), is(1));
    assertThat(query.getSortObject(), IsMapContaining.hasKey("date"));
    assertThat(query.getSortObject().get("date"), is(1));
  }

  @Test
  public void applySortQueryParam_ShouldApplyDescendingOrderByWhenSpecified() {
    Query query = new Query();
    Map<String, String> restApiQueries = new HashMap<>();
    restApiQueries.put(SORT.getCode(), "-created");
    applySortQueryParam(query, restApiQueries);

    assertThat(query.getSortObject().size(), is(1));
    assertThat(query.getSortObject(), IsMapContaining.hasKey("created"));
    assertThat(query.getSortObject().get("created"), is(-1));
  }
}
