package shop.products;

import javafx.scene.image.Image;
import shop.products.parameters.Gender;
import shop.products.parameters.Price;

/**
 * Created on 2015-11-13
 * @author Daniel Kucal
 * email: dkucal@gmail.com
 * www: danielkucal.com
 */
public abstract class Product {
    private String name = "Undefined";
    private Image image = null;
    private Price price = new Price();
    private String brand = "unknown company";
    private String color = null;
    private Gender gender = Gender.UNISEX;
    private Integer id = 0;

    Product(){

    }

    Product(String name){
        this();
        this.name = name;
    }

    @Override
    public String toString(){
        return this.getClass().getSimpleName()
                + " " + this.getBrand()
                + " " + this.getName()
                + " [" + this.getPrice() + "]";
    }

    /**
     * Get product's name
     * @return product's name
     */
    public String getName() {
        return name;
    }

    /**
     * Set product's image
     * @param name new name for product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get product's image
     * @return product's image
     */
    public Image getImage() {
        return image;
    }

    /**
     * Set product's image
     * @param image new image for product
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * Get product's price
     * @return product's price
     */
    public Price getPrice() {
        return price;
    }

    /**
     * Set product's price
     * @param price new price for product
     */
    public void setPrice(Price price) {
        this.price = price;
    }

    /**
     * Get product's brand
     * @return brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Set product's brand
     * @param brand new brand for product
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Get product's color
     * @return color
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Set product's color
     * @param color new color for product
     */
    public void setColor(String color) {
        this.color = color;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
