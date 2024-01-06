package Dao;

import entities.Utilisateur;
import java.util.List;


public interface UtilisateurDao {
    List<Utilisateur> findAll();
    Utilisateur findById(Integer id);
    Utilisateur save(Utilisateur utilisateur);
    boolean update(Utilisateur utilisateur);
    boolean delete(Utilisateur utilisateur);
}
