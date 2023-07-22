package baguchan.fast_mob_ench.mobenchant;

import baguchan.enchantwithmob.mobenchant.MobEnchant;
import baguchan.enchantwithmob.registry.MobEnchants;
import com.github.alexthe666.citadel.server.tick.ServerTickRateTracker;
import com.github.alexthe666.citadel.server.tick.modifier.LocalEntityTickRateModifier;
import com.github.alexthe666.citadel.server.tick.modifier.TickRateModifier;
import net.minecraft.world.entity.LivingEntity;

public class FastMobEnchant extends MobEnchant {
    public FastMobEnchant(Properties properties) {
        super(properties);
    }

    @Override
    public void tick(LivingEntity entity, int level) {
        super.tick(entity, level);
        float speed = (float) (1 / (level + 1));
        ServerTickRateTracker tracker = ServerTickRateTracker.getForServer(entity.level().getServer());
        for (TickRateModifier modifier : tracker.tickRateModifierList) {
            if (modifier instanceof LocalEntityTickRateModifier entityTick && entityTick.getEntityId() == entity.getId()) {
                return;
            }
        }
        tracker.addTickRateModifier(new LocalEntityTickRateModifier(entity.getId(), entity.getType(), 0, entity.level().dimension(), 1200, speed));
    }

    public int getMinEnchantability(int enchantmentLevel) {
        return 1 + (enchantmentLevel - 1) * 10;
    }

    public int getMaxEnchantability(int enchantmentLevel) {
        return this.getMinEnchantability(enchantmentLevel) + 30;
    }

    protected boolean canApplyTogether(MobEnchant ench) {
        return ench != MobEnchants.SPEEDY.get() && super.canApplyTogether(ench);
    }
}
