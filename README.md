## Команда № 35

* Ян - [Telegram](https://t.me/yan_developer)
* Павел - [Telegram](https://t.me/pasha_kukuruza)
* Ирина - [Telegram](https://t.me/eireen_sid)

## Стек

<h3><i>Frontend</i> - Vue 3, Axios, Bootstrap, Pinia</h3>
<br>
<img src="./frontend/src/assets/img/readme/vue.svg" alt="Vue" width="100" height="100">
<br>
<h3><i>Backend</i> - Spring</h3>
<br>
<img src="./frontend/src/assets/img/readme/spring.png" alt="Spring" width="100" height="100">

## Описание проекта
Мы работаем над проектом Slider Game (Пятнашки).
Реализованы все фичи из базового минимума:
* Вывод поля с пятнашками и правилами игры
* Поле 4х4, кусочки распределяются рандомным образом 
* Удобно подсвеченная навигация
* Возможность начать игру заново
* Экран поздравления с окончанием игры

Из дополнительного списка реализован следующий функционал:
* Возможность выбора поля других размеров (3х3б 5х5)
* Возможность выбора из нескольких тематических иллюстраций

## Ссылка на деплой
https://hackathon-pummelerdevs-projects.vercel.app

## Инструкция, как развернуть проект локально
### Frontend
Проект запустится на порту `http://localhost:5173`
```bash
# Склонировать репозиторий
https://github.com/PummelerDev/hackathon.git

# Перейти в папку frontend
cd frontend

# Установить зависимости
npm install

# Запустить проект
npm run dev
```
### Backend
Проект запустится на порту `http://localhost:8080`
```bash
# Установить докер
# Склонировать репозиторий
https://github.com/PummelerDev/hackathon.git

# Перейти в папку с проектом

# Собрать проект
docker-compose build

# Запустить проект
docker-compose up -d
```
