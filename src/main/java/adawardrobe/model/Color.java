package adawardrobe.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "colors")
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_color")
    private Long id;

    @Column(nullable = false)
    private String color;

    @OneToMany(mappedBy = "color", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Furniture> furnitures;
}
