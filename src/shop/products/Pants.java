package shop.products;

/**
 * Created on 2015-11-13
 * @author Daniel Kucal
 * email: dkucal@gmail.com
 * www: danielkucal.com
 */
public class Pants extends Product {
    private Integer width = 32;
    private Integer height = 32;

    public Integer getWidth() {
        return width;
    }

    public Pants setWidth(Integer width) {
        this.width = width;
        return this;
    }

    public Integer getHeight() {
        return height;
    }

    public Pants setHeight(Integer height) {
        this.height = height;
        return this;
    }
}
