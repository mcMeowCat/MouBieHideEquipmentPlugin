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

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

/**
 * 代表該插件讀/寫封包需要做的事情
 * @author MouBieCat
 */
public final class PluginChannelDuplexHandler
        extends ChannelDuplexHandler {

    /**
     * 讀
     * @param ctx 參數
     * @param msg 參數
     * @throws Exception 異常
     */
    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg)
            throws Exception {
        super.channelRead(ctx, msg);
    }

    /**
     * 寫
     * @param ctx 參數
     * @param msg 參數
     * @param promise 參數
     */
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        super.write(ctx, msg, promise);
    }
}
