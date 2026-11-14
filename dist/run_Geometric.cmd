@echo off
chcp 65001 >nul
title Geometric CLI Calculator

java -jar "%~dp0GeometricCliCalculator.jar"

echo.
echo Программа завершена.
pause