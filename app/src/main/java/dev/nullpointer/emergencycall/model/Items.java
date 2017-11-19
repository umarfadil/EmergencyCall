package dev.nullpointer.emergencycall.model;

/**
 * Created by umarfadil on 11/19/17.
 */

public class Items {

    private  String title;
    private int thumbnail;

    public Items() {
    }

    public Items(String title, int thumbnail) {
        this.title = title;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
