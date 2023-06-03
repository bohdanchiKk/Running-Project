package com.example.springmvcapp.repository;

import com.example.springmvcapp.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event,Long> {
}
