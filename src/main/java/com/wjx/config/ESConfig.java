package com.wjx.config;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;

import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.util.CollectionUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: dingguo
 * @Date: 2019/6/27 上午9:48
 */
@Configuration
@EnableElasticsearchRepositories("com.wjx")
public class ESConfig {
    private static final Logger logger = LoggerFactory.getLogger(ESConfig.class);
    /**
     * 端口号
     */
    @Value("${elasticsearch.port}")
    private int esPort;
    /**
     * 集群名称
     */
    @Value("${elasticsearch.clustername}")
    private String esClusterName;
    /**
     * 集群的地址
     */
    @Value("#{'${elasticsearch.hosts:localhost}'.split(',')}")
    private List<String> hosts = new ArrayList<>();

    /**
     * 连接池
     */
    @Value("${elasticsearch.pool}")
    private String poolSize;

    /**
     * 初始化配置
     *
     * @return
     */
    private Settings settings() {
        Settings settings = Settings.builder()
                .put("cluster.name", esClusterName)
                .put("thread_pool.search.size", Integer.parseInt(poolSize))// 增加线程池个数，暂时设为5
                .put("client.transport.sniff", false).build();
        return settings;
    }

    @Bean
    protected Client buildClient() {
        TransportClient transportClient = new PreBuiltTransportClient(settings());

        if (!CollectionUtils.isEmpty(hosts)) {
            hosts.stream().forEach(h -> {
                try {
                    transportClient.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(h), esPort));

                } catch (UnknownHostException e) {
                    logger.error("Error addTransportAddress,with host:{}.", h);
                }
            });
        }
        return transportClient;
    }

    @Bean
    public ElasticsearchOperations elasticsearchOperations() {
        return new ElasticsearchTemplate(buildClient());
    }

}
