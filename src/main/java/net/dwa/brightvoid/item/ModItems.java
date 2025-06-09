package net.dwa.brightvoid.item;

import net.dwa.brightvoid.BrightVoid;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BrightVoid.MOD_ID);

    public static final DeferredItem<Item> STAR_FRAGMENT = ITEMS.register("star_fragment",
            () -> new Item(new Item.Properties().useItemDescriptionPrefix()));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
