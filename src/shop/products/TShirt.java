package shop.products;

import shop.interfaces.Resizeable;
import shop.products.parameters.Size;

/**
 * Created on 2016-01-31
 *
 * @author Daniel Kucal
 *         email: dkucal@gmail.com
 *         www: danielkucal.com
 */
public class TShirt extends AbstractShirt implements Resizeable {
    private Size size;

    public Size getSize() {
        return size;
    }

    public TShirt setSize(Size size) {
        this.size = size;
        return this;
    }
}
