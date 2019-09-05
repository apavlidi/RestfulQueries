# RestfulQueries [![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](https://github.com/apavlidi/IT_API/wiki/How-to-contribute)
Develop true restful APIs by supporting pagination, filtering, selection, sorting and searching. This project helps you add these featrues to your REST Controller easily without developing custom solutions.
The project is currently only compatible with Spring Boot + Spring Data MongoDB applications.

## Requirements
* Spring Boot
* Spring Data MongoDB

## Getting Started
For Maven-based projects, add the following to your pom.xml file. This dependency is available from the Maven Central repository.

```xml
<dependency>
    <groupId>com.github.apavlidi</groupId>
    <artifactId>restfulQueries</artifactId>
    <version>0.0.1</version>
</dependency>
```

## Usage
 1. Add a map parameter to your Rest Controller.
<pre>
    @GetMapping("/profile")
    private List<Profile> getAllProfiles(<b>@RequestParam Map &lt;String, String> filters</b>) {
        ....
    }
</pre>
 
 2. Call the <b>collectRestApiParams()</b> inside your controller and pass the RequestParam map.
<pre>
    Map&lt;String, String> restApiQueries = collectRestApiParams(filters);
</pre>

 3. Pass the filters to your service/repository and before calling the Spring Data MongoDB API call  <b>applyRestApiQueries()</b> and pass your query variable.
<pre>
  @Override
  public List<Profile> getAllProfilesDemo(Map&lt;String, String> filters) {
      Query query = new Query();
      <b>applyRestApiQueries(query, restApiQueries);</b>
      return mongoTemplate.find(query, Profile.class);
  }
</pre>

## Documentation

RestfulQueries documentation is available [here](https://github.com/apavlidi/RestfulQueries/wiki/API-Documentation).  

### Feedback

Suggestions and/or improvements are welcome!

### Licensing
This project is licensed under the [MIT License](https://github.com/apavlidi/RestfulQueries/blob/master/LICENSE)

