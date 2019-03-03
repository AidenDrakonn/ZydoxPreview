package me.drakonn.zydoxpreview.loaders;

import me.drakonn.zydoxpreview.ZydoxPreview;
import me.drakonn.zydoxpreview.kits.Kit;
import me.drakonn.zydoxpreview.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GuiLoader {

    private ZydoxPreview plugin;
    public GuiLoader(ZydoxPreview plugin)
    {
        this.plugin = plugin;
    }

    public Inventory loadGui()
    {
        ConfigurationSection section = plugin.getConfig().getConfigurationSection("maingui");
        ItemStack fillItem = Util.getItem(section.getConfigurationSection("fillitem"));
        String name = section.getString("name");
        int size = section.getInt("size");
        Inventory inv = Bukkit.createInventory(null, size, name);
        for(Kit kit : plugin.kits)
            inv.setItem(kit.getIconInvSlot(), kit.getIconItem());

        for(int i = 0; i < size; i++)
        {
            if(inv.getItem(i) == null || inv.getItem(i).getType().equals(Material.AIR))
                inv.setItem(i, fillItem);
        }

        return inv;
    }
}
