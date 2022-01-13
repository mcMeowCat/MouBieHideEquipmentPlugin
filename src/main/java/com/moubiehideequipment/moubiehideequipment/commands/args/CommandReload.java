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

package com.moubiehideequipment.moubiehideequipment.commands.args;

import com.moubieapi.api.Utils;
import com.moubieapi.api.commands.SenderType;
import com.moubieapi.moubieapi.commands.CommandAbstract;
import com.moubiehideequipment.MouBieCat;
import com.moubiehideequipment.api.yaml.MessageFileLoader;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 代表插件的重讀指令
 * @author MouBieCat
 */
public final class CommandReload
        extends CommandAbstract {

    /**
     * 建構子
     */
    public CommandReload() {
        super("reload", "用於將插件的相關配置重新讀取", SenderType.ANY_SENDER, new Permission("MouBieHideEquipment.reload"));
    }

    @Override
    public boolean runCommand(final @NotNull CommandSender sender, final @NotNull String[] args) {
        if (!this.hasPermission(sender) || !this.checkSenderType(sender))
            return false;

        final MessageFileLoader messages = MouBieCat.getInstance().getMessageFile();
        Utils.reloadMouBiePlugin(MouBieCat.getInstance().getName());
        sender.sendMessage(MouBieCat.PLUGIN_TITLE + messages.getReloadMessage());
        return true;
    }

    @Override
    @NotNull
    public List<String> runTabComplete(@NotNull CommandSender commandSender, @NotNull String[] strings) {
        return new ArrayList<>();
    }

}
