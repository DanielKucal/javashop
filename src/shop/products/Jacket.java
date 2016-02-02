package shop.products;

import shop.interfaces.Resizeable;
import shop.products.parameters.ClaspType;
import shop.products.parameters.Season;
import shop.products.parameters.Size;

/**
 * Created on 2015-11-13
 * @author Daniel Kucal
 * email: dkucal@gmail.com
 * www: danielkucal.com
 */
public class Jacket extends Product implements Resizeable {
    private Size size;
    private ClaspType clasp;
    private Season season;

    public Jacket(){
        super();
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public ClaspType getClasp() {
        return clasp;
    }

    public void setClasp(ClaspType clasp) {
        this.clasp = clasp;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }
}
