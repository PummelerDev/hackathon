package com.hackathon.backend.service;

import static java.lang.String.format;

import com.hackathon.backend.domain.entity.Image;
import com.hackathon.backend.domain.response.ImageResponse;
import com.hackathon.backend.mapper.ImageMapper;
import com.hackathon.backend.repository.ImageRepository;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class ImageService {

  private final ImageRepository imageRepository;
  private final ImageMapper imageMapper;

  public Image getById(long id) {
    return findImageOrThrowException(id);
  }

  public byte[] getBytesById(long id) {
    Image image = findImageOrThrowException(id);
    return image.getImage();
  }

  public List<ImageResponse> imageResponse() {
    return imageRepository.findAll()
        .stream()
        .map(imageMapper::mapToImageResponse)
        .collect(Collectors.toList());
  }

  public void save(MultipartFile file, String name) {
    Image image;
    try {
      BufferedImage img = ImageIO.read(file.getInputStream());
      if (img.getWidth() > 480 & img.getHeight() > 480) {
        img.flush();
        throw new IllegalArgumentException("No valid image resolution");
      }
      image = Image.builder()
          .name(name)
          .image(file.getBytes())
          .build();
    } catch (IOException e) {
      throw new IllegalArgumentException("No valid image");
    }
    imageRepository.save(image);
  }

  public void delete(long id) {
    Image image = findImageOrThrowException(id);
    imageRepository.delete(image);
  }

  private Image findImageOrThrowException(long id) {
    return imageRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException(format("Image with id %d not found", id)));
  }
}
