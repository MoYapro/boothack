package de.moyapro.netboot.rest;

import de.moyapro.netboot.entities.Pos;
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
  public String loadMap(@WebParam String map) {
    return game
      .loadMap(map)
      .render();
  }

  @GetMapping("/map")
  public String getMap() {
    return game
      .newGame(40, 20)
      .addPlayer(new Pos(1, 1))
      .render();
  }

  @PostMapping("/action")
  public String doAction(@RequestParam String action) {
    if (null == action || "Tab".equals(action)) {
      return game.render();
    }
    if (action.startsWith("Key")) {
      action = action.substring(3);
    }
    if (1 != action.length()) {
      return game.render();
    }
    return game.handleAction(action.toLowerCase().charAt(0)).render();
  }
}
