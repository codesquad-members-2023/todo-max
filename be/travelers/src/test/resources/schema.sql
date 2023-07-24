DROP TABLE IF EXISTS history;
DROP TABLE IF EXISTS task;


CREATE TABLE IF NOT EXISTS `process`
(
    `process_id` BIGINT AUTO_INCREMENT
    PRIMARY KEY,
    `name`       VARCHAR(50) NOT NULL
    );

CREATE TABLE IF NOT EXISTS `task`
(
    `task_id`      BIGINT AUTO_INCREMENT
    PRIMARY KEY,
    `title`        VARCHAR(50)                        NOT NULL,
    `contents`     VARCHAR(500)                       NULL,
    `platform`     VARCHAR(20)                        NOT NULL,
    `created_time` DATETIME DEFAULT CURRENT_TIMESTAMP NULL,
    `process_id`   BIGINT                             NOT NULL,
    `is_deleted`   TINYINT(1)                         DEFAULT 0,
    `position`     DOUBLE                             NOT NULL,
    CONSTRAINT `fk_Task_process_id`
    FOREIGN KEY (`process_id`) REFERENCES `process` (`process_id`)
    );

CREATE TABLE IF NOT EXISTS `history`
(
    `history_id`   BIGINT AUTO_INCREMENT
    PRIMARY KEY,
    `title`        VARCHAR(50)                        NOT NULL,
    `from`         VARCHAR(50)                        NOT NULL,
    `to`           VARCHAR(50)                        NOT NULL,
    `action_id`    BIGINT                             NOT NULL,
    `created_time` DATETIME DEFAULT CURRENT_TIMESTAMP NULL,
    `user_id`      BIGINT                             NOT NULL,
    CONSTRAINT `fk_History_action_id`
    FOREIGN KEY (`action_id`) REFERENCES `action` (`action_id`),
    CONSTRAINT `fk_History_user_id`
    FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
    );