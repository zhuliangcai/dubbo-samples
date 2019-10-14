package org.apache.dubbo.samples.provider;

import org.apache.zookeeper.server.ServerConfig;
import org.apache.zookeeper.server.ZooKeeperServerMain;

import java.io.IOException;

/**
 * @author liangcai_zhu
 * @Description TODO
 * @Date 2019/10/8 11:22
 */
public class EmbedZookeeperServer {
    public static void main(String[] args)throws IOException {

        args=new String[]{"2181","C:/zoo_data/"};

        ZooKeeperServerMain zooKeeperServer = new ZooKeeperServerMain();
        ServerConfig config=new ServerConfig();
        config.parse(args);
        zooKeeperServer.runFromConfig(config);
    }
}
