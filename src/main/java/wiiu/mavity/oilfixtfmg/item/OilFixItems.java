package wiiu.mavity.oilfixtfmg.item;

import net.minecraft.world.item.Item;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.*;

import wiiu.mavity.oilfixtfmg.OilFixTFMG;

public class OilFixItems {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, OilFixTFMG.MOD_ID);

	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
}