package myStore.Tests;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.FEATURES_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectPackages("pl.project")
@IncludeTags("user")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "html:out.html")
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "src/Cucumber/Features")
public class MyStoreTest {
}
