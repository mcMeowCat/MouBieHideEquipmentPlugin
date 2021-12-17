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
import com.cat.moubiehideequipment.packet.EquipmentPacketThread;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 代表該插件的命令主類
 * @author MouBieCat
 */
public final class CommandMain
        implements TabExecutor {

    @Override
    public boolean onCommand(final @NotNull CommandSender sender, final @NotNull Command command,
                             final @NotNull String label, final @NotNull String[] args) {
        final YamlConfiguration message = MouBieCat.getInstance().getMessage();

        if (sender.hasPermission("MouBieHideEquipment.use")) {
            if (sender instanceof final Player player && args.length == 1) {
                if (args[0].equalsIgnoreCase("on")) {
                    MouBieCat.getInstance().getPacketThreadManager().add(player, new EquipmentPacketThread(player));
                    player.sendMessage(MouBieCat.PLUGIN_TITLE + message.getString("Messages.Hide"));
                    return true;
                }

                else if (args[0].equalsIgnoreCase("off")) {
                    MouBieCat.getInstance().getPacketThreadManager().remove(player);
                    player.sendMessage(MouBieCat.PLUGIN_TITLE + message.getString("Messages.NotHide"));
                    return true;
                }
            }
            sender.sendMessage(MouBieCat.PLUGIN_TITLE + message.getString("Messages.Help"));
            return true;
        }

        sender.sendMessage(MouBieCat.PLUGIN_TITLE + message.getString("Messages.NotPermission"));
        return false;
    }

    @Override
    @NotNull
    public List<String> onTabComplete(final @NotNull CommandSender sender, final @NotNull Command command,
                                               final @NotNull String alias, final @NotNull String[] args) {
        final List<String> list = new ArrayList<>();

        if (args.length == 1) {
            list.add("on"); list.add("off");
        }

        return list;
    }

}
