package me.drakonn.zydoxpreview.kits;

import org.bukkit.inventory.ItemStack;

public class KitItem {

    private int invslot;
    private ItemStack item;
    private String name;

    public KitItem(int invslot, ItemStack item, String name)
    {
        this.invslot = invslot;
        this.item = item;
        this.name = name;
    }

    public int getInvslot() {
        return invslot;
    }

    public void setInvslot(int invslot) {
        this.invslot = invslot;
    }

    public ItemStack getItem() {
        return item.clone();
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public String getName() {
        return name;
    }
}
