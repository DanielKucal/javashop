package shop.products;

import shop.products.parameters.Material;

/**
 * Created on 2015-11-13
 * @author Daniel Kucal
 * email: dkucal@gmail.com
 * www: danielkucal.com
 */
public class TShirt extends Product {
    Material material = Material.UNKNOWN;

    public Material getMaterial() {
        return material;
    }

    public TShirt setMaterial(Material material) {
        this.material = material;
        return this;
    }
}
