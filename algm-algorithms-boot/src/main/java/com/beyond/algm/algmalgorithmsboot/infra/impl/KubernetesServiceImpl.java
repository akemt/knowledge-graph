package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.infra.DockerService;
import com.beyond.algm.algmalgorithmsboot.infra.KubernetesService;
import com.beyond.algm.exception.AlgException;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void makeK8sPod(String modId, String usrCode, String version) throws AlgException{

        try{

            //创建命名空间
            Namespace namespace = new NamespaceBuilder().withNewMetadata().withName(usrCode.toLowerCase()).and().build();
            client.namespaces().createOrReplace(namespace);

            log.info("k8s namespaces : {}",client.namespaces().list().toString());

            Map<String,String> map = new HashMap<String,String>();
            map.put("app",getUsrCodeModIdVersion(modId, version));

            ReplicationController replicationController = new ReplicationControllerBuilder()
                    .withApiVersion("v1")
                    .withKind("ReplicationController")
                    .withNewMetadata()
                        .withLabels(map)
                        .withName(getUsrCodeModIdVersion(modId, version))
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
                                .addNewImagePullSecret("myregistrykey")
                                .addToImagePullSecrets(new LocalObjectReferenceBuilder().withName("myregistrykey").build())
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
        map.put("app",getUsrCodeModIdVersion(modId, version));
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

    private String getUsrCodeModIdVersion(String modId, String version){
        return modId.toLowerCase()+"-"+version;
    }

    /**
     *  因为 k8s service 命名规则不允许 . 此处把 . 替换成 -
     * @param modId
     * @param version
     * @return
     */
    private String getServiceName(String modId, String version){
        return modId.toLowerCase()+"-"+version.replaceAll(".","-");
    }

}
