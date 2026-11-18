@echo off
chcp 65001 >nul
title Geometric GUI Calculator

java -jar "%~dp0GeometricGUICalculator.jar"

echo.
echo Программа завершена.
pause