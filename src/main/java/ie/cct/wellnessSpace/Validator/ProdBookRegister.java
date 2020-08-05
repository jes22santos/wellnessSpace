package ie.cct.wellnessSpace.Validator;

public class ProdBookRegister {

    private Integer id_booking;
    private Integer id_product;
    private int quantity;

    public ProdBookRegister(Integer id_booking, Integer id_product, int quantity) {
        this.id_booking = id_booking;
        this.id_product = id_product;
        this.quantity = quantity;
    }

    public ProdBookRegister() {
    }

    public Integer getId_booking() {
        return id_booking;
    }

    public void setId_booking(Integer id_booking) {
        this.id_booking = id_booking;
    }

    public Integer getId_product() {
        return id_product;
    }

    public void setId_product(Integer id_product) {
        this.id_product = id_product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
