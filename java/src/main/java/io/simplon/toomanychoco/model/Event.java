package io.simplon.toomanychoco.model;

import java.util.List;

public class Event {

    private String event_date;
    private int event_id ;
    private String creator;

    private List<String> pastry_list;

    public Event(String event_date, int event_id, String creator, List<String> pastry_list) {
        this.event_date = event_date;
        this.event_id = event_id;
        this.creator = creator;
        this.pastry_list = pastry_list;
    }

    public String getEvent_date() {
        return this.event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public int getEvent_id() {
        return this.event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public List<String> getPastryList() {
        return this.pastry_list;
    }

    public void setPastryList(List<String> pastry_list) {
        this.pastry_list = pastry_list;
    }

    public void addPastry(String pastry_name) {
        pastry_list.add(pastry_name);
    }

}
