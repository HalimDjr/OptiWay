package fr.uga.miage.l3.services;

import fr.uga.miage.l3.component.EquipeComponent;
import fr.uga.miage.l3.component.TourneeComponent;
import fr.uga.miage.l3.enums.StatutTournee;
import fr.uga.miage.l3.exceptions.technical.NotFoundCommandException;
import fr.uga.miage.l3.exceptions.technical.StatutTourneeException;
import fr.uga.miage.l3.mappers.TourneeMapper;
import fr.uga.miage.l3.models.CommandeEntity;
import fr.uga.miage.l3.models.EquipeEntity;
import fr.uga.miage.l3.models.TourneeEntity;
import fr.uga.miage.l3.repository.CommandeRepository;
import fr.uga.miage.l3.request.TourneeRequest;
import fr.uga.miage.l3.responses.TourneeResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TourneeService {
    private final TourneeComponent tourneeComponent;
    private final TourneeMapper tourneeMapper;
    private final EquipeComponent equipeComponent;
    private final CommandeRepository commandeRepository;

    /**
     * Crée une tournée à partir d'un TourneeRequest,
     * sauvegarde en base et met à jour la FK tournee dans chaque commande
     */
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public TourneeResponseDTO createTournee(TourneeRequest tourneeRequest) {
        Set<CommandeEntity> commandesSet = commandeRelativeTournee(tourneeRequest);
        EquipeEntity equipeAjoute = equipeAAjouter(tourneeRequest.getNumeroEquipe());

        TourneeEntity tourneeAjoute = new TourneeEntity(
                tourneeRequest.getIdTournee(),
                tourneeRequest.getTempsTotal(),
                statutTourneeSelonValeur(tourneeRequest.getStatutTournee()),
                tourneeRequest.getHeureDepart(),
                tourneeRequest.getDistanceTotale(),
                commandesSet,
                equipeAjoute
        );

        TourneeEntity saved = tourneeComponent.createTournee(tourneeAjoute);

        System.out.println("Tournée ajoutée avec succès ! ID: " + saved.getIdTournee());
        TourneeResponseDTO response = tourneeMapper.toResponse(saved);
        System.out.println("Response DTO: " + response);
        return response;
    }

    /**
     * Récupère les tournées par date
     */
    public List<TourneeResponseDTO> getTourneesByDate(LocalDate date) {
        System.out.println("Récupération des tournées pour la date:" + date);
        List<TourneeEntity> tournees = tourneeComponent.getTourneesByDate(date);
        return tournees.stream()
                .map(tourneeMapper::toResponse)
                .collect(Collectors.toList());
    }

    /**
     * Récupère les CommandeEntity correspondant aux ids dans TourneeRequest
     */
    private Set<CommandeEntity> commandeRelativeTournee(TourneeRequest tourneeRequest) {
        Set<CommandeEntity> commandesSet = new HashSet<>();
        Iterator<String> commandesAAjouterIdsIterator = tourneeRequest.getCommandes_ids().iterator();

        while (commandesAAjouterIdsIterator.hasNext()) {
            String commande_Id = commandesAAjouterIdsIterator.next();
            CommandeEntity commandeAAjouter = commandeRepository.findById(commande_Id)
                    .orElseThrow(() -> new NotFoundCommandException(
                            String.format("la commande avec l'identifiant %s n'existe pas", commande_Id)
                    ));
            commandesSet.add(commandeAAjouter);
        }
        return commandesSet;
    }

    /**
     * Récupère l'EquipeEntity correspondant au numeroEquipe
     */
    private EquipeEntity equipeAAjouter(int numeroEquipe) {
        EquipeEntity equipe = equipeComponent.getEquipeEntity(numeroEquipe);
        System.out.println("numero de equipe: " + equipe.getNumeroEquipe());
        System.out.println("nbHeuresMax: " + equipe.getNbHeuresMax());
        return equipe;
    }

    /**
     * Convertit un entier en StatutTournee
     * 1 → EN_COURS, 2 → PLANIFIEE, 3 → TERMINEE
     */
    private StatutTournee statutTourneeSelonValeur(int valeur) throws StatutTourneeException {
        switch (valeur) {
            case 1:
                return StatutTournee.EN_COURS;
            case 2:
                return StatutTournee.PLANIFIEE;
            case 3:
                return StatutTournee.TERMINEE;
            default:
                throw new StatutTourneeException(
                        "mauvaise valeur pour statusTournee 1:EN_COURS, 2:PLANIFIEE, 3:TERMINEE"
                );
        }
    }
    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
public TourneeResponseDTO updateTournee(int id, TourneeRequest request) {
    TourneeEntity tournee = tourneeComponent.getTourneeById(id)
            .orElseThrow(() -> new RuntimeException("Tournée " + id + " non trouvée"));

    // Met à jour les commandes
    Set<CommandeEntity> commandes = commandeRelativeTournee(request);
    tournee.setCommandes(commandes);

    // Met à jour l'équipe
    if (request.getNumeroEquipe() > 0) {
        EquipeEntity equipe = equipeAAjouter(request.getNumeroEquipe());
        tournee.setEquipe(equipe);
    }

    TourneeEntity saved = tourneeComponent.createTournee(tournee);
    return tourneeMapper.toResponse(saved);
}
}