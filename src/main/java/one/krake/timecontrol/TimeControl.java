package one.krake.timecontrol;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.Timer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import one.krake.timecontrol.TimeFrame;
import one.krake.timecontrol.proxy.ClientProxy;
import one.krake.timecontrol.proxy.CommonProxy;
import org.apache.logging.log4j.Logger;

@Mod(modid = TimeControl.MODID, name = TimeControl.NAME, version = TimeControl.VERSION)
public class TimeControl {
    public static final String MODID = "timecontrol";
    public static final String NAME = "TimeControl";
    public static final String VERSION = "0.1.0";

    public static final int TIMER_FIELD_COUNT = 20;

    @SidedProxy(clientSide = "one.krake.timecontrol.proxy.ClientProxy", serverSide = "one.krake.timecontrol.proxy.CommonProxy")
    public static CommonProxy proxy;

    public TimeFrame[] frames;
    public int frameIndex;
    public byte tps = 20; // Default Minecraft TPS

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        frames = new TimeFrame[10_000];
        frameIndex = -1;
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init();
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        if (!mc.inGameHasFocus) {
            return;
        }
        if (ClientProxy.keyUndo.isKeyDown()) {
            if (frameIndex > 0) {
                frameIndex -= 1;
                TimeFrame frame = frames[frameIndex];

                EntityPlayer p = mc.player;
                if (p != null) {
                    //p.setPositionAndUpdate(frame.x, frame.y, frame.z);
                    //p.setPositionAndRotation(frame.x, frame.y, frame.z, frame.yaw, frame.pitch);
                    p.setVelocity(frame.x-p.posX, frame.y-p.posY, frame.z-p.posZ);
                    p.rotationYaw = frame.yaw;
                    p.rotationPitch = frame.pitch;
                }
            }
        } else {
            EntityPlayer p = mc.player;
            if (p != null) {
                frameIndex += 1;
                if (frameIndex >= frames.length) {
                    frameIndex = frames.length - 1;
                    for (int i = 1; i <= frameIndex; ++i) {
                        frames[i - 1] = frames[i];
                    }
                }

                frames[frameIndex] = new TimeFrame(p.posX, p.posY, p.posZ, p.rotationYaw, p.rotationPitch);
            }
        }
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        boolean updated = false;
        if (ClientProxy.keyFaster.isPressed() && tps <= 97) {
            tps += 3;
            updated = true;
        }
        if (ClientProxy.keySlower.isPressed() && tps >= 4) {
            tps -= 3;
            updated = true;
        }
        if (updated) {
            Minecraft mc = Minecraft.getMinecraft();
            Timer timer = new Timer((float)tps);
            ObfuscationReflectionHelper.setPrivateValue(Minecraft.class, mc, timer, TIMER_FIELD_COUNT);
            mc.player.sendMessage(new TextComponentString(tps + " ticks/s"));
        }
    }
}
