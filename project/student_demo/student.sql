CREATE TABLE
  `student` (
    `id` varchar(255) NOT NULL,
    `name` varchar(255) NOT NULL,
    `age` int unsigned NOT NULL,
    `gender` varchar(255) NOT NULL DEFAULT '男',
    PRIMARY KEY (`id`)
  ) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

