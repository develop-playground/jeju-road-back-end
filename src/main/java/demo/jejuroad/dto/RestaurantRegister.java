package demo.jejuroad.dto;

import lombok.Data;

@Data
public class RestaurantRegister {

    private String name;
    private String category;
    private String introduction;
    private String wayToGo;
    private String zipcode;
    private String state;
    private String city;
    private String address;
    private double latitude;
    private double longitude;

    public String getCategory() {
        return category.toUpperCase();
    }
}
