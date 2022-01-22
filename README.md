# The cook

Eres un cocinero que tiene que preparar los platos de los comensales en el menor tiempo posible.
Pero que casualidad, no te queda ningún alimento, tienes que ir al supermercado a comprarlos.

<img src="docs/menu.PNG" width="300px" />

<img src="docs/supermercado.PNG" width="300px" />

<img src="docs/administrar.PNG" width="300px" />

<img src="docs/alimentos.PNG" width="300px" />

<img src="docs/platos.PNG" width="300px" />

<img src="docs/recetas.PNG" width="300px" />


# Instrucciones de desarrollo

The cook es un proyecto java-libgdx que utiliza el gestor de dependencias gradle.

Puedes abrir este proyecto con eclipse, Intelly idea, netbeans o android studio


# Eclipse

Pincha en File->Import

<img src="docs/eclipse1.PNG" width="300px" />

En la ventana que aparece elige "Existing Gradle project":

<img src="docs/eclipse2.PNG" width="400px" />

## Añadiendo la librería de conexión a la base de datos

Espera hasta que se descarguen todas las librerias que utiliza libgdx.
Una vez descargadas ahora vamos a enlazar en nuestro proyecto la librería que nos permite conectarnos a la base de datos.
Tenemos 2 opciones: directamente con el JAR descargado o hacerlo con gradle.




### Añadiendo el JAR descargándolo de internet

Ve a https://mvnrepository.com/

y pon el el buscador sqlite

<img src="docs/eclipse3.PNG" width="400px" />

Selecciona el resultado que tenga más usos:


<img src="docs/eclipse4.PNG" width="400px" />

Pincha en una librería un poco más antigua:

<img src="docs/eclipse5.PNG" width="200px" />

Pinchar en el archivo para descargarlo:

<img src="docs/eclipse6.PNG" width="400px" />

Haz click derecho con cocinitas-desktop, después en properties.

<img src="docs/eclipse7.PNG" width="400px" />

Selecciona Java build path (1) y la pestaña de Libraries, pincha en el botón Add JAR (2) y selecciona la librería que está en la ruta indicada:

<img src="docs/eclipse8.PNG" width="500px" />

<br /><br />

### Añadiendo a través de el archivo de configuración de gradle

Queremos ver arriba a la izquierda el Explorador de archivos, así que ve a Wondow->Show View->Project Explorer:

<img src="docs/eclipse9.PNG" width="500px" />

Vamos a Maven uy obtenemos el texto que hay que poner en el archivo de configuración de gradle:

<img src="docs/eclipse10.PNG" width="500px" />

Ahora en la riz del proyecto hay un archivo llamado build.gradle, en la parte de "project(":desktop")" tenemos que poner el tecto copiado:

<img src="docs/eclipse11.PNG" width="500px" />

Ahora botón derecho sobre ese archivo y pichamos en gradle->Refresh gradle project:

<img src="docs/eclipse12.PNG" width="500px" />





