package io.simplon.toomanychoco.model;

import java.util.Date;
import java.util.List;

public class Event {

    // Attributs
    private Date event_date;
    private User creator;
    private List<Pastry> pastries;

    // Constructeurs
    
    public Event() {
        
    }

    public Event(Date event_date, User creator, List<Pastry> pastries) {
        this.event_date = event_date;
        this.creator = creator;
        this.pastries = pastries;
    }

    public Event(Date event_date, User creator) {
        this.event_date = event_date;
        this.creator = creator;
    }

    public Date getEvent_date() {
        return event_date;
    }

    public void setEvent_date(Date event_date) {
        this.event_date = event_date;
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

}
