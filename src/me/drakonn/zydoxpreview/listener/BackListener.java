package me.drakonn.zydoxpreview.listener;

import me.drakonn.zydoxpreview.ZydoxPreview;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BackListener implements Listener {

    @EventHandler
    public void onBackClick(InventoryClickEvent event)
    {
        ItemStack clickedItem = event.getCurrentItem();
        Inventory inv = event.getClickedInventory();
        Player player = (Player)event.getWhoClicked();

        if(clickedItem == null || clickedItem.getType().equals(Material.AIR))
            return;

        if(ZydoxPreview.isKit(inv.getTitle()) == null)
            return;

        event.setCancelled(true);

        if(!clickedItem.isSimilar(ZydoxPreview.getBackItem()))
            return;

        player.openInventory(ZydoxPreview.getInstance().getGui());
        player.updateInventory();
    }
}
