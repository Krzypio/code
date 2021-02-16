Po zmianie lokalizacji projektu:
Usuń folder .idea oraz plik .iml, zaimportuj powtórnie projekt do intelij.

Zmiany w stosunku do kodu w książce:
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(KnightConfig.class);
        Knight knight = (Knight) context.getBean("knight");

Zmiany w pom.xml:

    <properties>
        <maven.compiler.source>12</maven.compiler.source>
        <maven.compiler.target>12</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>3.7.7</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>5.3.3</version>
        </dependency>
    </dependencies>

