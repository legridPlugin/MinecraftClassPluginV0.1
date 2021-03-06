package me.legrid.newplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;


public final class NewPlugin extends JavaPlugin implements Listener {

    public Inventory inv;


    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        CreateGUI();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("openmenu"))
            if (sender instanceof Player) {
                Player p = (Player) sender;

                p.openInventory(inv);
                return true;
            }

        if (command.getName().equalsIgnoreCase("givehelmetsupport"))
            if (sender instanceof Player){
                Player p = (Player) sender;

                p.getInventory().addItem(getHelmet());
                return true;
            }
        return false;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!e.getClickedInventory().equals(inv)) return;
        if (e.getCurrentItem() == null) return;
        if (e.getCurrentItem().getItemMeta() == null) return;
        if (e.getCurrentItem().getItemMeta().getDisplayName() == null) return;
        e.setCancelled(true);

        Player p = (Player) e.getWhoClicked();

        if(e.getSlot() == 19) {
            p.setWalkSpeed(0.3f);
            p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(30);
            p.removePotionEffect(PotionEffectType.REGENERATION);
            p.removePotionEffect(PotionEffectType.WEAKNESS);
            p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1000000, 0));
            p.getInventory().setHelmet(getHelmet());
        }
        if(e.getSlot() == 22) {
            p.setWalkSpeed(0.1f);
            p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(50);
            p.removePotionEffect(PotionEffectType.REGENERATION);
            p.removePotionEffect(PotionEffectType.WEAKNESS);
            p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1000000, 0));
            p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 1000000, 0));
            p.getInventory().setHelmet(getHelmet2());
        }
        if(e.getSlot() == 25) {
            p.setWalkSpeed(0.2f);
            p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(16);
            p.removePotionEffect(PotionEffectType.REGENERATION);
            p.removePotionEffect(PotionEffectType.WEAKNESS);
            p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
            p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, 1));
            p.getInventory().setHelmet(getHelmet3());
        }
    }

    @EventHandler
    public void onHasHelmetSupport(PlayerMoveEvent e) {
        Player p = (Player) e.getPlayer();

        if (p.getInventory().getHelmet().getItemMeta().getDisplayName().contains("?????????? ????????????????"))
            if (p.getInventory().getHelmet().getItemMeta().hasLore()) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1000000, 0));
            }
        if (p.getInventory().getHelmet().getItemMeta().getDisplayName().contains("?????????? ??????????"))
            if (p.getInventory().getHelmet().getItemMeta().hasLore()) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1000000, 0));
                p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 1000000, 0));
            }
        if (p.getInventory().getHelmet().getItemMeta().getDisplayName().contains("?????????? ????????????"))
            if (p.getInventory().getHelmet().getItemMeta().hasLore()) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, 1));
            }
    }


    public ItemStack getHelmet(){
        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta meta = (LeatherArmorMeta) helmet.getItemMeta();
        meta.setDisplayName(ChatColor.GREEN + "?????????? ????????????????");
        meta.setColor(Color.GREEN);
        meta.addEnchant(Enchantment.BINDING_CURSE, 1, true);
        meta.setUnbreakable(true);
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "???????????????????? ????????, ?????? ????????????????");
        meta.setLore(lore);

        helmet.setItemMeta(meta);

        return helmet;
    }
    public ItemStack getHelmet2(){
        ItemStack helmet2 = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta meta2 = (LeatherArmorMeta) helmet2.getItemMeta();
        meta2.setDisplayName(ChatColor.BLUE + "?????????? ??????????");
        meta2.setColor(Color.BLUE);
        meta2.setUnbreakable(true);
        List<String> lore2 = new ArrayList<String>();
        lore2.add(ChatColor.WHITE + "" + ChatColor.BOLD + "???????????????????? ????????, ?????? ??????????");
        meta2.setLore(lore2);
        meta2.addEnchant(Enchantment.BINDING_CURSE, 1, true);

        helmet2.setItemMeta(meta2);

        return helmet2;
    }
    public ItemStack getHelmet3(){
        ItemStack helmet3 = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta meta3 = (LeatherArmorMeta) helmet3.getItemMeta();
        meta3.setDisplayName(ChatColor.RED + "?????????? ????????????");
        meta3.setColor(Color.RED);
        meta3.setUnbreakable(true);
        List<String> lore3 = new ArrayList<String>();
        lore3.add(ChatColor.WHITE + "" + ChatColor.BOLD + "???????????????????? ????????, ?????? ????????????");
        meta3.setLore(lore3);
        meta3.addEnchant(Enchantment.BINDING_CURSE, 1, true);

        helmet3.setItemMeta(meta3);

        return helmet3;
    }

    public void CreateGUI() {

        inv = Bukkit.createInventory(null, 36, ChatColor.GOLD + "???????????????? ??????????");

        //????????????????????
        ItemStack item = new ItemStack(Material.GREEN_GLAZED_TERRACOTTA);
        ItemMeta m = item.getItemMeta();
        m.setDisplayName(ChatColor.GREEN + "??????????????");
        List<String> l = new ArrayList<String>();
        l.add(ChatColor.WHITE + "" + ChatColor.BOLD + "?????????????????????? ????????????:");
        l.add(ChatColor.WHITE + "" + ChatColor.BOLD + "1) ?????????????????????? ???????????????? ????????????????????????");
        l.add(ChatColor.WHITE + "" + ChatColor.BOLD + "2) ?????????????????? ??????-???? ????");
        l.add(ChatColor.WHITE + "" + ChatColor.BOLD + "3) ?????????????????????? ????-??????????");
        l.add(ChatColor.WHITE + "" + ChatColor.BOLD + "4) ?????????? ???????????????????????????? ?????????? ???????????? ????????????");
        m.setLore(l);
        item.setItemMeta(m);
        inv.setItem(19, item);

        //????????????
        ItemStack item2 = new ItemStack(Material.LIGHT_BLUE_GLAZED_TERRACOTTA);
        ItemMeta m2 = item2.getItemMeta();
        m2.setDisplayName(ChatColor.BLUE + "????????");
        List<String> l2 = new ArrayList<String>();
        l2.add(ChatColor.WHITE + "" + ChatColor.BOLD + "?????????????????????? ????????????:");
        l2.add(ChatColor.WHITE + "" + ChatColor.BOLD + "1) ?????????????????????? ???????????????? ????????????????????????");
        l2.add(ChatColor.WHITE + "" + ChatColor.BOLD + "2) ???????????? ?????????????????? ??????-???? ????");
        l2.add(ChatColor.WHITE + "" + ChatColor.BOLD + "3) ?????????????????????? ????-??????????");
        l2.add(ChatColor.WHITE + "" + ChatColor.BOLD + "4) ?????????? ???????????????????????????? ?????????? ???????????????????????? ????????????");
        l2.add(ChatColor.WHITE + "" + ChatColor.BOLD + "5) ???????????????????? ????????????????");
        m2.setLore(l2);
        item2.setItemMeta(m2);
        inv.setItem(22, item2);

        //??????????????????????
        ItemStack item3 = new ItemStack(Material.RED_GLAZED_TERRACOTTA);
        ItemMeta m3 = item3.getItemMeta();
        m3.setDisplayName(ChatColor.RED + "????????????");
        List<String> l3 = new ArrayList<String>();
        l3.add(ChatColor.WHITE + "" + ChatColor.BOLD + "?????????????????????? ????????????:");
        l3.add(ChatColor.WHITE + "" + ChatColor.BOLD + "1) ???????????????????????? ???????????????? ????????????????????????");
        l3.add(ChatColor.WHITE + "" + ChatColor.BOLD + "2) ?????????????????? ??????-???? ????");
        l3.add(ChatColor.WHITE + "" + ChatColor.BOLD + "3) ?????????? ???????????????????????????? ?????????? ?????????? ???????????? ????????????");
        l3.add(ChatColor.WHITE + "" + ChatColor.BOLD + "4) ???????????????????? ???????? 2");
        m3.setLore(l3);
        item3.setItemMeta(m3);
        inv.setItem(25, item3);

        //???????????? ????????????
        ItemStack item4 = new ItemStack(Material.BARRIER);
        ItemMeta m4 = item4.getItemMeta();
        m4.setDisplayName(ChatColor.WHITE + "???????????? ?????? ????????????");
        List<String> l4 = new ArrayList<String>();
        l4.add(ChatColor.WHITE + "" + ChatColor.BOLD + "???????????????????????? ??????");
        m4.setLore(l4);
        item4.setItemMeta(m4);
        inv.setItem(4, item4);
    }

}



