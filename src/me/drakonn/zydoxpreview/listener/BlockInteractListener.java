package me.drakonn.zydoxpreview.listener;

import me.drakonn.zydoxpreview.ZydoxPreview;
import me.drakonn.zydoxpreview.datamanagers.MessageManager;
import me.drakonn.zydoxpreview.datamanagers.SetBlocksManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockInteractListener implements Listener {

    @EventHandler
    public void onBlockClick(PlayerInteractEvent event)
    {
        if(!event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
            return;

        Player player = event.getPlayer();


        if(SetBlocksManager.setBlock.contains(event.getClickedBlock().getLocation())) {

            event.setCancelled(true);

            if (!player.hasPermission("zpreview.use")) {
                player.sendMessage(MessageManager.NO_PERMISSION);
                return;
            }

            player.openInventory(ZydoxPreview.getInstance().getGui());
        }
    }
}
