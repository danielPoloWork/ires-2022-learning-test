package it.euris.ires.teams.repositories;

import it.euris.ires.teams.dataPersistances.dataModels.Person;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;

@Getter
public class PersonRepository {

  private static volatile PersonRepository instance;
  private final List<Person> personList;

  private PersonRepository() {
    this.personList = new ArrayList<>();
  }

  public static PersonRepository getInstance() {

    PersonRepository result = instance;
    if (Optional.ofNullable(result).isPresent()) {
      return result;
    }

    synchronized(PersonRepository.class) {
      if (!Optional.ofNullable(instance).isPresent()) {
        instance = new PersonRepository();
      }
      return instance;
    }
  }
}
