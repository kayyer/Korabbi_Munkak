USE [CBB]
GO
/****** Object:  Table [dbo].[Events]    Script Date: 2021. 06. 16. 21:33:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Events](
	[EID] [int] IDENTITY(1,1) NOT NULL,
	[EventName] [varchar](32) NOT NULL,
	[StartDate] [date] NULL,
	[EndDate] [date] NULL,
	[Description] [varchar](256) NOT NULL,
	[GoalPoint] [int] NULL,
 CONSTRAINT [PK__Events__C190170B8AD62E3A] PRIMARY KEY CLUSTERED 
(
	[EID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[FAQ]    Script Date: 2021. 06. 16. 21:33:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[FAQ](
	[FID] [int] IDENTITY(1,1) NOT NULL,
	[Title] [varchar](32) NOT NULL,
	[ShortD] [varchar](64) NOT NULL,
	[LongD] [varchar](256) NOT NULL,
 CONSTRAINT [PK__FAQ__C1BEA5A22E75C9FB] PRIMARY KEY CLUSTERED 
(
	[FID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Messages]    Script Date: 2021. 06. 16. 21:33:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Messages](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[MID] [int] NOT NULL,
	[Content] [varchar](128) NOT NULL,
	[Date] [datetime] NULL,
	[SenderID] [int] NOT NULL,
 CONSTRAINT [PK_Messages] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MessagesToFrom]    Script Date: 2021. 06. 16. 21:33:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MessagesToFrom](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[MID] [int] NOT NULL,
	[UID1] [int] NOT NULL,
	[UID2] [int] NOT NULL,
	[Subject] [varchar](64) NOT NULL,
	[LastMessage] [datetime] NOT NULL,
 CONSTRAINT [PK__Messages__3214EC27EC28732C] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MultipleAnswer]    Script Date: 2021. 06. 16. 21:33:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MultipleAnswer](
	[MAID] [int] NOT NULL,
	[MAnswer] [varchar](128) NOT NULL,
	[isAccepted] [bit] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MAID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MultipleAToQ]    Script Date: 2021. 06. 16. 21:33:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MultipleAToQ](
	[ID] [int] NOT NULL,
	[MAID] [int] NOT NULL,
	[MQID] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[MultipleQuestion]    Script Date: 2021. 06. 16. 21:33:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[MultipleQuestion](
	[MQID] [int] NOT NULL,
	[MQuestion] [varchar](256) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[MQID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[QandA]    Script Date: 2021. 06. 16. 21:33:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[QandA](
	[QID] [int] IDENTITY(1,1) NOT NULL,
	[Question] [varchar](256) NOT NULL,
	[Answer] [varchar](128) NOT NULL,
	[UGID] [int] NOT NULL,
	[TID] [int] NOT NULL,
 CONSTRAINT [PK_QandA] PRIMARY KEY CLUSTERED 
(
	[QID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tasks]    Script Date: 2021. 06. 16. 21:33:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tasks](
	[TID] [int] IDENTITY(1,1) NOT NULL,
	[Type] [varchar](32) NOT NULL,
	[Name] [varchar](32) NOT NULL,
 CONSTRAINT [PK__Tasks__C456D7298D554D4D] PRIMARY KEY CLUSTERED 
(
	[TID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UserGroups]    Script Date: 2021. 06. 16. 21:33:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserGroups](
	[UGID] [int] IDENTITY(1,1) NOT NULL,
	[GroupName] [varchar](32) NOT NULL,
 CONSTRAINT [PK_UserGroups] PRIMARY KEY CLUSTERED 
(
	[UGID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 2021. 06. 16. 21:33:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[UID] [int] IDENTITY(1,1) NOT NULL,
	[FullName] [varchar](64) NULL,
	[UserName] [varchar](32) NOT NULL,
	[Description] [varchar](256) NULL,
	[BirthDate] [date] NULL,
	[HSHD_PASSWORD] [char](256) NOT NULL,
	[isAdmin] [bit] NOT NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[UID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UsersSessions]    Script Date: 2021. 06. 16. 21:33:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UsersSessions](
	[SesID] [uniqueidentifier] NOT NULL,
	[UID] [int] NOT NULL,
	[From] [datetime] NOT NULL,
	[Until] [datetime] NOT NULL,
 CONSTRAINT [PK_UsersSessions] PRIMARY KEY CLUSTERED 
(
	[SesID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UsersToEvents]    Script Date: 2021. 06. 16. 21:33:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UsersToEvents](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[UID] [int] NOT NULL,
	[EID] [int] NOT NULL,
	[Points] [int] NOT NULL,
 CONSTRAINT [PK_UsersToEvents] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UsersToUGroups]    Script Date: 2021. 06. 16. 21:33:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UsersToUGroups](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[UID] [int] NOT NULL,
	[UGID] [int] NOT NULL,
 CONSTRAINT [PK_UsersToUGroups] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Events] ON 
GO
INSERT [dbo].[Events] ([EID], [EventName], [StartDate], [EndDate], [Description], [GoalPoint]) VALUES (1, N'Pontos esemény', CAST(N'2021-06-10' AS Date), CAST(N'2021-07-20' AS Date), N'Ezen az eseményen 5000 pontot kell elérni', 5000)
GO
INSERT [dbo].[Events] ([EID], [EventName], [StartDate], [EndDate], [Description], [GoalPoint]) VALUES (2, N'Gyűjtögetős esemény', CAST(N'2021-06-12' AS Date), CAST(N'2021-07-01' AS Date), N'Gyűjtsd össze az összes képdarabkát!', -7)
GO
SET IDENTITY_INSERT [dbo].[Events] OFF
GO
SET IDENTITY_INSERT [dbo].[FAQ] ON 
GO
INSERT [dbo].[FAQ] ([FID], [Title], [ShortD], [LongD]) VALUES (1, N'Hol találom a feladataim?', N'A feladataidat a főmenü legfelső gombjával éred el.', N'A bal felső sarokban található AMIGOS gombra kattintva a főmenüben találod magad, ahol jobboldalon a legfelső ikonra kattinva a feladatok listája jelenik meg. Ott tudsz választani az elérhető feladatok közül.')
GO
INSERT [dbo].[FAQ] ([FID], [Title], [ShortD], [LongD]) VALUES (3, N'Hol változatok jelszót?', N'A jelszó változtatást a felhasználói beállításoknál találod.', N'A jelszó megváltoztatásához, a menüsávban található felhasználónevedre kattints. A megjelenő oldal megfelelő kitöltése után sikeresen meg tudod változtatni a jelszavad.')
GO
SET IDENTITY_INSERT [dbo].[FAQ] OFF
GO
SET IDENTITY_INSERT [dbo].[Messages] ON 
GO
INSERT [dbo].[Messages] ([ID], [MID], [Content], [Date], [SenderID]) VALUES (1, 1, N'Szia! Lenne egy feladat', CAST(N'2021-06-05T00:00:00.000' AS DateTime), 1)
GO
INSERT [dbo].[Messages] ([ID], [MID], [Content], [Date], [SenderID]) VALUES (2, 1, N'Szia! Mi az?', CAST(N'2021-06-06T00:00:00.000' AS DateTime), 4)
GO
INSERT [dbo].[Messages] ([ID], [MID], [Content], [Date], [SenderID]) VALUES (3, 1, N'A feladatoknál megtalálod. :)', CAST(N'2021-06-07T00:00:00.000' AS DateTime), 1)
GO
INSERT [dbo].[Messages] ([ID], [MID], [Content], [Date], [SenderID]) VALUES (4, 1, N'Rendben.', CAST(N'2021-06-08T15:30:45.360' AS DateTime), 4)
GO
INSERT [dbo].[Messages] ([ID], [MID], [Content], [Date], [SenderID]) VALUES (5, 1, N'Megnézted?', CAST(N'2021-06-08T15:31:42.307' AS DateTime), 1)
GO
INSERT [dbo].[Messages] ([ID], [MID], [Content], [Date], [SenderID]) VALUES (6, 1, N'Aha, megnéztem, mindjárt csinálom.', CAST(N'2021-06-12T14:31:30.533' AS DateTime), 4)
GO
INSERT [dbo].[Messages] ([ID], [MID], [Content], [Date], [SenderID]) VALUES (7, 1, N'Kész.', CAST(N'2021-06-15T10:47:40.457' AS DateTime), 4)
GO
INSERT [dbo].[Messages] ([ID], [MID], [Content], [Date], [SenderID]) VALUES (8, 1, N'Szuper, ügyes vagy!', CAST(N'2021-06-15T11:12:09.150' AS DateTime), 1)
GO
INSERT [dbo].[Messages] ([ID], [MID], [Content], [Date], [SenderID]) VALUES (9, 1, N'Köszönöm.', CAST(N'2021-06-16T19:46:46.837' AS DateTime), 4)
GO
INSERT [dbo].[Messages] ([ID], [MID], [Content], [Date], [SenderID]) VALUES (10, 1, N'Nagyon szépen köszönöm', CAST(N'2021-06-16T20:20:03.327' AS DateTime), 4)
GO
INSERT [dbo].[Messages] ([ID], [MID], [Content], [Date], [SenderID]) VALUES (11, 1, N'Thank you', CAST(N'2021-06-16T20:20:30.867' AS DateTime), 4)
GO
INSERT [dbo].[Messages] ([ID], [MID], [Content], [Date], [SenderID]) VALUES (12, 1, N'Thank you', CAST(N'2021-06-16T20:22:10.510' AS DateTime), 4)
GO
SET IDENTITY_INSERT [dbo].[Messages] OFF
GO
SET IDENTITY_INSERT [dbo].[MessagesToFrom] ON 
GO
INSERT [dbo].[MessagesToFrom] ([ID], [MID], [UID1], [UID2], [Subject], [LastMessage]) VALUES (1, 1, 4, 1, N'Feladatod van', CAST(N'2021-06-16T20:22:10.510' AS DateTime))
GO
SET IDENTITY_INSERT [dbo].[MessagesToFrom] OFF
GO
SET IDENTITY_INSERT [dbo].[QandA] ON 
GO
INSERT [dbo].[QandA] ([QID], [Question], [Answer], [UGID], [TID]) VALUES (1, N'Oroszlán angolul', N'Lion', 1, 1)
GO
INSERT [dbo].[QandA] ([QID], [Question], [Answer], [UGID], [TID]) VALUES (2, N'Tigris angolul', N'Tiger', 1, 1)
GO
INSERT [dbo].[QandA] ([QID], [Question], [Answer], [UGID], [TID]) VALUES (3, N'VAlami', N'Valami', 1, 3)
GO
INSERT [dbo].[QandA] ([QID], [Question], [Answer], [UGID], [TID]) VALUES (4, N'v', N'v', 1, 5)
GO
INSERT [dbo].[QandA] ([QID], [Question], [Answer], [UGID], [TID]) VALUES (6, N'v', N'v', 1, 6)
GO
INSERT [dbo].[QandA] ([QID], [Question], [Answer], [UGID], [TID]) VALUES (7, N'Kérdés', N'Válasz', 2, 1)
GO
SET IDENTITY_INSERT [dbo].[QandA] OFF
GO
SET IDENTITY_INSERT [dbo].[Tasks] ON 
GO
INSERT [dbo].[Tasks] ([TID], [Type], [Name]) VALUES (1, N'Memory', N'Memória')
GO
INSERT [dbo].[Tasks] ([TID], [Type], [Name]) VALUES (3, N'CrossWord', N'Keresztrejtvény')
GO
INSERT [dbo].[Tasks] ([TID], [Type], [Name]) VALUES (4, N'Puzzle-pieces', N'Képkirakós')
GO
INSERT [dbo].[Tasks] ([TID], [Type], [Name]) VALUES (5, N'WordCrosser', N'Szókihúzó')
GO
INSERT [dbo].[Tasks] ([TID], [Type], [Name]) VALUES (6, N'WordSearcher', N'Szókereső')
GO
INSERT [dbo].[Tasks] ([TID], [Type], [Name]) VALUES (7, N'Pairing', N'Párosítós')
GO
SET IDENTITY_INSERT [dbo].[Tasks] OFF
GO
SET IDENTITY_INSERT [dbo].[UserGroups] ON 
GO
INSERT [dbo].[UserGroups] ([UGID], [GroupName]) VALUES (1, N'Angolosok')
GO
INSERT [dbo].[UserGroups] ([UGID], [GroupName]) VALUES (2, N'Kis iskolások')
GO
INSERT [dbo].[UserGroups] ([UGID], [GroupName]) VALUES (3, N'10 év felettiek')
GO
SET IDENTITY_INSERT [dbo].[UserGroups] OFF
GO
SET IDENTITY_INSERT [dbo].[Users] ON 
GO
INSERT [dbo].[Users] ([UID], [FullName], [UserName], [Description], [BirthDate], [HSHD_PASSWORD], [isAdmin]) VALUES (1, N'Adminisztrátor', N'admin', N'Fő adminisztrátor', NULL, N'8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918                                                                                                                                                                                                ', 1)
GO
INSERT [dbo].[Users] ([UID], [FullName], [UserName], [Description], [BirthDate], [HSHD_PASSWORD], [isAdmin]) VALUES (4, N'Normál felhasználó', N'user', N'', NULL, N'8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918                                                                                                                                                                                                ', 0)
GO
SET IDENTITY_INSERT [dbo].[Users] OFF
GO
INSERT [dbo].[UsersSessions] ([SesID], [UID], [From], [Until]) VALUES (N'eab10ef6-793a-4e93-bb06-14c2beb57634', 4, CAST(N'2021-06-16T17:31:00.960' AS DateTime), CAST(N'2021-06-16T23:58:37.657' AS DateTime))
GO
INSERT [dbo].[UsersSessions] ([SesID], [UID], [From], [Until]) VALUES (N'429aec9e-9117-44d3-9404-4567384103f5', 1, CAST(N'2021-06-16T18:04:38.020' AS DateTime), CAST(N'2021-06-16T23:48:03.653' AS DateTime))
GO
SET IDENTITY_INSERT [dbo].[UsersToEvents] ON 
GO
INSERT [dbo].[UsersToEvents] ([ID], [UID], [EID], [Points]) VALUES (1, 4, 1, 30)
GO
INSERT [dbo].[UsersToEvents] ([ID], [UID], [EID], [Points]) VALUES (2, 4, 2, -3)
GO
SET IDENTITY_INSERT [dbo].[UsersToEvents] OFF
GO
SET IDENTITY_INSERT [dbo].[UsersToUGroups] ON 
GO
INSERT [dbo].[UsersToUGroups] ([ID], [UID], [UGID]) VALUES (1, 4, 1)
GO
INSERT [dbo].[UsersToUGroups] ([ID], [UID], [UGID]) VALUES (2, 4, 2)
GO
SET IDENTITY_INSERT [dbo].[UsersToUGroups] OFF
GO
/****** Object:  StoredProcedure [dbo].[authorize]    Script Date: 2021. 06. 16. 21:33:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		CBB - ZB
-- Create date: 2021-06-06
-- Description:	Bejelentkezéses ellenőrzés
-- =============================================
CREATE PROCEDURE [dbo].[authorize]
	@username varchar(32),
	@psw char(256) = NULL,
	@sessionid char(36) = NULL
AS
BEGIN
	DECLARE @UserID int
	SELECT @UserID = [UID] FROM Users WHERE @username = UserName

	DECLARE @SID uniqueidentifier
	SELECT @SID = SesID FROM UsersSessions
		WHERE [UID] = @UserID
			AND Until > GETDATE()

	if @sessionid is not null
		begin
			DECLARE @givenSID uniqueidentifier
			SELECT @givenSID = CONVERT(uniqueidentifier, @sessionid)

			if @givenSID = @SID
				begin
					SELECT u.*, us.SesID, us.Until FROM Users u
						JOIN UsersSessions us on us.[UID] = u.[UID]
						WHERE u.[UID] = @UserID
				end
		end
	else if @psw is not null
		begin
			DECLARE @hsd char(256)
			SELECT @hsd = HSHD_PASSWORD FROM Users WHERE [UID] = @UserID

			if @hsd = @psw
				begin
					DECLARE @already datetime
					SELECT @already = Until FROM UsersSessions WHERE [UID] = @UserID

					if @already > GETDATE()
						BEGIN
							UPDATE UsersSessions
								SET [Until] = DATEADD(HOUR, 3, GETDATE())
								WHERE [UID] = @UserID AND Until > GETDATE()
						END
					else
						begin
							INSERT INTO [dbo].[UsersSessions]
								   ([SesID]
								   ,[UID]
								   ,[From]
								   ,[Until])
							 VALUES
								   (NEWID()
								   ,@UserID
								   ,GETDATE()
								   ,DATEADD(HOUR, 3, GETDATE()))

							DELETE FROM UsersSessions
								WHERE Until < GETDATE()

						   end
						 
					SELECT u.*, us.SesID, us.Until FROM UsersSessions us
						JOIN Users u on u.[UID] = us.[UID]
						WHERE us.[UID] = @UserID AND us.Until > GETDATE()
				end
		end
END
GO
/****** Object:  StoredProcedure [dbo].[getEvent]    Script Date: 2021. 06. 16. 21:33:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		CBB - ZB
-- Create date: 2021-06-12
-- Description:	Események listája
-- =============================================
CREATE PROCEDURE [dbo].[getEvent]
	@EID int = NULL,
	@UID int
AS
BEGIN
	if @EID is null
		begin
			SELECT e.EID, e.[EventName], e.StartDate, e.EndDate, e.GoalPoint, ue.Points FROM [Events] e
				JOIN UsersToEvents ue ON ue.[UID] = @UID and ue.EID = e.EID
				WHERE e.[EndDate] > GETDATE() AND e.[StartDate] < GETDATE()
		end
	else
		begin
			SELECT e.EID, e.[EventName], e.StartDate, e.EndDate, e.[Description], e.GoalPoint, ue.Points FROM [Events] e
				JOIN UsersToEvents ue ON ue.[UID] = @UID and ue.EID = e.EID
				WHERE e.[EndDate] > GETDATE() AND e.[StartDate] < GETDATE() AND e.EID = @EID
		end
END
GO
/****** Object:  StoredProcedure [dbo].[getFAQ]    Script Date: 2021. 06. 16. 21:33:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		CBB - ZB
-- Create date: 2021-06-07
-- Description:	FAQ lekérdezése
-- =============================================
CREATE PROCEDURE [dbo].[getFAQ]
	@FID int = NULL
AS
BEGIN
	if @FID is null
		begin
			SELECT FID, Title, ShortD FROM FAQ
		end
	else
		begin
			SELECT FID, Title, ShortD, LongD FROM FAQ WHERE FID = @FID
		end
END
GO
/****** Object:  StoredProcedure [dbo].[getMessages]    Script Date: 2021. 06. 16. 21:33:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		CBB - ZB
-- Create date: 2021-06-07
-- Description: Üzenetek lekérése
-- =============================================
CREATE PROCEDURE [dbo].[getMessages]
	@sessionid char(36),
	@MID int = NULL
AS
BEGIN
	DECLARE @SID uniqueidentifier
	SELECT @SID = CONVERT(uniqueidentifier, @sessionid)

	DECLARE @UserID int
	SELECT @UserID = us.[UID] FROM UsersSessions us
		JOIN Users u on u.[UID] = us.[UID]
		WHERE us.Until > GETDATE() AND us.SesID = @SID

	IF @UserID is not null
		begin

			if @MID is null
				begin
					
					SELECT ID, MID, UID1, UID2, ISNULL(u.UserName, '-') as 'U1Name', ISNULL(u2.UserName, '-') as 'U2Name', [Subject] FROM MessagesToFrom
							LEFT JOIN Users u on UID1 != @UserID and u.[UID] = UID1
							LEFT JOIN Users u2 on UID2 != @UserID and u2.[UID] = UID2
						WHERE UID1 = @UserID OR UID2 = @UserID
						ORDER BY [LastMessage]
				end
			else
				begin
					DECLARE @isAuth int
					SELECT @isAuth = COUNT(ID) FROM MessagesToFrom
						WHERE [MID] = @MID AND (UID1 = @UserID OR UID2 = @UserID)

					if @isAuth > 0
						begin
							SELECT MID, Content, [Date], SenderID FROM [Messages]
								WHERE [MID] = @MID
							ORDER BY [Date]
						end
				end

		end
END
GO
/****** Object:  StoredProcedure [dbo].[getQGroups]    Script Date: 2021. 06. 16. 21:33:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		CBB - ZB
-- Create date: 2021-06-15
-- Description:	Kérdéscsoportok keresése
-- =============================================
CREATE PROCEDURE [dbo].[getQGroups] 
	
AS
BEGIN
	SELECT qg.QGID, qg.QGroupName FROM QuestionGroups qg
END
GO
/****** Object:  StoredProcedure [dbo].[getTasks]    Script Date: 2021. 06. 16. 21:33:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Ziaja Bálint
-- Create date: 2021-06-14
-- Description:	Feladatok listázása
-- =============================================
CREATE PROCEDURE [dbo].[getTasks] 
	@UID int = NULL,
	@all int = 0
AS
BEGIN
	if @all = 0 AND @UID is not null
		begin
			SELECT t.TID, t.[Type], t.[Name], ug.[GroupName] FROM Tasks t
				JOIN UsersToUGroups utg on utg.[UID] = @UID
				JOIN QandA q on q.TID = t.TID AND utg.[UGID] = utg.[UGID]
				JOIN UserGroups ug on ug.[UGID] = utg.[UGID]
		end
	else if @all != 0
		begin
			SELECT t.TID, t.[Type], t.[Name] FROM Tasks t
		end
END
GO
/****** Object:  StoredProcedure [dbo].[getUserGroups]    Script Date: 2021. 06. 16. 21:33:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		CBB - ZB
-- Create date: 2021-06-13
-- Description: Felhasználói csoportok listázása
-- =============================================
CREATE PROCEDURE [dbo].[getUserGroups]
	@UGID int = NULL,
	@UID int = NULL
AS
BEGIN
	IF @UGID is not null
		begin
			SELECT ug.UGID, ug.GroupName FROM UserGroups ug
				WHERE ug.UGID = @UGID
		end
	else if @UID is not null
		begin
			SELECT ug.UGID, ug.GroupName FROM UserGroups ug
				JOIN Users u on u.[UID] = @UID
				JOIN UsersToUGroups utug on utug.[UID] = @UID and utug.UGID = ug.UGID
		end
	else
		begin
			SELECT ug.UGID, ug.GroupName FROM UserGroups ug
		end
END
GO
/****** Object:  StoredProcedure [dbo].[getUsers]    Script Date: 2021. 06. 16. 21:33:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		CBB - ZB
-- Create date: 2021-06-08
-- Description:	Felhasználók listázása
-- =============================================
CREATE PROCEDURE [dbo].[getUsers] 
	@sessionid char(36),
	@senderID int,
	@username varchar(32) = NULL,
	@UID int = NULL
AS
BEGIN
	DECLARE @SID uniqueidentifier
	SELECT @SID = CONVERT(uniqueidentifier, @sessionid)

	DECLARE @isOk int
	SELECT @isOk = COUNT(us.[UID]) FROM UsersSessions us
		JOIN Users u on u.[UID] = us.[UID]
		WHERE us.SesID = @SID AND us.Until > GETDATE() AND @senderID = us.[UID]

	IF @isOk > 0
		begin
			SELECT @isOk = 0
			SELECT @isOk = COUNT([UID]) FROM Users
				WHERE ([UID] = @senderID AND @UID is not null AND [UID] = @UID AND @username is null)	--vagy magát kérte
					OR (isAdmin = 1 AND [UID] = @senderID)												--vagy admin

			IF @isOk > 0
				begin
					if @UID is null and @username is null
						begin

							SELECT [UID], FullName, UserName, [Description], BirthDate, isAdmin FROM Users
						
						end
					else if @username is not null
						begin
							SELECT [UID], FullName, UserName, [Description], BirthDate, isAdmin FROM Users
								WHERE [UserName] LIKE '%' + @username + '%'
						end
					else
						begin
							
							SELECT [UID], FullName, UserName, [Description], BirthDate, isAdmin FROM Users
								WHERE [UID] = @UID

						end
				end

		end
END
GO
/****** Object:  StoredProcedure [dbo].[setQA]    Script Date: 2021. 06. 16. 21:33:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		CBB - ZB
-- Create date: 2021-06-12
-- Description:	Kérdés-válasz feltöltés
-- =============================================
CREATE PROCEDURE [dbo].[setQA] 
	@question varchar(256),
	@answer varchar(128),
	@UGID int,
	@TID int
AS
BEGIN

	INSERT INTO [dbo].[QandA]
				([Question]
				,[Answer]
				,[TID]
				,[UGID])
			VALUES
				(@question
				,@answer
				,@TID
				,@UGID)

	
END
GO
/****** Object:  StoredProcedure [dbo].[setUserGroups]    Script Date: 2021. 06. 16. 21:33:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		CBB - ZB
-- Create date: 2021-06-13
-- Description:	Felhasználói csoportok szerkesztése
-- =============================================
CREATE PROCEDURE [dbo].[setUserGroups]
	@UGID int = null,
	@UID int = null,
	@UserGroupName varchar(32) = null,
	@senderID int,
	@delete int = 0
AS
BEGIN
	DECLARE @isAdmin int
	SELECT @isAdmin = u.isAdmin FROM Users u
		JOIN UsersSessions us on us.[UID] = u.[UID]
		WHERE u.[UID] = @senderID AND us.Until > GETDATE()

	IF @isAdmin is not null AND @isAdmin = 1
		begin
			IF @delete = 0
				begin

					if @UserGroupName is not null AND @UGID is null
						begin
							DECLARE @newID int

							INSERT INTO [dbo].[UserGroups]
									   ([GroupName])
								 VALUES
									   (@UserGroupName)

							SELECT @newID = @@IDENTITY
						end

					else if @UserGroupName is not null AND @UGID is not null
						begin
							UPDATE UserGroups
								SET GroupName = @UserGroupName
								WHERE UGID = @UGID
						end

					else if @UID is not null and @UGID is not null
						begin
							INSERT INTO [dbo].[UsersToUGroups]
									   ([UID]
									   ,[UGID])
								 VALUES
									   (@UID
									   ,@UGID)
						end

				end
			ELSE
				begin

					if @UGID is not null and @UID is null
						begin
							DELETE FROM UserGroups
								WHERE UGID = @UGID

							DELETE FROM UsersToUGroups
								WHERE UGID = @UGID

							DELETE FROM UsersGroupsToTasks
								WHERE UGID = @UGID

							DELETE FROM QandA 
								WHERE UGID = @UGID
						end

					else if @UID is not null and @UGID is not null
						begin
							DELETE FROM UsersToUGroups
								WHERE UGID = @UGID AND [UID] = @UID
						end

				end
		end
END
GO
/****** Object:  StoredProcedure [dbo].[setUsers]    Script Date: 2021. 06. 16. 21:33:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		CBB - ZB
-- Create date: 2021-06-08
-- Description:	Felhasználók módosítása
-- =============================================
CREATE PROCEDURE [dbo].[setUsers] 
	@sessionid char(36),
	@senderID int,
	@UID int = NULL,
	@username varchar(32) = NULL,
	@fullname varchar(64) = NULL,
	@description varchar(256) = NULL,
	@psw char(256) = NULL,
	@birthdate date = NULL,
	@delete int = 0
AS
BEGIN
	DECLARE @SID uniqueidentifier
	SELECT @SID = CONVERT(uniqueidentifier, @sessionid)

	DECLARE @isOk int
	SELECT @isOk = COUNT(us.[UID]) FROM UsersSessions us
		JOIN Users u on u.[UID] = us.[UID]
		WHERE us.SesID = @SID AND us.Until > GETDATE()

	IF @isOk > 0
		begin
			SELECT @isOk = 0
			SELECT @isOk = COUNT([UID]) FROM Users
				WHERE ([UID] = @senderID AND [UID] is not null AND [UID] = @UID)			--vagy önmaga és magát kérte
					OR (isAdmin = 1 AND [UID] = @senderID)							--vagy admin

			IF @isOk > 0
				begin
					if @delete = 0
						begin
							if @UID is not null
								begin
									DECLARE @alternateB date
									DECLARE @alternateP char(256)

									SELECT @alternateB = BirthDate, @alternateP = HSHD_PASSWORD FROM Users
										WHERE [UID] = @UID

									UPDATE [dbo].[Users]
									   SET [FullName] = @fullname
										  ,[UserName] = @username
										  ,[Description] = @description
										  ,[BirthDate] = ISNULL(@birthdate, @alternateB)
										  ,[HSHD_PASSWORD] = ISNULL(@psw, @alternateP)
									 WHERE [UID] = @UID
								end
							else
								begin

									INSERT INTO [dbo].[Users]
											   ([FullName]
											   ,[UserName]
											   ,[Description]
											   ,[BirthDate]
											   ,[HSHD_PASSWORD]
											   ,[isAdmin])
										 VALUES
											   (@fullname
											   ,@username
											   ,@description
											   ,@birthdate
											   ,@psw
											   ,0)

								end
						end
					else
						begin
							DELETE FROM Users
								WHERE [UID] = @UID

							DELETE FROM UsersToEvents
								WHERE [UID] = @UID

							DELETE FROM UsersToUGroups
								WHERE [UID] = @UID

							DELETE FROM UsersSessions
								WHERE [UID] = @UID
						end
				end

		end
END
GO
/****** Object:  StoredProcedure [dbo].[writeMessage]    Script Date: 2021. 06. 16. 21:33:33 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		CBB - ZB
-- Create date: 2021-06-08
-- Description:	Üzenet írása
-- =============================================
CREATE PROCEDURE [dbo].[writeMessage] 
	@uid int,
	@sessionid char(36),
	@content varchar(128),
	@subject varchar(64) = NULL,
	@MID int = NULL,
	@toID int = NULL,
	@isGroup int = NULL
AS
BEGIN
	DECLARE @isUser int
	DECLARE @isAdmin bit
	DECLARE @SID uniqueidentifier
	SELECT @SID = CONVERT(uniqueidentifier, @sessionid)

	SELECT @isUser = us.[UID], @isAdmin = u.isAdmin FROM UsersSessions us
		JOIN Users u ON us.[UID] = u.[UID]
		WHERE us.Until > GETDATE() AND us.[UID] = @uid ANd us.SesID = @SID

	IF @isUser is not null
		begin
			if @MID is null and @subject is not null		--új üzenet
				begin
					DECLARE @newMID int

					if @isAdmin = 1
						begin

							if @isGroup = 0
								begin

									INSERT INTO [dbo].[Messages]
											   ([Content]
											   ,[Date]
											   ,[SenderID])
										 VALUES
											   (@content
											   ,GETDATE()
											   ,@uid)
									SELECT @newMID = @@IDENTITY

									INSERT INTO [dbo].[MessagesToFrom]
										   ([MID]
										   ,[UID1]
										   ,[UID2]
										   ,[Subject]
										   ,[LastMessage])
									 VALUES
										   (@newMID
										   ,@uid
										   ,@toID
										   ,@subject
										   ,GETDATE())
								end
							else
								begin
									DECLARE @usID int

									Select [UID] as 'UID'
										Into   #Temp
										FROM UserToGroups
										WHERE [UGID] = @toID

									While (Select Count(*) From #Temp) > 0
										Begin
											Select Top 1 @usID = [UID] From #Temp

											INSERT INTO [dbo].[Messages]
											   ([Content]
											   ,[Date]
											   ,[SenderID])
											 VALUES
												   (@content
												   ,GETDATE()
												   ,@uid)

											SELECT @newMID = @@IDENTITY

											INSERT INTO [dbo].[MessagesToFrom]
													([MID]
													,[UID1]
													,[UID2]
													,[Subject]
													,[LastMessage])
													VALUES
														(@newMID
														,@uid
														,@usID
														,@subject
														,GETDATE())

											Delete #Temp Where [UID] = @usID

										End
								end
						end
					else
						begin
					
							-- gyerek ír Adminnak (mindnek? közös chat? egynek?...)
							return

						end

				end
			else if @MID is not null
				begin

					INSERT INTO [dbo].[Messages]
							   ([MID]
							   ,[Content]
							   ,[Date]
							   ,[SenderID])
						 VALUES
							   (@MID
							   ,@content
							   ,GETDATE()
							   ,@uid)

					UPDATE [dbo].[MessagesToFrom]
					   SET [LastMessage] = GETDATE()
					 WHERE [MID] = @MID

				end
				
		end
END
GO