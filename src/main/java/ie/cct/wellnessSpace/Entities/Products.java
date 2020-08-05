package ie.cct.wellnessSpace.Entities;

import javax.persistence.*;

@Table(name="products")
@Entity
public class Products {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", initialValue = 1, allocationSize=1)
    @Column(name="id_product", nullable=false)
    private Integer id_product;

    @Column(name="name", nullable=false)
    private String name;
    @Column(name="cost", nullable=false)
    private double cost;
    @Column(name="quantity",nullable=false)
    private int quantity;

    public Products(String name, double cost, int quantity) {
        this.name = name;
        this.cost = cost;
        this.quantity = quantity;
    }

    public Products(Integer id_product) {
        this.id_product = id_product;
    }

    public Products() {
    }

    public Integer getId_product() {
        return id_product;
    }

    public void setId_product(Integer id_product) {
        this.id_product = id_product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
