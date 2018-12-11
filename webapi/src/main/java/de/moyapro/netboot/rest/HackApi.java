package de.moyapro.netboot.rest;

import de.moyapro.netfrag.game.Game;
import javax.jws.WebParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    return game.render();
  }

  @PostMapping("/action")
  public String doAction(@WebParam String action) {
    game.handleAction(action.charAt(0));
    return game.render();
  }
}
