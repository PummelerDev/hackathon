package com.hackathon.backend.mapper;

import com.hackathon.backend.domain.entity.Image;
import com.hackathon.backend.domain.response.ImageResponse;
import org.springframework.stereotype.Component;

@Component
public class ImageMapper {

  public ImageResponse mapToImageResponse(Image image) {
    return ImageResponse.builder()
        .id(image.getId())
        .name(image.getName())
        .build();
  }
}
