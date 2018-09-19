package com.amalia;

import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Amalia on 9/19/2018.
 */
public class MovieDetails {



    private String name;
    private Date   date;
    private Integer    length;
    private Integer    numOfMinutesWatched;
    private String genre;
    private double avgPercentage;


    private String user;

    public MovieDetails(Date date, String name, Integer length, Integer numOfMinutesWatched, String genre, String user) {
        this.name = name;
        this.date = date;
        this.length = length;
        this.numOfMinutesWatched = numOfMinutesWatched;
        this.genre = genre;
        this.user = user;
        calculateAvgPercentage();
    }

    public static  MovieDetails parseLine(String line, String filename){

        String regex="^(\\d+)/(\\d+)/(\\d+) (.*) (\\d+)min (\\d+)min (\\S+)$";
        Pattern p = Pattern.compile(regex);

        MovieDetails movie = null;
        Matcher m = p.matcher(line);
        String userName = filename.split("\\.")[0];
        while(m.find())
        {
            //map row to MovieDetails object
            Date date = new Date();
            date.setDate(Integer.parseInt(m.group(2)));
            date.setMonth(Integer.parseInt(m.group(1)));
            date.setYear( Integer.parseInt(m.group(3)));
            movie = new MovieDetails( date, m.group(4),Integer.parseInt(m.group(5)),Integer.parseInt(m.group(6)),m.group(7),userName);
            System.out.print(movie.name);
        }
        return movie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getNumOfMinutesWatched() {
        return numOfMinutesWatched;
    }

    public void setNumOfMinutesWatched(Integer numOfMinutesWatched) {
        this.numOfMinutesWatched = numOfMinutesWatched;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void calculateAvgPercentage(){
        if(this.length>0){
            this.avgPercentage = (((double)this.numOfMinutesWatched/this.length)*100);
            this.avgPercentage = BigDecimal.valueOf(this.avgPercentage).setScale(0, RoundingMode.HALF_UP).doubleValue();
        }
    }
    public double getAvgPercentage(){
        return this.avgPercentage;
    }
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

}
