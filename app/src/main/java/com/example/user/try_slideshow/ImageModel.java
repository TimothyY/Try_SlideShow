package com.example.user.try_slideshow;

import java.io.Serializable;

/**
 * Created by User on 3/16/2017.
 */

public class ImageModel implements Serializable {

    public String imageURI;

    public ImageModel(String imageURI) {
        this.imageURI = imageURI;
    }
}
