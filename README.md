# Resolución del ejercicio

Esta es la documentación correspondiente a una prueba de nivel de Java, cuyo enunciado se encuentra en el archivo README_EXERCISE.md.
####- ¿Qué has empezado implementando y por qué?
Lo primero que he hecho ha sido comentar completamente el código proporcionado y a adaptarlo línea a línea, empezando por la búsqueda por nombre de la ciudad. De esta manera, podía ver posibles errores de código y/o legibilidad sobre la marcha y me era más fácil decidir cómo lo abordaría yo.
####- ¿Qué problemas te has encontrado al implementar los tests y cómo los has solventado?
Dentro de lo que cabe ha sido una experiencia con pocos problemas, pero probablemente el que más me ha costado ha sido el de buscar un pronóstico muy antiguo (también aplicable a uno lejano en el futuro), ya que además de simplemente cambiar los valores pasados como parámetros por algo ligeramente más complejo (crear una nueva fecha parseando un SimpleDateFormat), he tenido que corregir una pequeña parte del código que hacía que diera un error en lugar de un mensaje "traducido" al usuario.
####- ¿Qué componentes has creado y por qué?
La estructura final del proyecto está dividida en dos clases: WeatherForecast, para interactuar con el usuario, y WeatherService, para interactuar con la API, y un total de cinco métodos entre las dos. Esta división la he hecho porque siempre he visto más correcto el separar todos los cálculos y operaciones internas de los que van a conectar con la red. Además, de esta manera también acaban quedando unos archivos más cortos y más legibles por parte del programador.
####- Si has utilizado dependencias externas, ¿por qué has escogido esas dependencias?
Más allá de lo que venía ya proporcionado, creo que la otra única clase que he utilizado es Calendar (para dividir un Date en día, mes y año), y es una clase interna. Me ha parecido que podía hacerse un programa apropiado con las herramientas proporcionadas.
####- ¿Has utilizado  streams, lambdas y optionals de Java 8? ¿Qué te parece la programación funcional?
No he utilizado ninguna de ellas. La programación funcional me parece muy útil y puede llegar a simplificar bastante el código, pero por otra parte  no siempre son adecuadas (en mi caso las he utilizado más cuando ha habido operaciones matemáticas de por medio) y, en muchos casos, hacen el programa menos legible.
####- ¿Qué piensas del rendimiento de la aplicación? 
Para mí, el test básico (con una ciudad existente y la fecha actual) me tarda unos 2,5 segundos. Es una cifra suficientemente baja, aunque probablemente haya factores (tanto internos como externos) que podrían hacer que fuera algo más rápido.
####- ¿Qué harías para mejorar el rendimiento si esta aplicación fuera a recibir al menos 100 peticiones por segundo?
Implementaría un sistema de hilos para evitar que se solapen y que incrementen el tiempo de uso de la aplicación.
####- ¿Cuánto tiempo has invertido para implementar la solución? 
En total, entre refactorización, comentarios y redacción de este README, entre unas tres y cuatro horas.
####- ¿Crees que en un escenario real valdría la pena dedicar tiempo a realizar esta refactorización?
Absolutamente. No sólo ayuda a ir encontrando errores que no habíamos visto anteriormente, también sirve para evitar falta de comprensión tanto por parte de otros programadores que colaboren en nuestro código como por nuestra propia parte en un futuro (todo dentro de las limitaciones que tengan la empresa y el proyecto).