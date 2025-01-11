package com.github.hirotask.sugoistick.item

import com.github.hirotask.sugoistick.inventory.LevelOneInventory
import dev.s7a.ktinventory.KtInventoryProvider
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ItemType

class LevelOneItem(
    private val invProvider: KtInventoryProvider,
) : CustomItem,
    ClickableItem {
    companion object {
        const val DISPLAY_NAME = "すごい棒 (Level.1)"
        val LORE = arrayOf<String>()
    }

    override fun create(): ItemStack {
        val item = ItemType.STICK.createItemStack()
        val itemMeta = item.itemMeta?.apply {
            setDisplayName(DISPLAY_NAME)
            lore = LORE.toMutableList()
        }
        item.setItemMeta(itemMeta)
        return item
    }

    override fun onClick(
        player: Player,
        clickType: ClickType,
    ) {
        when (clickType) {
            ClickType.LeftClick -> {
                if (player.isSneaking) {
                    LevelOneInventory(invProvider).create().open(player)
                }
            }
            ClickType.RightClick -> {}
        }
    }
}
