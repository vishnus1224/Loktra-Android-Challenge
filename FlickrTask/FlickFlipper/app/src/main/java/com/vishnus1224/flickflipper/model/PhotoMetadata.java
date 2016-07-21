package com.vishnus1224.flickflipper.model;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

/**
 * Created by Vishnu on 7/21/2016.
 */
public class PhotoMetadata {

    private String width;
    private String height;


    public PhotoMetadata(String html){

        Elements elements = Jsoup.parse(html).select("img");

        width = elements.attr("width");

        height = elements.attr("height");

    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }
}
