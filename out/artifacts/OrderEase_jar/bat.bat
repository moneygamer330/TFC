@echo off
setlocal

REM Ruta del directorio actual donde se encuentra el script
set "SCRIPT_DIR=%~dp0"

REM Ruta a la carpeta lib del SDK de JavaFX
set "PATH_TO_FX=%SCRIPT_DIR%javafx-sdk-22.0.1\lib"

REM Ruta al ejecutable de Java. Ajusta esto si es necesario.
set "JAVA_BIN=C:\Program Files\Java\jdk-11.0.2\bin\java.exe"

REM Comando para ejecutar el archivo JAR utilizando Java y especificando el SDK de JavaFX
"%JAVA_BIN%" --module-path "%PATH_TO_FX%" --add-modules javafx.controls,javafx.fxml -jar "%SCRIPT_DIR%TFC.jar"

endlocal
pause
