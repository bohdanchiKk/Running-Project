package com.example.springmvcapp.service;

import com.example.springmvcapp.dto.ClubDto;
import com.example.springmvcapp.models.Club;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();
    Club saveClub(Club club);

}
