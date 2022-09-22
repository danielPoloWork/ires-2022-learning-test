package it.euris.ires.teams.dataPersistances.dataModels;


import static java.util.UUID.randomUUID;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Team {

  private String uuid;
  private String name;
  private List<Person> personLists;

  public Team(String name, List<Person> personLists) {
    this.uuid = randomUUID().toString();
    this.name = name;
    this.personLists = personLists;
  }
}
