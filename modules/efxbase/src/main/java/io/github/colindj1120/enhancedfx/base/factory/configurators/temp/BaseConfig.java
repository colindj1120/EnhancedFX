package io.github.colindj1120.enhancedfx.base.factory.configurators.temp;

import io.github.colindj1120.enhancedfx.base.factory.configurators.base.ConfiguratorBase;
import javafx.scene.Node;

public interface BaseConfig<T extends ConfiguratorBase> {
    T getConfigurator();

    Node getNode();
}
