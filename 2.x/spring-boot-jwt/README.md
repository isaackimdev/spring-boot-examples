# Spring boot jwt

project config
![start](./image/start.png)


Dependencies
```
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    compileOnly 'org.projectlombok:lombok'
    developmentOnly 'org.springframework.boot:spring-boot-devtools'
    annotationProcessor 'org.projectlombok:lombok'
    testImplementation 'org.springframework.boot:spring-boot-starter-test'
	
    implementation 'io.jsonwebtoken:jjwt:0.9.1'
    implementation 'javax.xml.bind:jaxb-api:2.3.1'
}
```