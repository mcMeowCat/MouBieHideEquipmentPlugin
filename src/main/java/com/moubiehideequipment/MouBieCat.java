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

import com.moubieapi.api.plugin.Register;
import com.moubieapi.moubieapi.plugin.MouBiePluginBase;
import com.moubiehideequipment.api.manager.EquipmentThreadManager;
import com.moubiehideequipment.api.yaml.MessageFileLoader;
import com.moubiehideequipment.listener.PlayerServerListener;
import com.moubiehideequipment.moubiehideequipment.commands.CommandMain;
import com.moubiehideequipment.moubiehideequipment.manager.EquipmentPacketThreadManager;
import com.moubiehideequipment.moubiehideequipment.yaml.PluginMessageFileLoader;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * 代表該插件本身的主類
 * @author MouBieCat
 */
public final class MouBieCat
        extends MouBiePluginBase {

    // 插件標題
    public static final String PLUGIN_TITLE = "§7[§fMouBie§6HideEquipment§7] §r";

    // 玩家數據包線程管理器
    private final EquipmentThreadManager threadManager = new EquipmentPacketThreadManager();

    // 插件嵌入式檔案(Message.yml)
    private MessageFileLoader messageLoader = null;

    /** This is plugin enable **/
    @Register(name = "註冊插件檔案", type = Register.ActionType.ACTION_ENABLE, priority = Register.ActionPriority.HIGHEST)
    public void registerFiles() {
        this.messageLoader = new PluginMessageFileLoader();
    }

    @Register(name = "註冊插件指令", type = Register.ActionType.ACTION_ENABLE, priority = Register.ActionPriority.NORMAL)
    public void registerCommands() {
        final PluginCommand command = this.getCommand("MouBieHideEquipment");
        if (command != null)
            command.setExecutor(new CommandMain());
    }

    @Register(name = "註冊插件事件", type = Register.ActionType.ACTION_ENABLE, priority = Register.ActionPriority.NORMAL)
    public void registerListener() {
        Bukkit.getPluginManager().registerEvents(new PlayerServerListener(), this);
    }

    /** This is plugin reload **/
    @Register(name = "重載插件檔案", type = Register.ActionType.ACTION_RELOAD, priority =  Register.ActionPriority.HIGHEST)
    public void reloadFiles() {
        this.messageLoader.reloadFile();
    }

    /**
     * 獲取玩家數據包線程管理器
     * @return 線程管理器
     */
    @NotNull
    public EquipmentThreadManager getThreadManager() {
        return threadManager;
    }

    /**
     * 獲取插件嵌入式檔案(Message.yml)
     * @return Message.yml
     */
    @NotNull
    public MessageFileLoader getMessageFile() {
        return this.messageLoader;
    }

    /**
     * 獲取當前插件實例
     * @return 插件本身
     */
    @NotNull
    public static MouBieCat getInstance() {
        return JavaPlugin.getPlugin(MouBieCat.class);
    }

}
