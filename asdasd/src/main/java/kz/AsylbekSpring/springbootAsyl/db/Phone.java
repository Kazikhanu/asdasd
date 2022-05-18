package kz.AsylbekSpring.springbootAsyl.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "phone")

public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "marka")
    private String marka;
    @Column(name = "price")
    private int price;
    @Column(name = "desciption")
    private String description;
    @Column(name = "country")
    private String country;

}
