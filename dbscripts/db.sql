-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Ноя 28 2018 г., 17:46
-- Версия сервера: 5.6.41
-- Версия PHP: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `notes`
--

-- --------------------------------------------------------

--
-- Структура таблицы `notes`
--

CREATE TABLE `notes` (
  `id` int(10) NOT NULL,
  `title` varchar(255) NOT NULL,
  `creation_date` date NOT NULL,
  `text` text NOT NULL,
  `author_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `notes`
--

INSERT INTO `notes` (`id`, `title`, `creation_date`, `text`, `author_id`) VALUES
(1, 'Didle', '2018-11-04', 'dwdawddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd', 2),
(2, 'Egorka rulit', '2018-07-11', 'awdadwadawdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaw', 5),
(3, 'wwwwwwwwwww', '2018-08-10', 'Vasilisushka', 6),
(4, 'ONETWOTHREE', '2018-12-08', 'ONETWOTHREE', 3),
(5, 'Titled', '2018-06-13', 'awawdasdasdadsad', 1),
(6, 'Userische', '2018-07-10', 'UserischeUserischeUserischeUserischeUserischeUserischeUserischeUserischeUserischeUserischeUserischeUserischeUserischeUserische', 2),
(7, 'Kalymim', '2018-08-08', 'Kalymim2222222222222', 4);

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `id` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `enctypted_password` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id`, `name`, `enctypted_password`) VALUES
(1, 'User1', 34532355),
(2, 'User2312', 43523532),
(3, 'NamerOned', 4234),
(4, 'Kalyvan', 543645754),
(5, 'Egorich', 1212),
(6, 'Vasek223', 1231);

-- --------------------------------------------------------

--
-- Структура таблицы `user_privileges`
--

CREATE TABLE `user_privileges` (
  `id` int(10) NOT NULL,
  `privilege` int(5) NOT NULL,
  `notes_id` int(10) NOT NULL,
  `users_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `user_privileges`
--

INSERT INTO `user_privileges` (`id`, `privilege`, `notes_id`, `users_id`) VALUES
(1, 1, 2, 5),
(2, 3, 1, 2),
(3, 0, 4, 3),
(4, 1, 6, 2),
(5, 1, 7, 4);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `notes`
--
ALTER TABLE `notes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `author_id` (`author_id`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `user_privileges`
--
ALTER TABLE `user_privileges`
  ADD PRIMARY KEY (`id`),
  ADD KEY `notes_id` (`notes_id`),
  ADD KEY `users_id` (`users_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `notes`
--
ALTER TABLE `notes`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT для таблицы `user_privileges`
--
ALTER TABLE `user_privileges`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `notes`
--
ALTER TABLE `notes`
  ADD CONSTRAINT `notes_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`);

--
-- Ограничения внешнего ключа таблицы `user_privileges`
--
ALTER TABLE `user_privileges`
  ADD CONSTRAINT `user_privileges_ibfk_1` FOREIGN KEY (`notes_id`) REFERENCES `notes` (`id`),
  ADD CONSTRAINT `user_privileges_ibfk_2` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
