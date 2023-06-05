package com.example.springmvcapp.controller;

import com.example.springmvcapp.dto.ClubDto;
import com.example.springmvcapp.models.Club;
import com.example.springmvcapp.service.ClubService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClubController {
    private ClubService clubService;
    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }
    @GetMapping("/clubs")
        public String ListClubs(Model model){
        List<ClubDto> clubs = clubService.findAllClubs();
        model.addAttribute("clubs",clubs);
        return "clubs-list";
    }
    @GetMapping("/clubs/{clubId}")
    public String clubDetail(@PathVariable("clubId") Long clubId, Model model){
        ClubDto clubDto = clubService.findById(clubId);
        model.addAttribute("club",clubDto);
        return "clubs-detail";
    }
    @GetMapping("/clubs/new")
    public String createClubForm(Model model){
        Club club = new Club();
        model.addAttribute("club",club);
        return "clubs-create";
    }
    @GetMapping("/clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") Long clubId){
        clubService.delete(clubId);
        return "redirect:/clubs";
    }
    @PostMapping("/clubs/new")
    public String saveClub(@Valid @ModelAttribute("club") ClubDto clubDto, BindingResult result,Model model){
        if(result.hasErrors()){
            model.addAttribute("club",clubDto);
            return "clubs-create";
        }
        clubService.saveClub(clubDto);
        return "redirect:/clubs";
    }
    @GetMapping("/clubs/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") Long clubId,Model model){
        ClubDto club = clubService.findById(clubId);
        model.addAttribute("club",club);
        return "clubs-edit";
    }
    @PostMapping("/clubs/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") Long clubId,
                             @Valid @ModelAttribute("club") ClubDto club,
                             BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("club",club);
            return "clubs-edit";
        }
        club.setId(clubId);
        clubService.updateClub(club);
        return "redirect:/clubs";
    }
    @GetMapping("/clubs/search")
    public String searchClub(@RequestParam(value = "query") String query, Model model){
        List<ClubDto> clubs = clubService.searchClubs(query);
        model.addAttribute("clubs",clubs);
        return "clubs-list";
    }
}
