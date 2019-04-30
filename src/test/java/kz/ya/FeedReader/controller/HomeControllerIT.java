/*
 * Integration Tests for HomeController
 */
package kz.ya.FeedReader.controller;

import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 *
 * @author yerlana
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerIT {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void contextLoads() {
        Assertions.assertThat(mockMvc).isNotNull();
    }

    @Test
    public void shouldGetOkStatusAndWelcomeMessage() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/")).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Welcome to RSS Feed")));
    }
    
    @Test
    public void shouldGetNotFoundStatusForInvalidEndpoint() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/invalid")).andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
