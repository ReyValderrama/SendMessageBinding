# SendMessageBinding

- [Descripción](#descripci-n)
- [Características](#caracter-sticas)
- [Librerías](#librer-as)
- [Contenidos de aprendizaje](#contenidos-de-aprendizaje)


#### Descripción

Esta aplicación muestra una interfaz compuesta por dos vistas principales. En una se recoge el nombre de usuario y un mensaje y, tras pulsar un botón, ambos pasan a mostrarse en la segunda vista. En la aplicación se incluye también un menú desplegable con un apartado «sobre mí», que recoge información sobre la aplicación y su desarrolladora.

#### Características

- Sdk mínimo: 23
- Sdk objetivo: 32
- Sdk compilado: 32
- Versión app: 1.0

#### Librerías
Se han implementado dos librerías de terceros para este proyecto:
- https://github.com/orhanobut/logger: librería utilizada para imprimir logs en android.

Añadido en Build Gradle del módulo
```java
    implementation 'com.orhanobut:logger:2.2.0'
```

- https://github.com/jrvansuita/MaterialAbout: librería implementada para mostrar mensajes *about us*. Contiene numerosas opciones de personalización para este tipo de mensajes a través de sus métodos.

Añadido en Setting Gradle:
```java
  repositories {
        google()
        mavenCentral()
        maven { url "https://jitpack.io" }
        }
```

Añadido en Build Gradle del módulo
```java
    implementation 'com.github.jrvansuita:MaterialAbout:+'
```

**Nota importante:** esta librería entra en conflicto con las librerías de soporte de Android Studio, por lo que tras su implementación es necesario añadir la siguiente línea en las propiedades del Gradle:
```java
android.enableJetifier=true
```

#### Contenidos de aprendizaje

- **ViewBinding**: librería también llamada vinculación de vistas, se trata de un método que genera una clase de vinculación con cada archivo de vista XML. Esta clase es instanciada en el código de la activity y permite tener acceso tanto a la vista raíz como a todas las vistas que tengan un ID en el XML, por tanto, no es necesario seguir el método aprendido en el proyecto anterior, findViewById, que implicaba declarar componente a componente y vincularlos a su ID para poder usarlos. Para la vincluación de vistas es necesario:
  - Habilitar ViewBinding en cada módulo (build.gradle):

```java
android {
...
viewBinding {
enabled = true
    }
}
```

- Crear una variable de la clase de vinculación, que tendrá el nombre del XML de la activity añadiendo la palabra «Binding» al final. Por ejemplo, si tenemos un layout con nombre «activity_send_message», la clase será **ActivitySendMessageBinding**. De esta manera, se tiene aceso tanto a la vista root con el método getRoot(), como a todas las vistas que tengan ID finida en el layout.

```java
private ActivitySendMessageBinding binding;
```

- Configurar la instancia para ser usada en la activity. Para ello se usa un método de la propia clase declarada en el paso anterior: inflate(getLayoutInflater()), que crea una instancia de la clase de vinculación para la actividad en la que se declara. Además, se requiere que la vista raíz sea pasada al método setContentView() para que sea la vista activa. 

```java
        binding = ActivitySendMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
```
- **DataBinding**: librería que permite enlazar los componentes de la interfaz gráfica al modelo de datos. Utiliza la clase Binding mencionada en el punto anterior. Con este método se declara el modelo en la propia vista, que estará disponible tanto en la vista como en el código, donde deberá ser inicializada. Para habilitar DataBinding es necesario:

  - Añadir al fichero Build.Gradle del módulo:

```java
  android {
      dataBinding {
          enabled = true
          }
}
```
- Modificar el layout XML, cambiando el elemento raíz por <layout></layout> y definiendo, tras la declaración de este, la variable que se va a declarar: <variable></variable>, precedida de la etiqueta <data></data>. Dentro de la etiqueta de cada variable se deben definir los atributos name (nombre utilizado en el modelo, al que se referirá el código posteriormente) y type (ruta de la clase modelo). 
	
```java
    <layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <data>
        <variable
            name="message"
            type="com.mrv.sendmessagebinding.data.Message" />
    </data>
```
- **Generación de documentación JavaDoc**: esta documentación es utilizada por el lenguaje de programación Java para documentar, mediante etiquetas especiales, que son interpretadas una vez que la documentación se genera, las clases, variables o métodos de una aplicación. Permite el uso de etiquetas HTML y una serie de etiquetas propias. Para generar esta documentación es necesario añadir las siguientes líneas al Gradle del módulo:

```java
implementation fileTree(include: ['*.jar'], dir: 'libs')
        implementation files('C:/Users/Juanjo/AppData/Local/Android/Sdk/platforms/android-32/android.jar')
```

Una vez añadidas estas líneas y sincronizado el Gradle del módulo, se pulsa sobre Tools --> Generate JavaDoc

 **Nota importante:** una vez generada la documentación, estas líneas deben ser comentadas o se duplicará el fichero AndroidManifest y generará problemas en la aplicación. Debe volverse a sincronizar.

![](http://mrvvps.live/Prueba/JavaDoc1.PNG)![](http://mrvvps.live/Prueba/JavaDoc2.PNG)

- **Ciclo de vida de una Activity**: consistente en los diferentes estados por los que pasa una Actividad. Para el aprendizaje de este contenido se implementó una librería que permitía escribir logs. Estos logs se han lanzado sobreescribiendo cada uno de los métodos que componen los diferentes estados de una Activity: onCreate(), onDestroy(), onStart(), onStop(), onResume(), onPause() y onRestart()

- Ilustración simplificada del ciclo de vida de una actividad. Documentación Android Developer.
![](https://developer.android.com/guide/components/images/activity_lifecycle.png?hl=es-419)

- Ejemplo de código sobreescrito de uno de los métodos del ciclo de vida de una Activity:
```java
@Override
protected void onStart() {
        super.onStart();
        Log.d(TAG, "SendMessageActivity -> onStart()");
        }
```

- Código mostrado en el Log:
- 
![](http://mrvvps.live/Prueba/CicloVida.PNG)

- **Implementación de menú:** el menú de una aplicación es un recurso para el que se debe crear un directorio en res. Para ello se pulsa sobre File --> New --> Android Resource Directory, seleccionando el menú como el tipo de recurso. En este directorio se crea el menú, que en este proyecto será el mismo para toda la aplicación. Los diferentes elementos que se añadan al menú, irán precedidos de la etiqueta <item></item> como se muestra:
        
```java
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">
<item android:title="@string/aboutus" android:id="@+id/action_aboutus"/>
</menu>
```
Además, se debe inflar el menú en el código de la aplicación, utilizando la clase MenuInflater, además de implementar las funcionalidades de las opciones, que en este caso ha sido solo una, el item aboutus.

```java
 @Override
public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
        }

public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
        case R.id.action_aboutus:
        Intent intent = new Intent(this, AboutUsActivity.class);
        startActivity(intent);
        break;
        }
        return true;
        }
```
Como se puede ver en el código de onOptionsItemSelected(), se ha creado una Activity nueva solo para mostrar el contenido de la opción about us, con métodos de la librería mencionada anteriormente.

- **Implementación clase Application**: esta clase está presente en cada aplicación Android y de ella hay una única instancia. En este proyecto se ha creado una clase que extiende de Application y que guarda un dato global al que se puede acceder desde cualquier Activity, con solo hacer referencia a la instancia de esta clase en el código de la Activity que quiera acceder al dato.

```java
public class SendMessageApplication extends Application {

  //Usuario que ha iniciado sesión. DATO GLOBAL porque siempre
  //se va a acceder a él mediante el método getApplication().getUser()
  private User user;

  @Override
  public void onCreate() {
    super.onCreate();
    Logger.addLogAdapter(new AndroidLogAdapter());
    Logger.d("Se ha inicializado el objeto Application");
    user = new User("MRey", "mrv@gmail.com");
  }

  public User getUser() {
    return user;
  }
}
```
Código de la activity que usa SendMessageApplication para acceder al dato user.
```java
binding.setMessage(new Message(((SendMessageApplication) getApplication()).getUser()));
```
**Nota importante**: hay que declarar clase Application en el AndroidManifest de la siguiente manera:

```java
<application
        android:name=".SendMessageApplication"
```
- **Uso de icono para la app y redimensionado de imágenes**: a este proyecto se le ha cambiado el icono que aparece por defecto, añadiendo dentro de la carpeta mipmap un nuevo vector. Para esto se pulsa botón derecho sobre la carpeta mipmap --> New --> Vector Aset y se selecciona la imagen que se desea mostrar como icono, así como su fondo o su dimensión. Esta misma herramienta crea de manera autómata el icono en las diferentes resoluciones, de forma que, dependiendo de la resolución de pantalla del dispositivo, será mostrado el icono que corresponda de manera automática, buscando en la carpeta indicada el archivo con su resolución propia.
![](http://mrvvps.live/Prueba/Icono.PNG)
