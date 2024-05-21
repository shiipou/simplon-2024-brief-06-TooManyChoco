package io.simplon.toomanychoco.model;

import java.util.Date;
import java.util.List;

public class Event {


    // Attributs

   
   
    private List<Pastry> pastries;

    // Constructeurs
    
    public Event() {
        
    }

    public Event(Integer event_id, Date event_date, User creator, List<Pastry> pastries) {
        this.event_id = event_id;
        this.event_date = event_date;
        this.creator = creator;
        this.pastries = pastries;
    }

    public Event(Integer event_id, Date event_date, User creator) {
        this.event_id = event_id;
        this.event_date = event_date;
        this.creator = creator;
    }

    private Date event_date;
    private int event_id ;
    private User creator;

    private List<String> pastry_list;

    public Event(Date event_date, int event_id, User creator, List<String> pastry_list) {
        this.event_date = event_date;
        this.event_id = event_id;
        this.creator = creator;
        this.pastry_list = pastry_list;
    }


    public Date getEvent_date() {
        return this.event_date;

    }

    public void setEvent_date(Date event_date) {
        this.event_date = event_date;
    }


    public int getEvent_id() {
        return this.event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }


    public List<Pastry> getPastries() {
        return pastries;
    }

    public void setPastries(List<Pastry> pastries) {
        this.pastries = pastries;
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
