package org.telegram.telegrambots.facilities;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.facilities.proxysocketfactorys.HttpConnectionSocketFactory;
import org.telegram.telegrambots.facilities.proxysocketfactorys.HttpSSLConnectionSocketFactory;
import org.telegram.telegrambots.facilities.proxysocketfactorys.SocksSSLConnectionSocketFactory;
import org.telegram.telegrambots.facilities.proxysocketfactorys.SocksConnectionSocketFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by bvn13 on 17.04.2018.
 */
public class TelegramHttpClientBuilder {

    public static CloseableHttpClient build(DefaultBotOptions options) {
        HttpClientBuilder httpClientBuilder =
               HttpClientBuilder.create()
                                .useSystemProperties()
                                .setConnectionReuseStrategy((response,context)->true)
                                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                                .setConnectionTimeToLive(options.getKeepAliveTtl(), TimeUnit.SECONDS)
                                .setMaxConnTotal(options.getConnectionPoolSize());
        return httpClientBuilder.build();
    }


}
