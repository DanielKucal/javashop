package shop.products;

/**
 * Created on 2015-11-13
 * @author Daniel Kucal
 * email: dkucal@gmail.com
 * www: danielkucal.com
 */
public class Shirt extends TShirt {
    Integer collarSize = 0;

    public Integer getCollarSize() {
        return collarSize;
    }

    public Shirt setCollarSize(Integer collarSize) {
        this.collarSize = collarSize;
        return this;
    }
}
