# Hackathon MWC Reto backend by Nuwe & BDT

Desde cliente nos han pedido poder hacer una implementación de varias APIs y poder simplificar la respuesta.

El cliente quiere poder guardar los datos de sus usuarios y de las cuentas de Github, Gitlab y además poder comunicarse con ellos a través de un sistema de mensajería SMTP.

El arquitecto propone el siguiente esquema:

## VER ESQUEMA
<p>
        <img src="https://github.com/gerardpuigl/BDT_Hackathon_MWC/blob/main/schema/ArchitectureSchema.jpg" alt="java" title="java" width=100%/>
 </p>
 ----

## Tecnologias utilizadas
- JAVA
- SPRING
- MONGODB

## APIS implementadas

- [RESTCountries](https://restcountries.eu/)
- [Mailboxlayer](https://mailboxlayer.com/)
- [GitHub API](https://docs.github.com/es/rest)
- [Gitlab API](https://docs.gitlab.com/ee/api/)

## URL
- [DEMO](https://gerardpuigbdthackathonmwc.herokuapp.com/)

 ----

# Endopoints


## Register New User
- **Descripción**: Registra al usuario y lo guarda, devuelve un Token JWT. Se gestionan la sesión usando una estratégia remota enviando y recibiendo JWT.
- **AUTH** : No necesita authenticación
- **Method & Path**: `[POST] /register `
- **REQUEST BODY**

ALL information:
```javascript
{
    "name":"TestUser03",
    "username":"User07",
    "email":"example@gmail.com",
    "isEmailVerified":true,
    "password":"hackathonMWC",
    "gitUserId":"1",
    "githubUserId": "gagocarrilloedgar",
    "gitlabUserId": "jack_smith",
    "countryId":"ES"
}
```
MANDATORY information:
```javascript
{
    "username":"User03",
    "email":"example@gmail.com",
    "password":"hackathonMWC"
}
```

<details>
    <summary>More information</summary>
           
- **SUCCESS RESPONSE: [Status = 200]**
           
```javascript
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJVc2VyMDciLCJleHAiOjE2MjQzMTY2MTAsImlhdCI6MTYyNDI4MDYxMH0.iEDq_LMvWvqkyXXZjgy4zfeIsfXpauPm0FlQLPoKUfVOqRsor8hCdn5f5a1l8b4qr6H7hy8NcrVbRpZ6W47fZg"
}
```

- **ERROR RESPONSE: [Status = 422]**
```postman
    The user cannot be created.
    rawPassword cannot be null
```
```postman
    The user cannot be created.
    The email: null hasn't valid format
```
```postman
    The user cannot be created.
    The email: aaa@aaa.com domain don't exist
```

</details>

## Login
- **Descripción**: Registra al usuario y lo, guarda la sesión usando una estratégia remota y envía la información del usuario en JWT.
- **AUTH** : No necesita authenticación
- **Method & Path**: `[POST] /login `
- **REQUEST BODY**
```javascript
{
    "username":"{{username}}",
    "password":"{{password}}"
}
```
           
<details>
    <summary>More information</summary>
           
- **SUCCESS RESPONSE: [Status = 200]** 
```javascript
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJVc2VyMDciLCJleHAiOjE2MjQzMTY2MTAsImlhdCI6MTYyNDI4MDYxMH0.iEDq_LMvWvqkyXXZjgy4zfeIsfXpauPm0FlQLPoKUfVOqRsor8hCdn5f5a1l8b4qr6H7hy8NcrVbRpZ6W47fZg"
}
```

- **ERROR RESPONSE: [Status = 401]**
```postman
{
    "timestamp": 1624280812684,
    "status": 401,
    "error": "Unauthorized",
    "trace":

    etc.
}
```
           
</details>

## Respuesta para los enpoinds si falla la autenticación:

- **ERROR REPONSE: 401**
```
{
    "timestamp": xxxxxxxxxx,
    "status": 401,
    "error": "Unauthorized",
    "message": "Unauthorized",
    "path": "/xxxx/xxx"
}
```

## Get user by id
- **Descripción**: Obtiene el objeto entero de un usuario
- **AUTH** : Necesitas enviar un Jwt Token en el header. Solo puedes obtener información de tu propio usuario.
- **Method & Path**: `[GET] /user/:id `
- **Path Parameter**: `id=[String]`

<details>
    <summary>More information</summary>
           
- **SUCCESS RESPONSE: [Status = 200]** 
```javascript
{
    "id": "60c4b7968c71c14b521ed76b",
    "name": "TestUser02",
    "username": "User02",
    "email": "TestUser02@nuwe.io",
    "isEmailVerified": true,
    "githubUserId": "gerardpuigl",
    "gitlabUserId": "jack_smith",
    "countryId": "ES"
}
```
- **ERROR RESPONSE: [Status = 422]**
```postman
    The user cannot be found.
    The Jwt User and id sent don't match
```
</details>

## Update user by id
- **Descripción**: Se puede actualizar un usuario a través de su ID
- **AUTH** : Necesitas enviar un Jwt Token en el header. Solo puedes actualizar tu propio usuario.
- **Method & Path**: `[PUT] /user/:id `
- **Path Parameter**: `id=[String]`
- **REQUEST BODY** 
```javascript
{
    "id": "60c4b7968c71c14b521ed76b",
    "name": "UpdateName02",
    "username": "User02",
    "email": "TestUser02@nuwe.io",
    "isEmailVerified": true,
    "password":"hackathonMWC",
    "githubUserId": "gerardpuigl",
    "gitlabUserId": "",
    "countryId": "ES"
}
```
           
<details>
    <summary>More information</summary>

- **SUCCESS RESPONSE: [Status = 200]** 
```javascript
{
    "id": "60c4b7968c71c14b521ed76b",
    "name": "TestUser02",
    "username": "User02",
    "email": "TestUser02@nuwe.io",
    "isEmailVerified": true,
    "githubUserId": "gerardpuigl",
    "gitlabUserId": "jack_smith",
    "countryId": "ES"
}
```
- **ERROR RESPONSE: [Status = 422]**
```postman
    The user cannot be updated.
    The id in the path is different from id defined inside the Json
```

</details>

## Create user by id
- **Descripción**: Crea un usuario
- **AUTH** : Necesitas enviar un Jwt Token en el header.
- **Method & Path**: `[POST] /user/:id `
- **Path Parameter**: `id=[String]`
- **REQUEST BODY**

MANDATORY information:
```javascript
{
    "username":"User03",
    "email":"example@gmail.com",
    "password":"hackathonMWC"
}
```

ALL information:
```javascript
{
    "name":"TestUser03",
    "username":"User03",
    "email":"example@gmail.com",
    "isEmailVerified":true,
    "password":"hackathonMWC",
    "gitUserId":"1",
    "githubUserId": "gerardpuigl",
    "gitlabUserId": "jack_smith",
    "countryId":"ES"
}
```

<details>
    <summary>More information</summary>

- **SUCCESS RESPONSE: [Status = 200]** 
```javascript
{
    "id": "60d0889332cf106edb17ac13",
    "name": "TestUser03",
    "username": "User03",
    "email": "example@gmail.com",
    "isEmailVerified": true,
    "githubUserId": "gerardpuigl",
    "gitlabUserId": "jack_smith",
    "countryId": "ES"
}
```
or
```javascript
{
    "id": "60d0899e32cf106edb17ac16",
    "username": "User09",
    "email": "example@gmail.com",
    "isEmailVerified": false
}
```
- **ERROR RESPONSE: [Status = 422]**
```postman
    The user cannot be updated.
    The id in the path is different from id defined inside the Json
```
```postman
    The user cannot be created.
    rawPassword cannot be null
```
```postman
    The user cannot be created.
    The email: null hasn't valid format
```
```postman
    The user cannot be created.
    The email: aaa@aaa.com domain don't exist
```

</details>

## Delete user by id
- **Descripción**: Se puede borrar objeto user a través de su ID
- **AUTH** : Necesitas enviar un Jwt Token en el header. Solo puedes eliminar tu propio usuario.
- **Method & Path**: `[DELETE] /user/:id `
- **Path Parameter**: `id=[String]`
           
<details>
    <summary>More information</summary>

- **SUCCESS RESPONSE: [Status = 200]** 
```postman
    User deleted correctly.
```
- **ERROR RESPONSE: [Status = 422]**
```postman
    The user cannot be found.
    The Jwt User and id sent don't match
```

</details>

## Get Gitlab user info:
- **Descripción**: Devuele los datos de usuario del modelo de githubUser entrando dándo el nombre de usuario gitlab
- **AUTH** : Necesitas enviar un Jwt Token en el header. Solo puedes obtener información de tu propio usuario.
- **Method & Path**: `[GET] /user/:id/gitlab `
- **Path Parameter**: `id=[String]`

<details>
    <summary>More information</summary>
           
- **SUCCESS RESPONSE: [Status = 200]** 
```javascript
{
    "id": "5023502",
    "name": "Jack Smith ",
    "web_url": "https://gitlab.com/jack_smith",
    "repositoriesURL": "https://gitlab.com/users/jack_smith/projects",
    "repositories": [
        {
            "id": 15532034,
            "name": "My Awesome Project",
            "description": "This is my Test Project ",
            "web_url": "https://gitlab.com/jack_smith/my-awesome-project"
        }
    ]
}
```
- **ERROR RESPONSE: [Status = 422]**
```postman
    The user cannot be found.
    The Jwt User and id sent don't match
```
```postman
    No user found with id: 
    60c4b7968c71c14b521ed76b403 Forbidden from GET https://gitlab.com/api/v4/users?username=
```

</details>

## Get Github user info:
- **Descripción**: Devuele los datos de usuario del modelo de githubUser entrando dándo el nombre de usuario github  
- **AUTH** : Necesitas enviar un Jwt Token en el header. Solo puedes obtener información de tu propio usuario.
- **Method & Path**: `[GET] /user/:id/github `
- **Path Parameter**: `id=[String]`

           
<details>
    <summary>More information</summary>

- **SUCCESS RESPONSE: [Status = 200]** 
```javascript
{
    "id": "72300632",
    "name": "Gerard Puig",
    "url": "https://api.github.com/users/gerardpuigl",
    "repos_url": "https://api.github.com/users/gerardpuigl/repos",
    "repositories": [
        {
            "id": 376239834,
            "name": "BDT_Hackathon_MWC",
            "description": null,
            "html_url": "https://github.com/gerardpuigl/BDT_Hackathon_MWC"
        },
    ...etc
```
- **ERROR RESPONSE: [Status = 422]**
```postman
    The user cannot be found.
    The Jwt User and id sent don't match
```
```postman
    404 Not Found from GET https://api.github.com/users/
```

</details>

## Post Gitlab user into User
- **Descripción**: Crea un GithubUserl, lo conecta con un usuario previamante creado a través de su id y guarda el User
- **AUTH** : Necesitas enviar un Jwt Token en el header. Solo puedes actualizar tu propio usuario.
- **Method & Path**: `[POST] /user/:id/gitlab/:username `
- **Path Parameter**: `id=[String] & username=[String]`

<details>
    <summary>More information</summary>

- **SUCCESS RESPONSE: [Status = 200]** 
```javascript
{
    "id": "5023502",
    "name": "Jack Smith ",
    "web_url": "https://gitlab.com/jack_smith",
    "repositoriesURL": "https://gitlab.com/users/jack_smith/projects",
    "repositories": [
        {
            "id": 15532034,
            "name": "My Awesome Project",
            "description": "This is my Test Project ",
            "web_url": "https://gitlab.com/jack_smith/my-awesome-project"
        }
    ]
}
```
- **ERROR RESPONSE: [Status = 422]**
```postman
    The user cannot be found.
    The Jwt User and id sent don't match
```
```postman
    No user found with id: 
    60c4b7968c71c14b521ed76b403 Forbidden from GET https://gitlab.com/api/v4/users?username=
```

</details>

## Post Github user into User
- **Descripción**: Crea un GitlabUser, lo conecta con un usuario previamante creado a través de su id y guarda el User
- **AUTH** : Necesitas enviar un Jwt Token en el header. Solo puedes actualizar tu propio usuario.
- **Method & Path**: `[POST] /user/:id/gitlab/:username `
- **Path Parameter**: `id=[String] &
 username=[String]`

<details>
    <summary>More information</summary>

- **SUCCESS RESPONSE: [Status = 200]** 
```javascript
{
    "id": "72300632",
    "name": "Gerard Puig",
    "url": "https://api.github.com/users/gerardpuigl",
    "repos_url": "https://api.github.com/users/gerardpuigl/repos",
    "repositories": [
        {
            "id": 376239834,
            "name": "BDT_Hackathon_MWC",
            "description": null,
            "html_url": "https://github.com/gerardpuigl/BDT_Hackathon_MWC"
        },
    ...etc
```
- **ERROR RESPONSE: [Status = 422]**
```postman
    The user cannot be found.
    The Jwt User and id sent don't match
```
```postman
    404 Not Found from GET https://api.github.com/users/
```

</details>

## Country List:
- **Descripción**: Devuele la lista de paises donde los parámetros que devuelo son los que se muestran en el diseño de la arquitectura
- **AUTH** : Necesitas enviar un Jwt Token en el header.
- **Method & Path**: `[GET] /countries`

<details>
    <summary>More information</summary>
           
- **SUCCESS RESPONSE: [Status = 200]** 
```javascript
{
[
    {
        "id": "AF",
        "name": "Afghanistan",
        "alpha2Code": "AF",
        "alpha3Code": "AFG",
        "callingCodes": [
            "93"
        ]
    },
...etc
```
- **ERROR RESPONSE: [Status = 422]**
```postman
    The user cannot be found.
    The Jwt User and id sent don't match
```
```postman
    404 Not Found from GET https://api.github.com/users/
```

</details>

## Notification to User by Id
- **Descripción**: Envía un email de notificación standar al usuario 
- **AUTH** : No necesita authenticación
- **Method & Path**: `[POST] /user/:id/notification `
- **Path Parameter**: `id=[String]`
           
<details>
    <summary>More information</summary>
           
- **SUCCESS RESPONSE: [Status = 200]** 
```postman

    Email sent to user id: 60c4b7968c71c14b521ed76b

```

- **ERROR RESPONSE: [Status = 401]**
```postman
    Email can't be sent.
    No user with this id: xxxxxxxxx
```
</details>

----

# Testing

En la carpeta Postman de este repositorio encontrará una colección completa de peticiones y cuatro entornos para probar con las credenciales de autenticación correctas e incorrectas para testear la seguridad y para la aplicación en local o la desplegada en heroku.

Disfruta de esta API.

----

<details>
    <summary>VER ENUNCIADO</summary>


### Enunciado | 
Desde cliente nos han pedido poder hacer una implementación de varias APIs y poder simplificar la respuesta y el eso de estas.

El cliente quiere poder guardar los datos de sus usuarios y de las cuentas de Github, Gitlab y además poder comunicarse con ellos a través de un sistema de mensajería SMTP.

El arquitecto propone el siguiente esquema:

<details>
           <summary>VER ESQUEMA</summary>
            <p>
               <img src="https://github.com/gerardpuigl/BDT_Hackathon_MWC/blob/main/schema/ArchitectureSchema.jpg" alt="java" title="java" width=100%/>
            </p>
         </details>

----

### TAREAS | 

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

----

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

</details>

----
