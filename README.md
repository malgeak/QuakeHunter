# QuakeHunter

Aplicación demo desarrollada para Android con el lenguaje de programación Kotlin y el patron de diseño MVVM. Esta aplicación utiliza el API https://earthquake.usgs.gov/fdsnws/event/1/ para mostrar información de sismos que ocurren al rededor del mundo.

## Stack

*Kotlin
*MVVM
*Splash Screen by Google
*Maps SDK for Android
*Retrofit
*Gson
*Dagger
*Room
*Coroutines
*Navigation
*LiveData
*Databinding
*ViewBinding
*Observables

### Requisitos de dispositivo

Debido al uso de Maps SDK es necesario que el emulador o dispsitivo cuente con soporte de los servicios de google, de lo contrario el mapa no se mostrará
correctamente. 

El nivel de API minimo para esta aplicación es 23

## Importante
Es necesario agregar el archivo de local.properties el cual deberá contener la variable MAPS_API_KEY y el KEY  de uso para Maps de Google
