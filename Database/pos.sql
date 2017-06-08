-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 29, 2017 at 05:51 PM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 7.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pos`
--

-- --------------------------------------------------------

--
-- Table structure for table `company`
--

CREATE TABLE `company` (
  `id` int(11) NOT NULL,
  `name` varchar(200) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `company`
--

INSERT INTO `company` (`id`, `name`, `address`, `phone`) VALUES
(1, 'Game Store', '277, Luq Briffa Street, Fgura', '+356 21314151');

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `surname` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `company_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`id`, `name`, `surname`, `phone`, `company_id`) VALUES
(1, 'Alexander', 'Mifsud', '+356 79973936', 1),
(2, 'James', 'DeCoeur', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `description`) VALUES
(1, 'Metal Gear Solid V', 'The fisth installment of the metal gear series and the last featuring koshima'),
(2, 'Final Fantasy XV', 'The fifteenth installment of the Final Fantasy Series, the first even open world gameplay, winner of the 2016 Game of the Year Edition'),
(3, 'Horizon Dawn', 'Fresh new Game that has just been released and is capturing the hearts of many gamers');

-- --------------------------------------------------------

--
-- Table structure for table `productPrice`
--

CREATE TABLE `productPrice` (
  `id` int(11) NOT NULL,
  `effectiveDate` datetime DEFAULT NULL,
  `price` decimal(10,3) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `productPrice`
--

INSERT INTO `productPrice` (`id`, `effectiveDate`, `price`, `product_id`) VALUES
(1, '2017-01-01 00:00:00', '49.950', 1),
(2, '2017-02-01 00:00:00', '45.950', 1),
(3, '2017-03-01 00:00:00', '39.950', 1),
(4, '2017-01-01 00:00:00', '64.950', 2),
(5, '2017-02-01 00:00:00', '60.950', 2),
(6, '2017-03-01 00:00:00', '55.950', 2),
(7, '2017-01-01 00:00:00', '64.950', 3),
(8, '2017-02-01 00:00:00', '62.950', 3),
(9, '2017-03-01 00:00:00', '60.950', 3),
(10, '0000-00-00 00:00:00', '55.950', 2);

-- --------------------------------------------------------

--
-- Table structure for table `transactionHeader`
--

CREATE TABLE `transactionHeader` (
  `id` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `totalprice` decimal(10,3) DEFAULT NULL,
  `employee_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transactionHeader`
--

INSERT INTO `transactionHeader` (`id`, `date`, `totalprice`, `employee_id`) VALUES
(3, '2017-02-21 20:22:49', '0.000', 1),
(4, '2017-02-21 20:26:07', '0.000', 1),
(5, '2017-02-21 20:27:20', '0.000', 1),
(6, '2017-02-21 20:28:40', '0.000', 1),
(7, '2017-02-21 20:31:05', '0.000', 1),
(8, '2017-02-21 20:33:07', '0.000', 1),
(9, '2017-02-21 20:36:57', '0.000', 1),
(10, '2017-02-21 20:42:21', '0.000', 1),
(11, '2017-02-21 20:50:12', '0.000', 1),
(12, '2017-02-21 20:52:24', '0.000', 1),
(13, '2017-02-21 20:54:05', '0.000', 1),
(14, '2017-02-21 21:00:00', '188.850', 1),
(15, '2017-02-21 21:01:24', '350.700', 1),
(16, '2017-02-26 16:11:57', '0.000', 1),
(17, '2017-02-26 16:13:57', '0.000', 1),
(18, '2017-02-26 16:18:58', '0.000', 1),
(19, '2017-02-26 16:20:27', '0.000', 1),
(20, '2017-02-26 16:23:08', '0.000', 1),
(21, '2017-02-26 16:24:28', '0.000', 1),
(22, '2017-02-26 16:26:23', '0.000', 1),
(23, '2017-02-26 16:30:15', '0.000', 1),
(24, '2017-02-26 16:32:04', '0.000', 1),
(25, '2017-02-26 16:40:09', '0.000', 1),
(26, '2017-02-26 16:42:34', '0.000', 1),
(27, '2017-02-26 17:02:26', '0.000', 1),
(28, '2017-02-26 17:21:43', '1279.950', 1),
(29, '2017-02-26 17:25:51', '0.000', 1),
(30, '2017-02-26 17:32:59', '91.900', 1),
(31, '2017-02-26 17:35:27', '1447.850', 1),
(32, '2017-02-26 17:53:16', '0.000', 1),
(33, '2017-02-27 08:09:56', '832.150', 1),
(34, '2017-02-27 08:17:19', '106.900', 1),
(35, '2017-02-27 08:24:48', '0.000', 1),
(36, '2017-02-27 08:26:48', '45.950', 1),
(37, '2017-02-27 08:29:43', '123.900', 1),
(38, '2017-02-27 08:34:15', '45.950', 1),
(39, '2017-02-27 08:35:41', '0.000', 1),
(40, '2017-02-27 08:38:34', '0.000', 1),
(41, '2017-02-27 08:42:56', '45.950', 1),
(42, '2017-02-27 08:58:34', '45.950', 1),
(43, '2017-02-27 08:59:35', '45.950', 1),
(44, '2017-02-27 09:03:41', '45.950', 1),
(45, '2017-02-27 09:11:20', '45.950', 1),
(46, '2017-02-27 09:14:00', '91.900', 1),
(47, '2017-02-27 09:15:40', '125.900', 1),
(48, '2017-02-28 19:51:57', '228.800', 1),
(49, '2017-02-28 20:53:59', '182.850', 1),
(50, '2017-02-28 20:57:19', '0.000', 1),
(51, '2017-02-28 21:01:38', '169.850', 1),
(52, '2017-02-28 21:10:01', '60.950', 1),
(53, '2017-02-28 21:12:37', '137.850', 1),
(54, '2017-02-28 21:17:54', '91.900', 1),
(55, '2017-03-03 20:09:25', '239.700', 1),
(56, '2017-03-03 20:31:35', '60.950', 1),
(57, '2017-03-04 11:36:30', '0.000', 1),
(58, '2017-03-04 11:37:12', '292.700', 1),
(61, '2017-03-05 20:24:12', '159.800', 1),
(62, '2017-03-05 20:24:15', '0.000', 1),
(63, '2017-03-05 20:25:50', '156.850', 1),
(67, '2017-03-05 21:24:03', '172.850', 2),
(69, '2017-03-06 07:55:33', '95.900', 1),
(74, '2017-03-06 08:18:36', '111.900', 2),
(75, '2017-03-06 08:24:29', '263.750', 1),
(76, '2017-03-08 08:59:23', '79.900', 1);

-- --------------------------------------------------------

--
-- Table structure for table `transactionItem`
--

CREATE TABLE `transactionItem` (
  `id` int(11) NOT NULL,
  `transactionheader_id` int(11) NOT NULL,
  `productprice_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transactionItem`
--

INSERT INTO `transactionItem` (`id`, `transactionheader_id`, `productprice_id`, `quantity`) VALUES
(4, 13, 2, 2),
(5, 14, 8, 3),
(6, 15, 2, 1),
(7, 15, 5, 5),
(8, 21, 2, 1),
(9, 22, 2, 1),
(10, 23, 5, 3),
(11, 24, 2, 1),
(12, 25, 2, 1),
(13, 25, 5, 2),
(14, 26, 2, 1),
(15, 26, 5, 2),
(16, 27, 2, 1),
(17, 28, 5, 21),
(18, 29, 5, 2),
(19, 30, 2, 2),
(20, 31, 8, 23),
(21, 32, 2, 2),
(22, 33, 2, 2),
(23, 33, 2, 12),
(24, 33, 8, 3),
(25, 34, 2, 1),
(26, 34, 5, 1),
(27, 36, 2, 1),
(28, 37, 5, 1),
(29, 37, 8, 1),
(30, 38, 2, 1),
(31, 41, 2, 1),
(32, 42, 2, 1),
(33, 43, 2, 1),
(34, 44, 2, 1),
(35, 45, 2, 1),
(36, 46, 2, 2),
(37, 47, 8, 2),
(38, 48, 2, 1),
(39, 48, 5, 3),
(40, 49, 5, 3),
(41, 51, 2, 1),
(42, 51, 5, 1),
(43, 51, 8, 1),
(44, 52, 5, 1),
(45, 53, 2, 3),
(46, 54, 2, 2),
(47, 55, 3, 4),
(48, 55, 3, 2),
(49, 56, 9, 1),
(50, 58, 3, 1),
(51, 58, 6, 1),
(52, 58, 9, 1),
(53, 58, 3, 2),
(54, 58, 6, 1),
(56, 61, 3, 2),
(57, 61, 3, 2),
(59, 62, 3, 2),
(60, 62, 6, 2),
(61, 62, 9, 2),
(62, 63, 3, 1),
(63, 63, 6, 1),
(64, 63, 9, 1),
(65, 67, 6, 2),
(66, 67, 9, 1),
(67, 69, 3, 1),
(68, 69, 6, 1),
(70, 74, 6, 1),
(71, 74, 6, 1),
(72, 75, 6, 2),
(73, 75, 3, 1),
(74, 75, 6, 2),
(75, 76, 3, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `company`
--
ALTER TABLE `company`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`id`),
  ADD KEY `company_id` (`company_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `productPrice`
--
ALTER TABLE `productPrice`
  ADD PRIMARY KEY (`id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `transactionHeader`
--
ALTER TABLE `transactionHeader`
  ADD PRIMARY KEY (`id`),
  ADD KEY `employee_id` (`employee_id`);

--
-- Indexes for table `transactionItem`
--
ALTER TABLE `transactionItem`
  ADD PRIMARY KEY (`id`),
  ADD KEY `transactionheader_id` (`transactionheader_id`),
  ADD KEY `productprice_id` (`productprice_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `company`
--
ALTER TABLE `company`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `productPrice`
--
ALTER TABLE `productPrice`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `transactionHeader`
--
ALTER TABLE `transactionHeader`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=77;
--
-- AUTO_INCREMENT for table `transactionItem`
--
ALTER TABLE `transactionItem`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `employees`
--
ALTER TABLE `employees`
  ADD CONSTRAINT `employees_ibfk_1` FOREIGN KEY (`company_id`) REFERENCES `company` (`id`);

--
-- Constraints for table `productPrice`
--
ALTER TABLE `productPrice`
  ADD CONSTRAINT `productprice_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `transactionHeader`
--
ALTER TABLE `transactionHeader`
  ADD CONSTRAINT `transactionheader_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`);

--
-- Constraints for table `transactionItem`
--
ALTER TABLE `transactionItem`
  ADD CONSTRAINT `transactionitem_ibfk_1` FOREIGN KEY (`transactionheader_id`) REFERENCES `transactionHeader` (`id`),
  ADD CONSTRAINT `transactionitem_ibfk_2` FOREIGN KEY (`productprice_id`) REFERENCES `productPrice` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
