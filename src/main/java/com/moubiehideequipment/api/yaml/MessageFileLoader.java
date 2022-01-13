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

package com.moubiehideequipment.api.yaml;

import com.moubieapi.api.yaml.PluginFileLoader;
import org.jetbrains.annotations.NotNull;

/**
 * 代表該插件的嵌入式檔案 Message.yml
 * @author MouBieCat
 */
public interface MessageFileLoader
        extends PluginFileLoader {

    /**
     * 獲取幫助訊息
     * @return 訊息
     */
    @NotNull String getHelpMessage();

    /**
     * 獲取隱藏裝備訊息
     * @return 訊息
     */
    @NotNull String getHideMessage();

    /**
     * 獲取不再隱藏裝備訊息
     * @return 訊息
     */
    @NotNull String getNotHideMessage();

    /**
     * 獲取沒有權限訊息
     * @return 訊息
     */
    @NotNull String getNotPermissionMessage();

    /**
     * 獲取重新加載配置訊息
     * @return 訊息
     */
    @NotNull String getReloadMessage();

}
