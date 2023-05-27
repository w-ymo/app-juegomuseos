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
Vincent van Gogh -> Los Girasoles
Diego Velázquez -> Las Meninas
Katsushika Hokusai -> La Gran Ola de Kanagawa
Leonardo da Vinci -> La Gioconda
Leonardo da Vinci -> La Última Cena
Francisco de Goya -> Saturno
Francisco de Goya -> La Maja Vestida
El Bosco -> El Jardín de las Delicias
Miguel Ángel -> El David
Miguel Ángel -> La Piedad
Rafael Sanzio -> La Escuela de Atenas
Sandro Boticcelli -> El Nacimiento de Venus
Frida Kahlo -> Frieda y Diego Rivera
Eugène Delacroix -> La Libertad Guiando al Pueblo
Auguste Rodin -> El Pensador
Antoni Gaudí -> Sagrada Familia
Alexandre Gustave -> Torre Eiffel
Cecilia Giménez Zueco -> Ecce Homo de Borja
Kotagawa Utamaro -> Tres Bellezas de Nuestros Días
Otani Oniji III -> Toshusai Sharaku
Cildo Mireies -> Babel 2001
Pablo Picasso -> Guernica
Louise Bourgeois -> Maman

INSERT INTO `autores` (`nombre_autor`, `id_pais`) 
VALUES ('Vincent van Gogh', 10), ('Diego Velázquez', 2), 
('Katsushika Hokusai', 8), ('Leonardo da Vinci', 7), 
('Francisco de Goya', 2), ('El Bosco', 10), ('Sandro Boticcelli', 7),
('Miguel Ángel', 7), ('Rafael Sanzio', 7), ('Frida Kahlo', 11),
('Eugène Delacroix', 4), ('Auguste Rodin', 4), ('Antoni Gaudí', 2),
('Alexandre Gustave', 4), ('Cecilia Giménez Zueco', 2), ('Kotagawa Utamuro', 8),
('Otani Oniji III', 8), ('Cildo Mireies', 12), ('Pablo Picasso', 2),
('Louise Bourgeois', 4);
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
Museo de los inodoros -> India			          
Museo del alcantarillado -> Francia		        
Museo de los excrementos -> Italia		        
Museo del agua de grifo -> China		          
Museo del cabello -> Turquía			            
Museo del calcetín -> Japón			              
Museo de los orinales -> España			          
Museo de la comida quemada -> Estados Unidos	
Museo de la tortura -> España			            
Museo del collar de perro -> Inglaterra		    

INSERT INTO `museos` (`nombre_museo`, `id_pais`) 
VALUES ('Inodoro', 5), ('Alcantarillado', 4), 
('Excremento', 5), ('Agua de Grifo', 1), ('Cabello', 9), 
('Calcetín', 8), ('Orinal', 2), ('Comida Quemada', 3), 
('Tortura', 2), ('Collar de Perro', 6);
*/
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `obras`
--

CREATE TABLE `obras` (
  `id_obra` int(11) NOT NULL COMMENT 'PK',
  `nombre_obra` varchar(100) NOT NULL,
  `descripción_obra` varchar(100) NOT NULL,
  `clave_obra` varchar(50) NOT NULL,
  `disciplina` varchar(100) NOT NULL,
  `imagen_obra` varchar(200) NOT NULL,
  `id_museo` int(11) NOT NULL COMMENT 'FK -> museos',
  `id_autor` int(11) NOT NULL COMMENT 'FK -> autores'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
('Inglaterra'), ('Italia'), ('Japón'), ('Turquía'), 
('Países Bajos'), ('México') ('Brasil');
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
('Países Bajos'), ('México'), ('Brasil');

INSERT INTO `museos` (`nombre_museo`, `id_pais`) VALUES ('Inodoro', 5), 
('Alcantarillado', 4), ('Excremento', 5), ('Agua de Grifo', 1), ('Cabello', 9), 
('Calcetín', 8), ('Orinal', 2), ('Comida Quemada', 3), ('Tortura', 2), ('Collar de Perro', 6);

INSERT INTO `autores` (`nombre_autor`, `id_pais`) VALUES ('Vincent van Gogh', 10), 
('Diego Velázquez', 2), ('Katsushika Hokusai', 8), ('Leonardo da Vinci', 7), 
('Francisco de Goya', 2), ('El Bosco', 10), ('Sandro Boticcelli', 7),('Miguel Ángel', 7), 
('Rafael Sanzio', 7), ('Frida Kahlo', 11), ('Eugène Delacroix', 4), ('Auguste Rodin', 4), 
('Antoni Gaudí', 2), ('Alexandre Gustave', 4), ('Cecilia Giménez Zueco', 2), 
('Kotagawa Utamuro', 8), ('Otani Oniji III', 8), ('Cildo Mireies', 12), ('Pablo Picasso', 2),
('Louise Bourgeois', 4);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
