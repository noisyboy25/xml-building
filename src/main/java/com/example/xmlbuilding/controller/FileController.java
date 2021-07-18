package com.example.xmlbuilding.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {
  private final File database = new File("database.xml");

  @PostMapping("/upload")
  public void upload(@RequestParam("file") MultipartFile file, HttpServletResponse response) throws IOException {
    if (!file.isEmpty() && "text/xml".equals(file.getContentType())) {
      Files.copy(file.getInputStream(), database.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
    response.sendRedirect("/file.html");
  }

  @GetMapping("/download")
  public void download(HttpServletResponse response) throws IOException {
    response.setContentType("application/xml");
    response.setHeader("Content-Disposition", "attachment; filename=\"backup.xml\"");
    Files.copy(database.toPath(), response.getOutputStream());
  }
}
