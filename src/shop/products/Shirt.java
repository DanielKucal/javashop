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

    public void setCollarSize(Integer collarSize) {
        this.collarSize = collarSize;
    }

    public Boolean getTieIncluded() {
        return isTieIncluded;
    }

    public void setTieIncluded(Boolean tieIncluded) {
        isTieIncluded = tieIncluded;
    }
}
