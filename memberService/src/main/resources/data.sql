

CREATE TABLE Bills(mid varchar(20), lastPaidDate date, dueAmount int, lateCharge int, dueDate date, pid varchar(20));

INSERT INTO Bills(mid, lastPaidDate, dueAmount, lateCharge, dueDate) VALUES ('M101', '2020-06-01', 10000, 800, '2021-02-01');
INSERT INTO Bills(mid, lastPaidDate, dueAmount, lateCharge, dueDate) values('M102', '2019-12-10', 50500, 6500, '2020-12-10');
INSERT INTO Bills(mid, lastPaidDate, dueAmount, lateCharge, dueDate) values('M103', '2021-07-20', 12000, 0, '2022-07-30');
INSERT INTO Bills(mid, lastPaidDate, dueAmount, lateCharge, dueDate) values('M104', '2021-01-01', 8000, 0, '2021-08-10');
INSERT INTO Bills(mid, lastPaidDate, dueAmount, lateCharge, dueDate) values('M105', '2020-01-05', 25000, 4000, '2021-07-05');