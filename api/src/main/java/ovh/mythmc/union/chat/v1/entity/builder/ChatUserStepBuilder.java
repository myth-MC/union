package ovh.mythmc.union.chat.v1.entity.builder;

import org.jetbrains.annotations.NotNull;
import ovh.mythmc.union.chat.v1.entity.ChatEntity;
import ovh.mythmc.union.chat.v1.entity.ChatUser;

import java.util.UUID;

public interface ChatUserStepBuilder extends ChatEntityStepBuilder {

    static @NotNull IdentifierStep<ChatUser> builder() {
        return new ChatUserStepBuilderImpl();
    }

    interface IdentifierStep<T> {

        @NotNull GroupStep<T> identifier(@NotNull UUID uuid);

    }

    interface GroupStep<T> {

        @NotNull GetPrefixStep<T> group(@NotNull ChatEntity group);

    }

}
