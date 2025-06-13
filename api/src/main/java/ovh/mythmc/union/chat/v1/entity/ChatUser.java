package ovh.mythmc.union.chat.v1.entity;

import org.jetbrains.annotations.NotNull;
import ovh.mythmc.union.util.Identified;

import java.util.Optional;
import java.util.UUID;

public interface ChatUser extends ChatEntity, Identified<UUID> {

    @NotNull Optional<ChatEntity> group();

}
