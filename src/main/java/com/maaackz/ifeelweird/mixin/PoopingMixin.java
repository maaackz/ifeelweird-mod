package com.maaackz.ifeelweird.mixin;

import com.maaackz.ifeelweird.item.CustomItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnimalEntity.class)
public abstract class PoopingMixin extends PassiveEntity {
    int poop_ticks;

    void setRandomPoopTime()
    {
        //2 - 10 minutes
        int seconds = random.nextBetween(2, 10) * 60; //1 minute = 60 seconds, 5 minutes = 3 * 60 seconds.

        //Minecraft runs at 20 ticks per second.
        this.poop_ticks = 20 * seconds;
    }


    protected PoopingMixin(EntityType<? extends PassiveEntity> entityType, World world) {
        super(entityType, world);
        setRandomPoopTime();
    }

    @Inject(method = "tickMovement()V", at = @At("TAIL"))
    void shitty(CallbackInfo ci)
    {
        this.poop_ticks--;
        if(this.poop_ticks <= 0)
        {
            setRandomPoopTime();

            for (int i = 0; i < random.nextBetween(0, 1); i++)
                this.dropStack(CustomItems.SAND_OCEAN_MUSIC_DISC.getDefaultStack());
            this.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.getWorld().addParticle(ParticleTypes.HEART, this.getPos().x, this.getPos().y, this.getPos().z, 0, -.2, 0);
            this.getWorld().addParticle(ParticleTypes.HEART, this.getPos().x, this.getPos().y, this.getPos().z, -.2, -.2, 0);
            this.getWorld().addParticle(ParticleTypes.HEART, this.getPos().x, this.getPos().y, this.getPos().z, +.2, -.2, 0);
            this.getWorld().addParticle(ParticleTypes.HEART, this.getPos().x, this.getPos().y, this.getPos().z, 0, -.2, -.2);
            this.getWorld().addParticle(ParticleTypes.HEART, this.getPos().x, this.getPos().y, this.getPos().z, 0, -.2, +.2);

        }
    }
}
