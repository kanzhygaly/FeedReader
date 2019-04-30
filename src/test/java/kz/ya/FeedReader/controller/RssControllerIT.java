/*
 * Integration Tests for RssController
 */
package kz.ya.FeedReader.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import kz.ya.FeedReader.model.FeedItem;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 *
 * @author yerlana
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RssControllerIT {

    @Autowired
    private MockMvc mockMvc;

    // Used for converting heroes to/from JSON
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void contextLoads() {
        Assertions.assertThat(mockMvc).isNotNull();
    }

    @Test
    public void shouldGetOkStatusAndReturnResultWithTenEntries() throws Exception {
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/rss")
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<FeedItem> heroes = fromJsonResult(result, List.class);

        Assertions.assertThat(heroes).isNotEmpty();
        Assertions.assertThat(heroes.size()).isEqualTo(10);
    }

    /**
     * Convert JSON Result to object.
     *
     * @param result JSON result
     * @param valueType expected object class
     * @return the result as expected object class
     * @throws Exception
     */
    <T> T fromJsonResult(MvcResult result, Class<T> valueType) throws Exception {
        return this.mapper.readValue(result.getResponse().getContentAsString(), valueType);
    }

    /**
     * Convert object to JSON bytes.
     *
     * @param object object to convert
     * @return byte array with JSON representation
     * @throws Exception
     */
    byte[] toJson(Object object) throws Exception {
        return this.mapper.writeValueAsString(object).getBytes();
    }
}
