package ovh.mythmc.union.permission.v1.entity.builder;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.ApiStatus.Experimental;

import ovh.mythmc.union.permission.v1.entity.PermissionUser;
import ovh.mythmc.union.permission.v1.entity.query.PermissionQueryOptions;
import ovh.mythmc.union.permission.v1.group.PermissionGroup;
import ovh.mythmc.union.permission.v1.group.result.PermissionGroupResult;
import ovh.mythmc.union.permission.v1.node.PermissionNode;
import ovh.mythmc.union.permission.v1.node.result.PermissionNodeResult;
import ovh.mythmc.union.util.Buildable;

@Experimental
public interface PermissionUserStepBuilder {

    static @NotNull IdentifierStep builder() {
        return new PermissionUserStepBuilderImpl();
    }

    interface IdentifierStep {

        @NotNull HasStep identifier(@NotNull Supplier<UUID> identifier);

    }
    
    interface HasStep {

        @NotNull AddStep has(@NotNull BiFunction<@NotNull PermissionNode, @NotNull PermissionQueryOptions, @NotNull PermissionNodeResult> has);

    }

    interface AddStep {

        @NotNull AddTransientStep add(@NotNull BiFunction<@NotNull PermissionNode, @NotNull PermissionQueryOptions, @NotNull PermissionNodeResult> add);

    }

    interface AddTransientStep {

        @NotNull RemoveStep addTransient(@NotNull BiFunction<@NotNull PermissionNode, @NotNull PermissionQueryOptions, @NotNull PermissionNodeResult> addTransient);

    }

    interface RemoveStep {

        @NotNull RemoveTransientStep remove(@NotNull BiFunction<@NotNull PermissionNode, @NotNull PermissionQueryOptions, @NotNull PermissionNodeResult> remove);

    }

    interface RemoveTransientStep {

        @NotNull GroupsStep removeTransient(@NotNull BiFunction<@NotNull PermissionNode, @NotNull PermissionQueryOptions, @NotNull PermissionNodeResult> removeTransient);
        
    }

    interface GroupsStep {

        @NotNull PrimaryGroupStep groups(@NotNull Function<@NotNull PermissionQueryOptions, @NotNull Set<PermissionGroup>> groups);
        
    }

    interface PrimaryGroupStep {

        @NotNull AddGroupStep primaryGroup(@NotNull Function<@NotNull PermissionQueryOptions, @NotNull Optional<PermissionGroup>> primaryGroup);
        
    }

    interface AddGroupStep {

        @NotNull RemoveGroupStep addGroup(@NotNull BiFunction<@NotNull PermissionGroup, @NotNull PermissionQueryOptions, @NotNull PermissionGroupResult> addGroup);
        
    }

    interface RemoveGroupStep {

        @NotNull BuildStep removeGroup(@NotNull BiFunction<@NotNull PermissionGroup, @NotNull PermissionQueryOptions, @NotNull PermissionGroupResult> removeGroup);

    }

    interface BuildStep extends Buildable<PermissionUser> {

    }

}
