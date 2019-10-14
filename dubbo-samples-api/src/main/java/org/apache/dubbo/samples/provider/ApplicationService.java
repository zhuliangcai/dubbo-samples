/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.dubbo.samples.provider;


import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.samples.api.GreetingsService;

import java.util.concurrent.CountDownLatch;

public class ApplicationService {
    private static String zookeeperHost = System.getProperty("zookeeper.address", "127.0.0.1");

    public static void main(String[] args) throws Exception {
        ServiceConfig<GreetingsService> service = new ServiceConfig<>();

        service.setToken(true);
//        官方并未直接给出referenceConfig如何在consumer上配置token，但由源码分析得来，
//        Dubbo采用隐式参数传递token，RpcContext.getContext().setAttachment("token","123456")：

        //应用程序配置
        ApplicationConfig applicationConfig = new ApplicationConfig("first-dubbo-provider_");
        applicationConfig.setId("my-dubbo-id");
        service.setApplication(applicationConfig);

//        注册中心配置
        service.setRegistry(new RegistryConfig("zookeeper://" + zookeeperHost + ":2181"));


//        服务接口
        service.setInterface(GreetingsService.class);
//服务实现
        service.setRef(new GreetingsServiceImpl());
        service.export();

        System.out.println("dubbo service started");

        MainClass.getThreadCount();

        new CountDownLatch(1).await();
    }
}
