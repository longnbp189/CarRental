USE [master]
GO
/****** Object:  Database [Car_Rental]    Script Date: 03/04/2021 3:52:53 PM ******/
CREATE DATABASE [Car_Rental]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Car_Rental', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\Car_Rental.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'Car_Rental_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\Car_Rental_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [Car_Rental] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Car_Rental].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Car_Rental] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Car_Rental] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Car_Rental] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Car_Rental] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Car_Rental] SET ARITHABORT OFF 
GO
ALTER DATABASE [Car_Rental] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Car_Rental] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Car_Rental] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Car_Rental] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Car_Rental] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Car_Rental] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Car_Rental] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Car_Rental] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Car_Rental] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Car_Rental] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Car_Rental] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Car_Rental] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Car_Rental] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Car_Rental] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Car_Rental] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Car_Rental] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Car_Rental] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Car_Rental] SET RECOVERY FULL 
GO
ALTER DATABASE [Car_Rental] SET  MULTI_USER 
GO
ALTER DATABASE [Car_Rental] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Car_Rental] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Car_Rental] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Car_Rental] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [Car_Rental] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'Car_Rental', N'ON'
GO
USE [Car_Rental]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 03/04/2021 3:52:53 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Account](
	[username] [varchar](200) NOT NULL,
	[password] [varchar](300) NOT NULL,
	[fullname] [nvarchar](150) NOT NULL,
	[role] [bit] NOT NULL,
	[phone] [varchar](15) NOT NULL,
	[address] [varchar](150) NOT NULL,
	[createDate] [datetime] NOT NULL,
	[status] [bit] NOT NULL,
	[activation] [varchar](max) NULL,
 CONSTRAINT [PK_account] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Car]    Script Date: 03/04/2021 3:52:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Car](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[carName] [varchar](50) NOT NULL,
	[color] [varchar](50) NOT NULL,
	[year] [int] NOT NULL,
	[price] [float] NOT NULL,
	[quantity] [int] NOT NULL,
	[picture] [varchar](max) NOT NULL,
	[status] [bit] NOT NULL,
	[categoryId] [varchar](50) NOT NULL,
	[typeId] [varchar](50) NOT NULL,
 CONSTRAINT [PK_car] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Category]    Script Date: 03/04/2021 3:52:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Category](
	[categoryId] [varchar](50) NOT NULL,
	[categoryName] [varchar](150) NOT NULL,
 CONSTRAINT [PK_category] PRIMARY KEY CLUSTERED 
(
	[categoryId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Discount]    Script Date: 03/04/2021 3:52:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Discount](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[discountCode] [varchar](50) NOT NULL,
	[expireDate] [datetime] NOT NULL,
	[discountPercent] [float] NOT NULL,
 CONSTRAINT [PK_Discount] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Rental]    Script Date: 03/04/2021 3:52:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Rental](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[total] [float] NOT NULL,
	[rentalDate] [datetime] NOT NULL,
	[username] [varchar](200) NOT NULL,
	[discountId] [int] NULL,
	[status] [bit] NOT NULL,
 CONSTRAINT [PK_rental] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[RentalDetail]    Script Date: 03/04/2021 3:52:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[RentalDetail](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[rentalId] [int] NOT NULL,
	[carId] [int] NOT NULL,
	[quantity] [int] NOT NULL,
	[price] [float] NOT NULL,
	[startDate] [datetime] NOT NULL,
	[endDate] [datetime] NOT NULL,
	[feedbackContent] [varchar](max) NULL,
	[rating] [int] NULL,
 CONSTRAINT [PK_RentalDetail] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Type]    Script Date: 03/04/2021 3:52:54 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Type](
	[id] [varchar](50) NOT NULL,
	[typeName] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Type] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[Account] ([username], [password], [fullname], [role], [phone], [address], [createDate], [status], [activation]) VALUES (N'b@gmail.com', N'123456', N'ÃÂ¡dsdsd', 0, N'1234567878', N'asd', CAST(N'2021-03-11 16:24:59.433' AS DateTime), 0, N'PzaQP3')
INSERT [dbo].[Account] ([username], [password], [fullname], [role], [phone], [address], [createDate], [status], [activation]) VALUES (N'h@gmail.com', N'123456', N'Long Long', 0, N'0321654897', N'c1', CAST(N'2021-03-06 13:26:43.667' AS DateTime), 1, N'b4B6vn')
INSERT [dbo].[Account] ([username], [password], [fullname], [role], [phone], [address], [createDate], [status], [activation]) VALUES (N'hsddung92long@gmail.com', N'123456', N'long nguyen', 0, N'0123456789', N'quan 9', CAST(N'2021-03-10 07:18:08.767' AS DateTime), 1, N'Cdi0I3')
INSERT [dbo].[Account] ([username], [password], [fullname], [role], [phone], [address], [createDate], [status], [activation]) VALUES (N'SE140811@gmail.com', N'123456', N'Dat Do', 1, N'4556789102', N'District 10', CAST(N'2021-02-28 00:00:00.000' AS DateTime), 1, NULL)
INSERT [dbo].[Account] ([username], [password], [fullname], [role], [phone], [address], [createDate], [status], [activation]) VALUES (N'SE140814@gmail.com', N'123456', N'Long Nguyen', 0, N'1234567890', N'District 9', CAST(N'2021-02-27 17:34:47.100' AS DateTime), 1, N'VE1Yfs')
SET IDENTITY_INSERT [dbo].[Car] ON 

INSERT [dbo].[Car] ([id], [carName], [color], [year], [price], [quantity], [picture], [status], [categoryId], [typeId]) VALUES (1, N'Jaguar XJL', N'black', 2018, 65, 2, N'https://cdn-thethao247.com/origin_850x0/upload/thanh96/2020/05/07/jaguar-xjl.jpg', 1, N'c1', N't4')
INSERT [dbo].[Car] ([id], [carName], [color], [year], [price], [quantity], [picture], [status], [categoryId], [typeId]) VALUES (2, N'BMW 7 Series', N'gray', 2019, 70, 5, N'https://cdn-thethao247.com/origin_850x0/upload/thanh96/2020/05/07/bmw-7-series.jpg', 1, N'c2', N't4')
INSERT [dbo].[Car] ([id], [carName], [color], [year], [price], [quantity], [picture], [status], [categoryId], [typeId]) VALUES (3, N'Audi A8', N'black', 2020, 60, 4, N'https://cdn-thethao247.com/origin_850x0/upload/thanh96/2020/05/07/audi-a8.jpg', 1, N'c3', N't2')
INSERT [dbo].[Car] ([id], [carName], [color], [year], [price], [quantity], [picture], [status], [categoryId], [typeId]) VALUES (4, N'BRIO', N'orange', 2021, 90, 10, N'http://hondaotodn.com/wp-content/uploads/2020/01/sf.png', 1, N'c1', N't4')
INSERT [dbo].[Car] ([id], [carName], [color], [year], [price], [quantity], [picture], [status], [categoryId], [typeId]) VALUES (5, N'CRV', N'gray', 2020, 40, 6, N'http://hondaotodn.com/wp-content/uploads/2020/08/724dbc2f137d4ffdd4ffe079119836b9.png', 1, N'c1', N't4')
INSERT [dbo].[Car] ([id], [carName], [color], [year], [price], [quantity], [picture], [status], [categoryId], [typeId]) VALUES (6, N'CITY', N'red', 2021, 75, 3, N'http://hondaotodn.com/wp-content/uploads/2021/01/2020-Honda-City-RS-Preview-1-850x567-1.jpg', 1, N'c1', N't4')
INSERT [dbo].[Car] ([id], [carName], [color], [year], [price], [quantity], [picture], [status], [categoryId], [typeId]) VALUES (7, N'CIVIC', N'orange', 2021, 50, 2, N'http://hondaotodn.com/wp-content/uploads/2020/01/CVS19-101-red.png', 1, N'c1', N't4')
INSERT [dbo].[Car] ([id], [carName], [color], [year], [price], [quantity], [picture], [status], [categoryId], [typeId]) VALUES (8, N'ACCORD', N'black', 2021, 60, 5, N'http://hondaotodn.com/wp-content/uploads/2020/01/1160x689.png', 1, N'c1', N't4')
INSERT [dbo].[Car] ([id], [carName], [color], [year], [price], [quantity], [picture], [status], [categoryId], [typeId]) VALUES (9, N'PORTER H150', N'white', 2020, 20, 6, N'https://hyundaitansonnhat.com/wp-content/uploads/2020/07/PORTER-H150-1.png', 1, N'c2', N't2')
INSERT [dbo].[Car] ([id], [carName], [color], [year], [price], [quantity], [picture], [status], [categoryId], [typeId]) VALUES (10, N'KONA', N'blue', 2021, 40, 5, N'https://hyundaitansonnhat.com/wp-content/uploads/2020/07/KONA-1.png', 1, N'c2', N't4')
INSERT [dbo].[Car] ([id], [carName], [color], [year], [price], [quantity], [picture], [status], [categoryId], [typeId]) VALUES (11, N'SOLATI', N'white', 2020, 30, 4, N'https://hyundaitansonnhat.com/wp-content/uploads/2020/07/SOLATI-1.png', 1, N'c2', N't2')
INSERT [dbo].[Car] ([id], [carName], [color], [year], [price], [quantity], [picture], [status], [categoryId], [typeId]) VALUES (12, N'SANTAFE', N'blue', 2019, 35, 3, N'https://hyundaitansonnhat.com/wp-content/uploads/2020/07/SANTAFE-1.png', 1, N'c2', N't4')
INSERT [dbo].[Car] ([id], [carName], [color], [year], [price], [quantity], [picture], [status], [categoryId], [typeId]) VALUES (13, N'TUCSON', N'gray', 2020, 56, 4, N'https://hyundaitansonnhat.com/wp-content/uploads/2020/07/TUCSON-1.png', 1, N'c2', N't4')
INSERT [dbo].[Car] ([id], [carName], [color], [year], [price], [quantity], [picture], [status], [categoryId], [typeId]) VALUES (14, N'Wigo', N'yellow', 2021, 60, 5, N'https://toyota-lythuongkiet.vn/wp-content/uploads/2019/02/home-wigo-1.png', 1, N'c3', N't4')
INSERT [dbo].[Car] ([id], [carName], [color], [year], [price], [quantity], [picture], [status], [categoryId], [typeId]) VALUES (15, N'Hilux', N'orange', 2021, 70, 6, N'https://toyota-lythuongkiet.vn/wp-content/uploads/2019/02/home-hilux-1.png', 1, N'c3', N't2')
INSERT [dbo].[Car] ([id], [carName], [color], [year], [price], [quantity], [picture], [status], [categoryId], [typeId]) VALUES (16, N'Alphard Luxury', N'black', 2020, 60, 4, N'https://toyota-lythuongkiet.vn/wp-content/uploads/2019/02/home-alphard-luxury.png', 1, N'c3', N't4')
INSERT [dbo].[Car] ([id], [carName], [color], [year], [price], [quantity], [picture], [status], [categoryId], [typeId]) VALUES (17, N'Innova', N'red', 2021, 40, 3, N'https://toyota-lythuongkiet.vn/wp-content/uploads/2020/10/home-innova.png', 1, N'c3', N't4')
INSERT [dbo].[Car] ([id], [carName], [color], [year], [price], [quantity], [picture], [status], [categoryId], [typeId]) VALUES (18, N'Land Prado', N'gray', 2021, 60, 4, N'https://toyota-lythuongkiet.vn/wp-content/uploads/2019/02/home-land-cruiser-prado.png', 1, N'c3', N't4')
INSERT [dbo].[Car] ([id], [carName], [color], [year], [price], [quantity], [picture], [status], [categoryId], [typeId]) VALUES (19, N'Mercedes-AMG GT Coupé', N'green', 2021, 100, 3, N'https://assets.oneweb.mercedes-benz.com/iris/iris.jpg?COSY-EU-100-1713d0VXqN8FqtyO67PobzIr3eWsrrCsdRRzwQZvVIZbMw3SGtGyItsd2JdcUfpAyXGEjymJ0leIAOB2sSnbApvPyI5uOo2QC3bsOkzNGTnm7j07ZhKVBYF%25vq8cXyr%25kWfDPJJ0lCrnOIJRdAbQOznRI5ue4YQC3PExkzN5%256m7jCtEhKVfj9%25vqLU9yLRaGfYaxHoWrH18jOn8wioxoiZ4egM4zuA1YtEWpTuz6ULquFIT9ZxeedNtjD%259j6hVNpLpIZIGwC7Ux0wPfejr9j&imgt=P27&bkgnd=9&pov=BE140&im=Crop,rect=(290,250,1280,580);Resize,width=300', 1, N'c5', N't4')
INSERT [dbo].[Car] ([id], [carName], [color], [year], [price], [quantity], [picture], [status], [categoryId], [typeId]) VALUES (20, N'D-Max', N'orange', 2021, 67, 4, N'https://i.xeoto.com.vn/auto/w250/isuzu/d-max/isuzu-d-max-2020.png', 1, N'c4', N't2')
INSERT [dbo].[Car] ([id], [carName], [color], [year], [price], [quantity], [picture], [status], [categoryId], [typeId]) VALUES (21, N'BMW M240i', N'blue', 2021, 70, 4, N'https://blogcdn.muaban.net/wp-content/uploads/2019/04/BMW-M240i.jpg', 1, N'c2', N't3')
INSERT [dbo].[Car] ([id], [carName], [color], [year], [price], [quantity], [picture], [status], [categoryId], [typeId]) VALUES (22, N'Mazda Miata', N'red', 2020, 60, 3, N'https://blogcdn.muaban.net/wp-content/uploads/2019/04/Mazda-Miata.jpg', 1, N'c3', N't3')
INSERT [dbo].[Car] ([id], [carName], [color], [year], [price], [quantity], [picture], [status], [categoryId], [typeId]) VALUES (23, N'Ford Mustang EcoBoost', N'red', 2021, 63, 3, N'https://blogcdn.muaban.net/wp-content/uploads/2019/04/Ford-Mustang-EcoBoost.jpg', 1, N'c1', N't3')
INSERT [dbo].[Car] ([id], [carName], [color], [year], [price], [quantity], [picture], [status], [categoryId], [typeId]) VALUES (24, N'Chevrolet Camaro ZL1', N'green', 2021, 90, 9, N'https://blogcdn.muaban.net/wp-content/uploads/2019/04/Chevrolet-Camaro-1LE-1.jpg', 1, N'c2', N't3')
SET IDENTITY_INSERT [dbo].[Car] OFF
INSERT [dbo].[Category] ([categoryId], [categoryName]) VALUES (N'c1', N'Honda')
INSERT [dbo].[Category] ([categoryId], [categoryName]) VALUES (N'c2', N'Hyundai')
INSERT [dbo].[Category] ([categoryId], [categoryName]) VALUES (N'c3', N'Toyota')
INSERT [dbo].[Category] ([categoryId], [categoryName]) VALUES (N'c4', N'Isuzu')
INSERT [dbo].[Category] ([categoryId], [categoryName]) VALUES (N'c5', N'Mercedes Benz')
SET IDENTITY_INSERT [dbo].[Discount] ON 

INSERT [dbo].[Discount] ([id], [discountCode], [expireDate], [discountPercent]) VALUES (1, N'123', CAST(N'2021-03-05 00:00:00.000' AS DateTime), 20)
INSERT [dbo].[Discount] ([id], [discountCode], [expireDate], [discountPercent]) VALUES (2, N'145', CAST(N'2021-03-04 00:00:00.000' AS DateTime), 10)
INSERT [dbo].[Discount] ([id], [discountCode], [expireDate], [discountPercent]) VALUES (5, N'1234', CAST(N'2021-03-06 00:00:00.000' AS DateTime), 30)
INSERT [dbo].[Discount] ([id], [discountCode], [expireDate], [discountPercent]) VALUES (10, N'12345', CAST(N'2021-03-18 00:00:00.000' AS DateTime), 30)
SET IDENTITY_INSERT [dbo].[Discount] OFF
SET IDENTITY_INSERT [dbo].[Rental] ON 

INSERT [dbo].[Rental] ([id], [total], [rentalDate], [username], [discountId], [status]) VALUES (19, 1881.5999755859375, CAST(N'2021-03-10 02:34:24.837' AS DateTime), N'se140814@gmail.com', 10, 1)
INSERT [dbo].[Rental] ([id], [total], [rentalDate], [username], [discountId], [status]) VALUES (20, 284.20001220703125, CAST(N'2021-03-10 07:38:33.677' AS DateTime), N'se140814@gmail.com', 10, 1)
SET IDENTITY_INSERT [dbo].[Rental] OFF
SET IDENTITY_INSERT [dbo].[RentalDetail] ON 

INSERT [dbo].[RentalDetail] ([id], [rentalId], [carId], [quantity], [price], [startDate], [endDate], [feedbackContent], [rating]) VALUES (20, 19, 13, 4, 2688, CAST(N'2021-03-19 02:33:00.000' AS DateTime), CAST(N'2021-03-21 02:33:00.000' AS DateTime), N'', 0)
INSERT [dbo].[RentalDetail] ([id], [rentalId], [carId], [quantity], [price], [startDate], [endDate], [feedbackContent], [rating]) VALUES (21, 20, 12, 1, 70, CAST(N'2021-03-21 05:28:00.000' AS DateTime), CAST(N'2021-03-21 07:28:00.000' AS DateTime), N'', 0)
INSERT [dbo].[RentalDetail] ([id], [rentalId], [carId], [quantity], [price], [startDate], [endDate], [feedbackContent], [rating]) VALUES (22, 20, 13, 3, 336, CAST(N'2021-03-21 05:28:00.000' AS DateTime), CAST(N'2021-03-21 07:28:00.000' AS DateTime), N'', 0)
SET IDENTITY_INSERT [dbo].[RentalDetail] OFF
INSERT [dbo].[Type] ([id], [typeName]) VALUES (N't2', N'PICKUP TRUCK')
INSERT [dbo].[Type] ([id], [typeName]) VALUES (N't3', N'SPORTS CAR')
INSERT [dbo].[Type] ([id], [typeName]) VALUES (N't4', N'SEDAN')
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__Discount__3D87979A9C8BB95B]    Script Date: 03/04/2021 3:52:54 PM ******/
ALTER TABLE [dbo].[Discount] ADD UNIQUE NONCLUSTERED 
(
	[discountCode] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Car]  WITH CHECK ADD  CONSTRAINT [FK_Car_Category] FOREIGN KEY([categoryId])
REFERENCES [dbo].[Category] ([categoryId])
GO
ALTER TABLE [dbo].[Car] CHECK CONSTRAINT [FK_Car_Category]
GO
ALTER TABLE [dbo].[Car]  WITH CHECK ADD  CONSTRAINT [FK_Car_Type] FOREIGN KEY([typeId])
REFERENCES [dbo].[Type] ([id])
GO
ALTER TABLE [dbo].[Car] CHECK CONSTRAINT [FK_Car_Type]
GO
ALTER TABLE [dbo].[Rental]  WITH CHECK ADD  CONSTRAINT [FK_Rental_Account] FOREIGN KEY([username])
REFERENCES [dbo].[Account] ([username])
GO
ALTER TABLE [dbo].[Rental] CHECK CONSTRAINT [FK_Rental_Account]
GO
ALTER TABLE [dbo].[Rental]  WITH CHECK ADD  CONSTRAINT [FK_Rental_Discount] FOREIGN KEY([discountId])
REFERENCES [dbo].[Discount] ([id])
GO
ALTER TABLE [dbo].[Rental] CHECK CONSTRAINT [FK_Rental_Discount]
GO
ALTER TABLE [dbo].[RentalDetail]  WITH CHECK ADD  CONSTRAINT [FK_RentalDetail_Car] FOREIGN KEY([carId])
REFERENCES [dbo].[Car] ([id])
GO
ALTER TABLE [dbo].[RentalDetail] CHECK CONSTRAINT [FK_RentalDetail_Car]
GO
ALTER TABLE [dbo].[RentalDetail]  WITH CHECK ADD  CONSTRAINT [FK_RentalDetail_Rental] FOREIGN KEY([rentalId])
REFERENCES [dbo].[Rental] ([id])
GO
ALTER TABLE [dbo].[RentalDetail] CHECK CONSTRAINT [FK_RentalDetail_Rental]
GO
USE [master]
GO
ALTER DATABASE [Car_Rental] SET  READ_WRITE 
GO
