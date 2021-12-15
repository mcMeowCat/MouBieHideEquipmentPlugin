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

package moubiehideequipment.packet;

import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import org.bukkit.craftbukkit.v1_18_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 代表一個數據包處裡幫手
 * @author MouBieCat
 */
public final class PacketHelper {

    // 注入中的玩家列表
    @NotNull
    private final List<Player> injectPlayers = new ArrayList<>();

    // 包處理程序
    @NotNull
    private static final String PACKET_LISTENER = "packet_handler";

    /**
     * 添加一個玩家的注入
     * @param player 玩家
     */
    public void injectPlayerPacket(final @NotNull Player player) {
        final PluginChannelDuplexHandler handler = new PluginChannelDuplexHandler();
        final ChannelPipeline pipeline = ((CraftPlayer) player).getHandle().b.a.k.pipeline();

        pipeline.addBefore(PACKET_LISTENER, player.getName(), handler);

        this.injectPlayers.add(player);
    }

    /**
     * 移除對一個玩家的注入
     * @param player 玩家
     */
    public void uninjectPlayerPacket(final @NotNull Player player) {
        final Channel channel = ((CraftPlayer) player).getHandle().b.a.k;

        channel.eventLoop().submit(() -> {
            channel.pipeline().remove(player.getName());
            return null;
        });

        this.injectPlayers.remove(player);
    }

    /**
     * 獲取數據包注入的玩家集合
     * @return 玩家集合
     */
    @NotNull
    public List<Player> getInjectPlayers() {
        return this.injectPlayers;
    }

}
