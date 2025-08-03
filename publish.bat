@echo off

REM Usage: publish.bat <version>
IF "%1"=="" (
    ECHO Usage: %0 ^<version^>
    EXIT /B 1
)

SET VERSION=%1

REM Execute the Gradle publish task with the provided version
SETLOCAL
SET VERSION=%VERSION%
CALL gradlew.bat publish
ENDLOCAL
