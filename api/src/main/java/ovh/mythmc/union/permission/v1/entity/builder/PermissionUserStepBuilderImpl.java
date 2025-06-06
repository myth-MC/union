package ovh.mythmc.union.permission.v1.entity.builder;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;

import ovh.mythmc.union.permission.v1.entity.PermissionUser;
import ovh.mythmc.union.permission.v1.entity.builder.PermissionUserStepBuilder.AddGroupStep;
import ovh.mythmc.union.permission.v1.entity.builder.PermissionUserStepBuilder.AddStep;
import ovh.mythmc.union.permission.v1.entity.builder.PermissionUserStepBuilder.AddTransientStep;
import ovh.mythmc.union.permission.v1.entity.builder.PermissionUserStepBuilder.BuildStep;
import ovh.mythmc.union.permission.v1.entity.builder.PermissionUserStepBuilder.GroupsStep;
import ovh.mythmc.union.permission.v1.entity.builder.PermissionUserStepBuilder.HasStep;
import ovh.mythmc.union.permission.v1.entity.builder.PermissionUserStepBuilder.IdentifierStep;
import ovh.mythmc.union.permission.v1.entity.builder.PermissionUserStepBuilder.PrimaryGroupStep;
import ovh.mythmc.union.permission.v1.entity.builder.PermissionUserStepBuilder.RemoveGroupStep;
import ovh.mythmc.union.permission.v1.entity.builder.PermissionUserStepBuilder.RemoveStep;
import ovh.mythmc.union.permission.v1.entity.builder.PermissionUserStepBuilder.RemoveTransientStep;
import ovh.mythmc.union.permission.v1.entity.query.PermissionQueryOptions;
import ovh.mythmc.union.permission.v1.group.PermissionGroup;
import ovh.mythmc.union.permission.v1.group.result.PermissionGroupResult;
import ovh.mythmc.union.permission.v1.node.PermissionNode;
import ovh.mythmc.union.permission.v1.node.result.PermissionNodeResult;

final class PermissionUserStepBuilderImpl implements IdentifierStep, HasStep, AddStep, AddTransientStep, RemoveStep, RemoveTransientStep, GroupsStep, PrimaryGroupStep, AddGroupStep, RemoveGroupStep, BuildStep{

    private UUID identifier;

    private BiFunction<PermissionNode, PermissionQueryOptions, PermissionNodeResult> has;

    private BiFunction<PermissionNode, PermissionQueryOptions, PermissionNodeResult> add;

    private BiFunction<PermissionNode, PermissionQueryOptions, PermissionNodeResult> addTransient;

    private BiFunction<PermissionNode, PermissionQueryOptions, PermissionNodeResult> remove;

    private BiFunction<PermissionNode, PermissionQueryOptions, PermissionNodeResult> removeTransient;

    private Function<PermissionQueryOptions, Set<PermissionGroup>> groups;

    private Function<PermissionQueryOptions, Optional<PermissionGroup>> primaryGroup;

    private BiFunction<PermissionGroup, PermissionQueryOptions, PermissionGroupResult> addGroup;
    
    private BiFunction<PermissionGroup, PermissionQueryOptions, PermissionGroupResult> removeGroup;

    @Override
    public @NotNull HasStep identifier(@NotNull Supplier<UUID> identifier) {
        this.identifier = identifier.get();
        return this;
    }

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
    public @NotNull GroupsStep removeTransient(@NotNull BiFunction<@NotNull PermissionNode, @NotNull PermissionQueryOptions, @NotNull PermissionNodeResult> removeTransient) {
        this.removeTransient = removeTransient;
        return this;
    }

    @Override
    public @NotNull PrimaryGroupStep groups(@NotNull Function<@NotNull PermissionQueryOptions, @NotNull Set<PermissionGroup>> groups) {
        this.groups = groups;
        return this;
    }

    @Override
    public @NotNull AddGroupStep primaryGroup(@NotNull Function<@NotNull PermissionQueryOptions, @NotNull Optional<PermissionGroup>> primaryGroup) {
        this.primaryGroup = primaryGroup;
        return this;
    }

    @Override
    public @NotNull RemoveGroupStep addGroup(@NotNull BiFunction<@NotNull PermissionGroup, @NotNull PermissionQueryOptions, @NotNull PermissionGroupResult> addGroup) {
        this.addGroup = addGroup;
        return this;
    }

    @Override
    public @NotNull BuildStep removeGroup(@NotNull BiFunction<@NotNull PermissionGroup, @NotNull PermissionQueryOptions, @NotNull PermissionGroupResult> removeGroup) {
        this.removeGroup = removeGroup;
        return this;
    }

    @Override
    public @NotNull PermissionUser build() {
        return new PermissionUser() {

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

            @Override
            public @NotNull UUID identifier() {
                return identifier;
            }

            @Override
            public @NotNull Set<PermissionGroup> groups(@NotNull PermissionQueryOptions options) {
                return groups.apply(options);
            }

            @Override
            public @NotNull Optional<PermissionGroup> primaryGroup(@NotNull PermissionQueryOptions options) {
                return primaryGroup.apply(options);
            }

            @Override
            public @NotNull PermissionGroupResult addGroup(@NotNull PermissionGroup group, @NotNull PermissionQueryOptions options) {
                return addGroup.apply(group, options);
            }

            @Override
            public @NotNull PermissionGroupResult removeGroup(@NotNull PermissionGroup group, @NotNull PermissionQueryOptions options) {
                return removeGroup.apply(group, options);
            }
            
        };
    }

}
