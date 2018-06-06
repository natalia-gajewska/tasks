call runcrud.bat
if "%ERRORLEVEL%" == "0" goto browser
echo.

:browser
start "" "C:\Program Files\Google\Chrome\Application\chrome.exe" -new-tab "http://localhost:8080/crud/v1/task/getTasks"
if "%ERRORLEVEL%" == "0" goto stoptomcat
echo Cannot open Google Chrome
goto fail

:stoptomcat
call %CATALINA_HOME%\bin\shutdown.bat

:runtomcat
call %CATALINA_HOME%\bin\startup.bat
goto end

:fail
echo.
echo There were errors

:end
echo.
echo Work is finished.