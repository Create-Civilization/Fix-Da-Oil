package wiiu.mavity.oilfixtfmg.blocks.entity;


import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class OilDepositBlockEntity extends BlockEntity {


	private int oilLevel;

	public OilDepositBlockEntity(BlockPos pPos, BlockState pBlockState) {
		super(OilFixBlockEntities.OIL_DEPOSIT_BLOCK_ENTITY.get(), pPos, pBlockState);
	}

	@Override
	public void load(CompoundTag pCompound) {
		oilLevel = pCompound.getInt("OilLevel");
	}


	@Override
	protected void saveAdditional(CompoundTag pTag) {
		pTag.putInt("OilLevel", oilLevel);
	}
}
