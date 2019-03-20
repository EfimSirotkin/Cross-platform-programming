package com.epam;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class ImageConstructor {
    private ImageView sourceImageView;

    ImageConstructor(String filePath){
        File distributor = new File(filePath);
        String localURL = distributor.toURI().toString();
        Image localImage = new Image(localURL,100, 50, false, true);
        this.setSourceImageView(new ImageView(localImage));
    }
    public ImageView getImageView(){
        return this.sourceImageView;
    }

    public void setSourceImageView(ImageView source){
        this.sourceImageView = source;
    }

}
