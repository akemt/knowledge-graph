package com.beyond.algm.algmalgorithmsboot.infra.impl;

import com.beyond.algm.algmalgorithmsboot.infra.MvnService;
import com.beyond.algm.exception.AlgException;
import org.apache.maven.shared.invoker.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;

/**
 * @Author: qihe
 * @Description:
 * @Date: create in 2017/11/23 11:03
 */
@Service
public class MvnServiceImpl implements MvnService {

    public void mvnPackageMod() throws AlgException{
        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile( new File( "D:/IdeaProjects/algmarket-publish/pom.xml" ) );
        request.setGoals( Arrays.asList( "clean", "package -Dmaven.test.skip=true"));

        Invoker invoker = new DefaultInvoker();
        invoker.setMavenHome(new File(System.getenv("MAVEN_HOME")));
        try {
            invoker.execute(request);
            InvocationResult result = invoker.execute( request );

            if ( result.getExitCode() != 0 )
            {
                throw new IllegalStateException( "Build failed." );
            }
        } catch (MavenInvocationException e) {
            e.printStackTrace();
        }
    }
}
