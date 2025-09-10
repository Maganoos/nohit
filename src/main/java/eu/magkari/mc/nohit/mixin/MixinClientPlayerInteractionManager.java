package eu.magkari.mc.nohit.mixin;

import eu.magkari.mc.nohit.NoHit;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerInteractionManager.class)
public class MixinClientPlayerInteractionManager {
    @Inject(method = "interactItem", at = @At("HEAD"), cancellable = true)
    private void onInteractItem(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        MinecraftClient mc = MinecraftClient.getInstance();
        ItemStack stack = player.getStackInHand(hand);

        if (NoHit.getConfig().bows && NoHit.getConfig().enabled && (stack.isOf(Items.BOW)) || stack.isOf(Items.CROSSBOW)) {
            if (mc.player == null) return;
            NoHit.sendMessage(mc.player);
            cir.setReturnValue(ActionResult.PASS);
        }
    }
}
