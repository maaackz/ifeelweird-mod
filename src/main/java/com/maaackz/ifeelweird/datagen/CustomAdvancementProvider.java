package com.maaackz.ifeelweird.datagen;

import com.maaackz.ifeelweird.ifeelweird;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;

import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.block.Blocks;
import net.minecraft.predicate.entity.LocationPredicate;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.StructureKeys;

import java.util.function.Consumer;


public class CustomAdvancementProvider extends FabricAdvancementProvider {

    public CustomAdvancementProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        Advancement rootAdvancement = Advancement.Builder.create()
                .display(
                        Blocks.CHISELED_SANDSTONE,
                        Text.translatable("advancements.nether.find_fortress.title"),
                        Text.translatable("advancements.nether.find_fortress.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("desert_pyramid", TickCriterion.Conditions.createLocation(LocationPredicate.feature(StructureKeys.DESERT_PYRAMID)))
                .rewards(AdvancementRewards.Builder.function(new Identifier(ifeelweird.MOD_ID, "play_pharaohs_curse_sound")))
                .build(consumer, ifeelweird.MOD_ID + "/root");
    }
}
