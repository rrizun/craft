package craft.service;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import helpers.JdbcHelper;
import helpers.LogHelper;

@Service
public class CraftService {

  private final DataSource dataSource;

  public CraftService(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public String hello(String name) {
    log("hello", name);
    return String.format("Hello, %s!", name);
  }

  public String now() throws Exception {
    return new JdbcHelper(dataSource).query(String.class, "select now()").iterator().next();
  }

  private void log(Object... args) {
    new LogHelper(this).log(args);
  }

}