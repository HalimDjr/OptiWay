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
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TourneeService {
    private final TourneeComponent tourneeComponent;
    private final TourneeMapper tourneeMapper;
    private final EquipeComponent equipeComponent;
    private final CommandeRepository commandeRepository;


    /**
     * @description
     *
     * cette méthode prend un objet de TourneeRequest le transfome en TourneeEntity et le stock
     * dans la base de données
     * les deux attribut Set<CommandeEntity> commandes et Equipe equipe de TourneeEntity sont assignées manuellement car
     * j'arrive pas à les mapper directement
     * l'assignation manuelle se fait à travers les deux méthodes commandeRelativeTournee et equipeAAjouter définie en dessous

     * construction de la tournee via new TourneeeEntity()

     * ajout de la tournee via tourneeComponent.createTournee(tourneeAjoute)
     * mapping de la tourneeAjoute-> tourneeRespopnseDto via tourneeMapepr
     *
     *
     *
     *
     * @param: objet de type TourneeRequest désérialize d'un json compatible avec les paramétres de TourneeRequest
     * @return: un objet de type TourneeResponseDto
     * */
    public TourneeResponseDTO createTournee(TourneeRequest tourneeRequest) {
        Set<CommandeEntity> commandesSet = commandeRelativeTournee(tourneeRequest);
        EquipeEntity equipeAjoute = equipeAAjouter(tourneeRequest.getNumeroEquipe());

        TourneeEntity tourneeAjoute = new TourneeEntity(
                tourneeRequest.getIdTournee(),
                tourneeRequest.getTempsTotal(),
                statutTourneeSelonValeur(tourneeRequest.getStatutTournee()),
                tourneeRequest.getDateTournee(),
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
     * @param : objet de type TourneeRequest dont lequelle on veut récupérer les commandes relatives à l'attribut commande_ids
     * @return : un objet de type Set<CommandEntity> qui est la listes des commandes relatives aux ids des commandes dans commande_ids >
     * */

    private Set<CommandeEntity> commandeRelativeTournee(TourneeRequest tourneeRequest){
        Set<CommandeEntity> commandesSet= new HashSet<>();
        Iterator<String> commandesAAjouterIdsIterator=tourneeRequest.getCommandes_ids().iterator();

        while(commandesAAjouterIdsIterator.hasNext()){
            String commande_Id=commandesAAjouterIdsIterator.next();
            CommandeEntity commandeAAjouter=commandeRepository.findById(commande_Id)
                    .orElseThrow(()->new NotFoundCommandException
                            (String.format("la commande avec l'identifiant %s n'existe pas vérifie les ids des commandes de cette tournee",commande_Id)));
            commandesSet.add(commandeAAjouter);
        }
        return commandesSet;

    }

    /**
     * @param numeroEquipe :le numéro de l'quipe pour laquelle on veut récupérer l'ntity
     * @return :l'entité correspendate aux paramétre numeroEquipe
     */

    private EquipeEntity equipeAAjouter(int numeroEquipe){
        EquipeEntity equipe = equipeComponent.getEquipeEntity(numeroEquipe);
        System.out.println("numero de equipe: "+equipe.getNumeroEquipe());
        System.out.println("nbHeuresMax: " +equipe.getNbHeuresMax());
        return equipe;
    }
    public List<TourneeResponseDTO> getTourneesByDate(LocalDate date) {
        System.out.println("Récupération des tournées pour la date:"+ date);

        List<TourneeEntity> tournees = tourneeComponent.getTourneesByDate(date);

        return tournees.stream()
                .map(tourneeMapper::toResponse)
                .collect(Collectors.toList());
    }



    private StatutTournee statutTourneeSelonValeur(int valeur) throws StatutTourneeException {
        switch (valeur){
            case 1:
                return StatutTournee.EN_COURS;
            case 2:
                return StatutTournee.PLANIFIEE;
            case 3:
                return StatutTournee.TERMINEE;
            default :
                throw new StatutTourneeException("mauvaise valeur pour statusTournee 1:En_Cour,2:PLANIFIEE,3:TERMINEE");

        }
    }

}
