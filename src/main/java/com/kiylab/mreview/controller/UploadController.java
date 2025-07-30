package com.kiylab.mreview.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Controller
@Log4j2
public class UploadController {
  @Value("${spring.servlet.multipart.location}")
  private String uploadPath;

  @PostMapping("uploadAjax")
  public @ResponseBody ResponseEntity<?> uploadAjax(MultipartFile[] files) {
    return ResponseEntity.ok(Arrays.stream(files).map(f -> {
      String uuid = null;
      String forderPath = null;
      try {
        // 이미지만 업로드 가능
        if (!f.getContentType().startsWith("image/")) {
          log.warn(f.getContentType() + " is not supported");
          return ResponseEntity.badRequest().build();
        }
        log.info(f.getOriginalFilename());
        forderPath = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        uuid = UUID.randomUUID().toString();
        forderPath = uploadPath + "/"+ forderPath;
        String saveName = uuid + "_" + f.getOriginalFilename();

        new File(forderPath).mkdirs();

        //파일저장
        f.transferTo(new File(forderPath, saveName));
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      return Map.of("fileName", f.getOriginalFilename(), "size", f.getSize(), "uuid", uuid, "path", forderPath);
    }).toList());

  }
  @GetMapping("uploadEx")
  public void uploadEx() {

    
  }
}
