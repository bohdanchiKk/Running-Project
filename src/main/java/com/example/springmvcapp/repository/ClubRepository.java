package com.example.springmvcapp.repository;

import com.example.springmvcapp.models.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
//CRUD (create,update,get,delete)
@Repository
public interface ClubRepository extends JpaRepository<Club,Long> {
    Optional<Club> findByTitle(String title);
    @Query("SELECT c from Club c where c.title like concat('%',:query,'%')")
    List<Club> searchClubs(String query);
}
