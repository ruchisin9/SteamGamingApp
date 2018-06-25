package com.assignedpro.nj.steam.Request;

/**
 * Created by Lincoln on 18/05/16.
 */
public class Games {
    private String name;
    private int appid;
    private int thumbnail;

    public Games() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Games(String name, int appid, int thumbnail) {
        this.name = name;
        this.appid = appid;
        this.thumbnail = thumbnail;

    }

}
