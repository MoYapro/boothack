package de.moyapro.netboot.rest;

import javax.jws.WebParam;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloApi {

  private String map = null;


  @PostMapping("/loadMap")
  public void loadMap(@WebParam String map) {
    this.map = map;
  }

  @GetMapping("/map")
  public String getMap() {
    return map;
  }
}
