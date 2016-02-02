package shop.products;

import shop.interfaces.Materialized;
import shop.products.parameters.Material;

/**
 * Created on 2015-11-13
 * @author Daniel Kucal
 * email: dkucal@gmail.com
 * www: danielkucal.com
 */
public abstract class AbstractShirt extends Product implements Materialized {
    private Material material;

    public AbstractShirt(){
        super();
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
