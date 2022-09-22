package it.euris.ires.teams.businessLogics.implementers;

import it.euris.ires.teams.businessLogics.services.IPersonService;
import it.euris.ires.teams.businessLogics.services.ITeamService;
import it.euris.ires.teams.dataPersistances.dataModels.Person;
import it.euris.ires.teams.dataPersistances.dataModels.Team;
import it.euris.ires.teams.repositories.PersonRepository;
import it.euris.ires.teams.repositories.TeamRepository;
import java.util.Optional;

public class PersonImpl implements IPersonService {

  private final PersonRepository personRepository;
  private final TeamRepository teamRepository;
  private final ITeamService teamService;

  public PersonImpl() {
    this.personRepository = PersonRepository.getInstance();
    this.teamRepository = TeamRepository.getInstance();
    this.teamService = new TeamImpl();
  }

  @Override
  public void create(Person person, String teamName) {
    Optional<Team> teamOptional = Optional.ofNullable(teamService.retrieveByName(teamName));

    if (teamOptional.isPresent()) {
      int index = 0;
      for (Team t : teamRepository.getTeamList()) {
        if (t.getName().equals(teamName)) {
          Optional<Person> personOptional = Optional.ofNullable(retrieveByName(person.getName()));

          if (personOptional.isPresent()) {
            for (Person p : t.getPersonLists()) {
              if (p.getName().equals(person.getName())) {
                throw new IllegalArgumentException("Person already assigned to this team.");
              } else {
                personRepository.getPersonList().add(person);
                teamRepository.getTeamList().get(index).getPersonLists().add(person);
              }
            }
          } else {
            personRepository.getPersonList().add(person);
            teamRepository.getTeamList().get(index).getPersonLists().add(person);
          }
        }
        index++;
      }
    } else {
      throw new IllegalArgumentException("Team name does not exists!");
    }
  }

  @Override
  public Person retrieveByName(String name) {
    Person personFound = null;
      for (Person p : personRepository.getPersonList()) {
        if (p.getName().equals(name)) {
          personFound = p;
        }
      }
      return personFound;
    }
}
