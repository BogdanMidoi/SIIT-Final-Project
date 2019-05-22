package SIIT.Final.Project.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name="travels")
public class AddTripM {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private long idProfile;

    @Size(min = 3, max = 30, message = "At least 3 characters")
    private String tripName;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "dateFrom")
    private LocalDate dateFrom;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "dateTo")
    private LocalDate dateTo;

    @Size(min = 20, max= 10000, message = "A thought about your trip")
    private String impressions;

    private String tripImpresions;

    private String tripImage;

    private String dateStart;

    private String dateFinish;

    private String locationTrip;

    public String getTripName() {
        return tripName;
    }

    public String getTripImpresions() {
        return tripImpresions;
    }

    public String getTripImage() {
        return tripImage;
    }

    public String getDateStart() {
        return dateStart;
    }

    public String getDateFinish() {
        return dateFinish;
    }

    public String getLocationTrip() {
        return locationTrip;
    }
}
