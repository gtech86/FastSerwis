package pl.fastserwis.fastserwis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.fastserwis.fastserwis.model.Devices;

@Repository
public interface DeviceRepo extends CrudRepository<Devices, Long> {

}
