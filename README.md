# Rectangle CLI Calculator

Консольное приложение на Java для вычисления геометрических характеристик прямоугольника.

## Возможности

Программа позволяет:

- Вычислить периметр прямоугольника.
- Вычислить площадь прямоугольника.
- Вычислить длину диагонали.
- Вычислить длину окружности, описанной около прямоугольника.
- Вычислить радиус вписанной окружности, если прямоугольник является квадратом.
- Изменить размеры прямоугольника без перезапуска программы.

## Используемые технологии

- Java
- Eclipse IDE
- JUnit 5
- Git и GitHub
- jpackage

## Структура проекта

```
RectangleCliCalculator/
│
├── src/
│   ├── main/java/
│   │   └── ru/oivt/rectanglecli/
│   │       ├── App.java
│   │       ├── model/
│   │       │   └── Rectangle.java
│   │       ├── ui/
│   │       │   └── ConsoleUI.java
│   │       └── util/
│   │           └── InputUtils.java
│   │
│   └── test/java/
│       └── ru/oivt/rectanglecli/model/
│           └── RectangleTest.java
│
├── dist/
│   └── RectangleCliCalculator.jar
│
├── build/
│   ├── RectangleCliCalculator/
│   │   ├── RectangleCliCalculator.exe
│   │   ├── app/
│   │   │   └── RectangleCliCalculator.cfg
│   │   └── runtime/
│   │
│   └── RectangleCliCalculator-Windows-x64.zip
│
├── README.md
└── .gitignore
```

## Варианты запуска программы

### 1. Запуск через Eclipse IDE

1. Открой проект в Eclipse IDE.
2. Найди класс `App.java`.
3. Нажми на него правой кнопкой мыши.
4. Выбери **Run As → Java Application**.
5. Следуй инструкциям в консоли.
### 2. Запуск через JAR-файл

1. Скачай файл `RectangleCliCalculator.jar` из папки `dist` или из GitHub.
2. Убедись, что на компьютере установлена Java.
3. Открой командную строку (CMD) в папке с JAR-файлом.
4. Выполни команду:
```bash
java -jar RectangleCliCalculator.jar
```
5. Программа запустится в консоли.

**Требования:** установленная Java, совместимая с версией сборки приложения.
### 3. Запуск через EXE-файл (Windows)

Для Windows подготовлена самостоятельная версия приложения, созданная с помощью `jpackage`.

1. Скачай архив `RectangleCliCalculator-Windows-x64.zip`.
2. Распакуй архив в удобное место.
3. Открой распакованную папку `RectangleCliCalculator`.
4. Запусти файл `RectangleCliCalculator.exe`.
## Тестирование

Для проверки математических вычислений используются модульные тесты JUnit 5.

Чтобы запустить тесты:

1. Найди `RectangleTest.java`.
2. Нажми на него правой кнопкой мыши.
3. Выбери **Run As → JUnit Test**.

## Математические формулы

- Периметр: `P = 2(a + b)`
- Площадь: `S = a × b`
- Диагональ: `d = √(a² + b²)`
- Длина описанной окружности: `C = π × d`
- Радиус вписанной окружности для квадрата: `r = a / 2`

## Автор

- ФИО: Даулбаев Диас Саматович
    
- Группа: Ом-ИЦ-231
    
- Учебный год: 2025–2026