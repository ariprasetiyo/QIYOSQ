# QIYOSQ
Create QIYOSQ by your self

- Run this application with apache maven
- Run test with command : 
mvn -DTest:SampleClass test, example : mvn -DTest:InitDataTest test

- Run application : 
mvn spring-boot:run

- Compile and run application with skip test :
mvn clean package spring-boot:run -DskipTests

- Run application with skip test and remote debug :
mvn spring-boot:run -DskipTests -Drun.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005"

- Access link url :
http://localhost:8181/login

- Git ignore file mode :
git config core.fileMode false

- Root folder
	* webapp
		* admin
		* pages
			* v1
				* pages
				* spa
					* templates
					* js e.g -> "/admin/v1/spa/js/sammy.template.js"
		* login
		* resources ( public )
			* boostrap
			* dist
			* plugis

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.5.7.RELEASE)
```