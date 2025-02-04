package wiiu.mavity.oilfixtfmg;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {

	public static ForgeConfigSpec SPEC;
	public static Config SERVER;

	public static void load() {
		var pair = new ForgeConfigSpec.Builder().configure(Config::new);
		SPEC = pair.getRight();
		SERVER = pair.getLeft();
	}

	public ForgeConfigSpec.IntValue oilPerDeposit;

	public Config(ForgeConfigSpec.Builder builder) {
		oilPerDeposit = builder
			.comment("The amount of oil per oil deposit. (mB)")
			.translation("oilfixtfmg.oilPerDeposit")
			.defineInRange("oilPerDeposit", 10000000, 1, Integer.MAX_VALUE);
	}

	static {
		load();
	}
}