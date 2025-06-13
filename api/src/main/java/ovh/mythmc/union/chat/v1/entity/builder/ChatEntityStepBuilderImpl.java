package ovh.mythmc.union.chat.v1.entity.builder;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import ovh.mythmc.union.chat.v1.entity.ChatEntity;
import ovh.mythmc.union.chat.v1.entity.result.ChatModificationResult;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

final class ChatEntityStepBuilderImpl implements ChatEntityStepBuilder.GetPrefixStep<ChatEntity>, ChatEntityStepBuilder.GetSuffixStep<ChatEntity>, ChatEntityStepBuilder.SetPrefixStep<ChatEntity>, ChatEntityStepBuilder.SetSuffixStep<ChatEntity>, ChatEntityStepBuilder.BuildStep<ChatEntity> {

    private Supplier<Optional<Component>> prefixSupplier;

    private Supplier<Optional<Component>> suffixSupplier;

    private Function<Component, ChatModificationResult> prefixSetter;

    private Function<Component, ChatModificationResult> suffixSetter;

    @Override
    public ChatEntityStepBuilder.@NotNull GetSuffixStep<ChatEntity> prefixSupplier(@NotNull Supplier<Optional<Component>> prefixSupplier) {
        this.prefixSupplier = prefixSupplier;
        return this;
    }

    @Override
    public ChatEntityStepBuilder.@NotNull SetPrefixStep<ChatEntity> suffixSupplier(@NotNull Supplier<Optional<Component>> suffixSupplier) {
        this.suffixSupplier = suffixSupplier;
        return this;
    }

    @Override
    public ChatEntityStepBuilder.@NotNull SetSuffixStep<ChatEntity> prefixSetter(@NotNull Function<Component, ChatModificationResult> prefixSetter) {
        this.prefixSetter = prefixSetter;
        return this;
    }

    @Override
    public ChatEntityStepBuilder.@NotNull BuildStep<ChatEntity> suffixSetter(@NotNull Function<Component, ChatModificationResult> suffixSetter) {
        this.suffixSetter = suffixSetter;
        return this;
    }

    @Override
    public @NotNull ChatEntity build() {
        return new ChatEntity() {
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
        };
    }
}
