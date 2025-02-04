package wiiu.mavity.oilfixtfmg;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import wiiu.mavity.oilfixtfmg.blocks.OilFixBlocks;
import wiiu.mavity.oilfixtfmg.blocks.entity.OilFixBlockEntities;
import wiiu.mavity.oilfixtfmg.item.OilFixItems;

@Mod(OilFixTFMG.MOD_ID)
public class OilFixTFMG {

    public static final String MOD_ID = "oilfixtfmg";

    @SuppressWarnings("removal") // 4.3.10+ makes breaking changes and is shipped to devs by default, but 47.3.0 is still shipped to users by default
	public OilFixTFMG() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, Config.SPEC);

		OilFixItems.register(modEventBus);
		OilFixBlocks.register(modEventBus);
		OilFixBlockEntities.register(modEventBus);
	}
}