-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-05-2023 a las 20:34:21
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dim_gf`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `autores`
--

CREATE TABLE `autores` (
  `id_autor` int(11) NOT NULL COMMENT 'PK',
  `nombre_autor` varchar(100) NOT NULL,
  `id_pais` int(11) NOT NULL COMMENT 'FK -> paises'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------
/*
INSERT INTO `autores` (`nombre_autor`, `id_pais`) 
VALUES ('Vincent van Gogh', 10), ('Diego Velázquez', 2), 
('Katsushika Hokusai', 8), ('Leonardo da Vinci', 7), 
('Francisco de Goya', 2), ('El Bosco', 10), ('Sandro Boticcelli', 7),
('Miguel Ángel', 7), ('Rafael Sanzio', 7), ('Frida Kahlo', 11),
('Eugène Delacroix', 4), ('Auguste Rodin', 4), ('Antoni Gaudí', 2),
('Alexandre Gustave', 4), ('Cecilia Giménez Zueco', 2), ('Kotagawa Utamuro', 8),
('Otani Oniji III', 8), ('Cildo Mireies', 12), ('Pablo Picasso', 2),
('Louise Bourgeois', 4), ('Gregorio Fernández', 2), ('Kuai Xiang', 1),
('Gian Lorenzo Bernini', 7), ('José Luján Pérez', 2), ('Pedro Millán', 2),
('Juan de Mesa', 2), ('Giuseppe Sanmartino', 7);
*/
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `museos`
--

CREATE TABLE `museos` (
  `id_museo` int(11) NOT NULL COMMENT 'PK',
  `nombre_museo` varchar(100) NOT NULL,
  `id_pais` int(11) NOT NULL COMMENT 'FK -> paises'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------
/*
INSERT INTO `museos` (`nombre_museo`, `id_pais`) VALUES ('Nueva Pinacoteca de Múnich', 13), ('Museo del Prado', 2),
('Museo Metropolitano de Arte de Nueva York', 3), ('Museo del Louvre', 4), ('Santa Maria del las Gracias', 7), 
('Galería de la Academia', 7), ('Galería Uffizi', 7), ('Museos Vaticanos', 14), ('Museo de Arte Moderno de San Francisco', 3), 
('Museo Rodin', 4), ('Santuario de Misericordia de Borja', 2), ('Museo Koishikawa Ukiyo-e', 8), ('Museo Tate Modern', 5), 
('Museo Nacional Centro de Arte Reina Sofía', 2), ('Museo Guggenheim', 2), ('Museo Nacional de Escultura', 2), 
('Museo del Palacio', 1), ('Catedral de Astorga', 2), ('Iglesia Penitencial de la Santa Vera Cruz', 2), 
('Iglesia de Santa María de la Victoria', 7), ('Catedral de Canarias', 2), ('Museo de Bellas Artes de Sevilla' , 2),
('Museo Nacional de San Martino', 7), ('Museo de los Inodoros', 5), ('Museo del Alcantarillado', 4), 
('Museo de los Excrementos', 7), ('Museo del Agua de Grifo', 1), ('Museo del Cabello', 9), ('Museo del Calcetín', 8), 
('Museo de los Orinales', 2), ('Museo de la Comida Quemada', 3), ('Museo de la Tortura', 2), ('Museo del Collar de Perro ', 5);
*/
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `obras`
--

CREATE TABLE `obras` (
  `id_obra` int(11) NOT NULL COMMENT 'PK',
  `nombre_obra` varchar(100) NOT NULL,
  `descripcion_obra` varchar(100) NOT NULL,
  `clave_obra` varchar(50) NOT NULL,
  `disciplina` varchar(100) NOT NULL,
  `imagen_obra` varchar(200) NOT NULL,
  `id_museo` int(11) COMMENT 'FK -> museos',
  `id_autor` int(11) NOT NULL COMMENT 'FK -> autores'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------
/*
INSERT INTO `obras` (`nombre_obra`, `descripcion_obra`, `clave_obra`, `disciplina`, `imagen_obra`,`id_museo`, `id_autor`)
VALUES ('Los Girasoles', 'Óleo sobre lienzo de 93 x 72 cm realizado en 1888', 'girasoles', 'Pintura', 'http://4.bp.blogspot.com/-vnKoUZlLuSQ/VPevhmY4FKI/AAAAAAAAADg/Paj7GQf2o5c/s1600/Vincent_Willem_van_Gogh_128.jpg', 1, 1),
('Las Meninas', 'Óleo sobre lienzo de 320,5 x 281,5 cm realizado en 1656', 'meninas', 'Pintura', 'https://4.bp.blogspot.com/-Og960_owCP4/WNv0JgvSivI/AAAAAAAAAn4/NsalJJ63tBk0JGd1P3nINYo2WBS_BG6VwCEw/w1200-h630-p-k-no-nu/Las_Meninas_01.jpg', 2, 2),
('La Gran Ola de Kanagawa', 'Impresión xilográfica de 25 x 37 cm realizado entre 1830 y 1833', 'ola', 'Ukiyo-e', 'https://upload.wikimedia.org/wikipedia/commons/thumb/0/0d/Great_Wave_off_Kanagawa2.jpg/1024px-Great_Wave_off_Kanagawa2.jpg', 3, 3),
('La Gioconda', 'Óleo sobre tabla de álamo de 79 x 53 cm realizado entre 1503 y 1519', 'gioconda', 'Pintura', 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Leonardo_da_Vinci_-_Mona_Lisa_%28Louvre%2C_Paris%29.jpg/800px-Leonardo_da_Vinci_-_Mona_Lisa_%28Louvre%2C_Paris%29.jpg', 4, 4),
('La Última Cena', 'Óleo sobre yeso de 880 x 460 cm realizado entre 1495 y 1498', 'cena', 'Pintura', 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/48/The_Last_Supper_-_Leonardo_Da_Vinci_-_High_Resolution_32x16.jpg/1920px-The_Last_Supper_-_Leonardo_Da_Vinci_-_High_Resolution_32x16.jpg', 5, 4),
('Saturno', 'Óleo sobre revoco de 146 x 83 cm realizado entre 1820 y 1823', 'saturno', 'Pintura', 'https://upload.wikimedia.org/wikipedia/commons/thumb/8/82/Francisco_de_Goya%2C_Saturno_devorando_a_su_hijo_%281819-1823%29.jpg/800px-Francisco_de_Goya%2C_Saturno_devorando_a_su_hijo_%281819-1823%29.jpg', 2, 5),
('La Maja Vestida', 'Óleo sobre lienzo de 95 x 188 cm realizado entre 1800 y 1808', 'maja', 'Pintura', 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/a8/Maja_vestida_%28Prado%29.jpg/1920px-Maja_vestida_%28Prado%29.jpg', 2, 5),
('El Jardín de las Delicias', 'Óleo sobre tabla de 220 x 389 cm realizado entre 1500 y 1505', 'jardin', 'Pintura', 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/ae/El_jard%C3%ADn_de_las_Delicias%2C_de_El_Bosco.jpg/1920px-El_jard%C3%ADn_de_las_Delicias%2C_de_El_Bosco.jpg', 2, 6),
('David', 'Escultura de mármol blanco de 5,17 m de altura y 5572 kg de masa realizada entre 1501 y 1504', 'david', 'Escultura', 'https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Michelangelo%27s_David_-_right_view_2.jpg/800px-Michelangelo%27s_David_-_right_view_2.jpg', 6, 8),
('Piedad del Vaticano', 'Escultura de mármol de 1,74 x 1,95 m realizada entre 1498 y 1499', 'piedad', 'Escultura', 'https://upload.wikimedia.org/wikipedia/commons/thumb/8/8a/Michelangelo%27s_Pieta_5450_cropncleaned.jpg/800px-Michelangelo%27s_Pieta_5450_cropncleaned.jpg', 8, 8),
('La Escuela de Atenas', 'Pintura al fresco de 500 x 770 cm realizada entre 1509 y 1511', 'escuela', 'Pintura', 'https://upload.wikimedia.org/wikipedia/commons/3/31/La_scuola_di_Atene.jpg', 8, 9),
('El Nacimiento de Venus', 'Temple sobre lienzo de 278,5 x 172,5 cm realizado entre 1482 y 1485', 'nacimiento', 'Pintura', 'https://upload.wikimedia.org/wikipedia/commons/thumb/0/0b/Sandro_Botticelli_-_La_nascita_di_Venere_-_Google_Art_Project_-_edited.jpg/1280px-Sandro_Botticelli_-_La_nascita_di_Venere_-_Google_Art_Project_-_edited.jpg', 7, 7),
('Frieda y Diego Rivera', 'Óleo sobre lienzo de 100 x 78,74 cm realizado en 1931', 'frieda', 'Pintura', 'https://upload.wikimedia.org/wikipedia/en/7/75/Frieda_and_Diego_Rivera.jpg', 9, 10),
('La Libertad Guiando al Pueblo', 'Óleo sobre lienzo de 260 x 325 cm realizado en 1830', 'libertad', 'Pintura','https://upload.wikimedia.org/wikipedia/commons/thumb/5/5d/Eug%C3%A8ne_Delacroix_-_Le_28_Juillet._La_Libert%C3%A9_guidant_le_peuple.jpg/1024px-Eug%C3%A8ne_Delacroix_-_Le_28_Juillet._La_Libert%C3%A9_guidant_le_peuple.jpg', 4, 11),
('El Pensador', 'Escultura de bronce de 186 cm de alto realizada en 1904', 'pensador', 'Escultura', 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/a5/Mus%C3%A9e_Rodin_1.jpg/800px-Mus%C3%A9e_Rodin_1.jpg', 12, 10),
('Sagrada Familia', 'Basílica de 90 x 60 x 170 m comenzada en 1882', 'familia', 'Arquitectura', 'https://d3tf9yuhsp2bpn.cloudfront.net/visita_guiada_sagrada_familia_tu_experiencia_320190430040455.jpg', NULL, 13),
('Torre Eiffel', 'Torre de observación de hierro de 125 x 125 x 300 m realizada entre 1887 y 1889', 'torre', 'Arquitectura', 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/d2/Eiffelturm.JPG/800px-Eiffelturm.jpg', NULL, 14), -----------
('Ciudad Prohibida', 'Complejo palaciego de madera de 72 ha realizado entre 1406 y 1420', 'ciudad', 'Arquitectura', 17, 22),            
('Ecce Homo de Borja', 'Óleo sobre lienzo de 66 x 40 cm realizado en 1930', 'borja', 'Pintura', 11, 15),
('Tres Bellezas de Nuestros Días', 'Impresión xilográficade de 37,9 x 24,9 cm realizada en 1793', 'bellezas', 'Ukiyo-e', 12, 16),
('Otani Oniji III', 'Impresión xilográficade de 38 x 25 cm realizada en 1794', 'otani', 'Ukiyo-e', 3, 17),
('Babel 2001', 'Torre circular compuesta por cientos de radios analógicas de segunda mano', 'babel', 'Escultura', 13, 18),
('Guernica', 'Óleo sobre lienzo de 776,6 x 349,3 cm realizado en 1937', 'guernica', 'Pintura', 14, 19),
('Mamá', 'Escultura de bronce de 10,2 x 8,9 m y 3658 kg realizada en 1999', 'mama', 'Escultura', 15, 20),
('La Piedad', 'Escultura de madera policromada realizada en 1616', 'piedad', 'Escultura', 16, 21),
('Cristo Yacente', 'Escultura de madera policromada realizada en 1627', 'tumbado', 'Escultura', 16, 21),
('Inmaculada Concepción', 'Escultura de madera policromada realizada en 1626', 'inmaculada', 'Escultura', 18, 21),
('Santa Teresa de Jesús', 'Escultura de madera policromada realizada en 1625', 'teresa', 'Escultura', 16, 21),
('Cristo Atado a la Columna', 'Escultura de madera policromada realizada en 1619', 'columna', 'Escultura', 19, 21),
('La Dolorosa de la Vera Cruz', 'Escultura de madera policromada realizada en 1623', 'dolorosa', 'Escultura', 19, 21),
('Éxtasis de Santa Teresa', 'Escultura de mármol blanco realizada entre 1645 y 1652', 'teresa', 'Escultura', 20, 23),
('Dolorosa de Luján Pérez', 'Escultura de madera policromada realizada en 1803', 'dolorosa', 'Escultura', 21, 24),
('Cristo Atado a la Columna', 'Escultura de barro cocido realizada en 1490', 'columna', 'Escultura', 22, 25),
('Inmaculada Concepción', 'Escultura de madera policromada realizada entre 1615 y 1627', 'inmaculada', 'Escultura', 16, 26),
('Cristo velado', 'Escultura de mármol blanco realizada en 1753', 'tumbado', 'Escultura', 23, 27);
*/
-- --------------------------------------------------------


--
-- Estructura de tabla para la tabla `paises`
--

CREATE TABLE `paises` (
  `id_pais` int(11) NOT NULL,
  `nombre_pais` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------
/*
INSERT INTO `paises` (`nombre_pais`) VALUES ('China'), 
('España'), ('Estados Unidos'), ('Francia'), ('India'), 
('Reino Unido'), ('Italia'), ('Japón'), ('Turquía'), 
('Países Bajos'), ('México'), ('Brasil'), ('Alemania'), 
('Ciudad del Vaticano');
*/
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ranking`
--

CREATE TABLE `ranking` (
  `id_ranking` int(11) NOT NULL,
  `nombre_usuario` varchar(10) NOT NULL,
  `puntuacion` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `autores`
--
ALTER TABLE `autores`
  ADD PRIMARY KEY (`id_autor`),
  ADD KEY `id_pais` (`id_pais`) USING BTREE;

--
-- Indices de la tabla `museos`
--
ALTER TABLE `museos`
  ADD PRIMARY KEY (`id_museo`),
  ADD KEY `id_pais` (`id_pais`) USING BTREE;

--
-- Indices de la tabla `obras`
--
ALTER TABLE `obras`
  ADD PRIMARY KEY (`id_obra`),
  ADD KEY `id_museo` (`id_museo`),
  ADD KEY `id_autor` (`id_autor`);

--
-- Indices de la tabla `paises`
--
ALTER TABLE `paises`
  ADD PRIMARY KEY (`id_pais`);

--
-- Indices de la tabla `ranking`
--
ALTER TABLE `ranking`
  ADD PRIMARY KEY (`id_ranking`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `autores`
--
ALTER TABLE `autores`
  MODIFY `id_autor` int(11) NOT NULL AUTO_INCREMENT COMMENT 'PK';

--
-- AUTO_INCREMENT de la tabla `museos`
--
ALTER TABLE `museos`
  MODIFY `id_museo` int(11) NOT NULL AUTO_INCREMENT COMMENT 'PK';

--
-- AUTO_INCREMENT de la tabla `obras`
--
ALTER TABLE `obras`
  MODIFY `id_obra` int(11) NOT NULL AUTO_INCREMENT COMMENT 'PK';

--
-- AUTO_INCREMENT de la tabla `paises`
--
ALTER TABLE `paises`
  MODIFY `id_pais` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `ranking`
--
ALTER TABLE `ranking`
  MODIFY `id_ranking` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `autores`
--
ALTER TABLE `autores`
  ADD CONSTRAINT `autores_ibfk_1` FOREIGN KEY (`id_pais`) REFERENCES `paises` (`id_pais`);

--
-- Filtros para la tabla `museos`
--
ALTER TABLE `museos`
  ADD CONSTRAINT `museos_ibfk_1` FOREIGN KEY (`id_pais`) REFERENCES `paises` (`id_pais`);

--
-- Filtros para la tabla `obras`
--
ALTER TABLE `obras`
  ADD CONSTRAINT `obras_ibfk_1` FOREIGN KEY (`id_museo`) REFERENCES `museos` (`id_museo`),
  ADD CONSTRAINT `obras_ibfk_2` FOREIGN KEY (`id_autor`) REFERENCES `autores` (`id_autor`);
COMMIT;

INSERT INTO `paises` (`nombre_pais`) VALUES ('China'), ('España'), ('Estados Unidos'), 
('Francia'), ('India'), ('Inglaterra'), ('Italia'), ('Japón'), ('Turquía'), 
('Países Bajos'), ('México'), ('Brasil'), ('Alemania'), ('Ciudad del Vaticano');

INSERT INTO `museos` (`nombre_museo`, `id_pais`) VALUES ('Nueva Pinacoteca de Múnich', 13), ('Museo del Prado', 2),
('Museo Metropolitano de Arte de Nueva York', 3), ('Museo del Louvre', 4), ('Santa Maria del las Gracias', 7), 
('Galería de la Academia', 7), ('Galería Uffizi', 7), ('Museos Vaticanos', 14), ('Museo de Arte Moderno de San Francisco', 3), 
('Museo Rodin', 4), ('Santuario de Misericordia de Borja', 2), ('Museo Koishikawa Ukiyo-e', 8), ('Museo Tate Modern', 5), 
('Museo Nacional Centro de Arte Reina Sofía', 2), ('Museo Guggenheim', 2), ('Museo Nacional de Escultura', 2), 
('Museo del Palacio', 1), ('Catedral de Astorga', 2), ('Iglesia Penitencial de la Santa Vera Cruz', 2), 
('Iglesia de Santa María de la Victoria', 7), ('Catedral de Canarias', 2), ('Museo de Bellas Artes de Sevilla' , 2),
('Museo Nacional de San Martino', 7), ('Museo de los Inodoros', 5), ('Museo del Alcantarillado', 4), 
('Museo de los Excrementos', 7), ('Museo del Agua de Grifo', 1), ('Museo del Cabello', 9), ('Museo del Calcetín', 8), 
('Museo de los Orinales', 2), ('Museo de la Comida Quemada', 3), ('Museo de la Tortura', 2), ('Museo del Collar de Perro ', 5);

INSERT INTO `autores` (`nombre_autor`, `id_pais`) 
VALUES ('Vincent van Gogh', 10), ('Diego Velázquez', 2), 
('Katsushika Hokusai', 8), ('Leonardo da Vinci', 7), 
('Francisco de Goya', 2), ('El Bosco', 10), ('Sandro Boticcelli', 7),
('Miguel Ángel', 7), ('Rafael Sanzio', 7), ('Frida Kahlo', 11),
('Eugène Delacroix', 4), ('Auguste Rodin', 4), ('Antoni Gaudí', 2),
('Alexandre Gustave', 4), ('Cecilia Giménez Zueco', 2), ('Kotagawa Utamuro', 8),
('Otani Oniji III', 8), ('Cildo Mireies', 12), ('Pablo Picasso', 2),
('Louise Bourgeois', 4), ('Gregorio Fernández', 2), ('Kuai Xiang', 1),
('Gian Lorenzo Bernini', 7), ('José Luján Pérez', 2), ('Pedro Millán', 2),
('Juan de Mesa', 2), ('Giuseppe Sanmartino', 7);

INSERT INTO `obras` (`nombre_obra`, `descripcion_obra`, `clave_obra`, `disciplina`, `imagen_obra`,`id_museo`, `id_autor`)
VALUES ('Los Girasoles', 'Óleo sobre lienzo de 93 x 72 cm realizado en 1888', 'girasoles', 'Pintura', 'http://4.bp.blogspot.com/-vnKoUZlLuSQ/VPevhmY4FKI/AAAAAAAAADg/Paj7GQf2o5c/s1600/Vincent_Willem_van_Gogh_128.jpg', 1, 1),
('Las Meninas', 'Óleo sobre lienzo de 320,5 x 281,5 cm realizado en 1656', 'meninas', 'Pintura', 'https://4.bp.blogspot.com/-Og960_owCP4/WNv0JgvSivI/AAAAAAAAAn4/NsalJJ63tBk0JGd1P3nINYo2WBS_BG6VwCEw/w1200-h630-p-k-no-nu/Las_Meninas_01.jpg', 2, 2),
('La Gran Ola de Kanagawa', 'Impresión xilográfica de 25 x 37 cm realizado entre 1830 y 1833', 'ola', 'Ukiyo-e', 'https://upload.wikimedia.org/wikipedia/commons/thumb/0/0d/Great_Wave_off_Kanagawa2.jpg/1024px-Great_Wave_off_Kanagawa2.jpg', 3, 3),
('La Gioconda', 'Óleo sobre tabla de álamo de 79 x 53 cm realizado entre 1503 y 1519', 'gioconda', 'Pintura', 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Leonardo_da_Vinci_-_Mona_Lisa_%28Louvre%2C_Paris%29.jpg/800px-Leonardo_da_Vinci_-_Mona_Lisa_%28Louvre%2C_Paris%29.jpg', 4, 4),
('La Última Cena', 'Óleo sobre yeso de 880 x 460 cm realizado entre 1495 y 1498', 'cena', 'Pintura', 'https://upload.wikimedia.org/wikipedia/commons/thumb/4/48/The_Last_Supper_-_Leonardo_Da_Vinci_-_High_Resolution_32x16.jpg/1920px-The_Last_Supper_-_Leonardo_Da_Vinci_-_High_Resolution_32x16.jpg', 5, 4),
('Saturno', 'Óleo sobre revoco de 146 x 83 cm realizado entre 1820 y 1823', 'saturno', 'Pintura', 'https://upload.wikimedia.org/wikipedia/commons/thumb/8/82/Francisco_de_Goya%2C_Saturno_devorando_a_su_hijo_%281819-1823%29.jpg/800px-Francisco_de_Goya%2C_Saturno_devorando_a_su_hijo_%281819-1823%29.jpg', 2, 5),
('La Maja Vestida', 'Óleo sobre lienzo de 95 x 188 cm realizado entre 1800 y 1808', 'maja', 'Pintura', 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/a8/Maja_vestida_%28Prado%29.jpg/1920px-Maja_vestida_%28Prado%29.jpg', 2, 5),
('El Jardín de las Delicias', 'Óleo sobre tabla de 220 x 389 cm realizado entre 1500 y 1505', 'jardin', 'Pintura', 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/ae/El_jard%C3%ADn_de_las_Delicias%2C_de_El_Bosco.jpg/1920px-El_jard%C3%ADn_de_las_Delicias%2C_de_El_Bosco.jpg', 2, 6),
('David', 'Escultura de mármol blanco de 5,17 m de altura y 5572 kg de masa realizada entre 1501 y 1504', 'david', 'Escultura', 'https://upload.wikimedia.org/wikipedia/commons/thumb/8/80/Michelangelo%27s_David_-_right_view_2.jpg/800px-Michelangelo%27s_David_-_right_view_2.jpg', 6, 8),
('Piedad del Vaticano', 'Escultura de mármol de 1,74 x 1,95 m realizada entre 1498 y 1499', 'piedad', 'Escultura', 'https://upload.wikimedia.org/wikipedia/commons/thumb/8/8a/Michelangelo%27s_Pieta_5450_cropncleaned.jpg/800px-Michelangelo%27s_Pieta_5450_cropncleaned.jpg', 8, 8),
('La Escuela de Atenas', 'Pintura al fresco de 500 x 770 cm realizada entre 1509 y 1511', 'escuela', 'Pintura', 'https://upload.wikimedia.org/wikipedia/commons/3/31/La_scuola_di_Atene.jpg', 8, 9),
('El Nacimiento de Venus', 'Temple sobre lienzo de 278,5 x 172,5 cm realizado entre 1482 y 1485', 'nacimiento', 'Pintura', 'https://upload.wikimedia.org/wikipedia/commons/thumb/0/0b/Sandro_Botticelli_-_La_nascita_di_Venere_-_Google_Art_Project_-_edited.jpg/1280px-Sandro_Botticelli_-_La_nascita_di_Venere_-_Google_Art_Project_-_edited.jpg', 7, 7),
('Frieda y Diego Rivera', 'Óleo sobre lienzo de 100 x 78,74 cm realizado en 1931', 'frieda', 'Pintura', 'https://upload.wikimedia.org/wikipedia/en/7/75/Frieda_and_Diego_Rivera.jpg', 9, 10),
('La Libertad Guiando al Pueblo', 'Óleo sobre lienzo de 260 x 325 cm realizado en 1830', 'libertad', 'Pintura','https://upload.wikimedia.org/wikipedia/commons/thumb/5/5d/Eug%C3%A8ne_Delacroix_-_Le_28_Juillet._La_Libert%C3%A9_guidant_le_peuple.jpg/1024px-Eug%C3%A8ne_Delacroix_-_Le_28_Juillet._La_Libert%C3%A9_guidant_le_peuple.jpg', 4, 11),
('El Pensador', 'Escultura de bronce de 186 cm de alto realizada en 1904', 'pensador', 'Escultura', 'https://upload.wikimedia.org/wikipedia/commons/thumb/a/a5/Mus%C3%A9e_Rodin_1.jpg/800px-Mus%C3%A9e_Rodin_1.jpg', 12, 10),
('Sagrada Familia', 'Basílica de 90 x 60 x 170 m comenzada en 1882', 'familia', 'Arquitectura', 'https://d3tf9yuhsp2bpn.cloudfront.net/visita_guiada_sagrada_familia_tu_experiencia_320190430040455.jpg', NULL, 13),
('Torre Eiffel', 'Torre de observación de hierro de 125 x 125 x 300 m realizada entre 1887 y 1889', 'torre', 'Arquitectura', 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/d2/Eiffelturm.JPG/800px-Eiffelturm.jpg', NULL, 14),
('Ciudad Prohibida', 'Complejo palaciego de madera de 72 ha realizado entre 1406 y 1420', 'ciudad', 'Arquitectura', 17, 22),            
('Ecce Homo de Borja', 'Óleo sobre lienzo de 66 x 40 cm realizado en 1930', 'borja', 'Pintura', 11, 15),
('Tres Bellezas de Nuestros Días', 'Impresión xilográficade de 37,9 x 24,9 cm realizada en 1793', 'bellezas', 'Ukiyo-e', 12, 16),
('Otani Oniji III', 'Impresión xilográficade de 38 x 25 cm realizada en 1794', 'otani', 'Ukiyo-e', 3, 17),
('Babel 2001', 'Torre circular compuesta por cientos de radios analógicas de segunda mano', 'babel', 'Escultura', 13, 18),
('Guernica', 'Óleo sobre lienzo de 776,6 x 349,3 cm realizado en 1937', 'guernica', 'Pintura', 14, 19),
('Mamá', 'Escultura de bronce de 10,2 x 8,9 m y 3658 kg realizada en 1999', 'mama', 'Escultura', 15, 20),
('La Piedad', 'Escultura de madera policromada realizada en 1616', 'piedad', 'Escultura', 16, 21),
('Cristo Yacente', 'Escultura de madera policromada realizada en 1627', 'tumbado', 'Escultura', 16, 21),
('Inmaculada Concepción', 'Escultura de madera policromada realizada en 1626', 'inmaculada', 'Escultura', 18, 21),
('Santa Teresa de Jesús', 'Escultura de madera policromada realizada en 1625', 'teresa', 'Escultura', 16, 21),
('Cristo Atado a la Columna', 'Escultura de madera policromada realizada en 1619', 'columna', 'Escultura', 19, 21),
('La Dolorosa de la Vera Cruz', 'Escultura de madera policromada realizada en 1623', 'dolorosa', 'Escultura', 19, 21),
('Éxtasis de Santa Teresa', 'Escultura de mármol blanco realizada entre 1645 y 1652', 'teresa', 'Escultura', 20, 23),
('Dolorosa de Luján Pérez', 'Escultura de madera policromada realizada en 1803', 'dolorosa', 'Escultura', 21, 24),
('Cristo Atado a la Columna', 'Escultura de barro cocido realizada en 1490', 'columna', 'Escultura', 22, 25),
('Inmaculada Concepción', 'Escultura de madera policromada realizada entre 1615 y 1627', 'inmaculada', 'Escultura', 16, 26),
('Cristo velado', 'Escultura de mármol blanco realizada en 1753', 'tumbado', 'Escultura', 23, 27);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
