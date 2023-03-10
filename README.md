# TALLER 5: TALLER DE MODULARIZACIÓN CON VIRTUALIZACIÓN E INTRODUCCIÓN A DOCKER Y A AWS
Se construye una aplicación con una arquitectura que incluye un balanceador de carga, 3 servicios que manejan las peticiones que entran y una base de datos no relacional, todo esto usando Docker y desplegándolo en AWS.
![Imgur](https://i.imgur.com/legoFeL.png)

---
### Prerrequisitos
Para elaborar este proyecto requerimos de las siguientes tecnologías:

 - **[Maven](https://openwebinars.net/blog/que-es-apache-maven/)**: Apache Maven es una herramienta que estandariza la configuración de un proyecto en todo su ciclo de vida.
 - **[Git](https://learn.microsoft.com/es-es/devops/develop/git/what-is-git)**: Es un sistema de control de versiones distribuido (VCS).
 - **[Docker](https://www.ibm.com/co-es/topics/docker)**: Docker es una plataforma de código abierto de contenedorización . Permite a los desarrolladores empaquetar aplicaciones en contenedores, componentes ejecutables estandarizados que combinan el código fuente de la aplicación con las bibliotecas del sistema operativo (SO) y las dependencias necesarias para ejecutar ese código en cualquier entorno.

---
### Instalación
Primero clonamos el repositorio

     git clone https://github.com/jorge-stack/Taller_05.git
    
Se accede al repositorio que acabamos de clonar

	 cd Taller_05

Traemos las imágenes definidas en el archivo docker-compose.yml que se encuentra dentro de la carpeta del proyecto

	 docker-compose pull

	
---
### Corriendo
Ahora podemos levantar el contenedor con

**Windows/Linux/MacOS**

	 docker-compose up -d

### Run
Una vez tenemos corriendo el contenedor podemos fijarnos de que todo este correctamente usando el siguiente comando

	 docker ps

Para este caso lo estamos desplegando en una instancia ec2 de AWS
![Docker ps](https://i.imgur.com/1CUc2Ss.png)

Ahora podremos acceder al servicio principal, en caso de que lo tenga desplegado en local su acceso sera por

	 http://localhost:8087

En mi caso, al estar en una instancia Ec2 puedo acceder con el DNS público o la IP publica que me proporciona AWS, y se vería de la siguiente manera
![Web App](https://i.imgur.com/QqKX67j.png)

En el caso, el aplicativo web recibe por el input cualquier cadena de texto y lo distribuye de manera equitativa por los servicios de log distribuidos que se presentan en el modelo de la arquitectura, esto gracias a la implementación del algoritmo de Round Robin de balanceo de cargas. el aplicativo regresara en formato JSON los 10 últimos inputs almacenados en la base de datos junto con su fecha de creación.
![Imgur](https://i.imgur.com/JgfMorz.png)

	
---
## Construido con

* [Maven](https://maven.apache.org/): Apache Maven es una herramienta que estandariza la configuración de un proyecto en todo su ciclo de vida.
* [Git](https://rometools.github.io/rome/):  Es un sistema de control de versiones distribuido (VCS).
* [IntelliJ](https://www.jetbrains.com/idea/): Es un entorno de desarrollo integrado para el desarrollo de programas informáticos.
* [Java 19](https://www.java.com/es/): Lenguaje de programación de propósito general, es decir, que sirve para muchas cosas, para web, servidores, aplicaciones móviles, entre otros. Java también es un lenguaje orientado a objetos, y con un fuerte tipado de variables.
* [Html](https://developer.mozilla.org/es/docs/Learn/Getting_started_with_the_web/HTML_basics): Es el código que se utiliza para estructurar y desplegar una página web y sus contenidos.
* [JavaScript](https://developer.mozilla.org/es/docs/Learn/JavaScript/First_steps/What_is_JavaScript): JavaScript es un lenguaje de programación o de secuencias de comandos que te permite implementar funciones complejas en páginas web
* [CSS](https://developer.mozilla.org/es/docs/Learn/CSS/First_steps/What_is_CSS):Las hojas de estilo en cascada permiten crear páginas web atractivas.
* [Docker](https://www.ibm.com/co-es/topics/docker): Docker es una plataforma de código abierto de contenedorización . Permite a los desarrolladores empaquetar aplicaciones en contenedores, componentes ejecutables estandarizados que combinan el código fuente de la aplicación con las bibliotecas del sistema operativo (SO) y las dependencias necesarias para ejecutar ese código en cualquier entorno.

## Autor
* **[Jorge David Saenz Diaz](https://co.linkedin.com/in/jorgedsaenzd/en)**  - [Jorge-Stack](https://github.com/jorge-stack?tab=repositories)

## Licencia
**©** Jorge David Saenz Diaz, Estudiante de Ingeniería de Sistemas de la Escuela Colombiana de Ingeniería Julio Garavito.
