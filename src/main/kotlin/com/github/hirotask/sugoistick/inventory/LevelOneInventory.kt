package com.github.hirotask.sugoistick.inventory

import com.github.hirotask.sugoistick.item.LevelOneItem
import dev.s7a.ktinventory.KtInventory
import dev.s7a.ktinventory.KtInventoryProvider
import org.bukkit.inventory.ItemType

class LevelOneInventory(
    private val provider: KtInventoryProvider,
) : CustomInventory {
    override val inventoryTitle: String = LevelOneItem.DISPLAY_NAME

    /**
     * "b": white glass
     * " ": blank
     * "g": lime terracotta
     * "r": red terracotta
     */
    private val inventoryMap =
        arrayOf<String>(
            "b",
            "b",
            "b",
            "b",
            "b",
            "b",
            "b",
            "b",
            "b",
            "b",
            " ",
            "b",
            "b",
            " ",
            "b",
            "b",
            "b",
            "b",
            "b",
            "b",
            "b",
            "b",
            "b",
            "b",
            "b",
            "g",
            "r",
        )

    override fun create(): KtInventory {
        return provider
            .create(inventoryTitle, 3) {
                inventoryMap.forEachIndexed { index, s ->
                    when (s) {
                        "b" ->
                            this.item(
                                index,
                                ItemType.WHITE_STAINED_GLASS_PANE.createItemStack {
                                    it.setDisplayName(" ")
                                },
                            ) {
                                it.isCancelled = true
                            }

                        "g" ->
                            this.item(
                                index,
                                ItemType.GREEN_TERRACOTTA.createItemStack {
                                    it.setDisplayName("設定")
                                },
                            ) {
                                it.isCancelled = true
                            }

                        "r" ->
                            this.item(
                                index,
                                ItemType.RED_TERRACOTTA.createItemStack {
                                    it.setDisplayName("キャンセル")
                                },
                            ) {
                                it.isCancelled = true
                            }
                    }
                }
            }
    }
}
