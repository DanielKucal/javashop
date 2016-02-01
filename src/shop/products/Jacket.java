package shop.products;

import shop.products.parameters.ClaspType;
import shop.products.parameters.Size;

/**
 * Created on 2015-11-13
 * @author Daniel Kucal
 * email: dkucal@gmail.com
 * www: danielkucal.com
 */
public class Jacket extends Product {
    private Size size = Size.M;
    private ClaspType clasp = ClaspType.Zip;

    public Jacket(){
        super();
    }

    public Size getSize() {
        return size;
    }

    public Jacket setSize(Size size) {
        this.size = size;
        return this;
    }

    public ClaspType getClasp() {
        return clasp;
    }

    public Jacket setClasp(ClaspType clasp) {
        this.clasp = clasp;
        return this;
    }
}
