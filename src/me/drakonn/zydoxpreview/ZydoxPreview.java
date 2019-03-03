package me.drakonn.zydoxpreview;

import me.drakonn.zydoxpreview.command.Command;
import me.drakonn.zydoxpreview.datamanagers.MessageManager;
import me.drakonn.zydoxpreview.datamanagers.SetBlocksManager;
import me.drakonn.zydoxpreview.kits.Kit;
import me.drakonn.zydoxpreview.listener.BackListener;
import me.drakonn.zydoxpreview.listener.BlockInteractListener;
import me.drakonn.zydoxpreview.listener.KitSelectListener;
import me.drakonn.zydoxpreview.loaders.GuiLoader;
import me.drakonn.zydoxpreview.util.Util;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class ZydoxPreview extends JavaPlugin {

    public static List<Kit> kits = new ArrayList<>();
    private Inventory gui;
    private static ZydoxPreview instance;
    private static ItemStack backItem;
    private SetBlocksManager setBlocksManager;
    public MessageManager messageManager;
    public GuiLoader guiLoader;

    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        setBlocksManager = new SetBlocksManager(this);
        messageManager = new MessageManager(this);
        setBlocksManager.loadBlocks();
        messageManager.loadMessages();
        backItem = Util.getItem(getConfig().getConfigurationSection("settings.backitem"));
        loadKits();
        guiLoader = new GuiLoader(this);
        gui = guiLoader.loadGui();
        registerListeners();
        registerCommands();
    }

    public void onDisable()
    {
        setBlocksManager.saveBlocks();
    }

    private void registerListeners()
    {
        getServer().getPluginManager().registerEvents(new KitSelectListener(), this);
        getServer().getPluginManager().registerEvents(new BackListener(), this);
        getServer().getPluginManager().registerEvents(new BlockInteractListener(), this);
    }

    private void registerCommands()
    {
        getCommand("kitpreview").setExecutor(new Command());
    }

    public void loadKits()
    {
        kits.clear();
        for(String kitName : getConfig().getConfigurationSection("kits").getKeys(false))
        {
            ConfigurationSection kitSection = getConfig().getConfigurationSection("kits."+kitName);
            kits.add(new Kit(kitSection));
        }
    }

    public static Kit isKit(String invTitle)
    {
        return kits.stream().filter(kit -> kit.getName().equalsIgnoreCase(invTitle))
                .findFirst().orElse(null);
    }

    public static Kit getKit(ItemStack iconItem)
    {
        if(!iconItem.hasItemMeta() || !iconItem.getItemMeta().hasDisplayName())
            return null;
        return kits.stream().filter(kit -> kit.getIconItem().getItemMeta().getDisplayName().equals(iconItem.getItemMeta().getDisplayName()))
                .findFirst().orElse(null);
    }

    public Inventory getGui() {
        return gui;
    }

    public static ZydoxPreview getInstance() {
        return instance;
    }

    public static ItemStack getBackItem() {
        return backItem;
    }

    public void setGui(Inventory gui) {
        this.gui = gui;
    }
}
