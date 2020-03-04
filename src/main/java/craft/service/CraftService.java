package craft.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.google.common.io.Files;
import com.google.gson.JsonObject;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import helpers.LogHelper;

@Service
public class CraftService {

  public CraftService() {
  }

  public String hello(String name) {
    log("hello", name);
    return String.format("Hello, %s!", name);
  }

  public List<JsonObject> getPlayers() throws Exception {
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
          int index = 0;
          JsonObject player = new JsonObject();

          for (String value : line.split(",")) {
            if (index < names.size())
              player.addProperty(names.get(index++), value);
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