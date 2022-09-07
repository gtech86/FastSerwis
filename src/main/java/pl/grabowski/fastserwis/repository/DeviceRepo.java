package pl.grabowski.fastserwis.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.grabowski.fastserwis.model.Devices;

@Repository
public interface DeviceRepo extends CrudRepository<Devices, Long> {

}
