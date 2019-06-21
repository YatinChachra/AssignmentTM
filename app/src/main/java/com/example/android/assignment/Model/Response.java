package com.example.android.assignment.Model;

/**
 * Created by 300 on 6/20/2019.
 */

public class Response {

    private static String id,question,tick,cross,image;

    public Response(String id, String question, String tick, String cross, String image) {
        this.id=id;
        this.question=question;
        this.tick=tick;
        this.cross=cross;
        this.image=image;
    }

    public static String getId() {
        return id;
    }

    public static void setId(String id) {
        Response.id = id;
    }

    public static String getQuestion() {
        return question;
    }

    public static void setQuestion(String question) {
        Response.question = question;
    }

    public static String getTick() {
        return tick;
    }

    public static void setTick(String tick) {
        Response.tick = tick;
    }

    public static String getCross() {
        return cross;
    }

    public static void setCross(String cross) {
        Response.cross = cross;
    }

    public static String getImage() {
        return image;
    }

    public static void setImage(String image) {
        Response.image = image;
    }
}
