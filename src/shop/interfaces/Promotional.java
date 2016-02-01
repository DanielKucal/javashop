package shop.interfaces;

import shop.products.parameters.Promotion;

/**
 * Created on 2016-02-01
 *
 * @author Daniel Kucal
 *         email: dkucal@gmail.com
 *         www: danielkucal.com
 */
public interface Promotional {
    Promotion getPromotion();
    Promotional setPromotion(Promotion promotion);
}
