package wiiu.mavity.oilfixtfmg;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import wiiu.mavity.oilfixtfmg.blocks.OilFixBlocks;
import wiiu.mavity.oilfixtfmg.blocks.entity.OilFixBlockEntities;
import wiiu.mavity.oilfixtfmg.item.OilFixItems;

@Mod(OilFixTFMG.MOD_ID)
public class OilFixTFMG {

    public static final String MOD_ID = "oilfixtfmg";

    public OilFixTFMG() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		OilFixItems.register(modEventBus);
		OilFixBlocks.register(modEventBus);
		OilFixBlockEntities.register(modEventBus);
	}
}