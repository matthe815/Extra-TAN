package extratan.blocks;

import extratan.core.ExtraTAN;
import extratan.creativetabs.CreativeTabHandler;
import lieutenant.registry.RegisterHandler;
import net.minecraft.block.BlockGlass;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

/**
 * Defining a transparent block type, similar to glass.
 * @author Matthew
 *
 */
public class TransparentBlockBase extends BlockGlass {

	public TransparentBlockBase(Material material, SoundType footstepSound, String name, String registryName, float hardness) {
		super(material, false);
		
		setUnlocalizedName(ExtraTAN.modId + "." + name)
			.setRegistryName(registryName)
			.setCreativeTab(CreativeTabHandler.ExtraTANBlocks)
			.setHardness(hardness)
			.setResistance(hardness / 4);
		
		setSoundType(footstepSound);
		
		RegisterHandler.AddBlock(this);
	}

}
