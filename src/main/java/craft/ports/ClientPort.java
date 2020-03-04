package craft.ports;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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

// {
//   "playerID": "zychto01",
//   "birthYear": "1990",
//   "birthMonth": "8",
//   "birthDay": "7",
//   "birthCountry": "USA",
//   "birthState": "IL",
//   "birthCity": "Monee",
//   "deathYear": "",
//   "deathMonth": "",
//   "deathDay": "",
//   "deathCountry": "",
//   "deathState": "",
//   "deathCity": "",
//   "nameFirst": "Tony",
//   "nameLast": "Zych",
//   "nameGiven": "Anthony Aaron",
//   "weight": "190",
//   "height": "75",
//   "bats": "R",
//   "throws": "R",
//   "debut": "2015-09-04",
//   "finalGame": "2017-08-19",
//   "retroID": "zycht001",
//   "bbrefID": "zychto01"
// }
class Player {
  public String playerID;//": "zychto01",
  public String birthYear;//": "1990",
  public String birthMonth;//": "8",
  public String birthDay;//": "7",
  public String birthCountry;//": "USA",
  public String birthState;//": "IL",
  public String birthCity;//": "Monee",
  public String deathYear;//": "",
  public String deathMonth;//": "",
  public String deathDay;//": "",
  public String deathCountry;//": "",
  public String deathState;//": "",
  public String deathCity;//": "",
  public String nameFirst;//": "Tony",
  public String nameLast;//": "Zych",
  public String nameGiven;//": "Anthony Aaron",
  public String weight;//": "190",
  public String height;//": "75",
  public String bats;//": "R",
  public String _throws;//": "R",
  public String debut;//": "2015-09-04",
  public String finalGame;//": "2017-08-19",
  public String retroID;//": "zycht001",
  public String bbrefID;//": "zychto01"
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

  // GET /api/players/{playerID}
  @GetMapping("/api/players/{playerID}")
  public JsonObject players(@PathVariable String playerID) throws Exception {
    JsonObject player = service.getPlayer(playerID);
    if (player == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT_FOUND");
    }
    return player;
  }

}