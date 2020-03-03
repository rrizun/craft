package craft.service;

import org.springframework.stereotype.Service;

@Service
public class CraftService {

  public String hello(String name) {
    return String.format("Hello, %s!", name);
  }

}