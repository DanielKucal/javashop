package shop.products;

import shop.interfaces.Promotional;
import shop.products.parameters.Promotion;

/**
 * Created on 2015-11-13
 * @author Daniel Kucal
 * email: dkucal@gmail.com
 * www: danielkucal.com
 */
public class Shoes extends Product implements Promotional {
    private Boolean hasHeel = false;
    private Integer size;
    private Promotion promotion;

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

    @Override
    public Promotion getPromotion() {
        return promotion;
    }

    @Override
    public Shoes setPromotion(Promotion promotion) {
        this.promotion = promotion;
        return this;
    }
}
