package rmc.mixins.minecraft_guard;

import java.util.UUID;

import com.mojang.authlib.GameProfile;

/**
 * Developed by RMC Team, 2021
 * @author KR33PY
 */
public abstract class MinecraftGuard {

    public static final GameProfile EXPLOSION_FAKE = new GameProfile(UUID.fromString("862238FC-17E6-47B4-A736-6187290DB9C6"), "[MinecraftExplosion]");

}