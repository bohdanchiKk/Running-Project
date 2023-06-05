package com.example.springmvcapp.service.impl;

import com.example.springmvcapp.dto.ClubDto;
import com.example.springmvcapp.models.Club;
import com.example.springmvcapp.repository.ClubRepository;
import com.example.springmvcapp.service.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.springmvcapp.mapper.ClubMapper.mapToClub;
import static com.example.springmvcapp.mapper.ClubMapper.mapToClubDto;

@Service
public class ClubServiceImpl implements ClubService {
    private ClubRepository clubRepository;
    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream().map((club) -> mapToClubDto(club)).collect(Collectors.toList());
    }

    @Override
    public Club saveClub(ClubDto clubDto) {
        Club club = mapToClub(clubDto);
         return clubRepository.save(club);
    }

    @Override
    public ClubDto findById(long clubId) {
        Club club = clubRepository.findById(clubId).get();
        return mapToClubDto(club);
    }

    @Override
    public void updateClub(ClubDto clubDto) {
        Club club1 = mapToClub(clubDto);
        clubRepository.save(club1);
    }

    @Override
    public void delete(Long clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
    public List<ClubDto> searchClubs(String query) {
        List<Club> clubs = clubRepository.searchClubs(query);
        return clubs.stream().map(club -> mapToClubDto(club)).collect(Collectors.toList());
    }
}
