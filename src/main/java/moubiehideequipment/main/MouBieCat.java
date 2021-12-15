/**
 * LICENSE
 * MouBieHideEquipmentPlugin
 * -------------
 * Copyright (C) 2021 MouBieCat(MouBie_Yuki)
 * -------------
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 */

package moubiehideequipment.main;

import moubiehideequipment.command.CommandMain;
import moubiehideequipment.packet.EquipmentPacketThread;
import moubiehideequipment.packet.PacketHelper;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * 代表該插件本身的主類
 * @author MouBieCat
 */
public final class MouBieCat
        extends JavaPlugin {

    // 插件標題
    public static final String PLUGIN_TITLE = "§7[§fMouBie§6HideEquipment§7] §r";

    // 代表 Message.yml 檔案
    private YamlConfiguration messageYaml = new YamlConfiguration();

    // 數據包處裡幫手
    private final PacketHelper packetHelper = new PacketHelper();

    // 數據包發送線程經理
    private final PacketThreadManager<EquipmentPacketThread> hideEquipmentPacketThreadManager =
            new PacketThreadManager<>();

    /**
     * 當插件啟用時調用
     */
    @Override
    public void onEnable() {
        this.loadFiles();
        this.loadCommands();
        System.out.println(MouBieCat.PLUGIN_TITLE + "§7插件成功§2啟用§7！");
    }

    /**
     * 當插件關閉時調用
     */
    @Override
    public void onDisable() {
        System.out.println(MouBieCat.PLUGIN_TITLE + "§7插件成功§c關閉§7！");
    }

    /**
     * 當插件重讀時調用
     */
    public void onReload() {
        this.loadFiles();
        System.out.println(MouBieCat.PLUGIN_TITLE + "§7插件成功§e重讀§7！");
    }

    /**
     * 檔案加載函數
     */
    private void loadFiles() {
        final File message = new File(this.getDataFolder() + "\\Message.yml");
        if (!message.exists()) this.saveResource("Message.yml", false);
        this.messageYaml = YamlConfiguration.loadConfiguration(message);
    }

    /**
     * 加載指令
     */
    private void loadCommands() {
        final PluginCommand mouBieHideEquipment = this.getCommand("MouBieHideEquipment");
        if (mouBieHideEquipment != null)
            mouBieHideEquipment.setExecutor(new CommandMain());
    }

    /**
     * 獲取該插件實例本身
     * @return 插件本身
     */
    @NotNull
    public static MouBieCat getInstance() {
        return JavaPlugin.getPlugin(MouBieCat.class);
    }

    /**
     * 獲取 Message.yml 檔案內容
     * @return 檔案內容
     */
    @NotNull
    public YamlConfiguration getMessage() {
        return this.messageYaml;
    }

    /**
     * 獲取數據包幫手
     * @return 數據包幫手
     */
    @NotNull
    public PacketHelper getPacketHelper() {
        return this.packetHelper;
    }

    /**
     * 獲取數據包發送線程經理
     * @return 數據包發送線程經理
     */
    @NotNull
    public PacketThreadManager<EquipmentPacketThread> getPacketThreadManager() {
        return this.hideEquipmentPacketThreadManager;
    }

}
