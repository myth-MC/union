package ovh.mythmc.union.chat.v1.entity.result;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

final class ChatModificationResultImpl implements ChatModificationResult {

    private final Type type;

    private final Component message;

    ChatModificationResultImpl(@NotNull Type type, @Nullable Component message) {
        this.type = type;
        this.message = message;
    }

    @Override
    public @NotNull Type type() {
        return this.type;
    }

    @Override
    public @NotNull Optional<Component> message() {
        return Optional.ofNullable(this.message);
    }

}
