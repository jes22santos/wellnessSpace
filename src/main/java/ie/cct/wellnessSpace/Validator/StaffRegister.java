package ie.cct.wellnessSpace.Validator;

import javax.validation.constraints.NotEmpty;

public class StaffRegister {

    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;
    @NotEmpty
    private String credential;
    @NotEmpty
    private String category;

    public StaffRegister(@NotEmpty String name, @NotEmpty String surname, @NotEmpty String credential, @NotEmpty String category) {
        this.name = name;
        this.surname = surname;
        this.credential = credential;
        this.category = category;
    }

    public StaffRegister() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
