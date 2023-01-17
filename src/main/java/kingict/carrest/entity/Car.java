package kingict.carrest.entity;

import javax.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "CAR")
public class Car {

    @Id
    @Column(name = "ID")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "BRAND")
    private String brand;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "PRICE")
    private Long price;

    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
    private List<CarRental> carRentals;

}
