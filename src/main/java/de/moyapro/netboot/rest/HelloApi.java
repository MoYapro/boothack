package de.moyapro.netboot.rest;

import javax.jws.WebParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloApi {

  @GetMapping("/hello")
  public String hey(@WebParam String name) {
    return "Hello " + name;
  }
}
