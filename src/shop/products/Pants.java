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
    private Integer width;
    private Integer height;
    private Promotion promotion;
    private Material material;

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    @Override
    public Promotion getPromotion() {
        return promotion;
    }

    @Override
    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    @Override
    public Material getMaterial() {
        return material;
    }

    @Override
    public void setMaterial(Material material) {
        this.material = material;
    }
}
