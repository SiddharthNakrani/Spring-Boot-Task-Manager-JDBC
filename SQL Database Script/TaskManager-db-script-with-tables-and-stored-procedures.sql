USE [master]
GO
/****** Object:  Database [TaskManager]    Script Date: 2/18/2024 7:21:30 PM ******/
CREATE DATABASE [TaskManager]
 CONTAINMENT = NONE
 ON  PRIMARY
( NAME = N'TaskManager', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\TaskManager.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON
( NAME = N'TaskManager_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.SQLEXPRESS\MSSQL\DATA\TaskManager_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [TaskManager] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [TaskManager].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [TaskManager] SET ANSI_NULL_DEFAULT OFF
GO
ALTER DATABASE [TaskManager] SET ANSI_NULLS OFF
GO
ALTER DATABASE [TaskManager] SET ANSI_PADDING OFF
GO
ALTER DATABASE [TaskManager] SET ANSI_WARNINGS OFF
GO
ALTER DATABASE [TaskManager] SET ARITHABORT OFF
GO
ALTER DATABASE [TaskManager] SET AUTO_CLOSE OFF
GO
ALTER DATABASE [TaskManager] SET AUTO_SHRINK OFF
GO
ALTER DATABASE [TaskManager] SET AUTO_UPDATE_STATISTICS ON
GO
ALTER DATABASE [TaskManager] SET CURSOR_CLOSE_ON_COMMIT OFF
GO
ALTER DATABASE [TaskManager] SET CURSOR_DEFAULT  GLOBAL
GO
ALTER DATABASE [TaskManager] SET CONCAT_NULL_YIELDS_NULL OFF
GO
ALTER DATABASE [TaskManager] SET NUMERIC_ROUNDABORT OFF
GO
ALTER DATABASE [TaskManager] SET QUOTED_IDENTIFIER OFF
GO
ALTER DATABASE [TaskManager] SET RECURSIVE_TRIGGERS OFF
GO
ALTER DATABASE [TaskManager] SET  DISABLE_BROKER
GO
ALTER DATABASE [TaskManager] SET AUTO_UPDATE_STATISTICS_ASYNC OFF
GO
ALTER DATABASE [TaskManager] SET DATE_CORRELATION_OPTIMIZATION OFF
GO
ALTER DATABASE [TaskManager] SET TRUSTWORTHY OFF
GO
ALTER DATABASE [TaskManager] SET ALLOW_SNAPSHOT_ISOLATION OFF
GO
ALTER DATABASE [TaskManager] SET PARAMETERIZATION SIMPLE
GO
ALTER DATABASE [TaskManager] SET READ_COMMITTED_SNAPSHOT OFF
GO
ALTER DATABASE [TaskManager] SET HONOR_BROKER_PRIORITY OFF
GO
ALTER DATABASE [TaskManager] SET RECOVERY SIMPLE
GO
ALTER DATABASE [TaskManager] SET  MULTI_USER
GO
ALTER DATABASE [TaskManager] SET PAGE_VERIFY CHECKSUM
GO
ALTER DATABASE [TaskManager] SET DB_CHAINING OFF
GO
ALTER DATABASE [TaskManager] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF )
GO
ALTER DATABASE [TaskManager] SET TARGET_RECOVERY_TIME = 60 SECONDS
GO
ALTER DATABASE [TaskManager] SET DELAYED_DURABILITY = DISABLED
GO
ALTER DATABASE [TaskManager] SET ACCELERATED_DATABASE_RECOVERY = OFF
GO
ALTER DATABASE [TaskManager] SET QUERY_STORE = ON
GO
ALTER DATABASE [TaskManager] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [TaskManager]
GO
/****** Object:  Table [dbo].[Person]    Script Date: 2/18/2024 7:21:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Person](
    [PersonId] [int] IDENTITY(1,1) NOT NULL,
    [FirstName] [nvarchar](50) NULL,
    [LastName] [nvarchar](50) NULL,
    [Username] [nvarchar](50) NULL,
    [Password] [nvarchar](50) NULL,
    [Age] [int] NULL,
    [Email] [nvarchar](321) NULL,
    [PhoneNumber] [nvarchar](25) NULL,
    CONSTRAINT [PK_Person] PRIMARY KEY CLUSTERED
(
[PersonId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[Priority]    Script Date: 2/18/2024 7:21:30 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[Priority](
    [PriorityID] [int] NOT NULL,
    [PriorityName] [nvarchar](10) NULL,
    CONSTRAINT [PK_Priority] PRIMARY KEY CLUSTERED
(
[PriorityID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[Status]    Script Date: 2/18/2024 7:21:30 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[Status](
    [StatusId] [int] NOT NULL,
    [StatusName] [nvarchar](20) NULL,
    CONSTRAINT [PK_Status] PRIMARY KEY CLUSTERED
(
[StatusId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY]
    GO
/****** Object:  Table [dbo].[Task]    Script Date: 2/18/2024 7:21:30 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE TABLE [dbo].[Task](
    [TaskId] [int] IDENTITY(1,1) NOT NULL,
    [PersonId] [int] NULL,
    [TaskName] [nvarchar](50) NULL,
    [Notes] [nvarchar](max) NULL,
    [StatusId] [int] NULL,
    [PriorityId] [int] NULL,
    CONSTRAINT [PK_Tasks] PRIMARY KEY CLUSTERED
(
[TaskId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
    ) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
    GO
ALTER TABLE [dbo].[Task]  WITH CHECK ADD  CONSTRAINT [FK_Tasks_Person] FOREIGN KEY([PersonId])
    REFERENCES [dbo].[Person] ([PersonId])
    GO
ALTER TABLE [dbo].[Task] CHECK CONSTRAINT [FK_Tasks_Person]
    GO
ALTER TABLE [dbo].[Task]  WITH CHECK ADD  CONSTRAINT [FK_Tasks_Priority] FOREIGN KEY([PriorityId])
    REFERENCES [dbo].[Priority] ([PriorityID])
    GO
ALTER TABLE [dbo].[Task] CHECK CONSTRAINT [FK_Tasks_Priority]
    GO
ALTER TABLE [dbo].[Task]  WITH CHECK ADD  CONSTRAINT [FK_Tasks_Status] FOREIGN KEY([StatusId])
    REFERENCES [dbo].[Status] ([StatusId])
    GO
ALTER TABLE [dbo].[Task] CHECK CONSTRAINT [FK_Tasks_Status]
    GO
/****** Object:  StoredProcedure [dbo].[AddPerson]    Script Date: 2/18/2024 7:21:30 PM ******/
    SET ANSI_NULLS ON
    GO
    SET QUOTED_IDENTIFIER ON
    GO
CREATE procedure [dbo].[AddPerson]
	@firstName nvarchar(50),
	@lastName nvarchar(50),
	@userName nvarchar(50),
	@password nvarchar(50),
	@age int,
	@email nvarchar(321),
	@phoneNumber nvarchar(25),
	@generatedValue int out
as
begin

insert into dbo.Person (FirstName, LastName, Username, [Password], Age, Email, PhoneNumber)values
    (
        @firstName,
        @lastName,
        @userName,
        @password,
        @age,
        @email,
        @phoneNumber
    );
set @generatedValue = SCOPE_IDENTITY();
end

GO
/****** Object:  StoredProcedure [dbo].[AddTask]    Script Date: 2/18/2024 7:21:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE procedure [dbo].[AddTask]


	@PersonId int,
	@TaskName nvarchar(50),
	@Notes nvarchar(max),
	@StatusId int,
	@PriorityId int

as
begin

insert into dbo.Task (PersonId, TaskName, Notes, StatusId, PriorityId) values
    (

        @PersonId,
        @TaskName,
        @Notes,
        @StatusId,
        @PriorityId
    )

end;
GO
/****** Object:  StoredProcedure [dbo].[DeletePerson]    Script Date: 2/18/2024 7:21:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

Create Procedure [dbo].[DeletePerson]
		@personId int
As
BEGIN

Delete from dbo.Person
where PersonId = @personId;

END
GO
/****** Object:  StoredProcedure [dbo].[DeleteTask]    Script Date: 2/18/2024 7:21:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create procedure [dbo].[DeleteTask]
	@taskId int
as
begin

delete from dbo.Task
where TaskId = @taskId

end

GO
/****** Object:  StoredProcedure [dbo].[GetAllPersons]    Script Date: 2/18/2024 7:21:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE Procedure [dbo].[GetAllPersons]
	@name nvarchar(50) = null
As
BEGIN

select	PersonId,
          FirstName,
          LastName,
          Username,
    [Password],
    Age,
    Email,
    PhoneNumber
from dbo.Person

where @name is null or (FirstName like '%' + @name + '%' or LastName like '%' + @name + '%');

END
GO
/****** Object:  StoredProcedure [dbo].[GetAllPriorities]    Script Date: 2/18/2024 7:21:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

Create Procedure [dbo].[GetAllPriorities]
as
Begin

Select
    p.PriorityID,
    p.PriorityName

from dbo.Priority p

end;
GO
/****** Object:  StoredProcedure [dbo].[GetAllStatuses]    Script Date: 2/18/2024 7:21:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create Procedure [dbo].[GetAllStatuses]
as
begin
Select
    s.StatusId,
    s.StatusName

from dbo.Status s

end
GO
/****** Object:  StoredProcedure [dbo].[GetPersonById]    Script Date: 2/18/2024 7:21:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE Procedure [dbo].[GetPersonById]
	@personId int
As
BEGIN

Select
    PersonId,
    FirstName,
    LastName,
    Username,
    [Password],
    Age,
    Email,
    PhoneNumber
from dbo.Person p
where p.PersonId = @personId;

END
GO
/****** Object:  StoredProcedure [dbo].[GetPriorityById]    Script Date: 2/18/2024 7:21:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create Procedure [dbo].[GetPriorityById]
	@priorityId int
as
BEGIN
Select
    p.PriorityID,
    p.PriorityName

from dbo.Priority p where p.PriorityID = @priorityId;


end
GO
/****** Object:  StoredProcedure [dbo].[GetStatusById]    Script Date: 2/18/2024 7:21:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
Create Procedure [dbo].[GetStatusById]
	@statusId int
as
begin
Select
    s.StatusId,
    s.StatusName

from dbo.Status s
where s.StatusId = @statusId;

end
GO
/****** Object:  StoredProcedure [dbo].[GetTasksByPersonId]    Script Date: 2/18/2024 7:21:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE Procedure [dbo].[GetTasksByPersonId]
		@personId int
As
BEGIN

Select
    TaskId,
    PersonId,
    TaskName,
    Notes,
    t.StatusId,
    s.StatusName,
    t.PriorityId,
    p.PriorityName
from dbo.Task t
         join dbo.Status s on t.StatusId = s.StatusId
         join dbo.Priority p on t.PriorityId = p.PriorityID
where t.PersonId = @personId;

END
GO
/****** Object:  StoredProcedure [dbo].[Login]    Script Date: 2/18/2024 7:21:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create procedure [dbo].[Login]
	@username nvarchar(50),
	@password nvarchar(50)
as
begin

select
    PersonId,
    FirstName,
    LastName,
    Username,
    [Password],
    Age,
    Email,
    PhoneNumber
from dbo.Person p

where p.Username = @username and p.Password = @password

end;
GO
/****** Object:  StoredProcedure [dbo].[UpdatePerson]    Script Date: 2/18/2024 7:21:30 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

Create Procedure [dbo].[UpdatePerson]
		@PersonId int,
		@FirstName nvarchar(50),
		@LastName nvarchar(50),
		@Username nvarchar(50),
		@Password nvarchar(50),
		@Age int ,
		@Email nvarchar(321),
		@PhoneNumber nvarchar(25)
As
BEGIN

update Person
set
    FirstName = @FirstName,
    LastName = @LastName,
    Username = @Username,
    [Password] = @Password,
    Age = @Age,
    Email = @Email,
    PhoneNumber = @PhoneNumber

where PersonId = @personId;

END
GO
USE [master]
GO
ALTER DATABASE [TaskManager] SET  READ_WRITE
GO
