package com.kiylab.mreview.controller;

import com.kiylab.mreview.domain.dto.MovieDTO;
import com.kiylab.mreview.domain.dto.MovieImageDTO;
import com.kiylab.mreview.domain.dto.PageRequestDTO;
import com.kiylab.mreview.service.MovieService;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Data
@Log4j2
@RequestMapping("movie")
public class MovieController {

  private final MovieService movieService;

  @GetMapping("register")
  public void register(){

  }
  @PostMapping("register")
  public String register (MovieDTO movieDTO, RedirectAttributes redirectAttributes) {
    redirectAttributes.addFlashAttribute("msg", movieService.register(movieDTO));
//    log.info(movieDTO);
    return "redirect:/movie/register";
//    return null;

  }
  @GetMapping("list")
  public void list(@ModelAttribute("requestDto") PageRequestDTO pageRequestDTO, Model model, MovieImageDTO movieImageDTO){
//    PageRequestDTO<?, ?> dto = movieService.getList(pageRequestDTO);
//    log.info(dto)
    model.addAttribute("movies", movieService.getList(pageRequestDTO));


  }
  ///
  @GetMapping("read")
  public void read(@ModelAttribute("requestDto") PageRequestDTO dto, Model model, Long mno){
    log.info(mno);
    model.addAttribute("dto", movieService.get(mno));
    model.addAttribute("pageDto", dto);
  }


//  @GetMapping("modify")
//  public void modify(@ModelAttribute("requestDto") PageRequestDTO dto, Model model, Long mno ){
//    model.addAttribute("dto", service.get(mno));
//
//  }
//  @PostMapping("modify")
//  public String modify( PageRequestDTO dto,BoardDTO boardDTO, Long mno, RedirectAttributes rttr){
//    service.modify(boardDTO);
//    rttr.addAttribute("mno", boardDTO.getmno());
//    rttr.addAttribute("page", dto.getPage());
//    rttr.addAttribute("size", dto.getSize());
//    rttr.addAttribute("type", dto.getType());
//    rttr.addAttribute("keyword", dto.getKeyword());
//    return "redirect:read";
////    model.addAttribute("pageDto", dto);
//  }
//  @PostMapping("remove")
//  public String remove( PageRequestDTO dto,BoardDTO boardDTO, Model model, Long mno, RedirectAttributes rttr){
//    service.remove(mno);

//    model.addAttribute("pageDto", dto);
//    rttr.addAttribute("msg", mno);
//    rttr.addAttribute("page", dto.getPage());
//    rttr.addAttribute("size", dto.getSize());
//
//    return "redirect:list";
//  }
}
