package me.drakonn.zydoxpreview.listener;

import me.drakonn.zydoxpreview.ZydoxPreview;
import me.drakonn.zydoxpreview.kits.Kit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class KitSelectListener implements Listener {

    @EventHandler
    public void onKitSelect(InventoryClickEvent event)
    {
        ItemStack clickedItem = event.getCurrentItem();
        Inventory inv = event.getClickedInventory();
        Player player = (Player)event.getWhoClicked();

        if(inv == null || ZydoxPreview.getInstance().getGui() == null)
            return;

        if(!inv.equals(ZydoxPreview.getInstance().getGui()))
            return;

        event.setCancelled(true);

        if(clickedItem == null || clickedItem.getType().equals(Material.AIR))
            return;

        Kit kit = ZydoxPreview.getKit(clickedItem);

        if(kit == null)
            return;

        player.openInventory(kit.getInv());
        player.updateInventory();
    }
}
