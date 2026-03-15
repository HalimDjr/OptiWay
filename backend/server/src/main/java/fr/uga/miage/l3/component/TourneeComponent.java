package fr.uga.miage.l3.component;


import fr.uga.miage.l3.models.TourneeEntity;

import fr.uga.miage.l3.repository.TourneeRepository;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TourneeComponent {
    private final TourneeRepository tourneeRepository;
    public TourneeEntity createTournee(TourneeEntity tourneeEntity){

        return tourneeRepository.save(tourneeEntity);
    }
    public List<TourneeEntity> getTourneesByDate(LocalDate date) {
        // Convertir LocalDate en Date pour la requête
        Date sqlDate = Date.valueOf(date);
        return tourneeRepository.findByDateTournee(sqlDate);
    }
}
