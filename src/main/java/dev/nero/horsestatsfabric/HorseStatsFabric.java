package dev.nero.horsestatsfabric;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.mojang.authlib.GameProfile;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.event.world.WorldTickCallback;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityInteraction;
import net.minecraft.entity.passive.HorseBaseEntity;
import net.minecraft.entity.passive.HorseEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.text.Texts;
import net.minecraft.util.ActionResult;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class HorseStatsFabric implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("horsestatsfabric");

	public static final String LOG_TITLE = "[HorseStatsFabric]";

	private final double MIN_HEALTH = 15;
	private final double MAX_HEALTH = 30;
	private final double MIN_JUMP_HEIGHT = 1.25;
	private final double MAX_JUMP_HEIGHT = 5;
	private final double MIN_SPEED = 4.8;
	private final double MAX_SPEED = 14.5;
	private final double MIN_SLOTS = 3;
	private final double MAX_SLOTS = 15;

	private double health;
	private double jumpHeight;
	private double speed;
	private int slots;
	private String owner;

	// Used to override the current overlay message if ours is already being displayed
	// Prevent "mounted" overlay text to override the stats message
	private Component overlayMessage;
	private int overlayMessageTime;


	@Override
	public void onInitialize() {
		LOGGER.info(LOG_TITLE+" Initializing HorseStatsFabric....");



		LOGGER.info(LOG_TITLE+" Successfully initialized HorseStatsFabric....");
	}
}
