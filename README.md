# parcial2
 
link al repositorio: https://github.com/mbp4/parcial2.git

## Descripción 

El proyecto se basa en cuatro partes: 

 -> Main Activity

 -> Carpeta de Horario

 -> Carpeta de Eventos

 -> Carpeta de Farmacias

## Pantalla Inicial

Esta pantalla es la principal y es la encargada de que el usuario navegue hacia una clase u otra.

Se compone de 3 botones: 

 -> Botón para ir al gestor de horario.

 -> Botón para ir al gestor de eventos.

 -> Botón paran ir al gestor de farmacias. 

## Carpeta de Horario

Esta carpeta contiene toda la lógica para el primer ejercicio, se compone de: 

 -> Horario: es la activity principal y esta compuesta de varios botones donde cada uno de ellos te llevan a las activities necesarias o al botón de volver al inicio.

 -> AhoraActivity: esta activity esta formada por dos textView, donde uno muestra la fecha y hora actual y debajo la asignatura en la que se encuentra el usuario, además tiene un botón que te devuelve a la activity anterior.

 -> AñadirActivity: esta activity esta formada por varios textEdit y un spinner para elegir el día en el que se va a añadir. Los textEdit son para el nombre de la asignatura y dos para la hora de inicio y de final de la asignatura, para elegir la hora con un TimePicker para que no haya errores a la hora del guardado. Además de tener un botón que guarda la asignatura y otro que lo cancela.

 -> VerActivity: esta activity permite al usuario escoger el día mediante un spinner y elegir el que se quiere consultar, cuando se elija uno de ellos se mostrará el listado de clases. Además de esto tiene un botón que permite al usuario volver a la actividad anterior.
 
 -> Asignatura: es un data class para poder añadir y utilizar las asignaturas.

## Carpeta de Eventos

Esta carpeta contiene toda la lógica para el segundo ejercicio, se compone de: 

 -> InicioEvento: esta activity muestra el inicio de los eventos y esta compuesto por un botón que cambiará el idioma a español o inglés (esto lo hace mediante los values de los strings), un botón para añadir eventos nuevos, un listado con todos los eventos y un botón que permite volver al inicio.

 -> EventoAdapter: esta activity se encarga de mostrar los datos de los eventos en el ListView de manera personalizada.

 -> AñadirEvento: esta activity esta compuesta de varios editText, uno para el nombre, para la decripcion, para la dirección, para el precio, para la fecha (que mostrará un AlertDialog con un calendario para elegir) y otro para el aforo. Además tiene dos botones, uno para añadirlo y otro para cancelarlo.

 -> Evento: es un data class para poder añadir y utilizar los eventos.

## Carpeta de Farmacias

Esta carpeta contiene toda la lógica para el tercer ejercicio, se compone de: 

 -> Iniciof: esta activity te muestra el listado de las farmacias con una imagen (la misma para todas), el nombre, el teléfono y un botón que te lleva a la pantalla de los detalles de la farmacia. Además tiene un botón de volver, que te devuelve a la actividad principal. 

 -> FarmaciaAdapter: esta activity se encarga de adaptar los datos al listView y mostrar unicamente el nombre y el teléfono (el teléfono al estar en un String con más datos utilizamos una función para que únicamente muestre el número), ademas de guardar el nombre para cuando se muestren los detalles.

 -> Detalles: esta activity muestra el nombre de la farmacia que se haya escogido y un mapa que simula la ubicacion de la farmacia, ademas contiene un botón que te permite volver a la anterior actividad.

 -> Farmacia: es un data class para poder gestionar las farmacias.
