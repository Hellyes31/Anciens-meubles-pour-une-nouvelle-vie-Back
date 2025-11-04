package adawardrobe.model;

import jakarta.persistence.*;

import java.sql.Date;

public class Transactions {

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
    private Furnitures furniture;

    @ManyToOne
    @JoinColumn(
            name = "id_buyer",
            referencedColumnName = "id_user",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_transactions_buyer")
    )
    private Users buyer;

    @Column(name = "sold_at", nullable = false)
    private Date soldAt;

    @Column(name = "sold_price", nullable = false)
    private Float soldPrice;
}
