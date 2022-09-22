package it.euris.ires.teams.repositories;

import it.euris.ires.teams.dataPersistances.dataModels.Team;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Getter;

@Getter
public class TeamRepository {

  private static volatile TeamRepository instance;
  private final List<Team> teamList;

  private TeamRepository() {
    this.teamList = new ArrayList<>();
  }

  public static TeamRepository getInstance() {

    TeamRepository result = instance;
    if (Optional.ofNullable(result).isPresent()) {
      return result;
    }

    synchronized(TeamRepository.class) {
      if (!Optional.ofNullable(instance).isPresent()) {
        instance = new TeamRepository();
      }
      return instance;
    }
  }
}
