package SIIT.Final.Project.repository;

import SIIT.Final.Project.model.Trip;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FileUploadRep extends PagingAndSortingRepository<Trip, Long> {

    public Trip findByName(String name);
}
