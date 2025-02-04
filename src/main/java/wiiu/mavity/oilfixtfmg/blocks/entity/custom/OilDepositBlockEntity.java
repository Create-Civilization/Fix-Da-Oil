package wiiu.mavity.oilfixtfmg.blocks.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import wiiu.mavity.oilfixtfmg.Config;
import wiiu.mavity.oilfixtfmg.blocks.entity.OilFixBlockEntities;

public class OilDepositBlockEntity extends BlockEntity {

	private int oilLevel = Config.SERVER.oilPerDeposit.get();

	public OilDepositBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(OilFixBlockEntities.OIL_DEPOSIT_BLOCK_ENTITY.get(), pPos, pBlockState);
	}

	public void setOilLevel(int oilLevel) {
		this.oilLevel = oilLevel;
	}

	public int getOilLevel() {
		return this.oilLevel;
	}

	@Override
	public void load(CompoundTag pCompound) {
		this.setOilLevel(pCompound.getInt("OilLevel"));
	}

	@Override
	public void saveAdditional(CompoundTag pTag) {
		pTag.putInt("OilLevel", this.getOilLevel());
	}
}