
cd /d %~dp0

CALL mvn spring-boot:run -Drun.jvmArguments="-Drunning.from.maven=true"

PAUSE
