package com.vmware.tanzu.support.scs.refreshmirror;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.cloudfoundry.client.CloudFoundryClient;
import org.cloudfoundry.client.v2.info.GetInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

@SpringBootApplication
public class RefreshMirrorApplication {

    private final Log LOG = LogFactory.getLog(RefreshMirrorApplication.class);

    @Autowired
    private CloudFoundryClient client;

    public static void main(String[] args) {
        SpringApplication.run(RefreshMirrorApplication.class, args);
    }

    @Bean
    public Consumer<String> refresh() {
        return refresher -> {
            LOG.info( refresher );
        };
    }

    @Bean
    public Function<String, String> cf() {
        return cf -> {
            String cfInfo = Objects.requireNonNull(client.info().get(GetInfoRequest.builder().build()).block()).toString();
            LOG.info( cfInfo );
            return cfInfo;
        };
    }

}
