package main.java.net.tnemc.invconnector.invsee;
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

import com.janboerman.invsee.spigot.api.EnderSpectatorInventory;
import com.janboerman.invsee.spigot.api.event.SpectatorInventorySaveEvent;
import net.tnemc.core.EconomyManager;
import net.tnemc.core.TNECore;
import net.tnemc.core.account.PlayerAccount;
import net.tnemc.core.account.holdings.HoldingsEntry;
import net.tnemc.core.currency.Currency;
import net.tnemc.core.currency.calculations.CalculationData;
import net.tnemc.core.currency.item.ItemCurrency;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.util.Optional;

/**
 * InvSeeInventorySaveListener
 *
 * @author creatorfromhell
 * @since 0.0.1.0
 */
public class InvSeeInventorySaveListener implements Listener {

  @EventHandler(priority = EventPriority.MONITOR)
  public void onSave(final SpectatorInventorySaveEvent event) {

    if(event.isCancelled()) return;

    final Optional<PlayerAccount> account = TNECore.api().getPlayerAccount(event.getInventory().getSpectatedPlayerId());
    if(account.isPresent()) {

      final String region = TNECore.eco().region().getMode().region(event.getInventory().getSpectatedPlayerId());
      for(Currency currency : TNECore.eco().currency().getCurrencies(region)) {
        if(currency.type().supportsItems()) {

          final CalculationData<Object> data = new CalculationData<>((ItemCurrency)currency,
                  event.getInventory(),
                  account.get().getUUID());

          account.get().getWallet().setHoldings(new HoldingsEntry(region, currency.getUid(),
                  TNECore.server().itemCalculations().calculateHoldings(data), ((event.getInventory() instanceof EnderSpectatorInventory)? EconomyManager.E_CHEST : EconomyManager.INVENTORY_ONLY)));
        }
      }
    }
  }
}