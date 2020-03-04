package craft.ports;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;

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

// playerID,birthYear,
// birthMonth,birthDay,birthCountry,birthState,birthCity,
// deathYear,deathMonth,deathDay,deathCountry,deathState,deathCity,
// nameFirst,nameLast,nameGiven,weight,height,bats,throws,debut,finalGame,retroID,bbrefID

class Player {
  public String playerID;
}

class GetPlayersResponse {
  public final List<JsonObject> players = new ArrayList<>();

  GetPlayersResponse(List<JsonObject> players) {
    this.players.addAll(players);
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

    // GET /api/players
    @GetMapping("/api/players")
    public GetPlayersResponse players() throws Exception {
      return new GetPlayersResponse(service.getPlayers());
    }
  
}