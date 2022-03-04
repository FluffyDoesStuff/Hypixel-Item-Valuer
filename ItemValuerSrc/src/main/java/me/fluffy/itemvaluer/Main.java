package me.fluffy.itemvaluer;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = Main.MODID, version = Main.VERSION)
public class Main
{
    public static final String MODID = "examplemod";
    public static final String VERSION = "1.0";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        FMLCommonHandler.instance().bus().register(this);
        MinecraftForge.EVENT_BUS.register(this);
    }
    
  //Fired when a chat message is about to be displayed on the client
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        String message = event.message.getUnformattedText();
            if(message.contains("hi")) {
                     Minecraft.getMinecraft().toggleFullscreen();
            }
    }
    
    @SubscribeEvent
    public void xd(ClientChatReceivedEvent event) {
        String message = event.message.getUnformattedText();
        //message is the message which the client receives.
        if(message.startsWith("+") && message.contains("coins") && !message.endsWith("for being generous")) {
            //Checks if the coin message you got isn't the tip message 
            String[] splittedMessage = message.split("coins");
            message = splittedMessage[0].replace("+", "");
            message = message.replace(" ", "");
            int coins = Integer.parseInt(message);
            totalCoins = totalCoins + coins;
        }
    }
    
    @SubscribeEvent
    public void render(RenderGameOverlayEvent event) {
        if (event.isCancelable() || event.type != ElementType.EXPERIENCE) {
            return;
        }
        FontRenderer fRender = Minecraft.getMinecraft().fontRendererObj;
        fRender.drawString(EnumChatFormatting.GREEN + "Coins: " + EnumChatFormatting.WHITE + totalCoins, 5, 5, 0);
    }
}
