package info.joelwilson.model;

import javax.validation.constraints.Size;

public class Address {

    private String userId;

    @Size(min = 2, max = 100)
    private String address1;

    private String address2;

    @Size(min = 2, max = 100)
    private String city;

    @Size(min = 2, max = 100)
    private String country;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
