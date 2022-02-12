-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 28 mai 2021 à 21:24
-- Version du serveur :  10.4.18-MariaDB
-- Version de PHP : 7.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `cslibrary`
--

-- --------------------------------------------------------

--
-- Structure de la table `admins`
--

CREATE TABLE `admins` (
  `adminId` int(11) NOT NULL,
  `adminUserName` text NOT NULL,
  `adminPassword` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `admins`
--

INSERT INTO `admins` (`adminId`, `adminUserName`, `adminPassword`) VALUES
(1, 'admin', '123');

-- --------------------------------------------------------

--
-- Structure de la table `books`
--

CREATE TABLE `books` (
  `bookId` int(11) NOT NULL,
  `bookTitle` varchar(30) NOT NULL,
  `bookAuthor` varchar(20) NOT NULL,
  `bookQuantity` int(10) NOT NULL,
  `bookPrice` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `books`
--

INSERT INTO `books` (`bookId`, `bookTitle`, `bookAuthor`, `bookQuantity`, `bookPrice`) VALUES
(24, 'Learn Python the Hard Way', 'Zed Shaw', 16, 10),
(25, 'Effective Java', 'Bloch Joshua', 15, 40),
(26, 'Python Machine Learning', 'Sebastian Raschka', 19, 50);

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

CREATE TABLE `clients` (
  `clientId` int(100) NOT NULL,
  `clientFirstName` varchar(50) NOT NULL,
  `clientLastName` varchar(50) NOT NULL,
  `clientAddress` varchar(50) NOT NULL,
  `clientPhone` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `clients`
--

INSERT INTO `clients` (`clientId`, `clientFirstName`, `clientLastName`, `clientAddress`, `clientPhone`) VALUES
(6, 'Asmae', 'Ghammouri', 'andalous', 656698852),
(7, 'Zahia', 'Elkhalifi', 'hoceima', 356898965),
(8, 'Hajar', 'Kerroumi', 'arfoud', 65656532),
(9, 'Latifa', 'Jabri', 'oujda', 556546453);

-- --------------------------------------------------------

--
-- Structure de la table `purchasedbooks`
--

CREATE TABLE `purchasedbooks` (
  `clientId` int(11) NOT NULL,
  `bookId` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `purchasedbooks`
--



--
-- Index pour les tables déchargées
--

--
-- Index pour la table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`adminId`);

--
-- Index pour la table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`bookId`);

--
-- Index pour la table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`clientId`);

--
-- Index pour la table `purchasedbooks`
--
ALTER TABLE `purchasedbooks`
  ADD KEY `fk_clientId` (`clientId`),
  ADD KEY `fk_bookId` (`bookId`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `admins`
--
ALTER TABLE `admins`
  MODIFY `adminId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `books`
--
ALTER TABLE `books`
  MODIFY `bookId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT pour la table `clients`
--
ALTER TABLE `clients`
  MODIFY `clientId` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `purchasedbooks`
--
ALTER TABLE `purchasedbooks`
  ADD CONSTRAINT `fk_bookId` FOREIGN KEY (`bookId`) REFERENCES `books` (`bookId`),
  ADD CONSTRAINT `fk_clientId` FOREIGN KEY (`clientId`) REFERENCES `clients` (`clientId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
