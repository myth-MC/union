package ovh.mythmc.union.chat.v1.user;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.Component;

public interface ChatUser {

    @NotNull Optional<Component> prefix();

    @NotNull Optional<Component> suffix();
    
}
