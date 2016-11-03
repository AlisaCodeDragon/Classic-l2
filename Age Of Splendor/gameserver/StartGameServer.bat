@echo off
title Nice Team Classic: Game Server Console
:start
echo Starting Nice Team GameServer.
echo.

REM ������ ������
set JAVA_OPTS=%JAVA_OPTS% -XX:PermSize=128m
set JAVA_OPTS=%JAVA_OPTS% -XX:MaxPermSize=256m

REM ������� � �������� ���������� ������
REM ����������� �������� ��� ������� � ��������: 1.5G
REM ����������� �������� ��� ������� ��� �������: 1G
REM -Xms � -Xmx ������ ���� ������ ����� ���� �����.
set JAVA_OPTS=%JAVA_OPTS% -Xmn512m
set JAVA_OPTS=%JAVA_OPTS% -Xms3096m
set JAVA_OPTS=%JAVA_OPTS% -Xmx3096m

REM ��������� �������� ������ � �����������
set JAVA_OPTS=%JAVA_OPTS% -Xnoclassgc
set JAVA_OPTS=%JAVA_OPTS% -XX:+AggressiveOpts
set JAVA_OPTS=%JAVA_OPTS% -XX:TargetSurvivorRatio=90
set JAVA_OPTS=%JAVA_OPTS% -XX:SurvivorRatio=16
set JAVA_OPTS=%JAVA_OPTS% -XX:MaxTenuringThreshold=12
set JAVA_OPTS=%JAVA_OPTS% -XX:+UseParNewGC
set JAVA_OPTS=%JAVA_OPTS% -XX:+UseConcMarkSweepGC
set JAVA_OPTS=%JAVA_OPTS% -XX:+CMSIncrementalMode
set JAVA_OPTS=%JAVA_OPTS% -XX:+CMSIncrementalPacing
set JAVA_OPTS=%JAVA_OPTS% -XX:+CMSParallelRemarkEnabled
REM ��� 64-������ ������ ����� -XX:+UseCompressedOops ��������� ������ ������ � ����� ������������������
REM set JAVA_OPTS=%JAVA_OPTS% -XX:+UseCompressedOops
set JAVA_OPTS=%JAVA_OPTS% -XX:UseSSE=3
set JAVA_OPTS=%JAVA_OPTS% -XX:+UseFastAccessorMethods

java -server -Dfile.encoding=UTF-8 %JAVA_OPTS% -cp config;../lib/* l2s.gameserver.GameServer

if ERRORLEVEL 2 goto restart
if ERRORLEVEL 1 goto error
goto end
:restart
echo.
echo Server restarted ...
echo.
goto start
:error
echo.
echo Server terminated abnormaly ...
echo.
:end
echo.
echo Server terminated ...
echo.

pause
