package SIIT.Final.Project.repository;

import SIIT.Final.Project.model.AddTripM;
import org.springframework.data.repository.CrudRepository;

public interface TripRepository extends CrudRepository<AddTripM, Integer> {
}
