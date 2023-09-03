package main.java.net.tnemc.invconnector;
/*
 * The New Economy
 * Copyright (C) 2022 - 2023 Daniel "creatorfromhell" Vidmar
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import main.java.net.tnemc.invconnector.invsee.InvSeeInventorySaveListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * InvBridgePlugin
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class InvBridgePlugin extends JavaPlugin {


  @Override
  public void onEnable() {

    if(Bukkit.getPluginManager().isPluginEnabled("InvSeePlusPlus")) {
      Bukkit.getPluginManager().registerEvents(new InvSeeInventorySaveListener(), this);
    }

    if(Bukkit.getPluginManager().isPluginEnabled("OpenInv")) {

    }
  }
}