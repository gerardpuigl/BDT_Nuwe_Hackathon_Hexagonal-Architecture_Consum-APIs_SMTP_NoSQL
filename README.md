# Reto backend Hackathon MWC done by Nuwe & BDT

Desde cliente nos han pedido poder hacer una implementación de varias APIs y poder simplificar la respuesta y el eso de estas.

El cliente quiere poder guardar los datos de sus usuarios y de las cuentas de Github, Gitlab y además poder comunicarse con ellos a través de un sistema de mensajería SMTP.

El arquitecto propone el siguiente esquema:

Markup : <details>
           <summary>VER ESQUEMA</summary>
            <p>
               <img src="https://github.com/gerardpuigl/BDT_Hackathon_MWC/blob/main/schema/ArchitectureSchema.jpg" alt="java" title="java" width=100%/>
             </p>
         </details>

----

# Deployment
https://gerardpuigbdthackathonmwc.herokuapp.com/

# Testing

En la carpeta Postman de este repositorio encontrara una colección completa de peticiones Postman y cuatro entornos para probar la aplicación en local o la desplegada en heroku y con las credenciales de autenticación correctas e incorrectas para testear la seguridad.

Si quieres provar la aplicación en local debes canviar el perfil de la base de datos en "application.properties" a "mongodb-local". Y tener una base de datos de mongodb corriendo en el  "localhost:27017" o configurar-la correctament.

Disfurta de esta API.

### User taks | 

- **TASK1:** Puedo acceder a la api a través de: "http://localhost:3000" [DONE]
- **TASK2:** Contiene los siguientes end points:
    - **ST1:** *GET* /user [DONE]
        - **Descripción**: Obtienes el objeto entero de un usuario
    - **ST2:** *DELETE* /user/:id [DONE]
        - **Descripción**: Se puede borrar objeto user a través de su ID
    - **ST3:** *PUT* /user/:id [DONE]
        - **Descripción**: Se puede actualizar un usuario a través de su ID
    - **ST4:** *POST* /user [DONE]
        - **Descripción**: Crea un usuario 
    - **ST5:** *GET* /user/:id/gitlab [DONE]
        - **Description**: Devuele los datos de usuario del modelo de githubUser entrando dándo el nombre de usuario gitlab
    - **ST6:** *GET* /user/:id/github [DONE]
        - **Description**: Devuele los datos de usuario del modelo de githubUser entrando dándo el nombre de usuario github    
    - **ST7:** *POST* /github/:username [DONE]
        - **Descripción**: Crea un GithubUserl, lo conecta con un usuario previamante creado a través de su id y guarda el User
    - **ST8:** *POST* /gitlab/:username [DONE]
        - **Descripción**: Crea un GitlabUser, lo conecta con un usuario previamante creado a través de su id y guarda el User
    - **ST9:** *GET* /countries [DONE]
        - **Description**: Devuele la lista de paises donde los parámetros que devuelo son los que se muestran en el diseño de la arquitectura
    - **ST10:** *POST* /register [IN PROGRESS]
        - **Description:** Registra al usuario y lo, guarda la sesión usando una estratégia local y envía la información del usuario. En este proceso a través de la función **TASK3** para verficiar el correo introducido.
    - **ST11:** *GET* /login [DONE]
        - **Description:** Guarda la sesión usando una estratégia local y envía la información al usuario
    - **ST12** *GET* /notification [IN PROGRESS]
        - **Descripción:** Envía un mensaje de notificación standar al usuario utilizando el config del **TASK6**
- **TASK3:** Generar un servicio que utilice la API de [Mailboxlayer](https://mailboxlayer.com/) y permita para verificar el correo de un usuario, tanto si tiene el formato de correo cómo si tiene un servidor existente asignado al dominio utilizado.   [IN PROGRESS]
- **TASK4:** Generar una función que al cargar el servidor coja todos los datos de la API de [RESTCountries](https://restcountries.eu/) y genere la lista de Paises en el backend que únicamente contenga los datos necesarios para el cliente (ver imagen) [DONE]
- **TASK5:** Crear un método de AUTH Local para guardar la sesión del usuario. [DONE]
- **TASK6:** Configurar un método de SMTP para poder enviar correos de forma automática a los usuarios. [IN PROGRESS]
- **TASK7:** Configurar PostgreSQL como posible BDD [IN PROGRESS]
- **TASK8:** Condigurar MongoDB como BDD principal. [DONE]

----

### DOD | Definition of Done

- **DOD-1:** Se ha hecho deploy de la API [DONE]
- **DOD-2:** Se han testado los diferentes endpoints de alguna forma: Testing (Unit, Integracióin, E2E) o Postman/Inmsomia [DONE]
- **DOD-3:** Tiene que estar desarrollado en NodeJS o Java [DONE]

---

### Reglas y recomendaciones 

- Si se detectan posibles plagios y copias se descalificará automáticamente a la persona
- Recomendable utilizar clean code y clean architecture


### Recursos
- [Naming Cheatsheet](https://github.com/gagocarrilloedgar/naming-cheatsheet)
- [RESTCountries](https://restcountries.eu/)
- [Mailboxlayer](https://mailboxlayer.com/)
- [JavaMail](https://javaee.github.io/javamail/)
- [GitHub API](https://docs.github.com/es/rest)
- [Gitlab API](https://docs.gitlab.com/ee/api/)
- [NodeMailer](https://nodemailer.com/about/)
- [Clean Architecture | CodelyTV](https://www.youtube.com/watch?v=y3MWfPDmVqo)
- [Arquitectura Hexagonal | CodelyTV](https://www.youtube.com/watch?v=GZ9ic9QSO5U)
- **Providers para hacer deploy:**
    - [Vercel](https://vercel.com/)
    - [Heroku](https://www.heroku.com/)
    - [Azure](https://azure.microsoft.com/es-es/)
    - [Digital Ocean](https://www.digitalocean.com/)
    - [OVH Cloud](https://www.ovh.es/)
    - [Google Cloud](https://cloud.google.com/)
