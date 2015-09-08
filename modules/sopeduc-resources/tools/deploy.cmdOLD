@ECHO OFF
echo Generer les ressources specifique au poste developpeur

REM #### Se rendre dans le dossier du script de génération des ressources
set deploySkmDir=..\..\tools\deploy
IF NOT EXIST %deploySkmDir% (
	echo ERREUR - Le dossier %deploySkmDir% n'existe pas
	goto ERREUR
)
cd %deploySkmDir%

REM #### Exécuter le script de génération des ressources
cmd /C "generatePropertiesDEV.cmd"
if %errorlevel% neq 0 (
	goto ERREUR
)

REM #### Revenir dans le module resources
set moduleResourcesDir=..\..\modules\ord-resources\
IF NOT EXIST %moduleResourcesDir% (
	echo ERREUR - Le dossier %moduleResourcesDir% n'existe pas
	goto ERREUR
)
cd %moduleResourcesDir%

REM ### Copier les resources vers le dossier de travail d'Eclipse pour le deploiement vers le serveur local.
IF NOT EXIST target\classes mkdir target\classes
xcopy /E /Y %deploySkmDir%\generatedResources\dev\local\instlocal\* target\classes
goto FIN

:ERREUR
	echo Impossible de générer les ressources 
	exit 1
	
:FIN