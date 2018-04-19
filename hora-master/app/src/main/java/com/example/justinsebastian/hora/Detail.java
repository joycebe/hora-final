package com.example.justinsebastian.hora;

/**
 * Created by JOYCE on 27-02-2018.
 */

public class Detail {
    public String source;
    public String destination;
    public String sourcetime;
    public String name;
    public String type;
    public String time1;
    public String stop1;
    public String time2;
    public String stop2;
    public String destinationtime;

    public Detail() {
    }

    public Detail(String source, String destination, String sourcetime, String name, String type, String time1, String stop1, String time2, String stop2, String destinationtime) {
        this.source = source;
        this.destination = destination;
        this.sourcetime = sourcetime;
        this.name = name;
        this.type = type;
        this.time1 = time1;
        this.stop1 = stop1;
        this.time2 = time2;
        this.stop2 = stop2;
        this.destinationtime = destinationtime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSourcetime() {
        return sourcetime;
    }

    public void setSourcetime(String sourcetime) {
        this.sourcetime = sourcetime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getStop1() {
        return stop1;
    }

    public void setStop1(String stop1) {
        this.stop1 = stop1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public String getStop2() {
        return stop2;
    }

    public void setStop2(String stop2) {
        this.stop2 = stop2;
    }

    public String getDestinationtime() {
        return destinationtime;
    }

    public void setDestinationtime(String destinationtime) {
        this.destinationtime = destinationtime;
    }
}