package me.drakonn.zydoxpreview.kits;

import me.drakonn.zydoxpreview.ZydoxPreview;
import me.drakonn.zydoxpreview.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Kit {

    private int iconInvSlot;
    private ItemStack iconItem;
    public List<KitItem> contents = new ArrayList<>();
    private ItemStack fillItem;
    private int size;
    private int backInvSlot;
    private String name;
    private Inventory inv;

    public Kit(ConfigurationSection section)
    {
        iconItem = Util.getItem(section.getConfigurationSection("iconitem"));
        fillItem = Util.getItem(section.getConfigurationSection("fillitem"));
        size = section.getInt("invsize");
        iconInvSlot = section.getInt("invslot");
        backInvSlot = section.getInt("backinvslot");
        name = section.getName();
        ConfigurationSection contentsSection = section.getConfigurationSection("contents");
        for(String content : contentsSection.getKeys(false))
        {
            ConfigurationSection contentSection = contentsSection.getConfigurationSection(content);
            int itemslot = contentSection.getInt("invslot");
            ItemStack item = Util.getItem(contentSection.getConfigurationSection("item"));
            String name = section.toString();
            contents.add(new KitItem(itemslot, item, name));
        }

        loadGui();
    }

    public void loadGui()
    {
        inv = Bukkit.createInventory(null, size, name);
        for(KitItem kitItem : contents)
            inv.setItem(kitItem.getInvslot(), kitItem.getItem());

        inv.setItem(backInvSlot, ZydoxPreview.getBackItem());

        for(int i = 0; i < size; i++)
        {
            if(inv.getItem(i) == null || inv.getItem(i).getType().equals(Material.AIR))
                inv.setItem(i, fillItem);
        }
    }

    public KitItem getKitItem(String name)
    {
        return contents.stream().filter(item -> item.getName().equalsIgnoreCase(name))
                .findFirst().orElse(null);
    }

    public String getName() {
        return name;
    }

    public int getIconInvSlot() {
        return iconInvSlot;
    }

    public Inventory getInv() {
        return inv;
    }

    public ItemStack getIconItem() {
        return iconItem;
    }
}
