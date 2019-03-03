package me.drakonn.zydoxpreview.command;

import me.drakonn.zydoxpreview.ZydoxPreview;
import me.drakonn.zydoxpreview.datamanagers.MessageManager;
import me.drakonn.zydoxpreview.datamanagers.SetBlocksManager;
import me.drakonn.zydoxpreview.kits.Kit;
import me.drakonn.zydoxpreview.util.Util;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String label, String[] args) {

        if(!command.getLabel().equalsIgnoreCase("kitpreview"))
            return true;

        if(args.length != 0)
        {
            if(args[0].equalsIgnoreCase("reload"))
            {
                if(commandSender.hasPermission("zydoxpreview.reload")) {
                    ZydoxPreview.getInstance().saveConfig();
                    ZydoxPreview.getInstance().reloadConfig();
                    ZydoxPreview.getInstance().messageManager.loadMessages();
                    ZydoxPreview.getInstance().loadKits();
                    ZydoxPreview.getInstance().setGui(ZydoxPreview.getInstance().guiLoader.loadGui());
                    commandSender.sendMessage(Util.color("&cZydoxPreview has been reloaded"));
                    return true;
                }
                else
                {
                    commandSender.sendMessage(MessageManager.NO_PERMISSION);
                    return true;
                }
            }

            if(args[0].equalsIgnoreCase("help")){
                for(String string : MessageManager.help)
                    commandSender.sendMessage(string);

                return true;
            }
        }

        if(!(commandSender instanceof Player)) {
            commandSender.sendMessage("please run command ingame, do /kitpreview help for help");
            return false;
        }

        Player player = (Player)commandSender;

        if(args.length == 0)
        {
            if(player.hasPermission("zpreview.use"))
            {
                player.openInventory(ZydoxPreview.getInstance().getGui());
                return true;
            }
            else
            {
                player.sendMessage(MessageManager.NO_PERMISSION);
                return true;
            }
        }

        if(args.length == 1 && args[0].equalsIgnoreCase("setblock"))
        {
            if(player.hasPermission("zpreview.setblock"))
            {
                Location loc = player.getTargetBlock((Set<Material>)null, 5).getLocation();
                if(loc != null) {
                    SetBlocksManager.setBlock.add(loc);
                    player.sendMessage(Util.color("&8Set the block you are looking at to be clickable"));
                    return true;
                }
                else
                {
                    player.sendMessage(Util.color("&c&l(!) &cNot a valid block"));
                    return false;
                }
            }
            else
            {
                player.sendMessage(MessageManager.NO_PERMISSION);
                return false;
            }
        }

        if(args.length == 1 && args[0].equalsIgnoreCase("delblock"))
        {
            if(player.hasPermission("zpreview.delblock"))
            {
                Location loc = player.getTargetBlock((Set<Material>)null, 5).getLocation();
                if(loc != null && SetBlocksManager.setBlock.contains(loc)) {
                    SetBlocksManager.setBlock.remove(loc);
                    player.sendMessage(Util.color("&8Removed block"));
                    return true;
                }
                else
                {
                    player.sendMessage(Util.color("&c&l(!) &cNot a valid block"));
                    return false;
                }
            }
            else
            {
                player.sendMessage(MessageManager.NO_PERMISSION);
                return false;
            }
        }

        Kit kit = ZydoxPreview.isKit(args[0]);

        if(kit == null)
        {
            player.sendMessage(MessageManager.KIT_NOT_FOUND);
            return false;
        }

        if(args.length == 1)
        {
            if(player.hasPermission("zpreview.use")) {
                player.openInventory(kit.getInv());
                return true;
            }
            else
            {
                player.sendMessage(MessageManager.NO_PERMISSION);
                return true;
            }
        }


        return true;
    }
}
