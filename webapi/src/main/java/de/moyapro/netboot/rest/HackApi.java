package de.moyapro.netboot.rest;

import de.moyapro.netboot.game.Game;
import javax.jws.WebParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Provide REST API to make game functions accessable
 */
@RestController
public class HackApi {

  private final Game game;

  @Autowired
  public HackApi(Game game) {
    this.game = game;
  }

  @PostMapping("/loadMap")
  public void loadMap(@WebParam String map) {
    game.loadMap(map);
  }

  @GetMapping("/map")
  public String getMap() {
    return game.render();
  }

  @PostMapping("/action")
  public String doAction(@RequestParam String action) {
    if (null == action || "Tab".equals(action)) {
      return game.render();
    }
    if (action.startsWith("Key")) {
      action = action.substring(3, action.length());
    }
    if (1 != action.length()) {
      return game.render();
    }
    return game.handleAction(action.toLowerCase().charAt(0)).render();
  }
}
