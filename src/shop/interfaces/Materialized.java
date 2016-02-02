package shop.interfaces;

import shop.products.parameters.Material;

/**
 * Created on 2016-02-01
 *
 * @author Daniel Kucal
 *         email: dkucal@gmail.com
 *         www: danielkucal.com
 */
public interface Materialized {
    Material getMaterial();
    void setMaterial(Material material);
}
