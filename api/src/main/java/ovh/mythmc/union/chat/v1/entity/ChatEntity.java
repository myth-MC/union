package ovh.mythmc.union.chat.v1.entity;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;
import ovh.mythmc.union.chat.v1.entity.result.ChatModificationResult;

public interface ChatEntity {

    @NotNull Optional<Component> prefix();

    @NotNull Optional<Component> suffix();

    @NotNull ChatModificationResult prefix(@NotNull Component prefix);

    @NotNull ChatModificationResult suffix(@NotNull Component suffix);
    
}
