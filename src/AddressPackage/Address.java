package AddressPackage;

public class Address {
    private int id;
    private String addressCode;
    private String addressType;
    private String address;
    private String district;
    private String zipcode;
    private String city;

    public Address(String addressCode, String addressType, String address, String district, String zipcode, String city) {
        this.addressCode = addressCode;
        this.addressType = addressType;
        this.address = address;
        this.district = district;
        this.zipcode = zipcode;
        this.city = city;
    }

    // Constructor with ID (for updates and retrieval)
    public Address(int id, String addressCode, String addressType, String address, String district, String zipcode, String city) {
        this.id = id;
        this.addressCode = addressCode;
        this.addressType = addressType;
        this.address = address;
        this.district = district;
        this.zipcode = zipcode;
        this.city = city;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddressCode() {
        return addressCode;
    }

    public void setAddressCode(String addressCode) {
        this.addressCode = addressCode;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

