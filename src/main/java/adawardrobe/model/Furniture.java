package adawardrobe.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "furnitures")
public class Furniture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_furniture")
    private Long id;


    @ManyToOne
    @JoinColumn(
            name = "type_id",
            referencedColumnName = "id_type",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_furnitures_type")
    )
    private Type type;

    @ManyToOne
    @JoinColumn(
            name = "color_id",
            referencedColumnName = "id_color",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_furnitures_color")
    )
    private Color color;

    @ManyToOne
    @JoinColumn(
            name = "photo_id",
            referencedColumnName = "id_photo",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_furnitures_photo")
    )
    private Photo photo;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Float price;

    @ManyToOne
    @JoinColumn(
            name = "status_id",
            referencedColumnName = "id_status",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_furnitures_status")
    )
    private Status status;

    @ManyToOne
    @JoinColumn(
            name = "seller_id",
            referencedColumnName = "id_user",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_furnitures_seller")
    )
    private User seller;

    @Column(nullable = false)
    private Timestamp created_at;

    @Column
    private Timestamp updated_at;

    @OneToMany(mappedBy = "furniture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

}
