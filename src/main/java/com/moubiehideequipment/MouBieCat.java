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

package com.moubiehideequipment;

import com.moubieapi.api.manager.Manager;
import com.moubieapi.api.plugin.MouBiePlugin;
import com.moubiehideequipment.command.CommandMain;
import com.moubiehideequipment.packet.EquipmentPacketThread;
import com.moubiehideequipment.yaml.PluginMessage;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/**
 * 代表該插件本身的主類
 * @author MouBieCat
 */
public final class MouBieCat
        extends MouBiePlugin {

    // 插件標題
    public static final String PLUGIN_TITLE = "§7[§fMouBie§6HideEquipment§7] §r";

    // 代表 Message.yml 檔案
    private PluginMessage message = null;

    // 代表數據包發送紀錄器
    private final PacketThreadManager manager = new PacketThreadManager();

    /**
     * 加載檔案時調用
     */
    @Override
    protected void loadFiles() {
        this.message = new PluginMessage();
    }

    /**
     * 加載指令時調用
     */
    @Override
    protected void loadCommands() {
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
     * 獲取插件嵌入式檔案 Message.yml
     * @return Message.yml
     */
    @NotNull
    public PluginMessage getMessageFile() {
        return this.message;
    }

    /**
     * 獲取數據包發送紀錄器
     * @return 紀錄器
     */
    @NotNull
    public Manager<UUID, EquipmentPacketThread> getPacketManager() {
        return this.manager;
    }

}
