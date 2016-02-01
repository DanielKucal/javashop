package shop.products;

import shop.products.parameters.Material;

/**
 * Created on 2015-11-13
 * @author Daniel Kucal
 * email: dkucal@gmail.com
 * www: danielkucal.com
 */
public abstract class AbstractShirt extends Product {
    private Material material = Material.UNKNOWN;

    public AbstractShirt(){
        super();
    }

    public Material getMaterial() {
        return material;
    }

    public AbstractShirt setMaterial(Material material) {
        this.material = material;
        return this;
    }
}
