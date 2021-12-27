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

package com.cat.moubiehideequipment.yaml;

import com.cat.moubiehideequipment.MouBieCat;
import com.moubiecat.moubieapi.yaml.PluginFileLoaderAbstract;
import org.jetbrains.annotations.NotNull;

/**
 * 代表該插件的嵌入式檔案 Message.yml
 * @author MouBieCat
 */
public final class PluginMessage
        extends PluginFileLoaderAbstract {

    private static final String MESSAGE_HELP_PATH = "Help";
    private static final String MESSAGE_HIDE_PATH = "Hide";
    private static final String MESSAGE_NOT_HIDE_PATH = "NotHide";
    private static final String MESSAGE_NOT_PERMISSION_PATH = "NotPermission";

    /**
     * 建構子
     */
    public PluginMessage() {
        super(MouBieCat.getInstance(), "", "Message.yml");
    }

    /**
     * 獲取幫助訊息
     * @return 訊息
     */
    @NotNull
    public String getHelp() {
        return this.getString(MESSAGE_HELP_PATH, true);
    }

    /**
     * 獲取隱藏裝備訊息
     * @return 訊息
     */
    @NotNull
    public String getHide() {
        return this.getString(MESSAGE_HIDE_PATH, true);
    }

    /**
     * 獲取取消隱藏裝備訊息
     * @return 訊息
     */
    @NotNull
    public String getNotHide() {
        return this.getString(MESSAGE_NOT_HIDE_PATH, true);
    }

    /**
     * 獲取權限不足的訊息
     * @return 訊息
     */
    @NotNull
    public String getNotPermission() {
        return this.getString(MESSAGE_NOT_PERMISSION_PATH, true);
    }

}
