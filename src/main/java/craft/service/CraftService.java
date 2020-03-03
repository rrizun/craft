package craft.service;

import org.springframework.stereotype.Service;

import craft.LogHelper;

@Service
public class CraftService {

  public String hello(String name) {
    log("hello", name);
    return String.format("Hello, %s!", name);
  }

  private void log(Object... args) {
    new LogHelper(this).log(args);
  }

}