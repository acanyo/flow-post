package com.kunkunyu.post.flow;

import com.kunkunyu.post.flow.extension.Follow;
import org.springframework.stereotype.Component;
import run.halo.app.extension.Scheme;
import run.halo.app.extension.SchemeManager;
import run.halo.app.extension.index.IndexSpecs;
import run.halo.app.plugin.BasePlugin;
import run.halo.app.plugin.PluginContext;

import java.util.Optional;


@Component
public class FlowPostPlugin extends BasePlugin {

    private final SchemeManager schemeManager;


    public FlowPostPlugin(PluginContext pluginContext, SchemeManager schemeManager) {
        super(pluginContext);
        this.schemeManager = schemeManager;
    }

    @Override
    public void start() {
        schemeManager.register(Follow.class, indexSpecs -> {

            indexSpecs.add(IndexSpecs.<Follow, String>single("email", String.class)
                .indexFunc(
                    follow -> Optional.ofNullable(follow.getEmail())
                        .orElse(null)
                )
            );
            indexSpecs.add(IndexSpecs.<Follow, Follow.FollowStatus>single("status", Follow.FollowStatus.class)
                .indexFunc(
                    follow -> Optional.ofNullable(follow.getStatus())
                        .orElse(null)
                )
            );
        });
    }

    @Override
    public void stop() {
        schemeManager.unregister(Scheme.buildFromType(Follow.class));
    }
}
