package lain.mods.cos.impl.client;

import lain.mods.cos.impl.ModObjects;
import lain.mods.cos.impl.network.packet.PacketOpenCosArmorInventory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.LogicalSidedProvider;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;

public enum KeyHandler
{

    INSTANCE;

    public KeyBinding keyOpenCosArmorInventory = new KeyBinding("cos.key.opencosarmorinventory", InputMappings.INPUT_INVALID.getKeyCode(), "key.categories.inventory");

    private void handleClientTick(TickEvent.ClientTickEvent event)
    {
        if (event.phase != Phase.START || !LogicalSidedProvider.INSTANCE.<Minecraft>get(LogicalSide.CLIENT).isGameFocused())
            return;
        if (keyOpenCosArmorInventory.isPressed())
            ModObjects.network.sendToServer(new PacketOpenCosArmorInventory());
    }

    public void registerEvents()
    {
        ClientRegistry.registerKeyBinding(keyOpenCosArmorInventory);
        MinecraftForge.EVENT_BUS.addListener(this::handleClientTick);
    }

}
