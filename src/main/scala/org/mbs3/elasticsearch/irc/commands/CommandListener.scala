package org.mbs3.elasticsearch.irc.commands

import org.pircbotx._
import org.pircbotx.hooks._
import org.pircbotx.hooks.events._
import org.pircbotx.hooks.types.GenericMessageEvent

class CommandListener extends ListenerAdapter[PircBotX] {
  
	override def onPrivateMessage(event: PrivateMessageEvent[PircBotX]): Unit = message(event)
    override def onMessage(event: MessageEvent[PircBotX]): Unit = message(event)
	
	def message(event: GenericMessageEvent[PircBotX]): Unit = {
	  
	  val sRawCommand = event.getMessage 
	  if(!sRawCommand.startsWith("!"))
	    return
	  
	  val sCommand = sRawCommand.replaceFirst("!", "")
	    
	  val commands : List[IrcCommand[PircBotX]] = List(HelloWorldCommand, GoodbyeCommand)
	  
	  val cmd = commands.filter(_.matches(sCommand))
	  if(cmd.length < 1)
	    event.respond("I don't know that command")
	  else
	    cmd.head.handle(sCommand, event)
	  
	}
}