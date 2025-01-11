package com.github.hirotask.sugoistick.item

import org.bukkit.inventory.ItemStack

interface CustomItem {
    fun create(): ItemStack
}
