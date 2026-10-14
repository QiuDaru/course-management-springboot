-- 學生選課系統 建表 DDL（對應 Entity）
-- 在 MySQL 的 manyBook 資料庫執行

CREATE TABLE IF NOT EXISTS student (
    student_id VARCHAR(8)   NOT NULL,
    name       VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    PRIMARY KEY (student_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS course (
    course_id   VARCHAR(255) NOT NULL,
    course_name VARCHAR(255) NOT NULL,
    credit      INT          NOT NULL,
    PRIMARY KEY (course_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS course_selection (
    student_id  VARCHAR(8)   NOT NULL,
    course_id   VARCHAR(255) NOT NULL,
    grade       INT          NULL,
    remark      VARCHAR(255) NULL,
    create_time DATETIME     NOT NULL,
    PRIMARY KEY (student_id, course_id),
    CONSTRAINT fk_cs_student FOREIGN KEY (student_id) REFERENCES student (student_id),
    CONSTRAINT fk_cs_course  FOREIGN KEY (course_id)  REFERENCES course (course_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
