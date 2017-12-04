package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.infra.KubernetesService;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.NamespaceBuilder;
import io.fabric8.kubernetes.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in 2017/12/1 16:16
 */
@Slf4j
@Service
public class KubernetesServiceImpl implements KubernetesService {

    public void makeK8sPod(){
        String master = "http://192.168.1.60:8080/";

        Config config = new ConfigBuilder().withMasterUrl(master).build();
        try (final KubernetesClient client = new DefaultKubernetesClient(config)) {

            System.out.println(
                    client.namespaces().list()
            );
            Namespace namespace = new NamespaceBuilder().withNewMetadata().withName("erniu4").and().build();
            client.namespaces().createOrReplace(namespace);
            System.out.println(
                    client.namespaces().list()
            );
        } catch (KubernetesClientException e) {
            log.error(e.getMessage(), e);
        }
    }

}
