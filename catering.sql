-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 13, 2024 at 04:27 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `catering`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `kata_sandi` varchar(255) DEFAULT NULL,
  `gaji` int(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`admin_id`, `nama`, `kata_sandi`, `gaji`) VALUES
(1, 'admin', 'admin123', 5000000);

-- --------------------------------------------------------

--
-- Table structure for table `jadwal_menu`
--

CREATE TABLE `jadwal_menu` (
  `jadwal_menu_id` int(11) NOT NULL,
  `menu_id` int(11) DEFAULT NULL,
  `tanggal_tersedia` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `jadwal_menu`
--

INSERT INTO `jadwal_menu` (`jadwal_menu_id`, `menu_id`, `tanggal_tersedia`) VALUES
(1, 1, '1'),
(2, 2, '1'),
(3, 3, '1'),
(4, 4, '2'),
(5, 5, '2'),
(6, 6, '2'),
(7, 7, '1'),
(8, 8, '1');

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `menu_id` int(11) NOT NULL,
  `nama_menu` varchar(255) DEFAULT NULL,
  `deskripsi_menu` varchar(255) DEFAULT NULL,
  `harga_menu` double DEFAULT NULL,
  `tanggal_tersedia` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`menu_id`, `nama_menu`, `deskripsi_menu`, `harga_menu`, `tanggal_tersedia`) VALUES
(1, 'Ayam mentai', 'Ayam mentai adalah hidangan ayam yang disajikan dengan saus krim beraroma gurih dan sedikit pedas.', 15000, '1'),
(2, 'Nasi Goreng Jawa', 'Nasi goreng Jawa adalah hidangan nasi goreng khas Jawa yang biasanya menggunakan bumbu-bumbu tradisional seperti kecap manis, bawang merah, bawang putih, dan biasanya disajikan dengan telur mata sapi serta kerupuk sebagai pelengkap.', 25000, '1'),
(3, 'Ayam Bakar Kecap', 'Ayam bakar kecap adalah hidangan ayam panggang yang dibumbui dengan kecap manis, bawang putih, dan rempah-rempah lainnya untuk memberikan cita rasa gurih dan manis pada daging ayam.', 23000, '1'),
(4, 'Ambokueh', 'Ambokueh adalah sejenis kue tradisional dari Indonesia, khususnya daerah Palembang, Sumatera Selatan. Kue ini terbuat dari beras ketan yang difermentasi, kemudian dikukus dan disajikan dengan taburan kelapa parut.', 20000, '2'),
(5, 'Sate padang', 'Sate Padang adalah hidangan sate khas dari Padang, Sumatera Barat, yang terkenal dengan bumbu kacangnya yang kaya rempah dan biasanya disajikan dengan kuah gulai khas Padang.', 20000, '2'),
(6, 'Bakmi Baso Ayam', 'Bakmi Baso Ayam adalah hidangan mi pangsit dengan tambahan bakso dan potongan daging ayam, disajikan dengan kuah kaldu ayam dan pelengkap seperti sayuran dan bawang goreng.', 15000, '2'),
(7, 'Ayam Pop', 'Ayam Pop adalah hidangan kuliner Indonesia yang terkenal, terdiri dari potongan ayam goreng kering dengan bumbu rempah yang khas, biasanya disajikan dengan nasi putih dan sambal.', 25000, '1'),
(8, 'sapipanggang', 'sapi enak', 20000, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `pesanan`
--

CREATE TABLE `pesanan` (
  `pesanan_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `tanggal_pesanan` varchar(10) DEFAULT NULL,
  `status_pesanan` enum('diproses','diantrian','selesai','dibatalkan') DEFAULT NULL,
  `total_harga` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pesanan`
--

INSERT INTO `pesanan` (`pesanan_id`, `user_id`, `tanggal_pesanan`, `status_pesanan`, `total_harga`) VALUES
(1, 1, '1', 'selesai', 40000),
(2, 1, '1', 'diproses', 25000),
(3, 2, '1', 'diproses', 15000),
(6, 1, '1', 'dibatalkan', 0),
(7, 1, '1', 'diantrian', 30000);

-- --------------------------------------------------------

--
-- Table structure for table `pesanan_detail`
--

CREATE TABLE `pesanan_detail` (
  `detail_pesanan_id` int(11) NOT NULL,
  `pesanan_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  `jumlah_pesanan` int(11) DEFAULT NULL,
  `harga_menu` double DEFAULT NULL,
  `keuntungan` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pesanan_detail`
--

INSERT INTO `pesanan_detail` (`detail_pesanan_id`, `pesanan_id`, `menu_id`, `jumlah_pesanan`, `harga_menu`, `keuntungan`) VALUES
(1, 1, 2, 1, 25000, 25000),
(2, 1, 3, 1, 15000, 15000),
(3, 1, 2, 1, 25000, 25000),
(4, 2, 3, 1, 15000, 15000),
(5, 6, 1, 2, 15000, NULL),
(6, 7, 1, 2, 15000, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `kata_sandi` varchar(255) DEFAULT NULL,
  `alamat` varchar(255) DEFAULT NULL,
  `daerah` varchar(255) DEFAULT NULL,
  `no_telepon` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `nama`, `kata_sandi`, `alamat`, `daerah`, `no_telepon`) VALUES
(1, 'riko', 'riko123', '1', 'Cimahi', '088219879751'),
(2, 'dani', 'dani123', 'Region 3', 'Jamika', '123123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`admin_id`);

--
-- Indexes for table `jadwal_menu`
--
ALTER TABLE `jadwal_menu`
  ADD PRIMARY KEY (`jadwal_menu_id`),
  ADD KEY `menu_id` (`menu_id`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`menu_id`);

--
-- Indexes for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD PRIMARY KEY (`pesanan_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `pesanan_detail`
--
ALTER TABLE `pesanan_detail`
  ADD PRIMARY KEY (`detail_pesanan_id`),
  ADD KEY `pesanan_id` (`pesanan_id`),
  ADD KEY `menu_id` (`menu_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `admin_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `jadwal_menu`
--
ALTER TABLE `jadwal_menu`
  MODIFY `jadwal_menu_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `menu`
--
ALTER TABLE `menu`
  MODIFY `menu_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `pesanan`
--
ALTER TABLE `pesanan`
  MODIFY `pesanan_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `pesanan_detail`
--
ALTER TABLE `pesanan_detail`
  MODIFY `detail_pesanan_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `jadwal_menu`
--
ALTER TABLE `jadwal_menu`
  ADD CONSTRAINT `jadwal_menu_ibfk_1` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`);

--
-- Constraints for table `pesanan`
--
ALTER TABLE `pesanan`
  ADD CONSTRAINT `pesanan_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `pesanan_detail`
--
ALTER TABLE `pesanan_detail`
  ADD CONSTRAINT `pesanan_detail_ibfk_1` FOREIGN KEY (`pesanan_id`) REFERENCES `pesanan` (`pesanan_id`),
  ADD CONSTRAINT `pesanan_detail_ibfk_2` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
