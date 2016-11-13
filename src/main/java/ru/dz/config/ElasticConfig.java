package ru.dz.config;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;
import org.springframework.scheduling.concurrent.ScheduledExecutorTask;
import ru.dz.services.ElasticExecutorTask;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * Created by Aydar Farrakhov on 05.09.16.
 */
@Configuration
public class ElasticConfig {

    private static final String CLUSTER_PROP = "cluster.name";
    private static final String TRANSPORT_SNIFF_PROP = "client.transport.sniff";

    public static final String MOVIE_CORP_INDEX = "moviecorp";
    public static final String MOVIE_TYPE = "movies";


    @Value("${elastic.ip:'127.0.0.1'}")
    private String elasticIP;

    @Value("${elastic.port:9300}")
    private int elasticPort;

    @Value("${elastic.period}")
    private long elasticPeriod;

    @Value("${cluster.name:'elasticsearch'}")
    private String clusterName;

    @Value("${client.transport.sniff:true}")
    private boolean transportSniff;

    @Autowired
    ElasticExecutorTask executorTask;

    @Bean
    public ScheduledExecutorFactoryBean scheduledExecutorFactoryBean() {
        ScheduledExecutorFactoryBean scheduledExecutorFactoryBean = new ScheduledExecutorFactoryBean();
        scheduledExecutorFactoryBean.setScheduledExecutorTasks(elasticExecutor());
        return scheduledExecutorFactoryBean;
    }

    @Bean
    public ScheduledExecutorTask elasticExecutor() {
        ScheduledExecutorTask scheduledExecutorTask = new ScheduledExecutorTask();
        scheduledExecutorTask.setRunnable(executorTask);
        scheduledExecutorTask.setPeriod(elasticPeriod);
        return scheduledExecutorTask;
    }

    @Bean
    public Client elasticSearchClient() throws UnknownHostException {

        Settings settings = Settings.settingsBuilder()
                .put(CLUSTER_PROP, clusterName)
                .put(TRANSPORT_SNIFF_PROP, transportSniff)
                .build();

        Client client = TransportClient.builder().settings(settings)
                .build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(elasticIP), elasticPort));
        initClient(client);

        return client;
    }

    private void initClient(Client client) {
        final IndicesExistsResponse res = client.admin().indices().prepareExists(MOVIE_CORP_INDEX).execute().actionGet();
        if (res.isExists()) {
            return;
        }

        final CreateIndexRequestBuilder createIndexRequestBuilder = client.admin().indices()
                .prepareCreate(MOVIE_CORP_INDEX);

        createIndexRequestBuilder.setSettings(getResource("autocomplete.json"));
        createIndexRequestBuilder.addMapping(MOVIE_TYPE, getResource("mapping.json"));

        createIndexRequestBuilder.execute().actionGet();

    }

    private String getResource(String file){
        URL url = Resources.getResource(file);
        try {
            return Resources.toString(url, Charsets.UTF_8);
        } catch (IOException e) {
            return "";
        }
    }


}
