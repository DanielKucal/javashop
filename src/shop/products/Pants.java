package shop.products;

import shop.interfaces.Materialized;
import shop.interfaces.Promotional;
import shop.products.parameters.Material;
import shop.products.parameters.Promotion;

/**
 * Created on 2015-11-13
 * @author Daniel Kucal
 * email: dkucal@gmail.com
 * www: danielkucal.com
 */
public class Pants extends Product implements Promotional, Materialized {
    private Integer width = 32;
    private Integer height = 32;
    private Promotion promotion;
    private Material material;

    public Integer getWidth() {
        return width;
    }

    public Pants setWidth(Integer width) {
        this.width = width;
        return this;
    }

    public Integer getHeight() {
        return height;
    }

    public Pants setHeight(Integer height) {
        this.height = height;
        return this;
    }

    @Override
    public Promotion getPromotion() {
        return promotion;
    }

    @Override
    public Pants setPromotion(Promotion promotion) {
        this.promotion = promotion;
        return this;
    }

    @Override
    public Material getMaterial() {
        return material;
    }

    @Override
    public Pants setMaterial(Material material) {
        this.material = material;
        return this;
    }
}
