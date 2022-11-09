CREATE DATABASE casestudy4;
SELECT * FROM user_role;
USE casestudy4;
INSERT INTO user VALUES
							(1,"admin", "123456"),
                            (2,"trainer", "123456"),
                            (3,"player", "123456"),
                            (4,"player", "123456");

INSERT INTO performance VALUES
								(1, "Xuất sắc"),
                                (2, "Tốt"),
                                (3, "Khá"),
                                (4, "Trung bình"),
                                (5, "Tệ");

INSERT INTO player_income VALUES
								(1, 200, 50, 500),
								(2, 250, 50, 300);

INSERT INTO position VALUES
								(1, "Tiền đạo"),
								(2, "Tiền vệ"),
								(3, "Hậu vệ"),
								(4, "Thủ môn");

INSERT INTO role VALUES
								(1, "ROLES_ADMIN"),
								(2, "ROLES_TRAINER"),
								(3, "ROLES_PLAYER");

INSERT INTO status VALUES
								(1, "Đang thi đấu"),
								(2, "Dự bị"),
								(3, "Chấn thương"),
                                (4, "Giải nghệ");

INSERT INTO trainer_income VALUES
								(1, 500, 500),
								(2, 400, 400),
								(3, 300, 300);



INSERT INTO player VALUES
								(1, "Bờ ra zin","1997-1-1",180,"ảnh","Rô lan đi nhô","ảnh CV",65,3,1,1,1,1),
								(2, "Việt Lame","1997-1-1",180,"ảnh","Khá Bá Ngô","ảnh CV",65,4,2,2,2,2);

INSERT INTO trainer VALUES
								(1, "Việt Name", "Ảnh cv", "1990-2-2", "Pắc Hăng Say", "2", 1),
                                (2, "Việt Lame", "Ảnh CV", "1991-3-3", "ADMIB", "1", "1");

INSERT INTO user_role VALUES	(1, 1),
								(2, 2),
								(3, 3),
								(4, 3);
