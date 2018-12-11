package de.moyapro.netboot.rest;

import de.moyapro.netfrag.entities.Pos;
import de.moyapro.netfrag.game.Game;
import javax.jws.WebParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HackApi {

  private Game game;


  @PostMapping("/loadMap")
  public void loadMap(@WebParam String map) {
    game = Game.getInstance(map);
  }

  @GetMapping("/map")
  public String getMap() {
    game = Game.getInstance(40, 20);
    game.addPlayer(new Pos(1, 1));
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
