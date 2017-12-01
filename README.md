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

- Request API
	1. Request token
		* URL : http://localhost:8181/admin/v1/security/request-token
		* HTTP Method = POST
		* Header Content-Type : "application/json" / "application/xml"
		* Request body : {"user":"ariariari","token":"98253668-476e-47c9-bf7a-05aaad45bf18"}
		* Response format : JSON / XML
		* Response body : {"sessionToken": "46C9FA43D36905106BD03BC89C93EF33", "csrfToken": "63b71d2a-b695-49f9-bf32-9fdd784b6fa3"}
	2. Auth token 
		* URL : http://localhost:8181/login
		* HTTP Method = POST
		* Header cookie : e.g JSESSIONID=46C9FA43D36905106BD03BC89C93EF33; XSRF-TOKEN=3b71d2a-b695-49f9-bf32-9fdd784b6fa3
		* Header X-XSRF-TOKEN : e.g 3b71d2a-b695-49f9-bf32-9fdd784b6fa3
		* Request body form-data e.g: 
			* username:ari
			* password:1234 
		* Response :
			* cookies JSESSIONID: e.g 1B2898B70D99DC13F54DAE4A9CF078D6
			* XSRF-TOKEN : e.g a7055b90-a035-420b-8674-275fa8b55eda
	3. API
		* E.g 1 :
			* URL e.g : http://localhost:8181/admin/v1/api/user/editUser
			* HTTP Method = POST
			* Header Content-Type : application/json / "application/xml"
			* Header cookie : e.g JSESSIONID=1B2898B70D99DC13F54DAE4A9CF078D6; XSRF-TOKEN=a7055b90-a035-420b-8674-275fa8b55eda
			* Header X-XSRF-TOKEN : e.g a7055b90-a035-420b-8674-275fa8b55eda
			* Request body : {"username":"ari","name":"asasa","email":"prasetiyooo@gmail.com","noHp":"085645480401","roles":[{"roleName":"admin","id":"1"}],"isActive":true,"password":"dummay","id":"1"}
		* E.g 2 :
			* URL e.g : http://localhost:8181/admin/v1/api/user/list
			* HTTP Method = POST
			* Header cookie : e.g JSESSIONID=1B2898B70D99DC13F54DAE4A9CF078D6; XSRF-TOKEN=a7055b90-a035-420b-8674-275fa8b55eda
			* Header X-XSRF-TOKEN : e.g a7055b90-a035-420b-8674-275fa8b55eda
			* Request orm-data e.g : 
				* limit:10
				* offset:0
```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v1.5.7.RELEASE)
```