package com.github.hirotask.sugoistick.inventory

import dev.s7a.ktinventory.KtInventory

interface CustomInventory {
    val inventoryTitle: String

    fun create(): KtInventory
}
