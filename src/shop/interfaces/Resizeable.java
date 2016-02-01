package shop.interfaces;

import shop.products.parameters.Size;

/**
 * Created on 2016-02-01
 *
 * @author Daniel Kucal
 *         email: dkucal@gmail.com
 *         www: danielkucal.com
 */
public interface Resizeable {
    Size getSize();
    Resizeable setSize(Size size);
}
