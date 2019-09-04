package com.apavlidi.resftullAPI;


import static com.apavlidi.core.RestFullAPI.collectRestApiParams;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.HashMap;
import java.util.Map;
import org.hamcrest.collection.IsMapContaining;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RestfullAPITest {

  private Map<String, String> filtersMap;

  @BeforeEach
  public void setup() {
    filtersMap = new HashMap<>();
  }

  @Test
  public void collectRestApiParams_shouldCollectAscendingSort() {
    filtersMap.put("sort", "username");
    Map<String, String> stringStringMap = collectRestApiParams(filtersMap);

    assertThat(stringStringMap, IsMapContaining.hasEntry("sort", "username"));
    assertThat(stringStringMap.keySet().size(), is(1));
  }

  @Test
  public void collectRestApiParams_shouldCollectDescendingSort() {
    filtersMap.put("sort", "-lastName");
    Map<String, String> stringStringMap = collectRestApiParams(filtersMap);

    assertThat(stringStringMap, IsMapContaining.hasEntry("sort", "-lastName"));
    assertThat(stringStringMap.keySet().size(), is(1));
  }

  @Test
  public void collectRestApiParams_shouldNotCollectSortWhenNotAvailable() {
    Map<String, String> stringStringMap = collectRestApiParams(filtersMap);

    assertThat(stringStringMap, not(IsMapContaining.hasKey("sort")));
    assertThat(stringStringMap.keySet().size(), is(0));
  }

  @Test
  public void collectRestApiParams_shouldCollectSelect() {
    filtersMap.put("select", "username,firstName");
    Map<String, String> stringStringMap = collectRestApiParams(filtersMap);

    assertThat(stringStringMap, IsMapContaining.hasEntry("select", "username,firstName"));
    assertThat(stringStringMap.keySet().size(), is(1));
  }

  @Test
  public void collectRestApiParams_shouldNotCollectSelectWhenNotAvailable() {
    Map<String, String> stringStringMap = collectRestApiParams(filtersMap);

    assertThat(stringStringMap, not(IsMapContaining.hasKey("select")));
    assertThat(stringStringMap.keySet().size(), is(0));
  }

  @Test
  public void collectRestApiParams_shouldCollectPageSize() {
    filtersMap.put("pageSize", "1");
    Map<String, String> stringStringMap = collectRestApiParams(filtersMap);

    assertThat(stringStringMap, IsMapContaining.hasEntry("pageSize", "1"));
    assertThat(stringStringMap.keySet().size(), is(1));
  }

  @Test
  public void collectRestApiParams_shouldNotCollectPageSizeWhenNotAvailable() {
    Map<String, String> stringStringMap = collectRestApiParams(filtersMap);

    assertThat(stringStringMap, not(IsMapContaining.hasKey("pageSize")));
    assertThat(stringStringMap.keySet().size(), is(0));
  }

  @Test
  public void collectRestApiParams_shouldCollectPage() {
    filtersMap.put("page", "1");
    Map<String, String> stringStringMap = collectRestApiParams(filtersMap);

    assertThat(stringStringMap, IsMapContaining.hasEntry("page", "1"));
    assertThat(stringStringMap.keySet().size(), is(1));
  }

  @Test
  public void collectRestApiParams_shouldNotCollectPageWhenNotAvailable() {
    Map<String, String> stringStringMap = collectRestApiParams(filtersMap);

    assertThat(stringStringMap, not(IsMapContaining.hasKey("page")));
    assertThat(stringStringMap.keySet().size(), is(0));
  }

  @Test
  public void collectRestApiParams_shouldCollectQ() {
    String searchValue = "{\"username\":\"kvisnia\"}";
    filtersMap.put("q", searchValue);
    Map<String, String> stringStringMap = collectRestApiParams(filtersMap);

    assertThat(stringStringMap, IsMapContaining.hasEntry("q", searchValue));
    assertThat(stringStringMap.keySet().size(), is(1));
  }

  @Test
  public void collectRestApiParams_shouldNotCollectQWhenNotAvailable() {
    Map<String, String> stringStringMap = collectRestApiParams(filtersMap);

    assertThat(stringStringMap, not(IsMapContaining.hasKey("q")));
    assertThat(stringStringMap.keySet().size(), is(0));
  }

}
