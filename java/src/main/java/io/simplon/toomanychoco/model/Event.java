package io.simplon.toomanychoco.model;

import java.util.Date;
import java.util.List;

public class Event {

    // Attributs
    private Date dateEvent;
    private User auteur;
    private List<Viennoiserie> viennoiseries;

    // Constructeurs
    public Event(Date dateEvent, User auteur, List<Viennoiserie> viennoiseries) {
        this.dateEvent = dateEvent;
        this.auteur = auteur;
        this.viennoiseries = viennoiseries;
    }

    public Event(Date dateEvent, User auteur) {
        this.dateEvent = dateEvent;
        this.auteur = auteur;
    }
    
    // Getters et Setters
    public Date getDateEvent() {
        return dateEvent;
    }

    public User getAuteur() {
        return auteur;
    }

    public List<Viennoiserie> getViennoiseries(){
        return viennoiseries;
    }
    
    public void setViennoiseries(List<Viennoiserie> viennoiseries) {
        this.viennoiseries = viennoiseries;
    }

    public void setDateEvent(Date dateEvent) {
        this.dateEvent = dateEvent;
    }

    public void setAuteur(User auteur) {
        this.auteur = auteur;
    }
}
