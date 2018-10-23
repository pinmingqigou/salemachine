package com.freeme.freemelite.dueros.devicemodule.screen.message;


import com.freeme.freemelite.dueros.devicemodule.screen.TokenPayload;

import java.io.Serializable;
import java.util.List;

public class RenderCardPayload extends TokenPayload implements Serializable {
    public String type;
    public String title;
    public String content;
    public ImageStructure image;
    public LinkStructure link;
    public List<ListItem> list;
    public List<ImageStructure> imageList;

    public static final class LinkStructure implements Serializable {
        public String url;
        public String anchorText;

        @Override
        public String toString() {
            return "LinkStructure{" +
                    "url='" + url + '\'' +
                    ", anchorText='" + anchorText + '\'' +
                    '}';
        }
    }

    public static final class ImageStructure implements Serializable {
        public String src;

        @Override
        public String toString() {
            return "ImageStructure{" +
                    "src='" + src + '\'' +
                    '}';
        }
    }

    public static final class ListItem implements Serializable {
        public String title;
        public String content;
        public ImageStructure image;
        public String url;

        @Override
        public String toString() {
            return "ListItem{" +
                    "title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", image=" + image +
                    ", url='" + url + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "{" +
                "type=" + type +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", image=" + image +
                ", link=" + link +
                ", list=" + list +
                ", imageList=" + imageList +
                ", token='" + token + '\'' +
                '}';
    }
}
