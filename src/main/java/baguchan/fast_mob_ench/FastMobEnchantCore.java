package baguchan.fast_mob_ench;

import baguchan.enchantwithmob.mobenchant.MobEnchant;
import baguchan.enchantwithmob.registry.MobEnchants;
import baguchan.fast_mob_ench.mobenchant.FastMobEnchant;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FastMobEnchantCore.MODID)
public class FastMobEnchantCore {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "fast_mob_ench";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<MobEnchant> MOB_ENCHANT = DeferredRegister.create(MobEnchants.MOB_ENCHANT.getRegistryKey(), MODID);

    // Creates a new Block with the id "examplemod:example_block", combining the namespace and path
    public static final RegistryObject<MobEnchant> FAST = MOB_ENCHANT.register("fast", () -> new FastMobEnchant(new MobEnchant.Properties(MobEnchant.Rarity.RARE, 2)));

    public FastMobEnchantCore() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        MOB_ENCHANT.register(modEventBus);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }
}
