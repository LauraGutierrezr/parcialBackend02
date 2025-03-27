# PARCIAL CVDS SEGUNDO CORTE backend

**👥 Estudiante CVDS:**
- Laura Valentina Gutiérrez Rico

--

## PROCEDIMIENTO PARCIAL:

**Como primer paso creo un repositorio privado en mi Git**

![image](https://github.com/user-attachments/assets/560c30af-93dc-4e3b-9d5b-3bc579ce8371)

**En Sprint Initializr creo un nuevo proyecto llamado parcial02 en maven**

![image](https://github.com/user-attachments/assets/13fa2a02-e758-402d-8d74-8bfe93b88b92)


**Análisis del problema**
  En el parcial presentan una necesidad a resolver para el cliente, consideré los siguientes pasos como el resultado final

      ```
      1. El usuario ingresa al sistema y añade los articulos deseados al carrito
      2. El sistema valida la información como producto, cantidad, tipo, precio...
      3. Si los datos son correctos, posteriormente paga y registra
      4. El usuario cosulta los pagos registrados mediante un identificador propio. 
      ```

    
**Creacion de DIAGRAMA DE CLASES**

Realicé la representación de todas las clases y su interacción en Astah, como sus tipos de datos, llaves... es importante para la compresión del problema, poder verlo de una manera holistica desde las clasesd que son la base

![image](https://github.com/user-attachments/assets/3761c907-2a24-4273-995e-a5a4b8384302)



**La planeación de la interaccion de los sistemas presentados**
Muestro lainteracción de los programas que usaré para poder realizar el parcial desde el fronted hasta el backend conb la base de datos

![image](https://github.com/user-attachments/assets/e5502bb2-5cc1-4a00-963d-17e05551eaf7)





'--------------------------------------------------------------------------------------------------------------------------------------'

**BACKEND**


**Modifico el archivo pom.xml integrando mongo, añadiendo Jacoco**

  --Jcoco


![image](https://github.com/user-attachments/assets/0978c850-a5c5-427e-8c48-ea53beb4fc76)


  --MongoDB

  
![image](https://github.com/user-attachments/assets/b8e5092e-53ac-411d-8aae-c1cd5c08ef28)



  --Creacion clases



![image](https://github.com/user-attachments/assets/590ffeaa-f493-456b-bd0e-752aecd87ca0)





**Creación proyecto MONGODB**

![image](https://github.com/user-attachments/assets/2512f93e-e504-43fd-a78a-cc8598931819)


**Pruebas con Jacoco**





--------------------------------------------------------------------------------------------------------------------------------------
**FRONTED**

Para la creación del front la realizaré con HTML desde mi local, teniendo en cuenta los archivos base que son .css, .js , .HTML tomé una plantilla básica para crearla a las necesidades del parcial y su finalidad


![image](https://github.com/user-attachments/assets/9e08e7e3-8fdf-4836-a721-b26de769b553)







**  **

   realice varios tipos de Test
  
  
  
  
  AGENTE LOG:
  
    - **Debe escribir en stdout cada vez que se modifica el stock de un producto`**:
    Implementandolo en la clase .java, quedaría**

      ```
      
      ```



**Estructura de todo el proyecto con tree**
--Se puede observar en la imagen la estructura de los archivos creados, en la carpeta parcial02, están almacenados los archivos que contienen las clases de test, el archivo pom.xml, src y target

![image](https://github.com/user-attachments/assets/bab77537-c998-4efa-abd5-5461c68af5f3)

