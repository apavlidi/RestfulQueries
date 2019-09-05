package com.apavlidi.resftullAPI;


import static com.apavlidi.core.RestFullAPI.collectRestApiParams;
import static com.apavlidi.domain.Filters.PAGE;
import static com.apavlidi.domain.Filters.PAGE_SIZE;
import static com.apavlidi.domain.Filters.SEARCH;
import static com.apavlidi.domain.Filters.SELECT;
import static com.apavlidi.domain.Filters.SORT;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.HashMap;
import java.util.Map;
import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FilterCollectorTest {

  private Map<String, String> filtersMap;

  @BeforeEach
  public void setup() {
    filtersMap = new HashMap<>();
  }

  @Test
  public void collectRestApiParams_shouldCollectAscendingSort() {
    filtersMap.put(SORT.getCode(), "username");
    Map<String, String> stringStringMap = collectRestApiParams(filtersMap);

    assertThat(stringStringMap, IsMapContaining.hasEntry(SORT.getCode(), "username"));
    assertThat(stringStringMap.keySet().size(), is(1));
  }

  @Test
  public void collectRestApiParams_shouldCollectDescendingSort() {
    filtersMap.put(SORT.getCode(), "-lastName");
    Map<String, String> stringStringMap = collectRestApiParams(filtersMap);

    assertThat(stringStringMap, IsMapContaining.hasEntry(SORT.getCode(), "-lastName"));
    assertThat(stringStringMap.keySet().size(), is(1));
  }

  @Test
  public void collectRestApiParams_shouldNotCollectSortWhenNotAvailable() {
    Map<String, String> stringStringMap = collectRestApiParams(filtersMap);

    assertThat(stringStringMap, not(IsMapContaining.hasKey(SORT.getCode())));
    assertThat(stringStringMap.keySet().size(), is(0));
  }

  @Test
  public void collectRestApiParams_shouldCollectSelect() {
    filtersMap.put(SELECT.getCode(), "username,firstName");
    Map<String, String> stringStringMap = collectRestApiParams(filtersMap);

    assertThat(stringStringMap, IsMapContaining.hasEntry(SELECT.getCode(), "username,firstName"));
    assertThat(stringStringMap.keySet().size(), is(1));
  }

  @Test
  public void collectRestApiParams_shouldNotCollectSelectWhenNotAvailable() {
    Map<String, String> stringStringMap = collectRestApiParams(filtersMap);

    assertThat(stringStringMap, not(IsMapContaining.hasKey(SELECT.getCode())));
    assertThat(stringStringMap.keySet().size(), is(0));
  }

  @Test
  public void collectRestApiParams_shouldCollectPageSize() {
    filtersMap.put(PAGE_SIZE.getCode(), "1");
    Map<String, String> stringStringMap = collectRestApiParams(filtersMap);

    assertThat(stringStringMap, IsMapContaining.hasEntry(PAGE_SIZE.getCode(), "1"));
    assertThat(stringStringMap.keySet().size(), is(1));
  }

  @Test
  public void collectRestApiParams_shouldNotCollectPageSizeWhenNotAvailable() {
    Map<String, String> stringStringMap = collectRestApiParams(filtersMap);

    assertThat(stringStringMap, not(IsMapContaining.hasKey(PAGE_SIZE.getCode())));
    assertThat(stringStringMap.keySet().size(), is(0));
  }

  @Test
  public void collectRestApiParams_shouldCollectPage() {
    filtersMap.put(PAGE.getCode(), "1");
    Map<String, String> stringStringMap = collectRestApiParams(filtersMap);

    assertThat(stringStringMap, IsMapContaining.hasEntry(PAGE.getCode(), "1"));
    assertThat(stringStringMap.keySet().size(), is(1));
  }

  @Test
  public void collectRestApiParams_shouldNotCollectPageWhenNotAvailable() {
    Map<String, String> stringStringMap = collectRestApiParams(filtersMap);

    assertThat(stringStringMap, not(IsMapContaining.hasKey(PAGE.getCode())));
    assertThat(stringStringMap.keySet().size(), is(0));
  }

  @Test
  public void collectRestApiParams_shouldCollectQ() {
    String searchValue = "{\"username\":\"kvisnia\"}";
    filtersMap.put(SEARCH.getCode(), searchValue);
    Map<String, String> stringStringMap = collectRestApiParams(filtersMap);

    assertThat(stringStringMap, IsMapContaining.hasEntry(SEARCH.getCode(), searchValue));
    assertThat(stringStringMap.keySet().size(), is(1));
  }

  @Test
  public void collectRestApiParams_shouldNotCollectQWhenNotAvailable() {
    Map<String, String> stringStringMap = collectRestApiParams(filtersMap);

    assertThat(stringStringMap, not(IsMapContaining.hasKey(SEARCH.getCode())));
    assertThat(stringStringMap.keySet().size(), is(0));
  }

}
