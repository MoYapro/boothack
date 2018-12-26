package de.moyapro.netboot;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest
public class ApiTest {

  private String initialMap = ""
    + "..▓..\n"
    + "▓M.M▓\n"
    + "▓▓@▓▓";

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void loadMap() throws Exception {

    this.mockMvc
      .perform(post("/loadMap").param("map", initialMap))
      .andExpect(status().isOk())
      .andExpect(content().string(initialMap));
  }

  @Test
  public void move() throws Exception {
    String resultMap = ""
      + ".@▓..\n"
      + "▓..M▓\n"
      + "▓▓.▓▓";
    this.mockMvc
      .perform(post("/loadMap").param("map", initialMap))
      .andExpect(status().isOk());
    this.mockMvc.perform(post("/action").param("action", "k")).andExpect(status().isOk());
    this.mockMvc.perform(post("/action").param("action", "h")).andExpect(status().isOk());
    this.mockMvc.perform(post("/action").param("action", "k")).andExpect(status().isOk()).andExpect(content().string(resultMap));
  }
}
