package com.uasz.enseign.mapping.Utilisateur;
import com.uasz.enseign.dto.Utilisateur.UtilisateurDTO;
import com.uasz.enseign.model.Utilisateur.Utilisateur;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
@Service
public class UtilisateurMapper{
    public UtilisateurDTO fromUtilisateur(Utilisateur utilisateur){
        UtilisateurDTO utilisateurDTO=new UtilisateurDTO();
        BeanUtils.copyProperties(utilisateur,utilisateurDTO);
        return  utilisateurDTO;
    }
    public Utilisateur fromUtilisateurDTO(UtilisateurDTO utilisateurDTO){
        Utilisateur utilisateur=new Utilisateur();
        BeanUtils.copyProperties(utilisateurDTO,utilisateur);
        return  utilisateur;
    }

}
