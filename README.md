**Proyecto base para automatizacion de pruebas con Serenity Screenplay**


Proyecto inicial para comenzar a automatizar pruebas con la libreria Serenity utilizando el patron de diseño Screenplay utilizando versiones actualizadas a Serenity 4.2.15
y gradle 8.10

**Documentacion oficial**

Writting User-centric tests using Serenity Screenplay

https://serenity-bdd.github.io/theserenitybook/latest/serenity-screenplay.html

Uso de version con Cucumber 7.20.1

https://github.com/serenity-bdd/serenity-core


**Antes de iniciar**

1. Revisar archivos base

    settings.gradle
    build.gradle
    serenity.conf

Completar tasks TODO que deben actualizarse para cada cliente (nombre del proyecto, uso de dependencias, actualizacion de versiones de gradle)

2. Renombrar paquetes co.com.client.project a acoplarse con cliente y proyecto

3. Eliminar carpeta .git antes de subir cambios al repositorio del cliente 

4. Verificar configuracion correcta de drivers en archivo serenity.conf src/test/resources/serenity.conf

5. Actualizar README.md 

**Paquetes**

libs - inclusion de .jar propios en el proyecto
ci - responsabilidad de almacenar jenkinsfile o yml files para CI/CD
gradle - ubicacion del gradle wrapper
src - arquitectura de automatizacion
target - reportes de pruebas


