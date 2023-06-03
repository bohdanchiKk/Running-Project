package com.example.springmvcapp.service.impl;

import com.example.springmvcapp.dto.EventDto;

public interface EventService {
    void createEvent(Long clubId, EventDto eventDto);
}
