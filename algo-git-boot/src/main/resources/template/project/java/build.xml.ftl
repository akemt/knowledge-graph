<?xml version="1.0"?>
<project name="GaoTest" default="repl" basedir="." xmlns:ivy="antlib:org.apache.ivy.ant">
  <description>
    Algorithmia build file
  </description>

  <!-- Set username / algo properties -->
  <property name="user" value="${username}"/>
  <property name="algo" value="${projectName}"/>

  <!-- Set global properties for this build -->
  <property name="timeout" value="180000"/> <!-- 3 minutes -->

  <!-- Build directories -->
  <property name="source.dir" location="src"/>
  <property name="build.dir"  location="bin"/>
  <property name="lib.dir"    location="lib"/>
  <property name="ivy.dir"    location="ivy"/>
  <property name="doc.dir"    location="doc"/>
  <property name="dist.dir"   location="dist"/>
  <property name="log.dir"    location="logs"/>

  <!-- Maven repository (override for alternate environments (dev, local)) -->
  <property name="repo.url" value="https://git.algorithmia.com"/>

  <!-- JAR file -->
  <property name="jar.file" value="${r'${algo}'}.jar"/>
  <property name="jar.version.file" value="${r'${user}'}-${r'${algo}'}.jar"/>

  <!-- Executables -->
  <property name="run.class" value="algorithmia.Main"/>

  <!-- Initialize -->
  <target name="init">
    <!-- Copy non-source files to build directory -->
    <copy includeemptydirs="false" todir="${r'${build.dir}'}">
      <fileset dir="${r'${source.dir}'}">
        <exclude name="**/*.java"/>
      </fileset>
    </copy>
  </target>

  <!-- Resolve dependencies via Ivy -->
  <target name="resolve" description="Resolve dependencies via Ivy">
    <ivy:resolve/>
    <ivy:cachepath pathid="ivy.classpath"/>
  </target>

  <!-- Compile sources -->
  <target name="compile" depends="init,resolve" description="Compile sources" >
    <!-- Create the build directory structure -->
    <mkdir dir="${r'${build.dir}'}"/>
    <!-- Compile the java code from ${r'${src}'} into ${r'${bin}'} -->
    <javac srcdir="${r'${source.dir}'}" destdir="${r'${build.dir}'}" includeantruntime="false" debug="true">
      <classpath>
        <fileset dir="${r'${lib.dir}'}" includes="*.jar" erroronmissingdir="false"/>
        <path refid="ivy.classpath"/>
      </classpath>
    </javac>
  </target>

  <!-- Resolve dependencies via Ivy, and copy into ivy dir -->
  <target name="stage" depends="compile" description="Resolve dependencies via Ivy">
    <jar jarfile="${r'${dist.dir}'}/${r'${jar.version.file}'}" basedir="${r'${build.dir}'}">
      <manifest>
        <attribute name="Main-Class" value="${r'${run.class}'}" />
      </manifest>
    </jar>
    <ivy:retrieve pattern="${r'${dist.dir}'}/[organisation]/[artifact]/[artifact]-[revision](-[classifier]).[ext]" type="jar,bundle" />
  </target>

  <!-- Generate javadocs for current project into ${r'${doc.dir}'} -->
  <target name="doc" depends="init" description="Generate documentation">
    <javadoc sourcepath="${r'${source.dir}'}" destdir="${r'${doc.dir}'}"/>
  </target>

  <!-- Start an algorithm server -->
  <target name="server" depends="compile" description="Start an algorithm server" >
    <java classname="${r'${run.class}'}" fork="true" failonerror="true">
      <classpath>
        <fileset dir="${r'${lib.dir}'}" includes="*.jar" erroronmissingdir="false"/>
        <path refid="ivy.classpath"/>
        <pathelement location="${r'${build.dir}'}"/>
      </classpath>
      <arg value="server"/>
    </java>
  </target>

  <!-- Start an algorithm read-eval-print-loop -->
  <target name="repl" depends="compile" description="Start an algorithm read-eval-print-loop" >
    <mkdir dir="${r'${log.dir}'}"/>
    <record name="${r'${log.dir}'}/repl.log" emacsmode="true" action="start"/>
    <java classname="${r'${run.class}'}" fork="false" failonerror="true">
      <classpath>
        <fileset dir="${r'${lib.dir}'}" includes="*.jar" erroronmissingdir="false"/>
        <path refid="ivy.classpath"/>
        <pathelement location="${r'${build.dir}'}"/>
      </classpath>
      <arg value="repl"/>
    </java>
    <record name="${r'${log.dir}'}/repl.log" action="stop"/>
  </target>

  <!-- Remove files generated during build -->
  <target name="clean" description="Remove files generated during build" >
    <delete dir="${r'${build.dir}'}"/>
    <delete dir="${r'${doc.dir}'}"/>
    <delete dir="${r'${dist.dir}'}"/>
    <delete dir="${r'${ivy.dir}'}"/>
  </target>

  <!-- Remove build files and clear caches -->
  <target name="clean-all" depends="clean" description="Remove build files and clear caches" >
    <ivy:cleancache/>
  </target>

</project>
