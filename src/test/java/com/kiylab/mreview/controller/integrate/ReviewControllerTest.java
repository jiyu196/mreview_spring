package com.kiylab.mreview.controller.integrate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kiylab.mreview.domain.dto.ReviewDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Log4j2
@SpringBootTest
@AutoConfigureMockMvc
public class ReviewControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void testExist() {
    log.info(mockMvc);
  }

  @Test
  public void testList() throws Exception {
    Long mno = 100L;
    mockMvc.perform(get(String.format("/review/%d/all", mno)))
            .andExpect(status().is(200))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$[0].mno").value(mno))
            .andExpect(jsonPath("$[0].records").doesNotExist());
  }

  @Test
  public void testCreate() throws Exception {
    ReviewDTO dto = ReviewDTO.builder()
            .mno(100L)
            .mid(2L)
            .grade(5)
            .text("느낌")
            .build();
    MvcResult result = mockMvc.perform(post(String.format("/review/%d", 101))
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto))
            )
            .andExpect(status().is(200)) // andExpect : 이후 기대하는 값
            .andReturn();
    String resultStr = result.getResponse().getContentAsString();
    log.info(resultStr);
  }
}
