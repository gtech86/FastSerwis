package pl.grabowski.fastserwis.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.grabowski.fastserwis.model.Devices;

@Repository
public interface DeviceRepo extends AbstractRepository<Devices> {
        Page<Devices> findAllBy(Pageable pageable);
        @Query(value = "FROM Devices dv JOIN FETCH dv.category WHERE dv.deviceId = :deviceId")
        Devices getDeviceById(Long deviceId);
}
