package ovh.mythmc.union.permission.v1.entity.builder;

import org.jetbrains.annotations.NotNull;

import java.util.function.BiFunction;

import org.jetbrains.annotations.ApiStatus.Experimental;

import ovh.mythmc.union.permission.v1.entity.PermissionEntity;
import ovh.mythmc.union.permission.v1.entity.query.PermissionQueryOptions;
import ovh.mythmc.union.permission.v1.node.PermissionNode;
import ovh.mythmc.union.permission.v1.node.result.PermissionNodeResult;
import ovh.mythmc.union.util.Buildable;

@Experimental
public interface PermissionEntityStepBuilder {

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

        @NotNull BuildStep removeTransient(@NotNull BiFunction<@NotNull PermissionNode, @NotNull PermissionQueryOptions, @NotNull PermissionNodeResult> removeTransient);
        
    }

    interface BuildStep extends Buildable<PermissionEntity> {

    }
    
}
