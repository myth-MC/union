package ovh.mythmc.union.permission.v1.entity.builder;

import java.util.function.BiFunction;

import org.jetbrains.annotations.NotNull;

import ovh.mythmc.union.permission.v1.entity.PermissionEntity;
import ovh.mythmc.union.permission.v1.entity.builder.PermissionEntityStepBuilder.AddStep;
import ovh.mythmc.union.permission.v1.entity.builder.PermissionEntityStepBuilder.AddTransientStep;
import ovh.mythmc.union.permission.v1.entity.builder.PermissionEntityStepBuilder.BuildStep;
import ovh.mythmc.union.permission.v1.entity.builder.PermissionEntityStepBuilder.HasStep;
import ovh.mythmc.union.permission.v1.entity.builder.PermissionEntityStepBuilder.RemoveStep;
import ovh.mythmc.union.permission.v1.entity.builder.PermissionEntityStepBuilder.RemoveTransientStep;
import ovh.mythmc.union.permission.v1.entity.query.PermissionQueryOptions;
import ovh.mythmc.union.permission.v1.node.PermissionNode;
import ovh.mythmc.union.permission.v1.node.result.PermissionNodeResult;

final class PermissionEntityStepBuilderImpl implements HasStep, AddStep, AddTransientStep, RemoveStep, RemoveTransientStep, BuildStep {
    
    private BiFunction<PermissionNode, PermissionQueryOptions, PermissionNodeResult> has;

    private BiFunction<PermissionNode, PermissionQueryOptions, PermissionNodeResult> add;

    private BiFunction<PermissionNode, PermissionQueryOptions, PermissionNodeResult> addTransient;

    private BiFunction<PermissionNode, PermissionQueryOptions, PermissionNodeResult> remove;

    private BiFunction<PermissionNode, PermissionQueryOptions, PermissionNodeResult> removeTransient;

    @Override
    public @NotNull AddStep has(@NotNull BiFunction<@NotNull PermissionNode, @NotNull PermissionQueryOptions, @NotNull PermissionNodeResult> has) {
        this.has = has;
        return this;
    }

    @Override
    public @NotNull AddTransientStep add(@NotNull BiFunction<@NotNull PermissionNode, @NotNull PermissionQueryOptions, @NotNull PermissionNodeResult> add) {
        this.add = add;
        return this;
    }

    @Override
    public @NotNull RemoveStep addTransient(@NotNull BiFunction<@NotNull PermissionNode, @NotNull PermissionQueryOptions, @NotNull PermissionNodeResult> addTransient) {
        this.addTransient = addTransient;
        return this;
    }

    @Override
    public @NotNull RemoveTransientStep remove(@NotNull BiFunction<@NotNull PermissionNode, @NotNull PermissionQueryOptions, @NotNull PermissionNodeResult> remove) {
        this.remove = remove;
        return this;
    }

    @Override
    public @NotNull BuildStep removeTransient(@NotNull BiFunction<@NotNull PermissionNode, @NotNull PermissionQueryOptions, @NotNull PermissionNodeResult> removeTransient) {
        this.removeTransient = removeTransient;
        return this;
    }

    @Override
    public @NotNull PermissionEntity build() {
        return new PermissionEntity() {

            @Override
            public @NotNull PermissionNodeResult has(@NotNull PermissionNode permission, @NotNull PermissionQueryOptions options) {
                return has.apply(permission, options);
            }

            @Override
            public @NotNull PermissionNodeResult add(@NotNull PermissionNode permission, @NotNull PermissionQueryOptions options) {
                return add.apply(permission, options);
            }

            @Override
            public @NotNull PermissionNodeResult addTransient(@NotNull PermissionNode permission, @NotNull PermissionQueryOptions options) {
                return addTransient.apply(permission, options);
            }

            @Override
            public @NotNull PermissionNodeResult remove(@NotNull PermissionNode permission, @NotNull PermissionQueryOptions options) {
                return remove.apply(permission, options);
            }

            @Override
            public @NotNull PermissionNodeResult removeTransient(@NotNull PermissionNode permission, @NotNull PermissionQueryOptions options) {
                return removeTransient.apply(permission, options);
            }
            
        };
    }

}
