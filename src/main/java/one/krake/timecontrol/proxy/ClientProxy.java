package one.krake.timecontrol.proxy;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.input.Keyboard;

public class ClientProxy extends CommonProxy {
    public static KeyBinding keyUndo;
    public static KeyBinding keyFaster;
    public static KeyBinding keySlower;

    public void init() {
        keyUndo = new KeyBinding("key.undo.desc", Keyboard.KEY_RSHIFT, "key.timecontrol.category");
        ClientRegistry.registerKeyBinding(keyUndo);

        keyFaster = new KeyBinding("key.faster.desc", Keyboard.KEY_UP, "key.timecontrol.category");
        ClientRegistry.registerKeyBinding(keyFaster);

        keySlower = new KeyBinding("key.slower.desc", Keyboard.KEY_DOWN, "key.timecontrol.category");
        ClientRegistry.registerKeyBinding(keySlower);
    }
}
