package wiiu.mavity.oilfixtfmg.blocks;

import com.drmangotea.tfmg.registry.TFMGBlocks;

import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.*;

import wiiu.mavity.oilfixtfmg.OilFixTFMG;
import wiiu.mavity.oilfixtfmg.blocks.custom.OilDepositBlock;
import wiiu.mavity.oilfixtfmg.item.OilFixItems;

import java.util.function.Supplier;

@SuppressWarnings("SameParameterValue")
public class OilFixBlocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, OilFixTFMG.MOD_ID);

	public static RegistryObject<Block> OIL_DEPOSIT_BLOCK = registerBlock(
		"oil_deposit_block", () -> new OilDepositBlock(BlockBehaviour.Properties.copy(TFMGBlocks.OIL_DEPOSIT.get()))
	);

	private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
		RegistryObject<T> toReturn = BLOCKS.register(name, block);
		registerBlockItem(name, toReturn);
		return toReturn;
	}

	private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
		OilFixItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
	}

	public static void register(IEventBus eventBus) {
		BLOCKS.register(eventBus);
	}
}