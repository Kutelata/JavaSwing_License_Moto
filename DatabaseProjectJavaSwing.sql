CREATE DATABASE ProjectJavaSwing
GO

USE ProjectJavaSwing
GO

CREATE TABLE [role]
(
	id BIT PRIMARY KEY,
	[role] VARCHAR(255)
)
GO

CREATE TABLE driver_license
(
	id INT PRIMARY KEY IDENTITY,
	driver_license NVARCHAR(255)
)
GO

CREATE TABLE [user]
(
	id INT PRIMARY KEY IDENTITY,
	account VARCHAR(255),
	pass VARCHAR(255),
	mark FLOAT,
	identification_card VARCHAR(255),
	[name] NVARCHAR(255),
	[image] NVARCHAR(255),
	gender BIT,
	birthday DATE,
	[address] NVARCHAR(255),
	role_id BIT FOREIGN KEY REFERENCES [role](id),
	driver_license_id INT FOREIGN KEY REFERENCES driver_license(id),
	test_time DATETIME,
	result BIT DEFAULT(0)
)
GO

CREATE TABLE [level]
(
	id INT PRIMARY KEY IDENTITY,
	[level] NVARCHAR(255)
)
GO

CREATE TABLE question
(
	id INT PRIMARY KEY IDENTITY,
	content NVARCHAR(255),
	[image] NVARCHAR(255),
	level_id INT FOREIGN KEY REFERENCES [level](id),
	driver_license_id INT FOREIGN KEY REFERENCES driver_license(id)
)
GO

CREATE TABLE answer 
(
	id INT PRIMARY KEY IDENTITY,
	content NVARCHAR(255),
	result BIT,
	question_id INT FOREIGN KEY REFERENCES question(id)
)
GO

CREATE TABLE result 
(
	[user_id] INT FOREIGN KEY REFERENCES [user](id),
	question_id INT FOREIGN KEY REFERENCES question(id),
	result bit
)
GO

INSERT INTO [role](id,role) VALUES 
	(0,'admin'),
	(1,'user')
GO

INSERT INTO driver_license(driver_license) VALUES
('A1'),
('A2'),
('A3'),
('A4'),
('B1'),
('B2'),
('C'),
('D'),
('F')
GO

INSERT INTO [user](account,pass,role_id) VALUES 
(N'admin','admin123',0)
GO

INSERT INTO [user](account,mark,pass,[name],identification_card,[image],gender,birthday,[address],role_id,driver_license_id) VALUES 
('dat',1,'1234',N'Đặng Tuấn Đạt',163389592,'C:\Users\ADMIN\Documents\NetBeansProjects\C1905M_Team3\src\image\A1.png',1,'1999-03-26',N'Mỹ Hưng - Mỹ Lộc - Nam Định',1,1)
GO

INSERT INTO [level](level) VALUES
	(N'Dễ'),
	(N'Thường'),
	(N'Khó')
GO

INSERT INTO question(content,[image],level_id,driver_license_id) VALUES
(N'Phần của đường bộ được sử dụng cho các phương tiện giao thông qua lại là gì ?',null,1,1),
(N'Phương tiện tham gia giao thông đường bộ” gồm những loại nào ?',null,1,1),
(N'Sử dụng rượu bia khi lái xe, nếu bị phát hiện thì bị xử lý như thế nào ?',null,1,1),
(N'Bạn đang lái xe phía trước có một xe cứu thương đang phát tín hiệu ưu tiên bạn có được phép vượt hay không ?',null,1,1),
(N'Hành vi sử dụng xe mô tô để kéo, đẩy xe mô tô khác bị hết xăng đến trạm mua xăng có được phép hay không ?',null,1,1),
(N'Biển báo hiệu hình tròn có nền xanh lam có hình vẽ màu trắng là loại biển gì dưới đây ?',N'../image/Question/q6.jpg',1,1),
(N'Bạn đang lái xe trong khu vực đô thị từ 22 giờ đến 5 giờ sáng hôm sau và cần vượt một xe khác, bạn cần báo hiệu như thế nào để đảm bảo an toàn giao thông ?',null,1,1),
(N'Người điều khiển phương tiện tham gia giao thông trong hầm đường bộ ngoài việc phải tuân thủ các quy tắc giao thông còn phải thực hiện những quy định nào dưới đây ?',null,1,1),
(N'Trên đoạn đường hai chiều không có giải phân cách giữa, người lái xe không được vượt xe khác trong các trường hợp nào dưới đây ?',null,1,1),
(N'Khi điều khiển xe chạy với tốc độ dưới 60 km/h, để đảm bảo khoảng cách an toàn giữa hai xe, người lái xe phải điều khiển xe như thế nào ?',null,1,1),
(N'Để báo hiệu cho xe phía trước biết xe mô tô của bạn muốn vượt, bạn phải có tín hiệu như thế nào dưới đây ?',null,2,1),
(N'Khi điều khiển xe mô tô tay ga xuống đường dốc dài, độ dốc cao, người lái xe cần thực hiện các thao tác nào dưới đây để đảm bảo an toàn ?',null,2,1),
(N'Tay ga trên xe mô tô hai bánh có tác dụng gì trong các trường hợp dưới đây ?',null,2,1),
(N'Biển nào cấm xe rẽ trái ?',N'../image/Question/q14.jpg',2,1),
(N'Biển nào dưới đây các phương tiện không được phép đi vào ?',N'../image/Question/q15.jpg',2,1),
(N'Biển nào xe mô tô hai bánh không được đi vào ?',N'../image/Question/q16.jpg',2,1),
(N'Biển nào báo hiệu nguy hiểm giao nhau với đường sắt ?',N'../image/Question/q17.jpg',2,1),
(N'Biển nào báo hiệu “Đường giao nhau” của các tuyến đường cùng cấp ?',N'../image/Question/q18.jpg',2,1),
(N'Biển nào chỉ dẫn nơi bắt đầu đoạn đường dành cho người đi bộ ?',N'../image/Question/q19.jpg',2,1),
(N'Biển nào dưới đây báo hiệu hết cấm vượt ?',N'../image/Question/q20.jpg',2,1),
(N'Vạch kẻ đường nào dưới đây là vạch phân chia hai chiều xe chạy (vạch tim đường), xe không được lấn làn, không được đè lên vạch ?',N'../image/Question/q21.jpg',3,1),
(N'Xe nào được quyền đi trước trong trường hợp này ?',N'../image/Question/q22.jpg',3,1),
(N'Xe tải kéo mô tô ba bánh như hình này có đúng quy tắc giao thông không ?',N'../image/Question/q23.jpg',3,1),
(N'Theo hướng mũi tên, những hướng nào xe mô tô được phép đi ?',N'../image/Question/q24.jpg',3,1),
(N'Các xe đi theo hướng mũi tên, xe nào vi phạm quy tắc giao thông ?',N'../image/Question/q25.jpg',3,1),
(N'Làn đường” là gì ?',null,3,1),
(N'Người tham gia giao thông đường bộ” gồm những đối tượng nào ?',null,3,1),
(N'Theo Luật phòng chống tác hại của rượu, bia, đối tượng nào dưới đây bị cấm sử dụng rượu bia khi tham gia giao thông ?',null,3,1),
(N'Người điều khiển xe mô tô hai bánh, ba bánh, xe gắn máy có được phép sử dụng xe để kéo hoặc đẩy các phương tiện khác khi tham gia giao thông không ?',null,3,1),
(N'Hành vi vận chuyển đồ vật cồng kềnh bằng xe mô tô, xe gắn máy khi tham gia giao thông có được phép hay không ?',null,3,1)
GO

INSERT INTO answer VALUES
(N'1- Phần mặt đường và lề đường.',0,1),
(N'2- Phần đường xe chạy.',1,1),
(N'3- Phần đường xe cơ giới.',0,1),
(N'1- Phương tiện giao thông cơ giới đường bộ.',0,2),
(N'2- Phương tiện giao thông thô sơ đường bộ và xe máy chuyên dùng.',0,2),
(N'3- Cả ý 1 và ý 2.',1,2),
(N'1- Chỉ bị nhắc nhở.',0,3),
(N'2- Bị xử phạt hành chính hoặc có thể bị xử lý hình sự tùy theo mức độ vi phạm.',1,3),
(N'3- Không bị xử lý hình sự.',0,3),
(N'1- Không được vượt.',1,4),
(N'2- Được vượt khi đang đi trên cầu.',0,4),
(N'3- Được phép vượt khi đi qua nơi giao nhau có ít phương tiện cùng tham gia giao thông.',0,4),
(N'4- Được vượt khi đảm bảo an toàn.',0,4),
(N'1- Chỉ được kéo nếu đã nhìn thấy trạm xăng.',0,5),
(N'2- Chỉ được thực hiện trên đường vắng phương tiện cùng tham gia giao thông.',0,5),
(N'3- Không được phép.',1,5),
(N'1- Biển báo nguy hiểm.',0,6),
(N'2- Biển báo cấm.',0,6),
(N'3- Biển báo hiệu lệnh phải thi hành.',1,6),
(N'4- Biển báo chỉ dẫn.',0,6),
(N'1- Phải báo hiệu bằng đèn hoặc còi.',0,7),
(N'2- Chỉ được báo hiệu bằng còi.',0,7),
(N'3- Phải báo hiệu bằng cả còi và đèn.',0,7),
(N'4- Chỉ được báo hiệu bằng đèn.',1,7),
(N'1- Xe cơ giới, xe máy chuyên dùng phải bật đèn; xe thô sơ phải bật đèn hoặc có vật phát sáng báo hiệu; chỉ được dừng xe, đỗ xe ở nơi quy định.',1,8),
(N'2- Xe cơ giới phải bật đèn ngay cả khi đường hầm sáng; phải cho xe chạy trên một làn đường và chỉ chuyển làn ở nơi được phép; được quay đầu xe, lùi xe khi cần thiết.',0,8),
(N'3- Xe máy chuyên dùng phải bật đèn ngay cả khi đường hầm sáng; phải cho xe chạy trên một làn đường và chỉ chuyển làn ở nơi được phép; được quay đầu xe, lùi xe khi cần thiết.',0,8),
(N'1- Xe bị vượt bất ngờ tăng tốc độ và cố tình không nhường đường.',0,9),
(N'2- Xe bị vượt giảm tốc độ và nhường đường.',0,9),
(N'3- Phát hiện có xe đi ngược chiều.',0,9),
(N'4- Cả ý 1 và ý 3.',1,9),
(N'1- Chủ động giữ khoảng cách an toàn phù hợp với xe chạy liền trước xe của mình.',0,10),
(N'2- Đảm bảo khoảng cách an toàn theo mật độ phương tiện, tình hình giao thông thực tế.',0,10),
(N'3- Cả ý 1 và ý 2.',1,10),
(N'1- Ra tín hiệu bằng tay rồi cho xe vượt qua.',0,11),
(N'2- Tăng ga mạnh để gây sự chú ý rồi cho xe vượt qua.',0,11),
(N'3- Bạn phải có tín hiệu bằng đèn hoặc còi.',1,11),
(N'1- Giữ tay ga ở mức độ phù hợp, sử dụng phanh trước và phanh sau để giảm tốc độ.',1,12),
(N'2- Nhả hết tay ga, tắt động cơ, sử dụng phanh trước và phanh sau để giảm tốc độ.',0,12),
(N'3- Sử dụng phanh trước để giảm tốc độ kết hợp với tắt chìa khóa điện của xe.',0,12),
(N'1- Để điều khiển xe chạy về phía trước.',0,13),
(N'2- Để điều tiết công suất động cơ qua đó điều khiển tốc độ của xe.',0,13),
(N'3- Để điều khiển xe chạy lùi.',0,13),
(N'4- Cả ý 1 và ý 2.',1,13),
(N'1- Biển 1.',1,14),
(N'2- Biển 2.',0,14),
(N'3- Cả hai biển.',0,14),
(N'1- Biển 1.',0,15),
(N'2- Biển 2.',0,15),
(N'3- Biển 1 và 2.',1,15),
(N'1- Biển 1.',0,16),
(N'2- Biển 2.',1,16),
(N'3- Biển 3.',0,16),
(N'1- Biển 1 và 2.',0,17),
(N'2- Biển 1 và 3.',1,17),
(N'3- Biển 2 và 3.',0,17),
(N'4- Cả ba biển.',0,17),
(N'1- Biển 1.',1,18),
(N'2- Biển 2.',0,18),
(N'3- Biển 3.',0,18),
(N'1- Biển 1.',0,19),
(N'2- Biển 2.',1,19),
(N'3- Biển 3.',0,19),
(N'1- Biển 1.',0,20),
(N'2- Biển 2.',0,20),
(N'3- Biển 3.',0,20),
(N'4- Biển 2 và 3.',1,20),
(N'1- Vạch 1.',0,21),
(N'2- Vạch 2.',1,21),
(N'3- Vạch 3.',0,21),
(N'4- Cả 3 vạch.',0,21),
(N'1- Mô tô.',0,22),
(N'2- Xe cứu thương.',1,22),
(N'1- Đúng.',0,23),
(N'2- Không đúng.',1,23),
(N'1- Cả ba hướng.',0,24),
(N'2- Hướng 1 và 2.',0,24),
(N'3- Hướng 1 và 3.',1,24),
(N'4- Hướng 2 và 3.',0,24),
(N'1- Xe con, xe tải, xe khách.',0,25),
(N'2- Xe tải, xe khách, xe mô tô.',1,25),
(N'3- Xe khách, xe mô tô, xe con.',0,25),
(N'4- Xe khách, xe mô tô, xe con.',0,25),
(N'1- Là một phần của phần đường xe chạy được chia theo chiều dọc của đường, sử dụng cho xe chạy.',0,26),
(N'2- Là một phần của phần đường xe chạy được chia theo chiều dọc của đường, có bề rộng đủ cho xe chạy an toàn.',1,26),
(N'3- Là đường cho xe ô tô chạy, dừng, đỗ an toàn.',0,26),
(N'1- Người điều khiển, người sử dụng phương tiện tham gia giao thông đường bộ.',0,27),
(N'2- Người điều khiển, dẫn dắt súc vật; người đi bộ trên đường bộ.',0,27),
(N'3- Cả ý 1 và ý 2.',1,27),
(N'1- Người điều khiển: Xe ô tô, xe mô tô, xe đạp, xe gắn máy.',0,28),
(N'2- Người ngồi phía sau người điều khiển xe cơ giới.',1,28),
(N'3- Người đi bộ.',0,28),
(N'4- Cả ý 1 và ý 2.',0,28),
(N'1- Được phép.',0,29),
(N'2- Nếu phương tiện được kéo, đẩy có khối lượng nhỏ hơn phương tiện của mình.',0,29),
(N'3- Tuỳ trường hợp.',0,29),
(N'4- Không được phép.',1,29),
(N'1- Không được vận chuyển.',1,30),
(N'2- Chỉ được vận chuyển khi đã chằng buộc cẩn thận.',0,30),
(N'3- Chỉ được vận chuyển vật cồng kềnh trên xe máy nếu khoảng cách về nhà ngắn hơn 2 km.',0,30)
GO

CREATE PROC getUser
@account VARCHAR(255), @pass VARCHAR(255)
AS
SELECT * FROM [user] WHERE [user].account = @account AND [user].pass = @pass
GO 

CREATE PROC getAllUser
AS
SELECT	[user].id,
		[user].account,
		[user].pass,
		[user].mark,
		[user].identification_card,
		[user].name,
		[user].image,
		[user].gender,
		[user].birthday,
		[user].address,
		driver_license.driver_license,
		[role].role,
		[user].test_time,
		[user].result,
		[user].role_id,
		[user].driver_license_id
FROM [user] 
JOIN [role] ON [user].role_id = [role].id
LEFT JOIN driver_license ON [user].driver_license_id = driver_license.id
GO

CREATE PROC getAllRole
AS
SELECT * FROM [role]
GO

CREATE PROC getRoleById
@id INT
AS
SELECT [role].id,[role].role FROM [role] JOIN [user] ON [user].role_id = [role].id WHERE [user].id = @id
GO

CREATE PROC getAllType
AS
SELECT * FROM driver_license
GO

CREATE PROC createUser
@account VARCHAR(255), @pass VARCHAR(255), @identification_card VARCHAR(255), @name NVARCHAR(255), @image NVARCHAR(255), @gender BIT, @birthday NVARCHAR(255), @address NVARCHAR(255),@driver_license_id INT, @roleId BIT
AS
INSERT INTO [user] (account, pass, identification_card, name, image, gender, birthday, address, driver_license_id, role_id) VALUES (@account,@pass,@identification_card,@name,@image,@gender,@birthday,@address,@driver_license_id,@roleId)
GO  

CREATE PROC deleteUser
@id INT
AS
DELETE [user] WHERE id = @id
GO

CREATE PROC updateUser
@id INT, @account VARCHAR(255), @pass VARCHAR(255), @identification_card VARCHAR(255), @name NVARCHAR(255), @image NVARCHAR(255), @gender BIT, @birthday NVARCHAR(255), @address NVARCHAR(255),@driver_license_id INT, @roleId BIT
AS
UPDATE [user] SET account = @account, pass = @pass, gender = @gender, identification_card = @identification_card, name = @name, image = @image, birthday = @birthday, address = @address, driver_license_id = @driver_license_id, role_id = @roleId WHERE id = @id
GO

CREATE PROC getAllLevel
AS
SELECT * FROM [level]
GO

CREATE PROC getAllQuestion
AS
SELECT 
	question.id,
	question.content AS question,
	question.[image],
	[level].[level],
	question.level_id
FROM question
JOIN level ON question.level_id = level.id
GO	

CREATE PROC getQuestion
AS
SELECT
	question.id,
	question.content AS question
FROM question
GO

CREATE PROC createQuestion
@question NVARCHAR(255), @levelId INT, @image NVARCHAR(255)
AS
INSERT INTO question(content,level_id,image) VALUES (@question,@levelId,@image)
GO

CREATE PROC deleteQuestion
@id INT
AS
DELETE [question] WHERE id = @id
GO

CREATE PROC updateQuestion
@id INT, @question NVARCHAR(255), @levelId INT, @image NVARCHAR(255)
AS
UPDATE question SET content = @question, level_id = @levelId, image = @image WHERE id = @id
GO

CREATE PROC getAllAnswer
AS
SELECT 
	answer.id,
	answer.content AS answer,
	question.content AS question,
	answer.result,
	answer.question_id
FROM answer
JOIN question ON answer.question_id = question.id
GO

CREATE PROC createAnswer
@content NVARCHAR(255), @result INT, @questionId INT
AS
INSERT INTO answer(content,result,question_id) VALUES (@content,@result,@questionId)
GO 

CREATE PROC deleteAnswer
@id INT
AS
DELETE answer WHERE id = @id
GO

CREATE PROC updateAnswer
@id INT, @content NVARCHAR(255), @result INT, @questionId INT
AS
UPDATE answer SET content = @content, result = @result, question_id = @questionId WHERE id = @id
GO

CREATE PROC getQuestionExam
@level nvarchar(255),@driver_license nvarchar(255)
AS
SELECT 
	q.id,
	q.content,
	q.[image],
	q.level_id,
	q.driver_license_id
FROM question q
JOIN [level] l on l.id = q.level_id
JOIN driver_license dl on dl.id = q.driver_license_id
WHERE l.[level] = @level and dl.driver_license = @driver_license
GO

CREATE PROC getAnswerExam
@id int
AS
SELECT 
	a.id,
	a.content,
	a.result,
	a.question_id
FROM answer a
JOIN question q on q.id = a.question_id
WHERE q.id = @id
GO

CREATE PROC getResult
@user_id int,@question_id int,@result int
AS
	if((select count([user_id]) from result where question_id = @question_id and [user_id] = @user_id) > 0 )
		UPDATE result set result = @result where question_id = @question_id and [user_id] = @user_id
	else
		INSERT INTO result values(@user_id,@question_id,@result)
go



CREATE PROC updatePass
@pass VARCHAR(255), @account VARCHAR(255)
AS
UPDATE [User] SET pass = @pass WHERE account like @account 
GO

CREATE PROC updateMark
@mark FLOAT, @id INT
AS
UPDATE [user] SET mark = @mark WHERE id = @id
GO

CREATE PROC selectByAcc
@account VARCHAR(255)
AS
SELECT*FROM[User] WHERE account like @account
GO

CREATE PROC getUserByAccount
@account VARCHAR(255)
AS
SELECT*FROM[User] WHERE account like @account
GO

CREATE PROC getUserById
@id INT
AS
SELECT*FROM [User] WHERE id = @id
GO

CREATE PROC countAns 
@id INT
AS
SELECT COUNT(question_id) as repliedAns FROM result WHERE [user_id] = @id
GO

CREATE PROC countTrueAns
@id INT
AS
SELECT COUNT(result) as TrueAnswers FROM result WHERE result = 1 and [user_id] = @id
GO

CREATE PROC updateTime
@id INT, @test_time VARCHAR(255)
AS
UPDATE [user] SET test_time = @test_time WHERE id = @id
GO


CREATE PROC updateUserResult
@id INT, @result BIT
AS
UPDATE [User] SET result = @result WHERE id = @id 
GO

CREATE PROC countUser
AS
SELECT COUNT(id) as userId FROM [user]
GO

CREATE PROC countPassUser
AS
SELECT COUNT(id) as PassUser FROM [user] WHERE mark >= 10
GO

CREATE PROC countFailureUser
AS
SELECT COUNT(id) as FailureUser FROM [user] WHERE mark < 10
GO

CREATE PROC searchUserByAccount
@account VARCHAR(255)
AS
SELECT
	[user].id,
	[user].account,
	[user].pass,
	[user].mark,
	[user].identification_card,
	[user].name,
	[user].image,
	[user].gender,
	[user].birthday,
	[user].address,
	driver_license.driver_license,
	[role].role,
	[user].test_time,
	[user].result,
	[user].role_id,
	[user].driver_license_id
FROM [user] 
JOIN [role] ON [user].role_id = [role].id
JOIN [driver_license] ON [user].driver_license_id = [driver_license].id
WHERE [user].account LIKE '%' + @account + '%'
GO

CREATE PROC searchUserByName
@name NVARCHAR(255)
AS
SELECT
	[user].id,
	[user].account,
	[user].pass,
	[user].mark,
	[user].identification_card,
	[user].name,
	[user].image,
	[user].gender,
	[user].birthday,
	[user].address,
	driver_license.driver_license,
	[role].role,
	[user].test_time,
	[user].result,
	[user].role_id,
	[user].driver_license_id
FROM [user] 
JOIN [role] ON [user].role_id = [role].id
JOIN [driver_license] ON [user].driver_license_id = [driver_license].id
WHERE [user].name LIKE '%' + @name + '%'
GO

CREATE PROC searchUserByGender
@gender BIT
AS
SELECT
	[user].id,
	[user].account,
	[user].pass,
	[user].mark,
	[user].identification_card,
	[user].name,
	[user].image,
	[user].gender,
	[user].birthday,
	[user].address,
	driver_license.driver_license,
	[role].role,
	[user].test_time,
	[user].result,
	[user].role_id,
	[user].driver_license_id
FROM [user] 
JOIN [role] ON [user].role_id = [role].id
JOIN [driver_license] ON [user].driver_license_id = [driver_license].id
WHERE [user].gender = @gender
GO

CREATE PROC searchUserByDriverLicense
@driverLicense INT
AS
SELECT
	[user].id,
	[user].account,
	[user].pass,
	[user].mark,
	[user].identification_card,
	[user].name,
	[user].image,
	[user].gender,
	[user].birthday,
	[user].address,
	driver_license.driver_license,
	[role].role,
	[user].test_time,
	[user].result,
	[user].role_id,
	[user].driver_license_id
FROM [user] 
JOIN [role] ON [user].role_id = [role].id
JOIN [driver_license] ON [user].driver_license_id = [driver_license].id
WHERE [user].driver_license_id = @driverLicense
GO