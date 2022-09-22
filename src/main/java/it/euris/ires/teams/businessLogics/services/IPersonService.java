package it.euris.ires.teams.businessLogics.services;

import it.euris.ires.teams.dataPersistances.dataModels.Person;

public interface IPersonService {
  void create(Person person, String teamName);
  Person retrieveByName(String name);
}
