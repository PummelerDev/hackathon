package com.hackathon.backend.service;

import static com.hackathon.backend.exception.ExceptionMessage.IMAGE_WITH_ID_NOT_FOUND;
import static com.hackathon.backend.exception.ExceptionMessage.NO_VALID_IMAGE_FOUND;
import static com.hackathon.backend.exception.ExceptionMessage.NO_VALID_IMAGE_RESOLUTION;
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

  public void save(List<MultipartFile> files, String name) {
    Image image;
    BufferedImage img;
    try {
      img = ImageIO.read(files.get(0).getInputStream());
      if (img.getWidth() != 480 | img.getHeight() != 480) {
        img.flush();
        throw new IllegalArgumentException(NO_VALID_IMAGE_RESOLUTION);
      }
      image = Image.builder()
          .name(name)
          .image(files.get(0).getBytes())
          .build();
    } catch (IOException | NullPointerException e) {
      throw new IllegalArgumentException(NO_VALID_IMAGE_FOUND);
    }
    img.flush();
    imageRepository.save(image);
  }

  public void delete(long id) {
    Image image = findImageOrThrowException(id);
    imageRepository.delete(image);
  }

  private Image findImageOrThrowException(long id) {
    return imageRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException(format(IMAGE_WITH_ID_NOT_FOUND, id)));
  }
}
