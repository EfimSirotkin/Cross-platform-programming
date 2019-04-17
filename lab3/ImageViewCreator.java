package com.epam.lab2.utilities;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class ImageViewCreator {
    private ImageView sourceImageView;

    public ImageViewCreator(String filePath, double width, double height, boolean preserveRatio, boolean smooth){
        File imageContainerFile = new File(filePath);
        Image sourceImage = new Image(imageContainerFile.toURI().toString(), width, height, preserveRatio, smooth);
        this.sourceImageView = new ImageView(sourceImage);
    }

    public ImageView getImageView() {
        return this.sourceImageView;
    }
}
