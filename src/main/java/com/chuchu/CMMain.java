package com.chuchu;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CMMain implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "CM";
    public static final String MOD_NAME = "Chuchus Mod";


    //create ItemGroups
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier("cm", "general"),
            () -> new ItemStack(Blocks.COBBLESTONE));

    //creates Item
    //TOOII=TIER_ONE_ORE_ITEM_INGOT
    public static final TierOneOreItemIngot TOOII = new TierOneOreItemIngot(new FabricItemSettings().group(CMMain.ITEM_GROUP).maxCount(64));

    /*public static final ItemGroup OTHER_GROUP = FabricItemGroupBuilder.create(
            new Identifier("ct", "other"))
            .icon(() -> new ItemStack(Items.BOWL))
            .build();*/
    //create Tools
    public static ToolItem TOTM_SHOVEL = new ShovelItem(TierOneToolMaterial.INSTANCE, 1.5F, -3.0F, new Item.Settings().group(CMMain.ITEM_GROUP));


            /* Declare and initialize our custom block instance.
       We set our block material to METAL, which requires a pickaxe to efficiently break.
       Hardness represents how long the break takes to break. Stone has a hardness of 1.5f, while Obsidian has a hardness of 50.0f.
    */
    //TOOBM=TIER_ONE_ORE_BLOCK_MAIN
    public static final TierOneOreBlockMain TOOBM = new TierOneOreBlockMain(FabricBlockSettings.of(Material.METAL).hardness(10.0f));
    //TOCB=TIER_ONE_CHEST_BLOCK
    public static final ChestBlock TOCB = new ChestBlock(FabricBlockSettings.of(Material.METAL).hardness(10.0f));

    //link BLock Entity and BLock CBE=CHEST_BLOCK_ENTITY
    public static BlockEntityType<ChestBlockEntity> CBE;


    //create ConfiguredFeature
    private static ConfiguredFeature<?, ?> TOOBM_OVERWORLD = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                    TOOBM.getDefaultState(),
                    9)) // vein size
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(
                    0, // bottom offset
                    0, // min y level
                    64))) // max y level
            .spreadHorizontally()
            .repeat(20); // number of veins per chunk





    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");
        //TODO: Initializer
        RegistryKey<ConfiguredFeature<?, ?>> toobmOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier("cm", "toobm_overworld"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, toobmOverworld.getValue(), TOOBM_OVERWORLD);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, toobmOverworld);
        CBE = Registry.register(Registry.BLOCK_ENTITY_TYPE, "cm:tocb", BlockEntityType.Builder.create(ChestBlockEntity::new, TOCB).build(null));

        Registry.register(Registry.BLOCK, new Identifier("cm", "tocb"), TOCB);
        Registry.register(Registry.ITEM, new Identifier("cm", "tocb"), new BlockItem(TOCB, new Item.Settings().group(CMMain.ITEM_GROUP)));

        Registry.register(Registry.ITEM, new Identifier("cm", "tooii"), TOOII);
        Registry.register(Registry.BLOCK, new Identifier("cm", "toobm"), TOOBM);
        Registry.register(Registry.ITEM, new Identifier("cm", "toobm"), new BlockItem(TOOBM, new Item.Settings().group(CMMain.ITEM_GROUP)));
        Registry.register(Registry.ITEM, new Identifier("cm", "totm_shovel"), TOTM_SHOVEL);
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

}