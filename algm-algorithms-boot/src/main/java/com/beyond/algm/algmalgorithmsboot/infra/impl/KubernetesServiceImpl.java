package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.infra.DockerService;
import com.beyond.algm.algmalgorithmsboot.infra.KubernetesService;
import com.beyond.algm.exception.AlgException;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in 2017/12/1 16:16
 */
@Slf4j
@Service
public class KubernetesServiceImpl implements KubernetesService {

    @Autowired
    KubernetesClient client;

    @Autowired
    private DockerService dockerService;

    @Value("${k8s.secretKey}")
    private String secretKey;

    public void makeK8sNamespace(String usrCode){
        //创建命名空间
        Namespace namespace = new NamespaceBuilder().withNewMetadata().withName(usrCode.toLowerCase()).and().build();
        client.namespaces().createOrReplace(namespace);
        log.debug("k8s namespaces : {}",client.namespaces().list().toString());
    }

    public void makeK8sPod(String modId, String usrCode, String version) throws AlgException{

        try{
            Map<String,String> map = new HashMap<String,String>();
            map.put("app",getUsrCodeModIdVersion(modId, usrCode, version));

            ReplicationController replicationController = new ReplicationControllerBuilder()
                    .withApiVersion("v1")
                    .withKind("ReplicationController")
                    .withNewMetadata()
                    .withLabels(map)
                    .withName(getUsrCodeModIdVersion(modId, usrCode, version))
                    .withNamespace(usrCode.toLowerCase())
                    .endMetadata()
                    .withNewSpec()
                    .withReplicas(1)
                    .withSelector(map)
                    .withNewTemplate()
                    .withNewMetadata()
                    .withLabels(map)
                    .endMetadata()
                    .withNewSpec()
                    .addNewContainer()
                    .withName(usrCode.toLowerCase())
                    .withImage(dockerService.getDockerTag(modId,usrCode,version))
                    .withImagePullPolicy("Always")
                    .withPorts(new ContainerPortBuilder().withContainerPort(8080).build())
                    .endContainer()
                    .addNewImagePullSecret(secretKey)
                    .addToImagePullSecrets(new LocalObjectReferenceBuilder().withName(secretKey).build())
                    .endSpec()
                    .endTemplate()
                    .endSpec()
                    .build();
            client.replicationControllers().delete(replicationController);
            client.replicationControllers()
                    .createOrReplace(replicationController);
        } catch (KubernetesClientException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void makeK8sService(String modId, String usrCode, String version) throws AlgException{
        Map<String,String> map = new HashMap<String,String>();
        map.put("app",getUsrCodeModIdVersion(modId, usrCode, version));
        try {
            io.fabric8.kubernetes.api.model.Service service = new ServiceBuilder()
                    .withKind("Service")
                    .withApiVersion("v1")
                    .withNewMetadata()
                    .withLabels(map)
                    .withName(getServiceName(modId, version))
                    .withNamespace(usrCode.toLowerCase())
                    .endMetadata()
                    .withNewSpec()
                    .withType("NodePort")
                    .withPorts(new ServicePortBuilder().withPort(80).withTargetPort(new IntOrStringBuilder().withIntVal(8080).build()).build())
                    .withSelector(map)
                    .endSpec()
                    .build();
            client.services().delete(service);
            client.services().createOrReplace(service);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            e.printStackTrace();
        }

    }

    public void makeK8sSecretForNamespace(String usrCode) throws AlgException{
        Map<String,String> data = new HashMap<String,String>();
        data.put(".dockerconfigjson","ewoJImF1dGhzIjogewoJCSIxOTIuMTY4LjEuOTI6OTQ0MyI6IHsKCQkJImF1dGgiOiAiWVdSdGFXNDZTR0Z5WW05eU1USXpORFU9IiwKCQkJImVtYWlsIjogIiIKCQl9Cgl9Cn0K");
        Secret secret = new SecretBuilder()
                .withApiVersion("v1")
                .withKind("Secret")
                .withNewMetadata()
                .withName(secretKey)
                .withNamespace(usrCode.toLowerCase())
                .endMetadata()
                .withData(data)
                .withType("kubernetes.io/dockerconfigjson")
                .build();
        client.secrets().createOrReplace(secret);
    }

    private String getUsrCodeModIdVersion(String modId, String usrCode, String version){
        return usrCode.toLowerCase()+"-"+modId.toLowerCase()+"-"+version;
    }

    /**
     *  因为 k8s service 命名规则不允许 . 此处把 . 替换成 -
     * @param modId
     * @param version
     * @return
     */
    private String getServiceName(String modId, String version){
        return modId.toLowerCase()+"-"+version.replaceAll("\\.","-");
    }

}
