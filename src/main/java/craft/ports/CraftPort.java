package craft.ports;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import craft.service.CraftService;

class HelloResponse {
  public final String name;

  HelloResponse(String name) {
    this.name = name;
  }
}

/**
 * CraftPort
 * 
 */
@RestController
public class CraftPort {

  private final CraftService service;

  /**
   * ctor
   * 
   * @param service
   */
  public CraftPort(CraftService service) {
    this.service = service;
  }

  // GET /hello?name={name}
  @GetMapping("/hello")
  public HelloResponse hello(@RequestParam String name) {
    return new HelloResponse(service.hello(name));
  }

  // GET /now
  @GetMapping("/now")
  public String now() throws Exception {
    return service.now();
  }

  // // GET /users
  // @GetMapping("/users")
  // public Object users() {
  // return service.users();
  // }

}