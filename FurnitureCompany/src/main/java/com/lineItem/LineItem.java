package com.lineItem;

import com.buyer.Buyer;
import com.furniture.Furniture;
import com.purchase.Purchase;

import javax.persistence.*;

@Entity
@Table(name = "line_item")
@SequenceGenerator(name = "line_item_gen", sequenceName = "line_item_gen", initialValue = 1000, allocationSize=1)
public class LineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "line_item_gen")
    @Column(name="line_item_id")
    private Long lineItemId;

    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "furniture_id")
    private Furniture furniture;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;
    private int quantity;

    public Purchase getPurchase() {
        return purchase;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public LineItem() {}

    public Long getLineItemId() {
        return lineItemId;
    }

    public void setLineItemId(Long lineItemId) {
        this.lineItemId = lineItemId;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Furniture getFurniture() {
        return furniture;
    }

    public void setFurniture(Furniture furniture) {
        this.furniture = furniture;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
