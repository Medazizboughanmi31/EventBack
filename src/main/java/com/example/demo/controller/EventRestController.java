package com.example.demo.controller;

import com.example.demo.entites.Event;
import com.example.demo.services.IEventService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/event")
public class EventRestController {
    @Autowired
    IEventService eventService ;
    @GetMapping("/retrieve-all-events")
    public List<Event> getEvents() {
        List<Event> listEvents = eventService.retrieveAllEvents() ;
        return listEvents;
    }

    @GetMapping("/retrieve-event/{idEvent}")
    public Event retrieveEvent(@PathVariable("idEvent") long idEvent) {
        Event  event=eventService.retrieveEvent(idEvent) ;
        return event ;
    }


    @PostMapping("/add-event")
    public Event addEvent(@RequestBody Event e) {
        Event event=eventService.addEvent(e) ;
        return event ;
    }

    @DeleteMapping("/remove-event/{idEvent}")
    public void removeEvent(@PathVariable("idEvent") long idEvent) {
        eventService.removeEvent(idEvent);
    }

    @PutMapping("/modify-event")
    public Event modifyEvent(@RequestBody Event e) {
        Event event=eventService.modifyEvent(e) ;
        return event ;
    }



}
