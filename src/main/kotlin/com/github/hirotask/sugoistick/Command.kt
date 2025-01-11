package com.github.hirotask.sugoistick

import com.github.hirotask.sugoistick.item.LevelOneItem
import dev.jorel.commandapi.CommandAPICommand
import dev.jorel.commandapi.executors.CommandExecutor
import dev.s7a.ktinventory.KtInventoryProvider
import org.bukkit.Color
import org.bukkit.entity.Player

class Command(
    private val inventoryProvider: KtInventoryProvider,
) {
    fun register() {
        CommandAPICommand("sstick")
            .withAliases("ss")
            .withFullDescription("sugoi-stick プラグインのコマンド")
            .withUsage(
                """
                ${Color.GREEN}/sstick get1 ${Color.WHITE}レベル1のすごい棒を取得します
                """.trimIndent(),
            ).withSubcommand(
                CommandAPICommand("get1")
                    .executes(
                        CommandExecutor { sender, _ ->
                            val player = sender as? Player ?: return@CommandExecutor
                            val playerInv = player.inventory
                            playerInv.addItem(LevelOneItem(inventoryProvider).create())
                        },
                    ),
            ).register()
    }
}
