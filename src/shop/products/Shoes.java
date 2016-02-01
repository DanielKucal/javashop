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
    private Integer sizeNum;
    private Promotion promotion;

    public Boolean getHasHeel() {
        return hasHeel;
    }

    public Shoes setHasHeel(Boolean hasHeel) {
        this.hasHeel = hasHeel;
        return this;
    }

    public Integer getSizeNum() {
        return sizeNum;
    }

    public Shoes setSizeNum(Integer sizeNum) {
        if(sizeNum < 10 || sizeNum > 50) throw new IllegalArgumentException("Shoe size must be between 10-50");
        this.sizeNum = sizeNum;
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
