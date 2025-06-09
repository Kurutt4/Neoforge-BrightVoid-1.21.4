package net.dwa.brightvoid.item;

import net.dwa.brightvoid.BrightVoid;
import net.dwa.brightvoid.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BrightVoid.MOD_ID);

    public static final Supplier<CreativeModeTab> BRIGHT_VOID_TAB = CREATIVE_MODE_TAB.register("bright_void_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.MAGNETITE.get()))
                    .title(Component.translatable("creativetab.brightvoid.brightvoid_items"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.MAGNETITE);
                        output.accept(ModItems.STAR_FRAGMENT);


                        output.accept(ModBlocks.MOON_STONE.get());
                        output.accept(ModBlocks.MOON_DEEP_STONE.get());
                        output.accept(ModBlocks.MOON_COAL_ORE.get());
                        output.accept(ModBlocks.MOON_IRON_ORE.get());
                        output.accept(ModBlocks.MOON_COPPER_ORE.get());
                        output.accept(ModBlocks.MOON_GOLD_ORE.get());
                        output.accept(ModBlocks.MOON_REDSTONE_ORE.get());
                        output.accept(ModBlocks.MOON_DIAMOND_ORE.get());
                        output.accept(ModBlocks.MOON_EMERALD_ORE.get());
                        output.accept(ModBlocks.MOON_LAPIS_ORE.get());
                        output.accept(ModBlocks.METEOR_BLOCK.get());

                    }).build());





public static void register(IEventBus eventBus) {
    CREATIVE_MODE_TAB.register(eventBus);
}
}
