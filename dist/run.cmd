@echo off
chcp 65001 >nul
title Rectangle CLI Calculator

java -jar "%~dp0RectangleCliCalculator.jar"

echo.
echo Программа завершена.
pause