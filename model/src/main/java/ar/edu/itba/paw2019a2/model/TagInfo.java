package ar.edu.itba.paw2019a2.model;

import ar.edu.itba.paw2019a2.model.Enum.Tag;

public class TagInfo {
    private Tag tag;

    private int amount;

    public TagInfo(Tag tag){
        this.tag = tag;
        this.amount = 0;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
