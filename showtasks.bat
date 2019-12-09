call runcrud.bat
if "%ERRORLEVEL%" == "0" goto startWebBrowser
echo.
echo runcrud had some errors - breaking up!
goto fail

:startWebBrowser
start chrome http://localhost:8080/crud/v1/task/getTasks
echo.
echo WebBrowser started!
goto end

:fail
echo.
echo Script showtasks failed.

:end
echo.
echo Script showtasks successfully.