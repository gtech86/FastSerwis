package pl.grabowski.fastserwis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityNotFoundException;

import static org.springframework.util.Assert.notNull;

@NoRepositoryBean
public interface AbstractRepository<T> extends JpaRepository<T, Long> {

    default T getByIdOrThrow(Long id) {
        notNull(id, "Id is null");
        return findById(id).orElseThrow(() -> new EntityNotFoundException("Object not found for id: " + id));
    }

    default void checkIfExists(Long id, String entityName) {
        if (!existsById(id)) {
            throw new EntityNotFoundException(entityName + " not found for id: " + id);
        }
    }
}
