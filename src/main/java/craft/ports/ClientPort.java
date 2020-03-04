package craft.ports;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import craft.service.CraftService;
import craft.service.Player;

class GetPlayersResponse {
  public final List<Player> players = new ArrayList<>();

  GetPlayersResponse(List<Player> players) {
    this.players.addAll(players);
  }
}

/**
 * ClientPort
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

  // GET /api/players
  @GetMapping("/api/players")
  public GetPlayersResponse players() throws Exception {
    return new GetPlayersResponse(service.getPlayers());
  }
  
  // GET /api/players/{playerID}
  @GetMapping("/api/players/{playerID}")
  public Player players(@PathVariable String playerID) throws Exception {
    Player player = service.getPlayer(playerID);
    if (player == null) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NOT_FOUND");
    }
    return player;
  }

}