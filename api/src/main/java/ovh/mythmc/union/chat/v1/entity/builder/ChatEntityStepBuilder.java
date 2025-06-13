package ovh.mythmc.union.chat.v1.entity.builder;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.NotNull;
import ovh.mythmc.union.chat.v1.entity.ChatEntity;
import ovh.mythmc.union.chat.v1.entity.result.ChatModificationResult;
import ovh.mythmc.union.util.Buildable;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public interface ChatEntityStepBuilder {

    static @NotNull GetPrefixStep<ChatEntity> builder() {
        return new ChatEntityStepBuilderImpl();
    }

    interface GetPrefixStep<T> {

        @NotNull GetSuffixStep<T> prefixSupplier(@NotNull Supplier<Optional<Component>> prefixSupplier);

    }

    interface GetSuffixStep<T> {

        @NotNull SetPrefixStep<T> suffixSupplier(@NotNull Supplier<Optional<Component>> suffixSupplier);

    }

    interface SetPrefixStep<T> {

        @NotNull SetSuffixStep<T> prefixSetter(@NotNull Function<Component, ChatModificationResult> prefixSetter);

    }

    interface SetSuffixStep<T> {

        @NotNull BuildStep<T> suffixSetter(@NotNull Function<Component, ChatModificationResult> suffixSetter);

    }

    interface BuildStep<T> extends Buildable<T> {

    }

}
