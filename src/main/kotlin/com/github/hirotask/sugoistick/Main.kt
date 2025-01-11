package com.github.hirotask.sugoistick

import com.github.hirotask.sugoistick.listener.PlayerInteractListener
import dev.s7a.ktinventory.KtInventoryProvider
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {
    override fun onEnable() {
        val inventoryProvider = KtInventoryProvider(this)

        // Command の登録
        Command(inventoryProvider).register()

        // EventListener の登録
        server.pluginManager.registerEvents(PlayerInteractListener(inventoryProvider), this)
    }
}
