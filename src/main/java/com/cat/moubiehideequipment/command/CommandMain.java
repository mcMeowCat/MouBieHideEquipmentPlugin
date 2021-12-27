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

package com.cat.moubiehideequipment.command;

import com.cat.moubiehideequipment.MouBieCat;
import com.cat.moubiehideequipment.command.args.CommandOff;
import com.cat.moubiehideequipment.command.args.CommandOn;
import com.moubiecat.moubieapi.manager.CommandManagerAbstract;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 代表該插件的命令主類
 * @author MouBieCat
 */
public final class CommandMain
        extends CommandManagerAbstract {

    /**
     * 建構子
     */
    public CommandMain() {
        this.add("ON", new CommandOn());
        this.add("OFF", new CommandOff());
    }

    @Override
    public boolean onCommand(final @NotNull CommandSender sender, final @NotNull Command command,
                             final @NotNull String label, final @NotNull String[] args) {

        // 判斷權限
        if (!sender.hasPermission("MouBieHideEquipment.use")) {
            sender.sendMessage(MouBieCat.PLUGIN_TITLE + MouBieCat.getInstance().getMessageFile().getNotPermission());
            return true;
        }

        // 查找要運行的命令
        if (args.length >= 1) {
            final com.moubiecat.api.commands.Command cmd = this.get(args[0].toUpperCase());

            if (cmd != null)
                // 運行
                return cmd.runCommand(sender, args);
        }

        // 發送幫助訊息
        sender.sendMessage(MouBieCat.PLUGIN_TITLE + MouBieCat.getInstance().getMessageFile().getHelp());
        return false;
    }

    @Override
    @NotNull
    public List<String> onTabComplete(final @NotNull CommandSender sender, final @NotNull Command command,
                                               final @NotNull String alias, final @NotNull String[] args) {
        final List<String> list = new ArrayList<>();

        if (args.length == 1) {
            for (final com.moubiecat.api.commands.Command cmd : this.getValues())
                list.add(cmd.getCommandName());
            return list;
        }

        else if (args.length > 1) {
            final com.moubiecat.api.commands.Command cmd = this.get(args[0].toUpperCase());
            if (cmd != null)
                return cmd.runTabComplete(sender, args);
        }

        return new ArrayList<>();
    }

}
