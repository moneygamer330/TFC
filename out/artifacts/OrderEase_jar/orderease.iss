; Script de Inno Setup para OrderEase
[Setup]
; Información básica
AppName=OrderEase
AppVersion=1.0
DefaultDirName={pf}\OrderEase
DefaultGroupName=OrderEase
OutputDir=.
OutputBaseFilename=OrderEaseInstaller
Compression=lzma
SolidCompression=yes

; Configuración del instalador
WizardStyle=modern
SetupIconFile="\OrderEase\src\main\resources\img\icon.ico"; Icono para el instalador

[Files]
; Archivos a incluir en el instalador
Source: "C:\Users\money\Desktop\TFC\OrderEase\out\artifacts\OrderEase_jar\TFC.jar"; DestDir: "{app}"; Flags: ignoreversion
Source: "C:\Users\money\Desktop\TFC\OrderEase\out\artifacts\OrderEase_jar\javafx-sdk-22.0.1\*"; DestDir: "{app}\javafx-sdk-22.0.1"; Flags: ignoreversion recursesubdirs createallsubdirs
Source: "C:\Users\money\Desktop\TFC\OrderEase\src\main\resources\img\icon.ico"; DestDir: "{app}"; Flags: ignoreversion
Source: "C:\Users\money\Desktop\TFC\OrderEase\out\artifacts\OrderEase_jar\runOrderEase.bat"; DestDir: "{app}"; Flags: ignoreversion
; Agrega otros archivos necesarios aquí, siguiendo el formato anterior

[Icons]
; Crear accesos directos
Name: "{group}\OrderEase"; Filename: "{app}\OrderEase.exe"; IconFilename: "{app}\icon.ico"
Name: "{group}\{cm:UninstallProgram,OrderEase}"; Filename: "{uninstallexe}"; IconFilename: "{app}\icon.ico"

[Run]
; Ejecutar el programa después de la instalación (opcional)
Filename: "{app}\OrderEase.exe"; Description: "{cm:LaunchProgram,OrderEase}"; Flags: nowait postinstall skipifsilent

[UninstallDelete]
; Eliminar el directorio de la aplicación durante la desinstalación
Type: filesandordirs; Name: "{app}"
