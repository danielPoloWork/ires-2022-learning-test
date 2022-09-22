package it.euris.ires.teams.dataPersistances.dataModels;

import static java.util.UUID.randomUUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Person {

  private String uuid;
  private String name;

  public Person(String name) {
    this.uuid = randomUUID().toString();
    this.name = name;
  }
}
