#Arquitectura hexagonal

Adaptación del código fuente aqui comentado https://medium.com/idealo-tech-blog/hexagonal-ports-adapters-architecture-e3617bcf00a0
y sito en https://github.com/tugcekonuklar/account-service para adecuarlo mejor a una arquitectura hexagonal sin que ni la capa de Domain
ni la capa de Application tengan ninguna referencia a datos de infraestructura ni a ninguna clase de Springboot.

Se establecen las 3 capas típicas de arquitectura hexagonal:

##Domain

- Clases de negocio
- Port (interfaz) que la capa Application debe implementar (Adapter)

##Application

- Casos de uso del negocio
- Port (interfaz) de repositorio que la capa Infraestructure debe implementar (Adapter)

##Infraestructure

- Implementa los repositorios de la clase de Application
- Utiliza Springboot
- Implementa el API de acceso a la capa Application y por tanto a los casos de uso



