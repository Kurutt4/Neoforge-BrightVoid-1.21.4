package net.dwa.brightvoid.event;

import net.dwa.brightvoid.BrightVoid;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingFallEvent;

@Mod(BrightVoid.MOD_ID)
public class ModGravityHandler {

    private static final ResourceKey<Level> MOON_DIMENSION = ResourceKey.create(
            Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath("dwabrightvoid", "moon")
    );

    private static final double MINECRAFT_GRAVITY_DEFAULT = -0.0784000015258789;
    private static final double MOON_GRAVITY_ACCELERATION = -0.02; // Ajuste este valor!
    private static final double MAX_FALL_SPEED = -0.7; // Exemplo, ajuste se necessário

    // ----- CÓDIGO DA GRAVIDADE (EXISTENTE) -----
    @SubscribeEvent
    public void onEntityTick(net.neoforged.neoforge.event.tick.EntityTickEvent.Post event) {
        Entity entity = event.getEntity();

        if (entity instanceof Player player && player.level().dimension().equals(MOON_DIMENSION)) {
            if (!player.onGround()) {
                Vec3 motion = player.getDeltaMovement();
                double currentY = motion.y;

                double neutralizedY = currentY - MINECRAFT_GRAVITY_DEFAULT;
                double newY = neutralizedY + MOON_GRAVITY_ACCELERATION;

                if (newY < MAX_FALL_SPEED) {
                    newY = MAX_FALL_SPEED;
                }

                player.setDeltaMovement(motion.x, newY, motion.z);
                // REMOVA OU COMENTE AQUI: player.fallDistance = 0;
            }
        }
    }

    // ----- NOVO CÓDIGO PARA DANO DE QUEDA AJUSTADO -----
    @SubscribeEvent
    public void onLivingFall(LivingFallEvent event) {
        // Verifica se a entidade que está caindo é um jogador e está na sua dimensão da Lua
        if (event.getEntity() instanceof Player player && player.level().dimension().equals(MOON_DIMENSION)) {

            float currentFallDistance = event.getDistance(); // Distância de queda acumulada

            // Fator de redução da distância de queda.
            // Ex: 0.5f significa que a cada 2 blocos reais de queda, conta como 1 para dano.
            // Para gravidade lunar, o dano deveria ser bem menor para a mesma distância.
            float fallDistanceReductionFactor = 0.3f; // Ajuste este valor! (0.0f = sem dano, 1.0f = dano normal)

            float newFallDistance = currentFallDistance * fallDistanceReductionFactor;

            // Define a nova distância de queda para o evento
            event.setDistance(newFallDistance);

            // Opcional: Se você quiser um limiar fixo maior (ex: só leva dano a partir de 10 blocos)
            // if (currentFallDistance < 10.0F) { // Exemplo: se cair menos de 10 blocos, cancela o evento
            //     event.setCanceled(true); // Cancela o dano de queda completamente para quedas abaixo do threshold
            // } else {
            //     // Caso contrário, aplica o fator de redução
            //     event.setDistance(newFallDistance);
            // }
            // A abordagem do fator de redução é geralmente mais flexível.
        }
    }
}