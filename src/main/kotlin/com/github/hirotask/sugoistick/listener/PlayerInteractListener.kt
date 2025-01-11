package com.github.hirotask.sugoistick.listener

import com.github.hirotask.sugoistick.item.ClickType
import com.github.hirotask.sugoistick.item.LevelOneItem
import dev.s7a.ktinventory.KtInventoryProvider
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

class PlayerInteractListener(private val inventoryProvider: KtInventoryProvider) : Listener {

    private fun convertActionToClickType(action: Action): ClickType {
        return if (action == Action.LEFT_CLICK_BLOCK || action == Action.LEFT_CLICK_AIR) ClickType.LeftClick
        else ClickType.RightClick
    }
    @EventHandler
    fun onInteract(e: PlayerInteractEvent) {
        e.isCancelled = true

        val player = e.player
        val itemInHand = player.inventory.itemInMainHand
        val clickType = convertActionToClickType(e.action)

        when(itemInHand.itemMeta?.displayName) {
            LevelOneItem.DISPLAY_NAME -> LevelOneItem(inventoryProvider).onClick(player, clickType)
        }

    }
}
