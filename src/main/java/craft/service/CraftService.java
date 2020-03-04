package craft.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import helpers.LogHelper;

@Service
public class CraftService {

  // ctor
  public CraftService() {
  }

  /**
   * returns all players
   */
  public List<Player> getPlayers() throws Exception {
    List<Player> players = new ArrayList<>();
    
    // convert from type unsafe json object to type safe java pojo
    for (JsonObject player : players()) {
      players.add(new Gson().fromJson(player, Player.class));
    }

    return players;
  }

  /**
   * get player by player ID
   * 
   * @param playerID
   * @return the player for the given playerID or null if not found
   * @throws Exception
   */
  public Player getPlayer(String playerID) throws Exception {
    for (JsonObject raw : players()) {
      Player player = new Gson().fromJson(raw, Player.class);

      if (player.playerID.equals(playerID))
        return player;
    }
    return null;
  }

  private List<JsonObject> players() throws Exception {
    List<JsonObject> players = new ArrayList<>();

    Resource resource = new ClassPathResource("People.csv");
    final BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
    try {
      String line;
      boolean once = true;
      List<String> names = new ArrayList<>();
      while ((line = reader.readLine()) != null) {
        // log(line);
        if (once) {
          once = false;
          for (String name : line.split(","))
            names.add(name);
        } else {
          int index = -1;
          JsonObject player = new JsonObject();

          for (String value : line.split(",")) {
            ++index;
            if (index < names.size()) {
              // discard null or empty values
              if (Strings.nullToEmpty(value).length()>0)
                player.addProperty(names.get(index), value);
            }
          }

          players.add(player);
        }
      }
    } finally {
      reader.close();
    }

    return players;
  }

  private void log(Object... args) {
    new LogHelper(this).log(args);
  }

}