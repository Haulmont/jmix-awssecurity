/*
 * Copyright 2020 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package test_support;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.springframework.util.SocketUtils;

public class TestContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        ((DefaultListableBeanFactory) applicationContext.getBeanFactory()).setAllowBeanDefinitionOverriding(false);
        int mockServerPort = SocketUtils.findAvailableTcpPort();
        TestPropertySourceUtils.addInlinedPropertiesToEnvironment(applicationContext,
                "jmix.awssecurity.test.mock-server-port=" + mockServerPort,
                "jmix.awssecurity.issuer=http://localhost:" + mockServerPort + "/{userPoolId}",
                "jmix.awssecurity.domain=http://localhost:" + mockServerPort);
    }
}
