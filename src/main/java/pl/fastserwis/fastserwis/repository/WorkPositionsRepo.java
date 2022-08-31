package pl.fastserwis.fastserwis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.fastserwis.fastserwis.model.WorkPositions;

@Repository
public interface WorkPositionsRepo extends CrudRepository<WorkPositions, Long> {
}
