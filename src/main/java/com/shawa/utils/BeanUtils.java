package com.shawa.utils;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;
import com.shawa.ShawaUDTCodec;
import org.springframework.context.annotation.Bean;

import java.net.InetSocketAddress;

public class BeanUtils {

    @Bean
    public CqlSession cqlSession() {
        CqlSession session = CqlSession.builder()
                .addContactPoint(new InetSocketAddress("127.0.0.1", 9042)) // Replace with your Cassandra endpoint
                .withLocalDatacenter("datacenter1")
//                .withAuthProvider(DriverConfigLoader.fromClasspath)
                .withKeyspace("shawacloud")
//                .addTypeCodecs(ShawaUDTCodec)
                .build();
        return session;
    }
    // Build a CqlSession with the custom codec registered
}

