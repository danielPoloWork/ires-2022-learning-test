package it.euris.ires.teams.businessLogics.implementers;

import it.euris.ires.teams.businessLogics.services.ITeamService;
import it.euris.ires.teams.dataPersistances.dataModels.Person;
import it.euris.ires.teams.dataPersistances.dataModels.Team;
import it.euris.ires.teams.repositories.TeamRepository;
import java.util.Optional;

public class TeamImpl implements ITeamService {

  private final TeamRepository teamRepository;

  public TeamImpl() {
    this.teamRepository = TeamRepository.getInstance();
  }

  @Override
  public void create(Team team) {
    Optional<Team> teamOptional = Optional.ofNullable(retrieveByName(team.getName()));

    if (teamOptional.isPresent()) {
      throw new IllegalArgumentException("Team name does already exists!");
    } else {
      teamRepository.getTeamList().add(team);
    }
  }

  @Override
  public Team retrieveByName(String name) {
    Team teamFound = null;
    for (Team t : teamRepository.getTeamList()) {
      if (t.getName().equals(name)) {
        teamFound = t;
      }
    }
    return teamFound;
  }

  @Override
  public Team retrieveByPersonName(String personName) {
    Team teamFound = null;
    for (Team t : teamRepository.getTeamList()) {
      for (Person p : t.getPersonLists()) {
        if (p.getName().equals(personName)) {
          teamFound = t;
          break;
        }
      }
    }
    return teamFound;
  }
}