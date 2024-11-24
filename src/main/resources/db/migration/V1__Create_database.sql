SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `fast_serwis`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `categories`
--

CREATE TABLE `categories` (
                              `category_id` bigint NOT NULL,
                              `category_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `clients`
--

CREATE TABLE `clients` (
                           `client_id` bigint NOT NULL,
                           `city` varchar(255) DEFAULT NULL,
                           `first_name` varchar(255) DEFAULT NULL,
                           `flat_number` int DEFAULT NULL,
                           `last_name` varchar(255) DEFAULT NULL,
                           `mail` varchar(255) DEFAULT NULL,
                           `phone` varchar(255) DEFAULT NULL,
                           `postal_code` varchar(255) DEFAULT NULL,
                           `street` varchar(255) DEFAULT NULL,
                           `street_number` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `devices`
--

CREATE TABLE `devices` (
                           `device_id` bigint NOT NULL,
                           `model` varchar(255) DEFAULT NULL,
                           `producer` varchar(255) DEFAULT NULL,
                           `serial_number` varchar(255) DEFAULT NULL,
                           `category_id` bigint NOT NULL,
                           `client_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `employees`
--

CREATE TABLE `employees` (
                             `employee_id` bigint NOT NULL,
                             `first_name` varchar(255) DEFAULT NULL,
                             `block` bit(1) DEFAULT NULL,
                             `last_name` varchar(255) DEFAULT NULL,
                             `mail` varchar(255) DEFAULT NULL,
                             `password` varchar(255) DEFAULT NULL,
                             `phone` varchar(255) DEFAULT NULL,
                             `username` varchar(255) DEFAULT NULL,
                             `role_id` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `order_types`
--

CREATE TABLE `order_types` (
                               `order_type_id` bigint NOT NULL,
                               `type_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `repair_orders`
--

CREATE TABLE `repair_orders` (
                                 `order_id` bigint NOT NULL,
                                 `charger` bit(1) DEFAULT NULL,
                                 `end_date` datetime(6) DEFAULT NULL,
                                 `expected_end_date` datetime(6) DEFAULT NULL,
                                 `fault_description` varchar(255) DEFAULT NULL,
                                 `order_date` datetime(6) DEFAULT NULL,
                                 `parts_price` decimal(19,2) DEFAULT NULL,
                                 `repair_description` varchar(255) DEFAULT NULL,
                                 `repair_price` decimal(19,2) DEFAULT NULL,
                                 `device_id` bigint DEFAULT NULL,
                                 `employee_id` bigint DEFAULT NULL,
                                 `order_type_id` bigint DEFAULT NULL,
                                 `status_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `roles`
--

CREATE TABLE `roles` (
                         `role_id` int NOT NULL,
                         `role_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `status`
--

CREATE TABLE `status` (
                          `status_id` bigint NOT NULL,
                          `status_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `categories`
--
ALTER TABLE `categories`
    ADD PRIMARY KEY (`category_id`);

--
-- Indeksy dla tabeli `clients`
--
ALTER TABLE `clients`
    ADD PRIMARY KEY (`client_id`);

--
-- Indeksy dla tabeli `devices`
--
ALTER TABLE `devices`
    ADD PRIMARY KEY (`device_id`),
  ADD KEY `FK328396uetnetexyi8dhfcem6w` (`category_id`),
  ADD KEY `FKd0i1vb74gh9q0iy0s6f64eya4` (`client_id`);

--
-- Indeksy dla tabeli `employees`
--
ALTER TABLE `employees`
    ADD PRIMARY KEY (`employee_id`),
  ADD UNIQUE KEY `UK_3gqbimdf7fckjbwt1kcud141m` (`username`),
  ADD KEY `FKah490190ww1q2a4piuv41hk6e` (`role_id`);

--
-- Indeksy dla tabeli `order_types`
--
ALTER TABLE `order_types`
    ADD PRIMARY KEY (`order_type_id`);

--
-- Indeksy dla tabeli `repair_orders`
--
ALTER TABLE `repair_orders`
    ADD PRIMARY KEY (`order_id`),
  ADD KEY `FKe9m7rqb6eieyj95ny3hcy58c9` (`device_id`),
  ADD KEY `FKe8ilxqakl9nuam61qo8pb2tge` (`employee_id`),
  ADD KEY `FK9m3n0gx72iik77t3qdiefh755` (`order_type_id`),
  ADD KEY `FKm4upyrxq3w0biepitkm8bncr4` (`status_id`);

--
-- Indeksy dla tabeli `roles`
--
ALTER TABLE `roles`
    ADD PRIMARY KEY (`role_id`);

--
-- Indeksy dla tabeli `status`
--
ALTER TABLE `status`
    ADD PRIMARY KEY (`status_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categories`
--
ALTER TABLE `categories`
    MODIFY `category_id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `clients`
--
ALTER TABLE `clients`
    MODIFY `client_id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `devices`
--
ALTER TABLE `devices`
    MODIFY `device_id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
    MODIFY `employee_id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `order_types`
--
ALTER TABLE `order_types`
    MODIFY `order_type_id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `repair_orders`
--
ALTER TABLE `repair_orders`
    MODIFY `order_id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
    MODIFY `role_id` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `status`
--
ALTER TABLE `status`
    MODIFY `status_id` bigint NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `devices`
--
ALTER TABLE `devices`
    ADD CONSTRAINT `FK328396uetnetexyi8dhfcem6w` FOREIGN KEY (`category_id`) REFERENCES `categories` (`category_id`),
  ADD CONSTRAINT `FKd0i1vb74gh9q0iy0s6f64eya4` FOREIGN KEY (`client_id`) REFERENCES `clients` (`client_id`);

--
-- Constraints for table `employees`
--
ALTER TABLE `employees`
    ADD CONSTRAINT `FKah490190ww1q2a4piuv41hk6e` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`);

--
-- Constraints for table `repair_orders`
--
ALTER TABLE `repair_orders`
    ADD CONSTRAINT `FK9m3n0gx72iik77t3qdiefh755` FOREIGN KEY (`order_type_id`) REFERENCES `order_types` (`order_type_id`),
  ADD CONSTRAINT `FKe8ilxqakl9nuam61qo8pb2tge` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`employee_id`),
  ADD CONSTRAINT `FKe9m7rqb6eieyj95ny3hcy58c9` FOREIGN KEY (`device_id`) REFERENCES `devices` (`device_id`),
  ADD CONSTRAINT `FKm4upyrxq3w0biepitkm8bncr4` FOREIGN KEY (`status_id`) REFERENCES `status` (`status_id`);
COMMIT;