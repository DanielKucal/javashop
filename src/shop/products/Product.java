package shop.products;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import shop.products.parameters.Price;

/**
 * Created on 2015-11-13
 * @author Daniel Kucal
 * email: dkucal@gmail.com
 * www: danielkucal.com
 */
public abstract class Product {
    String name = "Undefined";
    Image image = null;
    Price price = null;
    String brand = "unknown company";
    Color color = null;

    Product(){ }

    Product(String name){
        this();
        this.name = name;
    }

    @Override
    public String toString(){
        return this.getClass().getSimpleName()
                + " " + this.getName()
                + " (" + this.getColor() + ", "
                + this.getBrand() + ")";
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
     * @return this
     */
    public Product setName(String name) {
        this.name = name;
        return this;
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
     * @return this
     */
    public Product setImage(Image image) {
        this.image = image;
        return this;
    }

    /**
     * Get product's price
     * @return product's price
     */
    public Price getPrice() {
        return price;
    }

    /**
     * Set product's final price
     * @param price new price for product
     * @return this
     */
    public Product setPrice(double price) {
        this.price = new Price(price, true);
        return this;
    }

    /**
     * Set product's net price
     * @param price new net price for product
     * @return this
     */
    public Product setNetPrice(double price) {
        this.price = new Price(price);
        return this;
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
     * @return this
     */
    public Product setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    /**
     * Get product's color
     * @return color
     */
    public String getColor() {
        if (color != null)
            return color.toString();
        else
            return "undefined color";
    }

    /**
     * Set product's color
     * @param color new color for product
     * @return this
     */
    public Product setColor(Color color) {
        this.color = color;
        return this;
    }
}
