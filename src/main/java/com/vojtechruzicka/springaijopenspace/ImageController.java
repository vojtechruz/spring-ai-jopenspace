package com.vojtechruzicka.springaijopenspace;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageModel imageModel;

    public ImageController(ImageModel imageModel) {
        this.imageModel = imageModel;
    }


    @GetMapping("/cat")
    public void getChat(HttpServletResponse response) throws IOException {
        var imageOptions = OpenAiImageOptions.builder()
                .withHeight(1024)
                .withWidth(1024)
                .withN(1)
                .build();
        var instructions = "Image of cute playful cat.";
        var request = new ImagePrompt(instructions, imageOptions);

        response.sendRedirect(imageModel.call(request).getResult().getOutput().getUrl());

    }
}
