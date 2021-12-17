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

package com.cat.moubiehideequipment.listener;

import com.cat.moubiehideequipment.MouBieCat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * 有關玩家事件觸發操作
 * @author MouBieCat
 */
public class PlayerListener
        implements Listener {

    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();

        MouBieCat.getInstance().getPacketHelper().injectPlayerPacket(player);
    }

    /**
     * 玩家離開伺服器事件
     * @param event 事件
     */
    @EventHandler
    public void onQuit(final PlayerQuitEvent event) {
        final Player player = event.getPlayer();

        MouBieCat.getInstance().getPacketHelper().uninjectPlayerPacket(player);
        MouBieCat.getInstance().getPacketThreadManager().remove(player);
    }

}
