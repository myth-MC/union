package ovh.mythmc.union.chat.v1.provider;

import java.util.UUID;

import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;

public interface ChatProvider {

    @NotNull Component prefix(@NotNull UUID uuid);
    
}
