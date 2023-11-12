package com.hackathon.backend.controller;

import static org.springframework.http.HttpStatus.OK;

import com.hackathon.backend.domain.entity.Image;
import com.hackathon.backend.domain.response.ImageResponse;
import com.hackathon.backend.service.ImageService;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/images")
public class ImageController {

  private final ImageService imageService;

  @GetMapping()
  public ResponseEntity<List<ImageResponse>> getImageList() {
    List<ImageResponse> images = imageService.imageResponse();
    return new ResponseEntity<>(images, OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Image> getImageById(@PathVariable long id) {
    Image image = imageService.getById(id);
    return new ResponseEntity<>(image, OK);
  }

  @GetMapping("/bytes/{id}")
  public ResponseEntity<byte[]> getImageBytesById(@PathVariable long id) {
    byte[] image = imageService.getBytesById(id);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.parseMediaType(MediaType.IMAGE_JPEG_VALUE));
    headers.setContentDisposition(
        ContentDisposition.parse("attachment; file=")
    );
    return new ResponseEntity<>(image, headers, OK);
  }

  @PostMapping(consumes = "multipart/form-data")
  public void loadImage(@RequestPart("file") List<MultipartFile> files, @RequestParam String name) {
    imageService.save(
        files,
        URLDecoder.decode(name, StandardCharsets.UTF_8)
    );
  }

  @DeleteMapping("/{id}")
  public void loadImage(@PathVariable long id) {
    imageService.delete(id);
  }
}
