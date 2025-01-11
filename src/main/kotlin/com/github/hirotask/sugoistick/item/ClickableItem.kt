package com.github.hirotask.sugoistick.item

import org.bukkit.entity.Player

interface ClickableItem {
    fun onClick(
        player: Player,
        clickType: ClickType,
    )
}
