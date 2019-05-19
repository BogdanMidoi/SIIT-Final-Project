package SIIT.Final.Project.service;

import SIIT.Final.Project.model.AddTripM;
import SIIT.Final.Project.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TripService {

    @Autowired
    TripRepository tripRepository;

    public Iterable<AddTripM> getAllTrips(){
        return tripRepository.findAll();
    }

    public AddTripM saveTrip(AddTripM a){
        return tripRepository.save(a);
    }

    public Optional<AddTripM> getTrip(Integer id){
        return tripRepository.findById(id);
    }
}

