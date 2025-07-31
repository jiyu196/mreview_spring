package com.kiylab.mreview.controller;

import com.kiylab.mreview.domain.dto.MovieDTO;
import com.kiylab.mreview.domain.dto.PageRequestDTO;
import com.kiylab.mreview.service.MovieService;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("movie")
@Data
@Log4j2
public class MovieController {
  private final MovieService movieService;

  @GetMapping("register")
  public void register() {

  }

  @PostMapping("register")
  public String register(MovieDTO movieDTO, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("msg", movieService.register(movieDTO));
    return "redirect:/movie/register";
//    log.info(movieDTO);
//    return null;
  }

  @GetMapping("list")
  public void list(PageRequestDTO pageRequestDTO, Model model) {
    model.addAttribute("movies", movieService.getList(pageRequestDTO));
  }
}
