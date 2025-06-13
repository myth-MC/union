package ovh.mythmc.union.chat.v1.provider;

import org.jetbrains.annotations.NotNull;
import ovh.mythmc.union.chat.v1.entity.ChatUser;

import java.util.Optional;
import java.util.UUID;

public interface ChatProvider {

    @NotNull Optional<ChatUser> user(@NotNull UUID uuid);
    
}
