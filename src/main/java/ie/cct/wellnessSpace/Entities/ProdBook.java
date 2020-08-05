package ie.cct.wellnessSpace.Entities;

import javax.persistence.*;

@Table (name="prod_book")
@Entity
public class ProdBook {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="prodBook_seq")
    @SequenceGenerator(name = "prodBook_seq", sequenceName = "prodBook_seq", initialValue = 1, allocationSize=1)
    @Column(name="id_prodBook", nullable=false)
    private Integer id_prodBook;

    @Column(name="quantity", nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name="id_booking", nullable = false)
    private Bookings booking;

    @ManyToOne
    @JoinColumn(name="id_product", nullable = false)
    private Products product;

    public ProdBook(Integer id_prodBook) {
        this.id_prodBook = id_prodBook;
    }

    public ProdBook(int quantity, Bookings booking, Products product) {
        this.quantity = quantity;
        this.booking = booking;
        this.product = product;
    }

    public ProdBook() {
    }

    public Integer getId_prodBook() {
        return id_prodBook;
    }

    public void setId_prodBook(Integer id_prodBook) {
        this.id_prodBook = id_prodBook;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Bookings getBooking() {
        return booking;
    }

    public void setBooking(Bookings booking) {
        this.booking = booking;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }
}
