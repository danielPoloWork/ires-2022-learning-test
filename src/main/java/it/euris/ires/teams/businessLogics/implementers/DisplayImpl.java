package it.euris.ires.teams.businessLogics.implementers;

import it.euris.ires.teams.businessLogics.services.IPersonService;
import it.euris.ires.teams.businessLogics.services.ITeamService;
import it.euris.ires.teams.businessLogics.services.displays.IDisplayFullService;
import it.euris.ires.teams.businessLogics.services.displays.IDisplayPersonService;
import it.euris.ires.teams.businessLogics.services.displays.IDisplayTeamService;
import it.euris.ires.teams.dataPersistances.dataModels.Person;
import it.euris.ires.teams.dataPersistances.dataModels.Team;
import it.euris.ires.teams.repositories.PersonRepository;
import it.euris.ires.teams.repositories.TeamRepository;
import java.util.Collections;
import java.util.Comparator;
import java.util.Optional;

public class DisplayImpl
    implements
        IDisplayFullService,
        IDisplayPersonService,
        IDisplayTeamService {

  private final PersonRepository personRepository;
  private final TeamRepository teamRepository;

  private final ITeamService teamService;

  public DisplayImpl() {
    this.personRepository = PersonRepository.getInstance();
    this.teamRepository = TeamRepository.getInstance();

    this.teamService = new TeamImpl();
  }

  @Override
  public void printAll() {
    teamRepository.getTeamList().sort(Comparator.comparing(Team::getName));
    for (Team t : teamRepository.getTeamList()) {
      System.out.printf("TEAM_%s\n", t.getName());
      t.getPersonLists().sort(Comparator.comparing(Person::getName));
      for (Person p : t.getPersonLists()) {
        System.out.printf("\t%s", p.getName());
      }
    }
  }

  @Override
  public void printPersonNameAndTeamName(String personName) {
    Optional<Team> teamOptional = Optional.ofNullable(teamService.retrieveByPersonName(personName));

    teamOptional
        .ifPresent(team -> System.out.printf("PERSON_%s, TEAM_%s\n", personName, team.getName()));
  }

  @Override
  public void printTeamPersonList(String teamName) {
    Optional<Team> teamOptional = Optional.ofNullable(teamService.retrieveByName(teamName));

    if (teamOptional.isPresent()) {
      System.out.printf("TEAM_%s\n", teamOptional.get().getName());
      teamOptional
          .get()
          .getPersonLists()
          .sort(Comparator.comparing(Person::getName));
      for (Person p : teamOptional.get().getPersonLists()) {
        System.out.printf("\t%s", p.getName());
      }
    }
  }
}
