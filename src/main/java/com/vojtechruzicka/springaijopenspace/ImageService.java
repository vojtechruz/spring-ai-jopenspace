package com.vojtechruzicka.springaijopenspace;

import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private final ImageModel imageModel;

    public ImageService(ImageModel imageModel) {
        this.imageModel = imageModel;
    }


    public ImageResponse generateImageOfCat() {
        var imageOptions = OpenAiImageOptions.builder()
                .withHeight(1024)
                .withWidth(1024)
                .withN(1)
                .build();
        var instructions = "Image of cute cat.";
        var request = new ImagePrompt(instructions, imageOptions);

        return imageModel.call(request);
    }

}
