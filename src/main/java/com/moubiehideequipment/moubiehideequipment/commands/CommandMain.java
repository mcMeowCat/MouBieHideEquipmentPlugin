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

package com.moubiehideequipment.moubiehideequipment.commands;

import com.moubieapi.moubieapi.manager.CommandManagerAbstract;
import com.moubiehideequipment.MouBieCat;
import com.moubiehideequipment.api.yaml.MessageFileLoader;
import com.moubiehideequipment.moubiehideequipment.commands.args.CommandOff;
import com.moubiehideequipment.moubiehideequipment.commands.args.CommandOn;
import com.moubiehideequipment.moubiehideequipment.commands.args.CommandReload;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 代表該插見的主要命令類
 * @author MouBieCat
 */
public final class CommandMain
        extends CommandManagerAbstract {

    /**
     * 建構子
     */
    public CommandMain() {
        // 註冊指令
        this.add("on", new CommandOn());
        this.add("off", new CommandOff());
        this.add("reload", new CommandReload());
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender, final @NotNull Command command, final @NotNull String cmd, final @NotNull String[] args) {
        final MessageFileLoader messages = MouBieCat.getInstance().getMessageFile();

        // 檢查權限
        if (!sender.hasPermission("MouBieHideEquipment.use")) {
            sender.sendMessage(MouBieCat.PLUGIN_TITLE + messages.getNotPermissionMessage());
            return false;
        }

        // 檢查指令長度
        if (args.length > 0) {
            // 獲取輸入的指令
            final com.moubieapi.api.commands.Command argsCommand = this.get(args[0].toLowerCase());

            // 判斷是否擁有該指令並運行
            if (argsCommand != null)
                return argsCommand.runCommand(sender, args);
        }

        sender.sendMessage(MouBieCat.PLUGIN_TITLE + messages.getHelpMessage());
        return false;
    }

    @Override
    public List<String> onTabComplete(final @NotNull CommandSender sender, final @NotNull Command command, final @NotNull String cmd, final @NotNull String[] args) {

        // 檢查權限
        if (!sender.hasPermission("MouBieHideEquipment.use"))
            return new ArrayList<>();

        // 檢查指令長度
        if (args.length >= 1) {

            // 如果長度為一，則獲取所有指令集合
            if (args.length == 1)
                return this.getValues().stream().map(com.moubieapi.api.commands.Command::getCommandName).toList();

            // 獲取輸入的指令
            final com.moubieapi.api.commands.Command argsCommand = this.get(args[0].toLowerCase());

            // 判斷是否擁有該指令並獲取幫助指令集合
            if (argsCommand != null)
                return argsCommand.runTabComplete(sender, args);
        }

        return new ArrayList<>();
    }

}
