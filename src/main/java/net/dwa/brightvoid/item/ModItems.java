package net.dwa.brightvoid.item;

import net.dwa.brightvoid.BrightVoid;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BrightVoid.MOD_ID);

    public static final DeferredItem<Item> STAR_FRAGMENT = ITEMS.registerItem("star_fragment",
            Item::new);

    public static final DeferredItem<Item> MAGNETITE = ITEMS.registerItem("magnetite",
            Item::new);





    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}