package shop.products;

import shop.products.parameters.Size;

/**
 * Created on 2016-01-31
 *
 * @author Daniel Kucal
 *         email: dkucal@gmail.com
 *         www: danielkucal.com
 */
public class TShirt extends AbstractShirt {
    private Size size = Size.M;

    public Size getSize() {
        return size;
    }

    public TShirt setSize(Size size) {
        this.size = size;
        return this;
    }
}
