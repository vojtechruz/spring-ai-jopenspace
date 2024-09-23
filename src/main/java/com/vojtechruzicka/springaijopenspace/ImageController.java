package com.vojtechruzicka.springaijopenspace;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/cat")
    public void getImageOfCat(HttpServletResponse response) throws IOException {
        ImageResponse image = imageService.generateImageOfCat();
        String imageUrl = image.getResult().getOutput().getUrl();

        response.sendRedirect(imageUrl);
    }
}
