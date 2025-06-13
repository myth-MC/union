package ovh.mythmc.union.chat.v1.entity.builder;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import ovh.mythmc.union.chat.v1.entity.ChatEntity;
import ovh.mythmc.union.chat.v1.entity.ChatUser;
import ovh.mythmc.union.chat.v1.entity.result.ChatModificationResult;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

final class ChatUserStepBuilderImpl implements ChatUserStepBuilder.IdentifierStep<ChatUser>, ChatUserStepBuilder.GroupStep<ChatUser>, ChatEntityStepBuilder.GetPrefixStep<ChatUser>, ChatEntityStepBuilder.GetSuffixStep<ChatUser>, ChatEntityStepBuilder.SetPrefixStep<ChatUser>, ChatEntityStepBuilder.SetSuffixStep<ChatUser>, ChatEntityStepBuilder.BuildStep<ChatUser> {

    private UUID identifier;

    private ChatEntity group;

    private Supplier<Optional<Component>> prefixSupplier;

    private Supplier<Optional<Component>> suffixSupplier;

    private Function<Component, ChatModificationResult> prefixSetter;

    private Function<Component, ChatModificationResult> suffixSetter;

    @Override
    public ChatUserStepBuilder.@NotNull GroupStep<ChatUser> identifier(@NotNull UUID uuid) {
        this.identifier = uuid;
        return this;
    }

    @Override
    public ChatEntityStepBuilder.@NotNull GetPrefixStep<ChatUser> group(@NotNull ChatEntity group) {
        this.group = group;
        return this;
    }

    @Override
    public ChatEntityStepBuilder.@NotNull GetSuffixStep<ChatUser> prefixSupplier(@NotNull Supplier<Optional<Component>> prefixSupplier) {
        this.prefixSupplier = prefixSupplier;
        return this;
    }

    @Override
    public ChatEntityStepBuilder.@NotNull SetPrefixStep<ChatUser> suffixSupplier(@NotNull Supplier<Optional<Component>> suffixSupplier) {
        this.suffixSupplier = suffixSupplier;
        return this;
    }

    @Override
    public ChatEntityStepBuilder.@NotNull SetSuffixStep<ChatUser> prefixSetter(@NotNull Function<Component, ChatModificationResult> prefixSetter) {
        this.prefixSetter = prefixSetter;
        return this;
    }

    @Override
    public ChatEntityStepBuilder.@NotNull BuildStep<ChatUser> suffixSetter(@NotNull Function<Component, ChatModificationResult> suffixSetter) {
        this.suffixSetter = suffixSetter;
        return this;
    }

    @Override
    public @NotNull ChatUser build() {

        return new ChatUser() {

            @Override
            public @NotNull Optional<ChatEntity> group() {
                return Optional.ofNullable(group);
            }

            @Override
            public @NotNull Optional<Component> prefix() {
                return prefixSupplier.get();
            }

            @Override
            public @NotNull Optional<Component> suffix() {
                return suffixSupplier.get();
            }

            @Override
            public @NotNull ChatModificationResult prefix(@NotNull Component prefix) {
                return prefixSetter.apply(prefix);
            }

            @Override
            public @NotNull ChatModificationResult suffix(@NotNull Component suffix) {
                return suffixSetter.apply(suffix);
            }

            @Override
            public @NotNull UUID identifier() {
                return identifier;
            }

        };

    }

}
