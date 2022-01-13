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

package com.moubiehideequipment.moubiehideequipment.yaml;

import com.moubieapi.moubieapi.yaml.utils.PluginFileLoaderAbstract;
import com.moubiehideequipment.MouBieCat;
import com.moubiehideequipment.api.yaml.MessageFileLoader;
import org.jetbrains.annotations.NotNull;

/**
 * 代表該插件的嵌入式檔案 Message.yml
 * @author MouBieCat
 */
public final class PluginMessageFileLoader
        extends PluginFileLoaderAbstract
        implements MessageFileLoader {

    public static final String MESSAGE_HELP_MESSAGE_PATH = "Help";
    public static final String MESSAGE_HIDE_MESSAGE_PATH = "Hide";
    public static final String MESSAGE_NOT_HIDE_MESSAGE_PATH = "NotHide";
    public static final String MESSAGE_NOT_PERMISSION_MESSAGE_PATH = "NotPermission";
    public static final String MESSAGE_RELOAD_MESSAGE_PATH = "Reload";

    /**
     * 建構子
     */
    public PluginMessageFileLoader() {
        super(MouBieCat.getInstance(), "", "Message.yml");
    }

    /**
     * 獲取幫助訊息
     * @return 訊息
     */
    @NotNull
    public String getHelpMessage() {
        return this.getString(PluginMessageFileLoader.MESSAGE_HELP_MESSAGE_PATH);
    }

    /**
     * 獲取隱藏裝備訊息
     * @return 訊息
     */
    @NotNull
    public String getHideMessage() {
        return this.getString(PluginMessageFileLoader.MESSAGE_HIDE_MESSAGE_PATH);
    }

    /**
     * 獲取不再隱藏裝備訊息
     * @return 訊息
     */
    @NotNull
    public String getNotHideMessage() {
        return this.getString(PluginMessageFileLoader.MESSAGE_NOT_HIDE_MESSAGE_PATH);
    }

    /**
     * 獲取沒有權限訊息
     * @return 訊息
     */
    @NotNull
    public String getNotPermissionMessage() {
        return this.getString(PluginMessageFileLoader.MESSAGE_NOT_PERMISSION_MESSAGE_PATH);
    }

    /**
     * 獲取重新加載配置訊息
     *
     * @return 訊息
     */
    @NotNull
    public String getReloadMessage() {
        return this.getString(PluginMessageFileLoader.MESSAGE_RELOAD_MESSAGE_PATH);
    }

}
