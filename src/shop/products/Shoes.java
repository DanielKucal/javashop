package shop.products;

/**
 * Created on 2015-11-13
 * @author Daniel Kucal
 * email: dkucal@gmail.com
 * www: danielkucal.com
 */
public class Shoes extends Product {
    private Boolean hasHeel = false;
    private Integer size;

    public Boolean getHasHeel() {
        return hasHeel;
    }

    public Shoes setHasHeel(Boolean hasHeel) {
        this.hasHeel = hasHeel;
        return this;
    }

    public Integer getSize() {
        return size;
    }

    public Shoes setSize(Integer size) {
        if(size < 10 || size > 50) throw new IllegalArgumentException("Shoe size must be between 10-50");
        this.size = size;
        return this;
    }
}
