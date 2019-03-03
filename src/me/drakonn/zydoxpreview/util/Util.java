package me.drakonn.zydoxpreview.util;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Field;
import java.util.*;

public class Util {

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException var2) {
            return false;
        }
    }

    public static String color(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static List<String> color(List<String> list) {
        List<String> colored = new ArrayList();
        Iterator var3 = list.iterator();

        while(var3.hasNext()) {
            String s = (String)var3.next();
            colored.add(color(s));
        }

        return colored;
    }

    public static Material getMaterial(String s) {
        s = s.toUpperCase().replace(" ", "_");
        if (s.contains(":")) {
            s = s.split(":")[0];
        }

        return Material.getMaterial(s) != null ? Material.getMaterial(s) : (Material.matchMaterial(s) != null ? Material.matchMaterial(s) : (Material.valueOf(s) != null ? Material.valueOf(s) : (isInt(s) ? Material.getMaterial(Integer.parseInt(s)) : Material.PAPER)));
    }


    public static class EnchantGlow extends EnchantmentWrapper {
        private static Enchantment glow = null;
        private final String name = "Glow";

        public static ItemStack addGlow(ItemStack itemstack) {
            itemstack.addEnchantment(getGlow(), 1);
            return itemstack;
        }

        public static Enchantment getGlow() {
            if (glow != null) {
                return glow;
            } else {
                Field field = null;

                try {
                    field = Enchantment.class.getDeclaredField("acceptingNew");
                } catch (SecurityException | NoSuchFieldException var4) {
                    var4.printStackTrace();
                    return glow;
                }

                field.setAccessible(true);

                try {
                    field.set((Object)null, true);
                } catch (IllegalAccessException | IllegalArgumentException var3) {
                    var3.printStackTrace();
                }

                try {
                    glow = new EnchantGlow(Enchantment.values().length + 100);
                } catch (Exception var2) {
                    glow = Enchantment.getByName("Glow");
                }

                if (Enchantment.getByName("Glow") == null) {
                    Enchantment.registerEnchantment(glow);
                }

                return glow;
            }
        }

        public String getName() {
            return this.name;
        }

        public Enchantment getEnchantment() {
            return Enchantment.getByName("Glow");
        }

        public int getMaxLevel() {
            return 1;
        }

        public int getStartLevel() {
            return 1;
        }

        public EnchantmentTarget getItemTarget() {
            return EnchantmentTarget.ALL;
        }

        public boolean canEnchantItem(ItemStack item) {
            return true;
        }

        public boolean conflictsWith(Enchantment other) {
            return false;
        }

        public EnchantGlow(int i) {
            super(i);
        }
    }

    public static Enchantment getEnchantment(final String s) {
        final Map<String, Enchantment> enchants = new HashMap<String, Enchantment>();
        enchants.put("power", Enchantment.ARROW_DAMAGE);
        enchants.put("flame", Enchantment.ARROW_FIRE);
        enchants.put("infinite", Enchantment.ARROW_INFINITE);
        enchants.put("punch", Enchantment.ARROW_KNOCKBACK);
        enchants.put("sharp", Enchantment.DAMAGE_ALL);
        enchants.put("damage", Enchantment.DAMAGE_ALL);
        enchants.put("sharpness", Enchantment.DAMAGE_ALL);
        enchants.put("arthropod", Enchantment.DAMAGE_ARTHROPODS);
        enchants.put("arthropods", Enchantment.DAMAGE_ARTHROPODS);
        enchants.put("smite", Enchantment.DAMAGE_UNDEAD);
        enchants.put("mining", Enchantment.DIG_SPEED);
        enchants.put("efficiency", Enchantment.DIG_SPEED);
        enchants.put("unbreaking", Enchantment.DURABILITY);
        enchants.put("fire", Enchantment.FIRE_ASPECT);
        enchants.put("kb", Enchantment.KNOCKBACK);
        enchants.put("fortune", Enchantment.LOOT_BONUS_BLOCKS);
        enchants.put("loot", Enchantment.LOOT_BONUS_MOBS);
        enchants.put("looting", Enchantment.LOOT_BONUS_MOBS);
        enchants.put("water", Enchantment.OXYGEN);
        enchants.put("waterbreathing", Enchantment.OXYGEN);
        enchants.put("prot", Enchantment.PROTECTION_ENVIRONMENTAL);
        enchants.put("protection", Enchantment.PROTECTION_ENVIRONMENTAL);
        enchants.put("explosive", Enchantment.PROTECTION_EXPLOSIONS);
        enchants.put("explosions", Enchantment.PROTECTION_EXPLOSIONS);
        enchants.put("protexplosives", Enchantment.PROTECTION_EXPLOSIONS);
        enchants.put("protexplosions", Enchantment.PROTECTION_EXPLOSIONS);
        enchants.put("explosiveprot", Enchantment.PROTECTION_EXPLOSIONS);
        enchants.put("explosiveprotection", Enchantment.PROTECTION_EXPLOSIONS);
        enchants.put("fall", Enchantment.PROTECTION_FALL);
        enchants.put("feather", Enchantment.PROTECTION_FALL);
        enchants.put("falling", Enchantment.PROTECTION_FALL);
        enchants.put("featherfalling", Enchantment.PROTECTION_FALL);
        enchants.put("fireprot", Enchantment.PROTECTION_FIRE);
        enchants.put("fireprotection", Enchantment.PROTECTION_FIRE);
        enchants.put("projprot", Enchantment.PROTECTION_PROJECTILE);
        enchants.put("projprotection", Enchantment.PROTECTION_PROJECTILE);
        enchants.put("projectileprot", Enchantment.PROTECTION_PROJECTILE);
        enchants.put("arrowprotection", Enchantment.PROTECTION_PROJECTILE);
        enchants.put("projectileprotection", Enchantment.PROTECTION_PROJECTILE);
        enchants.put("silk", Enchantment.SILK_TOUCH);
        enchants.put("silktouch", Enchantment.SILK_TOUCH);
        enchants.put("watermine", Enchantment.WATER_WORKER);
        enchants.put("watermining", Enchantment.WATER_WORKER);
        enchants.put("arrowdamage", Enchantment.ARROW_DAMAGE);
        enchants.put("arrowfire", Enchantment.ARROW_FIRE);
        enchants.put("arrowinfinite", Enchantment.ARROW_INFINITE);
        enchants.put("arrowknockback", Enchantment.ARROW_KNOCKBACK);
        enchants.put("damageall", Enchantment.DAMAGE_ALL);
        enchants.put("damagearthropods", Enchantment.DAMAGE_ARTHROPODS);
        enchants.put("damageundead", Enchantment.DAMAGE_UNDEAD);
        enchants.put("digspeed", Enchantment.DIG_SPEED);
        enchants.put("fireaspect", Enchantment.FIRE_ASPECT);
        enchants.put("lootbonusblocks", Enchantment.LOOT_BONUS_BLOCKS);
        enchants.put("lootbonusmobs", Enchantment.LOOT_BONUS_MOBS);
        enchants.put("protectionenviromental", Enchantment.PROTECTION_ENVIRONMENTAL);
        enchants.put("protectionexplosions", Enchantment.PROTECTION_EXPLOSIONS);
        enchants.put("protectionfall", Enchantment.PROTECTION_FALL);
        enchants.put("protectionfire", Enchantment.PROTECTION_FIRE);
        enchants.put("protectionprojectile", Enchantment.PROTECTION_PROJECTILE);
        enchants.put("waterworker", Enchantment.WATER_WORKER);
        enchants.put("thorns", Enchantment.THORNS);
        return Enchantment.getByName(s) != null ? Enchantment.getByName(s.toUpperCase())
                : enchants.containsKey(s.toLowerCase().replace("_", ""))
                ? enchants.get(s.toLowerCase().replace("_", ""))
                : null;
    }


    public static ItemStack getItem(final ConfigurationSection section) {
        Set<String> keys = section.getKeys(false);
        ItemStack item = new ItemStack(Material.PAPER);
        if(keys.contains("material")) {
            if (getMaterial(section.getString("material")) != null)
                item.setType(getMaterial(section.getString("material")));
            else item.setType(Material.PAPER);
            if (section.getString("material").split(":").length > 1 && isInt(section.getString("material").split(":")[1]))
                item.setDurability((short) Integer.parseInt(section.getString("material").split(":")[1]));
        }

        final ItemMeta meta = item.getItemMeta();

        if (keys.contains("name"))
            meta.setDisplayName(color(section.getString( "name")));
        else meta.setDisplayName("");

        if (keys.contains("lore"))
            meta.setLore(color(section.getStringList( "lore")));

        if (keys.contains("enchanted") && section.getBoolean("enchanted"))
            meta.addEnchant(EnchantGlow.getGlow(), 1, true);

        if(keys.contains("enchantments"))
        {
            for(String enchantmentName : section.getStringList("enchantments"))
            {
                Enchantment enchantment = getEnchantment(enchantmentName.split(":")[0]);
                int level = Integer.valueOf(enchantmentName.split(":")[1]);
                if(enchantment != null) {
                    meta.addEnchant(enchantment, level, true);
                    System.out.println("[ZydoxPreview] applying enchantment " + enchantment.toString() + " of level " + enchantmentName.split(":")[1] + " to " + section.getName());
                }
                else System.out.println("[ZydoxPreview] Failed to load enchantment for " + section.getName() + " invalid enchantment");
            }
        }
        else System.out.println("[ZydoxPreview] Failed to load enchantment for " + section.getName() + " no enchantment");

        item.setItemMeta(meta);
        return item;
    }

    public static Location stringToLocation(final Plugin p, final String s) {
        if (p == null || s == null || s.isEmpty())
            return null;
        final String[] args = s.split(",");
        try {
            return new Location(p.getServer().getWorld(args[0].trim()), Double.parseDouble(args[1].trim()),
                    Double.parseDouble(args[2].trim()), Double.parseDouble(args[3].trim()),
                    (float) Double.parseDouble(args[4].trim()), (float) Double.parseDouble(args[5].trim()));
        } catch (final NullPointerException e) {
            return new Location(
                    p.getServer().getWorlds().stream().filter(w -> w.getEnvironment() == World.Environment.NORMAL).findFirst()
                            .get(),
                    Double.parseDouble(args[1].trim()), Double.parseDouble(args[2].trim()),
                    Double.parseDouble(args[3].trim()), (float) Double.parseDouble(args[4].trim()),
                    (float) Double.parseDouble(args[5].trim()));
        } catch (final NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public static String locationToString(final Location l) {
        try {
            return l.getWorld().getName() + "," + round(l.getX()) + "," + round(l.getY()) + "," + round(l.getZ()) + ","
                    + round(l.getYaw()) + "," + round(l.getPitch());
        } catch (final NullPointerException e) {
            return "";
        }
    }

    public static double round(final double num) {
        return (int) (num * 100) / 100.0;
    }
}
