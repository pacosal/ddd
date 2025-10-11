# Arquitectura hexagonal

Adaptación del código fuente aqui comentado https://medium.com/idealo-tech-blog/hexagonal-ports-adapters-architecture-e3617bcf00a0
y sito en https://github.com/tugcekonuklar/account-service para adecuarlo mejor a una arquitectura hexagonal sin que ni la capa de Domain
ni la capa de Application tengan ninguna referencia a datos de infraestructura ni a ninguna clase de Springboot.

Se establecen las 3 capas típicas de arquitectura hexagonal:

## Domain

- Clases de negocio
- Port (interfaz) que la capa Application debe implementar (Adapter)

## Application

- Casos de uso del negocio
- Port (interfaz) de repositorio que la capa Infraestructure debe implementar (Adapter)

## Infraestructure

- Implementa los repositorios de la clase de Application
- Utiliza Springboot
- Implementa el API de acceso a la capa Application y por tanto a los casos de uso

## Compilación
Cada capa debe poder ser compilada, solamente teniendo en cuenta las dependencias de otras. La de dominio ella sola.

El comando de compilación para:

Domain

C:\ddd\src\main\java>javac -d c:/temporal/classes/ -cp .;c:/temporal/lib/lombok.jar;c:/temporal/lib/validation.jar com/pacosal/bank/domain/*.java com/pacosal/bank/domain/command/*.java

C:\temporal\classes>jar -cvf c:/temporal/lib/domain.jar ./

Application

C:\ddd\src\main\java>javac -d c:/temporal/classes/ -cp .;c:/temporal/lib/lombok.jar;c:/temporal/lib/validation.jar;c:/temporal/lib/slf4j.jar;c:/temporal/lib/domain.jar com/pacosal/bank/application/*.java com/pacosal/bank/application/command/*.java com/pacosal/bank/application/error/*.java

Jar con domain y application

C:\temporal\classes>jar -cvf c:/temporal/lib/domain_application.jar ./

Infraestructure

Tiene muchas subcarpetas y hay que incluir cada una de ellas en el comando. Sería algo así:

C:\ddd\src\main\java>javac -d c:/temporal/classes/ -cp .;c:/temporal/lib/lombok.jar;c:/temporal/lib/validation.jar;c:/temporal/lib/slf4j.jar;c:/temporal/lib/domain_application.jar com/pacosal/bank/infraestructure/*.java ++++carpetas


## swagger

http://localhost:8080/swagger-ui.html

