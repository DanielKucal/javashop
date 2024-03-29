package shop.view;

import javafx.scene.control.*;
import shop.interfaces.Materialized;
import shop.interfaces.Promotional;
import shop.interfaces.Resizeable;
import shop.products.*;
import shop.products.parameters.*;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created on 2016-02-01
 *
 * @author Daniel Kucal
 *         email: dkucal@gmail.com
 *         www: danielkucal.com
 */
public class ViewGenerator {
    private Product product;

    public ViewGenerator(Product product){
        this.product = product;
    }

    public HashMap<String, Control> generateForm(){
        return this.generateForm(false);
    }

    public LinkedHashMap<String, Control> generateForm(Boolean fill){
        LinkedHashMap<String, Control> list = new LinkedHashMap<>();

        if (this.getProduct() instanceof Materialized) {
            ComboBox<Material> material = new StyledComboBox<>();
            material.setPromptText("Material");
            material.getItems().setAll(Material.values());
            if (fill) {
                Materialized materialized = (Materialized) product;
                material.setValue(materialized.getMaterial());
            }
            list.put("productMaterial", material);
        }
        if (this.getProduct() instanceof Resizeable) {
            ComboBox<Size> size = new StyledComboBox<>();
            size.setPromptText("Size");
            size.getItems().setAll(Size.values());
            if (fill) {
                Resizeable resizeable = (Resizeable) product;
                size.setValue(resizeable.getSize());
            }
            list.put("productSize", size);
        }
        if (this.getProduct() instanceof Promotional){
            TextField discount = new StyledTextField();
            discount.setPromptText("Discount between 10-70%");
            DatePicker fromDate = new StyledDatePicker();
            fromDate.setPromptText("Discount from...");
            DatePicker toDate = new StyledDatePicker();
            toDate.setPromptText("Discount to...");
            if (fill) {
                Promotional promotional = (Promotional) product;
                Promotion promotion;
                if ((promotion = promotional.getPromotion()) != null) {
                    if (promotion.getDiscount() != null)
                        discount.setText(promotion.getDiscount().toString());
                    fromDate.setValue(promotion.getDateFrom());
                    toDate.setValue(promotion.getDateTo());
                }
            }
            list.put("productPromotionDiscount", discount);
            list.put("productPromotionDateFrom", fromDate);
            list.put("productPromotionDateTo", toDate);
        }

        switch (Type.valueOf(product.getClass().getSimpleName())) {
            case Jacket:
                ComboBox<ClaspType> clasp = new StyledComboBox<>();
                clasp.setPromptText("Clasp type");
                clasp.getItems().setAll(ClaspType.values());
                ComboBox<Season> season = new StyledComboBox<>();
                season.setPromptText("Season");
                season.getItems().setAll(Season.values());
                if (fill) {
                    Jacket jacket = (Jacket) product;
                    clasp.setValue(jacket.getClasp());
                    season.setValue(jacket.getSeason());
                }
                list.put("productClasp", clasp);
                list.put("productSeason", season);
                break;

            case Pants:
                TextField width = new StyledTextField();
                width.setPromptText("Width");
                TextField height = new StyledTextField();
                height.setPromptText("Height");
                if (fill) {
                    Pants pants = (Pants) product;
                    if (pants.getWidth() != null)
                        width.setText(pants.getWidth().toString());
                    if (pants.getHeight() != null)
                    height.setText(pants.getHeight().toString());
                }
                list.put("productWidth", width);
                list.put("productHeight", height);
                break;

            case Shirt:
                TextField collar = new StyledTextField();
                collar.setPromptText("Collar size");
                CheckBox isTieIncluded = new StyledCheckBox("Is tie included?");
                if (fill) {
                    Shirt shirt = (Shirt) product;
                    if (shirt.getCollarSize() != null)
                        collar.setText(shirt.getCollarSize().toString());
                    isTieIncluded.setSelected(shirt.getTieIncluded());
                }
                list.put("productCollar", collar);
                list.put("productTie", isTieIncluded);
                break;

            case Shoes:
                CheckBox hasHeel = new StyledCheckBox("Has heel?");
                TextField size = new StyledTextField();
                size.setPromptText("Size");
                if (fill) {
                    Shoes shoes = (Shoes) product;
                    hasHeel.setSelected(shoes.getHasHeel());
                    if (shoes.getSizeNum() != null)
                        size.setText(shoes.getSizeNum().toString());
                }
                list.put("productHeel", hasHeel);
                list.put("productSizeNum", size);
                break;
        }

        return list;
    }

    public Product getProduct() {
        return product;
    }

    public ViewGenerator setProduct(Product product) {
        this.product = product;
        return this;
    }
}

class StyledComboBox<T> extends ComboBox<T> {
    public StyledComboBox(){
        super();
        this.getStyleClass().add("input");
    }
}

class StyledTextField extends TextField {
    public StyledTextField(){
        super();
        this.getStyleClass().add("input");
    }
}

class StyledDatePicker extends DatePicker {
    public StyledDatePicker(){
        super();
        this.getStyleClass().add("input");
    }
}

class StyledCheckBox extends CheckBox {
    public StyledCheckBox(String text){
        super(text);
        this.getStyleClass().add("input");
    }
}
