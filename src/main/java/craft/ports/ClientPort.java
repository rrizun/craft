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

class NowResponse {
  public final String now;

  NowResponse(String now) {
    this.now = now;
  }
}
/**
 * CraftPort
 * 
 */
@RestController
public class ClientPort {

  private final CraftService service;

  /**
   * ctor
   * 
   * @param service
   */
  public ClientPort(CraftService service) {
    this.service = service;
  }

  // GET /hello?name={name}
  @GetMapping("/hello")
  public HelloResponse hello(@RequestParam String name) {
    return new HelloResponse(service.hello(name));
  }

  // GET /now
  @GetMapping("/now")
  public NowResponse now() throws Exception {
    return new NowResponse(service.now());
  }

  // // GET /users
  // @GetMapping("/users")
  // public Object users() {
  // return service.users();
  // }

}