package com.kiylab.mreview.controller.unit;

import com.kiylab.mreview.controller.ReviewController;
import com.kiylab.mreview.repository.ReviewRepository;
import com.kiylab.mreview.service.ReviewService;
import com.kiylab.mreview.service.ReviewServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReviewController.class)
@ContextConfiguration(name = "application.vml")
public class ReviewControllerTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ReviewServiceImpl service;
  @MockBean
  private ReviewRepository repository;

  @Test
  public void 단순_목록_조회() throws Exception {
    Long mno = 100L;
    mockMvc.perform(MockMvcRequestBuilders.get(String.format("/review/%d/all", mno)));
  }
}
