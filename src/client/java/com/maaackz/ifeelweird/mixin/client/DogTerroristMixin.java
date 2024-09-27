package com.maaackz.ifeelweird.mixin.client;

import com.maaackz.ifeelweird.ifeelweird;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.feature.WolfCollarFeatureRenderer;
import net.minecraft.client.render.entity.model.WolfEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WolfCollarFeatureRenderer.class)
public abstract class DogTerroristMixin extends FeatureRenderer<WolfEntity, WolfEntityModel<WolfEntity>> {

//    private static final Identifier DEFAULT_SKIN = new Identifier("textures/entity/wolf/wolf_ski_mask.png");
    private static final Identifier CUSTOM_SKIN = new Identifier(ifeelweird.MOD_ID, "textures/entity/wolf/wolf_ski_mask.png");

    public DogTerroristMixin(FeatureRendererContext<WolfEntity, WolfEntityModel<WolfEntity>> context) {
        super(context);
    }

    @Inject(at = @At("HEAD"), method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/passive/WolfEntity;FFFFFF)V", cancellable = true)
    private void onRender(
            MatrixStack matrixStack,
            VertexConsumerProvider vertexConsumerProvider,
            int i,
            WolfEntity wolfEntity,
            float f,
            float g,
            float h,
            float j,
            float k,
            float l,
            CallbackInfo info) {

        if (wolfEntity.isTamed() && !wolfEntity.isInvisible()) {
            float[] fs = wolfEntity.getCollarColor().getColorComponents();
//            System.out.println("R: " + fs[0] + " G: " + fs[1] +" B: " + fs[2]);
            // Check if the collar color is black (RGB: 0, 0, 0)
//            0.11372549 G: 0.11372549 B: 0.12941177
            if (fs[0] == 0.11372549f && fs[1] == 0.11372549f && fs[2] == 0.12941177f) {
                // Change the SKIN to the CUSTOM_SKIN for black collar color
                renderModel(this.getContextModel(), CUSTOM_SKIN, matrixStack, vertexConsumerProvider, i, wolfEntity, fs[0], fs[1], fs[2]);
                info.cancel(); // Cancel the original render method
            } else {
                // Use the default skin for other colors
//                renderModel(this.getContextModel(), DEFAULT_SKIN, matrixStack, vertexConsumerProvider, i, wolfEntity, fs[0], fs[1], fs[2]);
            }
        }
    }


}