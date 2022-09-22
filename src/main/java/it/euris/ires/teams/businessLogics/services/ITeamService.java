package it.euris.ires.teams.businessLogics.services;

import it.euris.ires.teams.dataPersistances.dataModels.Team;

public interface ITeamService {
  void create(Team team);
  Team retrieveByName(String name);
  Team retrieveByPersonName(String personName);
}
