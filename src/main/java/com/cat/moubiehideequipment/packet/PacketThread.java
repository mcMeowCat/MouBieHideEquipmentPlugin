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

package com.cat.moubiehideequipment.packet;

import net.minecraft.network.protocol.Packet;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

/**
 * 代表樹舉包發送線程
 * @author MouBieCat
 */
public abstract class PacketThread
        extends BukkitRunnable {

    // 擁有者
    @NotNull
    protected final Player player;

    /**
     * 建構子
     * @param player 擁有者
     */
    public PacketThread(final @NotNull Player player) {
        this.player = player;
    }

    /**
     * 獲取線程擁有者
     * @return 玩家
     */
    @NotNull
    public final Player getOwner() {
        return this.player;
    }

    /**
     * 發送數據包給當前數據包擁有者所在世界的所有玩家
     */
    protected final void sendToPlayers(final @NotNull Packet<?> packet) {
        // 發送給該世界中的玩家
        final World world = this.player.getWorld();
        for (final Entity entity : world.getEntities())
            if (entity instanceof Player)
                this.sendPacket((Player) entity, packet);
    }

    /**
     * 發送數據包給玩家
     * @param player 接收者
     * @param packet 數據包
     */
    protected final void sendPacket(final @NotNull Player player, final @NotNull Packet<?> packet) {
        ((CraftPlayer)player).getHandle().b.a(packet);
    }

}
