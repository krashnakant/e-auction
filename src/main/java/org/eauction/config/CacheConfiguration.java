package org.eauction.config;

import io.github.jhipster.config.JHipsterProperties;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;
import org.ehcache.jsr107.Eh107Configuration;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
@AutoConfigureAfter(value = { MetricsConfiguration.class })
@AutoConfigureBefore(value = { WebConfigurer.class, DatabaseConfiguration.class })
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(Expirations.timeToLiveExpiration(Duration.of(ehcache.getTimeToLiveSeconds(), TimeUnit.SECONDS)))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache("users", jcacheConfiguration);
            cm.createCache(org.eauction.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(org.eauction.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(org.eauction.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(org.eauction.domain.SocialUserConnection.class.getName(), jcacheConfiguration);
            cm.createCache(org.eauction.domain.Item.class.getName(), jcacheConfiguration);
            cm.createCache(org.eauction.domain.Item.class.getName() + ".itemAttributes", jcacheConfiguration);
            cm.createCache(org.eauction.domain.Category.class.getName(), jcacheConfiguration);
            cm.createCache(org.eauction.domain.Category.class.getName() + ".subCategories", jcacheConfiguration);
            cm.createCache(org.eauction.domain.SubCategory.class.getName(), jcacheConfiguration);
            cm.createCache(org.eauction.domain.SubCategory.class.getName() + ".attributes", jcacheConfiguration);
            cm.createCache(org.eauction.domain.Attribute.class.getName(), jcacheConfiguration);
            cm.createCache(org.eauction.domain.ItemAttribute.class.getName(), jcacheConfiguration);
            cm.createCache(org.eauction.domain.Sale.class.getName(), jcacheConfiguration);
            cm.createCache(org.eauction.domain.Bid.class.getName(), jcacheConfiguration);
            cm.createCache(org.eauction.domain.Sale.class.getName() + ".items", jcacheConfiguration);
            cm.createCache(org.eauction.domain.Sale.class.getName() + ".accounts", jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
