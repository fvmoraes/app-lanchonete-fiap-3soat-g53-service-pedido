package com.fiap.lanchonete.application.cucumber;

import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cumcuber")
@SelectClasspathResource("features")
public class CumcuberTest {

}
