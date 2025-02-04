package wiiu.mavity.oilfixtfmg.mixin;

import com.drmangotea.tfmg.blocks.machines.oil_processing.pumpjack.base.PumpjackBaseBlockEntity;
import com.drmangotea.tfmg.registry.*;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import wiiu.mavity.oilfixtfmg.blocks.OilFixBlocks;
import wiiu.mavity.oilfixtfmg.blocks.entity.custom.OilDepositBlockEntity;

@SuppressWarnings({"AddedMixinMembersNamePattern", "DiscouragedShift", "resource"})
@Mixin(PumpjackBaseBlockEntity.class)
public abstract class DisableDefaultOilProcessMixin {

	@Shadow
	public BlockPos deposit;

	@Shadow
	public FluidTank tankInventory;

	@Shadow
	public int miningRate;

    @Inject(
		method = "process",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraftforge/fluids/capability/templates/FluidTank;setFluid(Lnet/minecraftforge/fluids/FluidStack;)V",
			shift = At.Shift.BEFORE
		),
		remap = false,
		cancellable = true
    )
    public void interceptProcessing(CallbackInfo ci) {
        ci.cancel();
		OilDepositBlockEntity blockEntity = (OilDepositBlockEntity)this.mixinGetLevel().getBlockEntity(this.deposit);
		assert blockEntity != null;
		int leftOverOilInDeposit = blockEntity.getOilLevel() - this.miningRate;
		if (leftOverOilInDeposit <= 0) return;
		blockEntity.setOilLevel(leftOverOilInDeposit);
		this.tankInventory.setFluid(
			new FluidStack(
				TFMGFluids.CRUDE_OIL.getSource(),
				this.tankInventory.getFluidAmount() + this.miningRate
			)
		);
    }

	@Inject(
		method = "findDeposit",
		at = @At(
			value = "HEAD"
		),
		remap = false,
		cancellable = true
	)
    public void interceptDepositSearch(CallbackInfo ci) {
		ci.cancel();
		BlockPos pos = this.mixinGetPos();
		int y = pos.getY();
		for(int i = 0; i < y + 64; i++) {
			BlockPos checkedPos = new BlockPos(pos.getX(), y - 1 - i, pos.getZ());
			BlockState state = this.mixinGetLevel().getBlockState(checkedPos);
			if (state.is(OilFixBlocks.OIL_DEPOSIT_BLOCK.get())) {
				this.deposit = checkedPos;
				return;
			}

			if (!state.is(TFMGBlocks.INDUSTRIAL_PIPE.get())) {
				this.deposit = null;
				return;
			}
		}
    }

	@Unique
	public BlockPos mixinGetPos() {
		return ((PumpjackBaseBlockEntity)(Object)this).getBlockPos();
	}

	@Unique
	public Level mixinGetLevel() {
		return ((PumpjackBaseBlockEntity)(Object)this).getLevel();
	}
}