package wiiu.mavity.oilfixtfmg.blocks.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wiiu.mavity.oilfixtfmg.OilFixTFMG;
import wiiu.mavity.oilfixtfmg.blocks.OilFixBlocks;

public class OilFixBlockEntities {

	public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
		DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, OilFixTFMG.MOD_ID);


	public static final RegistryObject<BlockEntityType<OilDepositBlockEntity>> OIL_DEPOSIT_BLOCK_ENTITY =
		BLOCK_ENTITIES.register("oil_deposit_block_entity", () ->
			BlockEntityType.Builder.of(OilDepositBlockEntity::new,
				OilFixBlocks.OIL_DEPOSIT_BLOCK.get()).build(null));


	public static void register(IEventBus eventBus) {
		BLOCK_ENTITIES.register(eventBus);
	}

}
