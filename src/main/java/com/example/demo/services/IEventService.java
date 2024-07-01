package com.example.demo.services;

import com.example.demo.entites.Event;

import java.util.List;

public interface IEventService {
    public List<Event> retrieveAllEvents() ;
    public Event retrieveEvent(long idEvent) ;
    public Event addEvent(Event e) ;
    public void removeEvent(long idEvent) ;
    public Event modifyEvent(Event event) ;
}
