package shop.products;

/**
 * Created on 2015-11-13
 * @author Daniel Kucal
 * email: dkucal@gmail.com
 * www: danielkucal.com
 */
public class Shirt extends AbstractShirt {
    private Integer collarSize;
    private Boolean isTieIncluded = false;

    public Shirt(){
        super();
    }

    public Integer getCollarSize() {
        return collarSize;
    }

    public Shirt setCollarSize(Integer collarSize) {
        this.collarSize = collarSize;
        return this;
    }

    public Boolean getTieIncluded() {
        return isTieIncluded;
    }

    public Shirt setTieIncluded(Boolean tieIncluded) {
        isTieIncluded = tieIncluded;
        return this;
    }
}
