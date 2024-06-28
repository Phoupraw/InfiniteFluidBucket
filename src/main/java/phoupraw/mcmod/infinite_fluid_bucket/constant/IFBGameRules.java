package phoupraw.mcmod.infinite_fluid_bucket.constant;

import com.google.common.base.Functions;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameRules;
import org.jetbrains.annotations.Unmodifiable;
import phoupraw.mcmod.infinite_fluid_bucket.InfiniteFluidBucket;

import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public sealed interface IFBGameRules permits IFBConstants {
    //PacketCodec<PacketByteBuf,Integer> PACKET_CODEC =
    //GameRules GAME_RULES = new GameRules();
    String WATER_BUCKET = InfiniteFluidBucket.ID + ":water_bucket";
    String EMPTY_BUCKET = InfiniteFluidBucket.ID + ":empty_bucket";
    String WATER_POTION = InfiniteFluidBucket.ID + ":water_potion";
    String GLASS_BOTTLE = InfiniteFluidBucket.ID + ":glass_bottle";
    String MILK_BUCKET = InfiniteFluidBucket.ID + ":milk_bucket";
    String LAVA_BUCKET = InfiniteFluidBucket.ID + ":lava_bucket";
    String HONEY_BOTTLE = InfiniteFluidBucket.ID + ":honey_bottle";
    Object2BooleanMap<String> VALUES = new Object2BooleanOpenHashMap<>(Stream.of(WATER_BUCKET, EMPTY_BUCKET, WATER_POTION, GLASS_BOTTLE, MILK_BUCKET).collect(Collectors.toMap(Function.identity(), Functions.constant(true))));
    @Unmodifiable
    BiMap<String, GameRules.Key<GameRules.BooleanRule>> KEYS = HashBiMap.create(Stream.of(WATER_BUCKET, EMPTY_BUCKET, WATER_POTION, GLASS_BOTTLE, MILK_BUCKET, LAVA_BUCKET, HONEY_BOTTLE).collect(Collectors.toMap(Function.identity(), IFBGameRules::of)));
    //BiMap<>
    //GameRules.Key<GameRules.BooleanRule> WATER_BUCKET = of(WATER_BUCKET_NAME);
    //GameRules.Key<GameRules.BooleanRule> EMPTY_BUCKET = of(EMPTY_BUCKET_NAME);
    //GameRules.Key<GameRules.BooleanRule> WATER_POTION = of(WATER_POTION_NAME);
    //GameRules.Key<GameRules.BooleanRule> GLASS_BOTTLE = of(GLASS_BOTTLE_NAME);
    //GameRules.Key<GameRules.BooleanRule> MILK_BUCKET = of(MILK_BUCKET_NAME);
    //GameRules.Key<GameRules.BooleanRule> LAVA_BUCKET = of(LAVA_BUCKET_NAME);
    //private static void onChange(MinecraftServer server, GameRules.BooleanRule rule) {
    //    for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
    //        //PacketCodecs.VAR_INT.encode();
    //        ServerPlayNetworking.send(player, new BoolGameRulePayload(EMPTY_BUCKET.getName(), rule.get()));
    //    }
    //    GAME_RULES.put(EMPTY_BUCKET.getName(), rule.get());
    //    //IFBConfig.getConfig().setEmptyBucket(rule.get());
    //    //GAME_RULES.get(EMPTY_BUCKET).set(rule.get(), server);
    //    //rule.set();
    //}
    private static GameRules.Key<GameRules.BooleanRule> of(String name) {
        //List<GameRules.Key<GameRules.BooleanRule>> holder = Collections.singletonList(null);
        //KEYS.put(name,key);
        return GameRuleRegistry.register(name, GameRules.Category.MISC, GameRuleFactory.createBooleanRule(VALUES.getBoolean(name), (server, rule) -> {
            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                ServerPlayNetworking.send(player, new BoolGameRulePayload(name, rule.get()));
            }
            VALUES.put(name, rule.get());
        }));
    }
    record BoolGameRulePayload(String name, boolean value) implements CustomPayload {
        public static final Id<BoolGameRulePayload> ID = new Id<>(IFBIDs.of("gamerule/bool"));
        public static final PacketCodec<PacketByteBuf, BoolGameRulePayload> CODEC = PacketCodec.tuple(PacketCodecs.STRING, BoolGameRulePayload::name, PacketCodecs.BOOL, BoolGameRulePayload::value, BoolGameRulePayload::new);
        //public static
        @Override
        public Id<? extends CustomPayload> getId() {
            return ID;
        }
    }
}
