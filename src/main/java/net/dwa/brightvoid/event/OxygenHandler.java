package net.dwa.brightvoid.event; // Ou net.dwa.brightvoid.core;

import net.dwa.brightvoid.BrightVoid;
import net.minecraft.ChatFormatting;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod; // Para o tick do jogador

// Imports para checar inventário
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items; // Para referência a itens vanilla
// Se você tiver itens de oxigênio customizados, importe-os também.
// import net.dwa.brightvoid.init.ModItems; // Exemplo para seus itens customizados

// Imports para HUD (ainda não usado, mas para o futuro)
import net.minecraft.network.chat.Component; // Para a mensagem no chat/HUD
import net.minecraft.client.Minecraft; // Para acessar o cliente e a HUD
import net.neoforged.neoforge.client.event.RenderGuiEvent; // Para renderizar na HUD (precisa ser no ClientModEventBus)
import net.neoforged.neoforge.client.event.RenderGuiEvent.Post; // Usaremos o Post para renderizar depois da HUD principal

// Imports para Dano/Debuffs
import net.minecraft.world.damagesource.DamageSources; // Para aplicar dano
import net.minecraft.world.damagesource.DamageTypes; // Para tipos de dano (no 1.21.4 é DamageTypes)
import net.minecraft.world.Difficulty; // Para ajustar a dificuldade
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects; // Para efeitos de poção
import net.neoforged.neoforge.event.tick.EntityTickEvent;

@Mod(BrightVoid.MOD_ID) // Também precisa da anotação @Mod
public class OxygenHandler {

    private static final ResourceKey<Level> MOON_DIMENSION = ResourceKey.create(
            net.minecraft.core.registries.Registries.DIMENSION,
            ResourceLocation.fromNamespaceAndPath("dwabrightvoid", "moon")
    );

    // --- CONFIGURAÇÕES DE OXIGÊNIO ---
    private static final int OXYGEN_CHECK_INTERVAL = 20; // Verifica a cada 20 ticks (1 segundo)
    private static final int OXYGEN_WARNING_DISPLAY_TICKS = 60; // Por quanto tempo a mensagem de aviso aparece (3 segundos)
    private static final int OXYGEN_DAMAGE_INTERVAL = 40; // Aplica dano a cada 40 ticks (2 segundos)
    private static final float OXYGEN_DAMAGE_AMOUNT = 1.0f; // Quantidade de dano por tick

    // Contador para o aviso na HUD
    private int oxygenWarningTimer = 0;

    // --- ITENS DE OXIGÊNIO ---
    // Defina os itens que o jogador precisa ter para respirar
    // Exemplo: um capacete de mergulhador customizado, ou um item de tanque de oxigênio
    // Por enquanto, vamos usar um item vanilla como exemplo (ex: CAPACETE DE FERRO)
    // No futuro, substitua por seus próprios itens, ex: ModItems.OXYGEN_HELMET
    private static final ItemStack OXYGEN_ITEM_HELMET = new ItemStack(Items.IRON_HELMET); // Exemplo: Capacete de Ferro
    private static final ItemStack OXYGEN_ITEM_TANK = new ItemStack(Items.BLUE_WOOL); // Exemplo: Lã Azul como "tanque"

    // --- MÉTODO DE VERIFICAÇÃO DE OXIGÊNIO NO TICK ---
    @SubscribeEvent
    public void onPlayerTick(net.neoforged.neoforge.event.tick.EntityTickEvent.Post event) {
        if (event.getEntity() instanceof Player player) {
            // Só aplica a lógica na dimensão da Lua e se não for o lado cliente no servidor dedicado,
            // ou se for um jogador no servidor em um cliente integrado.
            if (!player.level().dimension().equals(MOON_DIMENSION) || player.level().isClientSide()) {
                return; // Só queremos a lógica do lado do servidor na dimensão da lua
            }

            // Apenas para jogadores no modo Sobrevivência/Aventura
            if (player.isCreative() || player.isSpectator()) {
                oxygenWarningTimer = 0; // Reseta o timer para jogadores em modo criativo/espectador
                return;
            }

            // Verifica a cada 'OXYGEN_CHECK_INTERVAL' ticks
            if (player.tickCount % OXYGEN_CHECK_INTERVAL == 0) {
                boolean hasOxygen = checkOxygenEquipment(player);

                if (!hasOxygen) {
                    // Começa ou continua o aviso de oxigênio
                    oxygenWarningTimer = OXYGEN_WARNING_DISPLAY_TICKS;

                    // Aplica dano/debuffs
                    if (player.tickCount % OXYGEN_DAMAGE_INTERVAL == 0) {
                        applyOxygenDamage(player);
                    }
                } else {
                    oxygenWarningTimer = 0; // Tem oxigênio, reseta o aviso
                }
            }

            // Decrementa o timer do aviso para que ele desapareça depois de um tempo
            if (oxygenWarningTimer > 0) {
                oxygenWarningTimer--;
            }
        }
    }

    // --- MÉTODO PARA VERIFICAR EQUIPAMENTO DE OXIGÊNIO ---
    private boolean checkOxygenEquipment(Player player) {
        // Exemplo: O jogador precisa ter o capacete OU o tanque para ter oxigênio
        boolean hasHelmet = player.getInventory().armor.get(3).is(OXYGEN_ITEM_HELMET.getItem()); // Slot 3 é o capacete
        boolean hasTank = player.getInventory().contains(OXYGEN_ITEM_TANK); // Verifica se o tanque está em qualquer lugar do inventário

        // Se você precisa de AMBOS, mude para 'return hasHelmet && hasTank;'
        // Se você precisa de APENAS UM deles, mude para 'return hasHelmet || hasTank;'
        return hasHelmet && hasTank; // Exemplo: precisa de ambos (capacete e tanque)
    }

    // --- MÉTODO PARA APLICAR DANO DE OXIGÊNIO ---
    private void applyOxygenDamage(Player player) {
        // Crie um DamageSource personalizado ou use um existente
        // Para 1.21.4, DamageSource usa DamageTypes
        DamageSources damageSources = player.damageSources(); // Obtém as fontes de dano do jogador
        player.hurt(damageSources.drown(), OXYGEN_DAMAGE_AMOUNT); // Usando dano de afogamento como exemplo

        // Opcional: Adicionar debuffs
        player.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, OXYGEN_DAMAGE_INTERVAL + 10, 0, false, false)); // Fraqueza
        player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, OXYGEN_DAMAGE_INTERVAL + 10, 0, false, false)); // Lentidão na mineração
    }


    // --- MÉTODO PARA RENDERIZAR O AVISO NA HUD (Chamado APENAS no lado CLIENTE) ---
    // Este evento precisa ser registrado no ClientModEventBus na sua classe principal!
    @SubscribeEvent
    public void onRenderGui(RenderGuiEvent.Post event) {
        // Certifique-se de que estamos no lado do cliente e que o jogador existe
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().level != null && Minecraft.getInstance().isLocalServer()) {
            Player player = Minecraft.getInstance().player;

            // Só exibe o aviso se o timer estiver ativo e na dimensão da Lua
            if (oxygenWarningTimer > 0 && player.level().dimension().equals(MOON_DIMENSION)) {
                // E se o jogador estiver no modo Sobrevivência/Aventura
                if (!player.isCreative() && !player.isSpectator()) {
                    Component message = Component.translatable("warning.brightvoid.oxygen").withStyle(ChatFormatting.DARK_RED); // Mude a mensagem se quiser

                    Minecraft.getInstance().gui.setOverlayMessage(message, false);
                    // setOverlayMessage exibe uma mensagem temporária acima da hotbar.
                    // Ele tem um tempo de duração padrão. Se quisermos mais controle,
                    // teríamos que renderizar diretamente via PoseStack.
                }
            }
        }
    }
}