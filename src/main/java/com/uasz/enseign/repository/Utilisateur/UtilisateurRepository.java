package com.uasz.enseign.repository.Utilisateur;

import com.uasz.enseign.dto.Utilisateur.UtilisateurDTO;
import com.uasz.enseign.model.Maquette.UE;
import com.uasz.enseign.model.Utilisateur.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Utilisateur findByPrenom(String username);
//
//    List<Utilisateur> findByPrenom(String prenom);
//
//    List<Utilisateur> findByUes_Id(Long ueId);
}
