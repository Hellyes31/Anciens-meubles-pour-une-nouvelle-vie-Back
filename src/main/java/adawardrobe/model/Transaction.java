package adawardrobe.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_transaction")
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "id_furniture",
            referencedColumnName = "id_furniture",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_transactions_furniture")
    )
    private Furniture furniture;

    @ManyToOne
    @JoinColumn(
            name = "id_buyer",
            referencedColumnName = "id_user",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_transactions_buyer")
    )
    private User buyer;

    @Column(name = "sold_at", nullable = false)
    private Timestamp soldAt;


    @Column(name = "sold_price", nullable = false)
    private Float soldPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public Timestamp getSoldAt() {
        return soldAt;
    }

    public void setSoldAt(Timestamp soldAt) {
        this.soldAt = soldAt;
    }

    public Float getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(Float soldPrice) {
        this.soldPrice = soldPrice;
    }
}
