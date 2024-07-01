package com.example.demo.services.impl;

import com.example.demo.entites.Event;
import com.example.demo.repository.EventRepository;
import com.example.demo.services.IEventService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class EventServiceImpl  implements IEventService {
    @Autowired
    EventRepository eventRepository;

    public List<Event> retrieveAllEvents() {
        return eventRepository.findAll();
    }

    public Event retrieveEvent(long idEvent) {
        return eventRepository.findById(idEvent).get();
    }


    public Event addEvent(Event e) {
        return eventRepository.save(e);
    }


    public void removeEvent(long idEvent) {
        eventRepository.deleteById(idEvent);
    }


    public Event modifyEvent(Event event) {
        return eventRepository.save(event);
    }
}