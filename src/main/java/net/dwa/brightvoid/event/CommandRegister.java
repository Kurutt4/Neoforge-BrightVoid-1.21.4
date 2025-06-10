package net.dwa.brightvoid.event; // ajuste para seu pacote

import java.util.Set;

import net.dwa.brightvoid.BrightVoid;
import net.minecraft.commands.Commands;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceKey;

import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@Mod(BrightVoid.MOD_ID) // troque pelo mod id real
public class CommandRegister {
    public CommandRegister() {
        // Registrar event handlers no NeoForge event bus
        NeoForge.EVENT_BUS.addListener(this::onRegisterCommands);
    }

    // Ou usar @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    // public static void onRegisterCommands(RegisterCommandsEvent event) { ... }

    private void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("moon")  // comando "/moon"
                        .executes(ctx -> {
                            ServerPlayer player = ctx.getSource().getPlayerOrException();
                            MinecraftServer server = player.getServer();
                            // Cria o ResourceLocation da dimensão customizada sem usar o construtor privado
                            ResourceLocation moonId = ResourceLocation.fromNamespaceAndPath("dwabrightvoid", "moon");
                            // Cria a ResourceKey<Level> usando Registries.DIMENSION (substituindo Registry.DIMENSION_REGISTRY antigo):contentReference[oaicite:0]{index=0}
                            ResourceKey<Level> moonKey = ResourceKey.create(Registries.DIMENSION, moonId);
                            ServerLevel moonWorld = server.getLevel(moonKey);
                            if (moonWorld != null) {
                                // Teleporta o jogador para (0.5,100,0.5) na dimensão "moon"
                                // Usamos o novo teleportTo(level, x, y, z, Set<RelativeMovement>, yaw, pitch, setCamera)
                                // Passamos Set.of() (nenhuma flag relativa, coordenadas absolutas) e conservamos a orientação atual do jogador.
                                player.teleportTo(
                                        moonWorld,
                                        0.5, 100.0, 0.5,
                                        Set.of(),                         // sem movimento relativo (posição absoluta)
                                        player.getYRot(),
                                        player.getXRot(),
                                        false                            // não reiniciar câmera (mantenha a vista do jogador)
                                );
                            }
                            return 1;
                        })
        );
    }
}
