CREATE DATABASE CINEBOOST
GO

USE [CINEBOOST]
GO
/****** Object:  Trigger [tg_xoavedat]    Script Date: 21/04/2022 21:32:26 ******/
DROP TRIGGER [dbo].[tg_xoavedat]
GO
/****** Object:  Trigger [tg_tv]    Script Date: 21/04/2022 21:32:26 ******/
DROP TRIGGER [dbo].[tg_tv]
GO
/****** Object:  Trigger [tg_sc]    Script Date: 21/04/2022 21:32:26 ******/
DROP TRIGGER [dbo].[tg_sc]
GO
/****** Object:  Trigger [tg_pc]    Script Date: 21/04/2022 21:32:26 ******/
DROP TRIGGER [dbo].[tg_pc]
GO
/****** Object:  Trigger [tg_phim]    Script Date: 21/04/2022 21:32:26 ******/
DROP TRIGGER [dbo].[tg_phim]
GO
/****** Object:  Trigger [tg_xoa_nv]    Script Date: 21/04/2022 21:32:26 ******/
DROP TRIGGER [dbo].[tg_xoa_nv]
GO
/****** Object:  Trigger [tg_nv]    Script Date: 21/04/2022 21:32:26 ******/
DROP TRIGGER [dbo].[tg_nv]
GO
/****** Object:  Trigger [tg_xoaKCDA]    Script Date: 21/04/2022 21:32:26 ******/
DROP TRIGGER [dbo].[tg_xoaKCDA]
GO
/****** Object:  Trigger [tg_ghe]    Script Date: 21/04/2022 21:32:26 ******/
DROP TRIGGER [dbo].[tg_ghe]
GO
/****** Object:  Trigger [tg_donve]    Script Date: 21/04/2022 21:32:26 ******/
DROP TRIGGER [dbo].[tg_donve]
GO
/****** Object:  Trigger [tg_xoa_donThanhToan]    Script Date: 21/04/2022 21:32:26 ******/
DROP TRIGGER [dbo].[tg_xoa_donThanhToan]
GO
/****** Object:  Trigger [tg_DonDoan]    Script Date: 21/04/2022 21:32:26 ******/
DROP TRIGGER [dbo].[tg_DonDoan]
GO
/****** Object:  Trigger [tg_xoaDoAn]    Script Date: 21/04/2022 21:32:26 ******/
DROP TRIGGER [dbo].[tg_xoaDoAn]
GO
/****** Object:  Trigger [tg_doan]    Script Date: 21/04/2022 21:32:26 ******/
DROP TRIGGER [dbo].[tg_doan]
GO
/****** Object:  StoredProcedure [dbo].[SP_UPDATETONGDON]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[SP_UPDATETONGDON]
GO
/****** Object:  StoredProcedure [dbo].[sp_tracuulichchieu]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_tracuulichchieu]
GO
/****** Object:  StoredProcedure [dbo].[SP_TINHTONGDONVE]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[SP_TINHTONGDONVE]
GO
/****** Object:  StoredProcedure [dbo].[SP_TINHTONGDONDA]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[SP_TINHTONGDONDA]
GO
/****** Object:  StoredProcedure [dbo].[sp_thongke_khunggio]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_thongke_khunggio]
GO
/****** Object:  StoredProcedure [dbo].[sp_themdontt]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_themdontt]
GO
/****** Object:  StoredProcedure [dbo].[sp_themdonOf]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_themdonOf]
GO
/****** Object:  StoredProcedure [dbo].[SP_THEMDON]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[SP_THEMDON]
GO
/****** Object:  StoredProcedure [dbo].[sp_themDoAn]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_themDoAn]
GO
/****** Object:  StoredProcedure [dbo].[SP_SUATDACHIEUCUAPHONG]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[SP_SUATDACHIEUCUAPHONG]
GO
/****** Object:  StoredProcedure [dbo].[sp_suaDoAn]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_suaDoAn]
GO
/****** Object:  StoredProcedure [dbo].[SP_SELECTLICHPHONG]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[SP_SELECTLICHPHONG]
GO
/****** Object:  StoredProcedure [dbo].[SP_SELECTLICHBYTRANGTHAI]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[SP_SELECTLICHBYTRANGTHAI]
GO
/****** Object:  StoredProcedure [dbo].[SP_SELECTLICH]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[SP_SELECTLICH]
GO
/****** Object:  StoredProcedure [dbo].[sp_sapxepdoan]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_sapxepdoan]
GO
/****** Object:  StoredProcedure [dbo].[sp_qlsuatchieu]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_qlsuatchieu]
GO
/****** Object:  StoredProcedure [dbo].[sp_qldonthanhtoan]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_qldonthanhtoan]
GO
/****** Object:  StoredProcedure [dbo].[sp_qldoan]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_qldoan]
GO
/****** Object:  StoredProcedure [dbo].[sp_luongvedaban_theothangnam]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_luongvedaban_theothangnam]
GO
/****** Object:  StoredProcedure [dbo].[sp_luongvedaban_theongay]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_luongvedaban_theongay]
GO
/****** Object:  StoredProcedure [dbo].[sp_luongvedaban_theonam]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_luongvedaban_theonam]
GO
/****** Object:  StoredProcedure [dbo].[sp_luongvedaban_sapxep]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_luongvedaban_sapxep]
GO
/****** Object:  StoredProcedure [dbo].[sp_luongvedaban]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_luongvedaban]
GO
/****** Object:  StoredProcedure [dbo].[SP_LUONGVEBANCUASUAT]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[SP_LUONGVEBANCUASUAT]
GO
/****** Object:  StoredProcedure [dbo].[SP_LICHCHIEUNGAY]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[SP_LICHCHIEUNGAY]
GO
/****** Object:  StoredProcedure [dbo].[SP_GETTONGDON]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[SP_GETTONGDON]
GO
/****** Object:  StoredProcedure [dbo].[SP_FINDTIMESLOT]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[SP_FINDTIMESLOT]
GO
/****** Object:  StoredProcedure [dbo].[sp_donVe]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_donVe]
GO
/****** Object:  StoredProcedure [dbo].[sp_donThanhToan_NgayMua]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_donThanhToan_NgayMua]
GO
/****** Object:  StoredProcedure [dbo].[sp_donThanhToan_KhachHang]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_donThanhToan_KhachHang]
GO
/****** Object:  StoredProcedure [dbo].[sp_donthanhtoan]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_donthanhtoan]
GO
/****** Object:  StoredProcedure [dbo].[sp_donDoAn]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_donDoAn]
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthutong_thang]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_doanhthutong_thang]
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthutong_ngay]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_doanhthutong_ngay]
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthutong_nam]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_doanhthutong_nam]
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthutong]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_doanhthutong]
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthuphongve_theothangnam]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_doanhthuphongve_theothangnam]
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthuphongve_theongay]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_doanhthuphongve_theongay]
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthuphongve_theonam]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_doanhthuphongve_theonam]
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthuphongve_sapxep]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_doanhthuphongve_sapxep]
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthuphongve]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_doanhthuphongve]
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthuphim_top]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_doanhthuphim_top]
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthuphim_theothangnam]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_doanhthuphim_theothangnam]
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthuphim_theongay]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_doanhthuphim_theongay]
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthuphim_theonam]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_doanhthuphim_theonam]
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthuphim_sapxep]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_doanhthuphim_sapxep]
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthuphim]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_doanhthuphim]
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthudoan_theothangnam]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_doanhthudoan_theothangnam]
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthudoan_theoten]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_doanhthudoan_theoten]
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthudoan_theosize]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_doanhthudoan_theosize]
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthudoan_theongay]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_doanhthudoan_theongay]
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthudoan_theonam]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_doanhthudoan_theonam]
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthudoan]    Script Date: 21/04/2022 21:32:26 ******/
DROP PROCEDURE [dbo].[sp_doanhthudoan]
GO
ALTER TABLE [dbo].[VEDAT] DROP CONSTRAINT [FK_VEDAT_SUAT]
GO
ALTER TABLE [dbo].[VEDAT] DROP CONSTRAINT [FK_VEDAT_LOAIVE]
GO
ALTER TABLE [dbo].[VEDAT] DROP CONSTRAINT [FK_VEDAT_GHE]
GO
ALTER TABLE [dbo].[VEDAT] DROP CONSTRAINT [FK_VEDAT_DONDATVE]
GO
ALTER TABLE [dbo].[SUATCHIEU] DROP CONSTRAINT [FK_SC_PHONG]
GO
ALTER TABLE [dbo].[SUATCHIEU] DROP CONSTRAINT [FK_SC_PHIM]
GO
ALTER TABLE [dbo].[SUATCHIEU] DROP CONSTRAINT [FK_SC_NV]
GO
ALTER TABLE [dbo].[PHIM] DROP CONSTRAINT [FK_PHIM_NV]
GO
ALTER TABLE [dbo].[KICHCODOAN] DROP CONSTRAINT [FK_KICHCO_DOAN]
GO
ALTER TABLE [dbo].[KICHCODOAN] DROP CONSTRAINT [FK_KC_KCDA]
GO
ALTER TABLE [dbo].[GHE] DROP CONSTRAINT [FK_GHE_PHONG]
GO
ALTER TABLE [dbo].[GHE] DROP CONSTRAINT [FK_GHE_LOAI]
GO
ALTER TABLE [dbo].[DONTHANHTOAN] DROP CONSTRAINT [FK_DON_VE]
GO
ALTER TABLE [dbo].[DONTHANHTOAN] DROP CONSTRAINT [FK_DON_TV]
GO
ALTER TABLE [dbo].[DONTHANHTOAN] DROP CONSTRAINT [FK_DON_NV]
GO
ALTER TABLE [dbo].[DONTHANHTOAN] DROP CONSTRAINT [FK_DON_DA]
GO
ALTER TABLE [dbo].[DOANCT] DROP CONSTRAINT [FK_CT_KCDA]
GO
ALTER TABLE [dbo].[DOANCT] DROP CONSTRAINT [FK_CT_DONDA]
GO
ALTER TABLE [dbo].[DOAN] DROP CONSTRAINT [FK_DOAN_LOAI]
GO
/****** Object:  Index [U_G_S]    Script Date: 21/04/2022 21:32:26 ******/
ALTER TABLE [dbo].[VEDAT] DROP CONSTRAINT [U_G_S]
GO
/****** Object:  Index [UQ_SoDT_TV]    Script Date: 21/04/2022 21:32:26 ******/
ALTER TABLE [dbo].[THANHVIEN] DROP CONSTRAINT [UQ_SoDT_TV]
GO
/****** Object:  Index [UQ__NHANVIEN__A58DF1B81C18C60B]    Script Date: 21/04/2022 21:32:26 ******/
ALTER TABLE [dbo].[NHANVIEN] DROP CONSTRAINT [UQ__NHANVIEN__A58DF1B81C18C60B]
GO
/****** Object:  Index [UQ__NHANVIEN__161CF724FBF2B69B]    Script Date: 21/04/2022 21:32:26 ******/
ALTER TABLE [dbo].[NHANVIEN] DROP CONSTRAINT [UQ__NHANVIEN__161CF724FBF2B69B]
GO
/****** Object:  View [dbo].[vw_rand]    Script Date: 21/04/2022 21:32:26 ******/
DROP VIEW [dbo].[vw_rand]
GO
/****** Object:  Table [dbo].[VEDAT]    Script Date: 21/04/2022 21:32:26 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[VEDAT]') AND type in (N'U'))
DROP TABLE [dbo].[VEDAT]
GO
/****** Object:  Table [dbo].[THANHVIEN]    Script Date: 21/04/2022 21:32:26 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[THANHVIEN]') AND type in (N'U'))
DROP TABLE [dbo].[THANHVIEN]
GO
/****** Object:  Table [dbo].[SUATCHIEU]    Script Date: 21/04/2022 21:32:26 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[SUATCHIEU]') AND type in (N'U'))
DROP TABLE [dbo].[SUATCHIEU]
GO
/****** Object:  Table [dbo].[PHONGCHIEU]    Script Date: 21/04/2022 21:32:26 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[PHONGCHIEU]') AND type in (N'U'))
DROP TABLE [dbo].[PHONGCHIEU]
GO
/****** Object:  Table [dbo].[PHIM]    Script Date: 21/04/2022 21:32:26 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[PHIM]') AND type in (N'U'))
DROP TABLE [dbo].[PHIM]
GO
/****** Object:  Table [dbo].[NHANVIEN]    Script Date: 21/04/2022 21:32:26 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[NHANVIEN]') AND type in (N'U'))
DROP TABLE [dbo].[NHANVIEN]
GO
/****** Object:  Table [dbo].[LOAIVE]    Script Date: 21/04/2022 21:32:26 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[LOAIVE]') AND type in (N'U'))
DROP TABLE [dbo].[LOAIVE]
GO
/****** Object:  Table [dbo].[LOAIGHE]    Script Date: 21/04/2022 21:32:26 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[LOAIGHE]') AND type in (N'U'))
DROP TABLE [dbo].[LOAIGHE]
GO
/****** Object:  Table [dbo].[LOAIDOAN]    Script Date: 21/04/2022 21:32:26 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[LOAIDOAN]') AND type in (N'U'))
DROP TABLE [dbo].[LOAIDOAN]
GO
/****** Object:  Table [dbo].[KICHCODOAN]    Script Date: 21/04/2022 21:32:26 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[KICHCODOAN]') AND type in (N'U'))
DROP TABLE [dbo].[KICHCODOAN]
GO
/****** Object:  Table [dbo].[KICHCO]    Script Date: 21/04/2022 21:32:26 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[KICHCO]') AND type in (N'U'))
DROP TABLE [dbo].[KICHCO]
GO
/****** Object:  Table [dbo].[KCDAHISTORY]    Script Date: 21/04/2022 21:32:26 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[KCDAHISTORY]') AND type in (N'U'))
DROP TABLE [dbo].[KCDAHISTORY]
GO
/****** Object:  Table [dbo].[GHE]    Script Date: 21/04/2022 21:32:26 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[GHE]') AND type in (N'U'))
DROP TABLE [dbo].[GHE]
GO
/****** Object:  Table [dbo].[DONVE]    Script Date: 21/04/2022 21:32:26 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[DONVE]') AND type in (N'U'))
DROP TABLE [dbo].[DONVE]
GO
/****** Object:  Table [dbo].[DONTHANHTOAN]    Script Date: 21/04/2022 21:32:26 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[DONTHANHTOAN]') AND type in (N'U'))
DROP TABLE [dbo].[DONTHANHTOAN]
GO
/****** Object:  Table [dbo].[DONDOAN]    Script Date: 21/04/2022 21:32:26 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[DONDOAN]') AND type in (N'U'))
DROP TABLE [dbo].[DONDOAN]
GO
/****** Object:  Table [dbo].[DOANCT]    Script Date: 21/04/2022 21:32:26 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[DOANCT]') AND type in (N'U'))
DROP TABLE [dbo].[DOANCT]
GO
/****** Object:  Table [dbo].[DOAN]    Script Date: 21/04/2022 21:32:26 ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[DOAN]') AND type in (N'U'))
DROP TABLE [dbo].[DOAN]
GO
/****** Object:  UserDefinedFunction [dbo].[fn_RandIntBetween]    Script Date: 21/04/2022 21:32:26 ******/
DROP FUNCTION [dbo].[fn_RandIntBetween]
GO
/****** Object:  UserDefinedFunction [dbo].[fn_PickRandomChar]    Script Date: 21/04/2022 21:32:26 ******/
DROP FUNCTION [dbo].[fn_PickRandomChar]
GO
/****** Object:  UserDefinedFunction [dbo].[fn_MaKH]    Script Date: 21/04/2022 21:32:26 ******/
DROP FUNCTION [dbo].[fn_MaKH]
GO
/****** Object:  UserDefinedFunction [dbo].[fn_KhungGio]    Script Date: 21/04/2022 21:32:26 ******/
DROP FUNCTION [dbo].[fn_KhungGio]
GO
/****** Object:  UserDefinedFunction [dbo].[FN_GENERATE_MATV]    Script Date: 21/04/2022 21:32:26 ******/
DROP FUNCTION [dbo].[FN_GENERATE_MATV]
GO
USE [master]
GO
/****** Object:  Database [CINEBOOST]    Script Date: 21/04/2022 21:32:26 ******/

/****** Object:  Database [CINEBOOST]    Script Date: 21/04/2022 21:32:26 ******/
CREATE DATABASE [CINEBOOST]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'CINEBOOST', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\CINEBOOST.mdf' , SIZE = 4288KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'CINEBOOST_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\CINEBOOST.ldf' , SIZE = 1088KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [CINEBOOST] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [CINEBOOST].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [CINEBOOST] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [CINEBOOST] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [CINEBOOST] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [CINEBOOST] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [CINEBOOST] SET ARITHABORT OFF 
GO
ALTER DATABASE [CINEBOOST] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [CINEBOOST] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [CINEBOOST] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [CINEBOOST] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [CINEBOOST] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [CINEBOOST] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [CINEBOOST] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [CINEBOOST] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [CINEBOOST] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [CINEBOOST] SET  ENABLE_BROKER 
GO
ALTER DATABASE [CINEBOOST] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [CINEBOOST] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [CINEBOOST] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [CINEBOOST] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [CINEBOOST] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [CINEBOOST] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [CINEBOOST] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [CINEBOOST] SET RECOVERY FULL 
GO
ALTER DATABASE [CINEBOOST] SET  MULTI_USER 
GO
ALTER DATABASE [CINEBOOST] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [CINEBOOST] SET DB_CHAINING OFF 
GO
ALTER DATABASE [CINEBOOST] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [CINEBOOST] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [CINEBOOST] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'CINEBOOST', N'ON'
GO
USE [CINEBOOST]
GO
/****** Object:  UserDefinedFunction [dbo].[FN_GENERATE_MATV]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--------------------TAO MA_KH VOI SO LUONG KY TU, GIOI HAN CAC KY TY, LAY SO LUONG KY TU DUOI TRONG ID_TV
CREATE FUNCTION [dbo].[FN_GENERATE_MATV](
	@LENGHT INT,
	@ID_TV NVARCHAR(12),
	@CHARACTER NVARCHAR(Max),
	@SUB_OF_ID INT
)
RETURNS NVARCHAR(12) AS
BEGIN

		DECLARE @MATV NVARCHAR(12) = ''
		DECLARE @ID_TV_SUB nchar(3) = right(@ID_TV,@SUB_OF_ID)
		DECLARE @TIMES INT = @LENGHT - @SUB_OF_ID
		DECLARE @CHAR NCHAR(1) = ''
		WHILE @TIMES > 0
		BEGIN
			SET @CHAR = (SELECT dbo.fn_PickRandomChar(@CHARACTER))

			SET @MATV = @MATV + CAST(@CHAR AS nvarchar(1))
			SET @TIMES = @TIMES -1
		END

		SET @MATV = @MATV + @ID_TV_SUB

		RETURN @MATV + @ID_TV_SUB
END

GO
/****** Object:  UserDefinedFunction [dbo].[fn_KhungGio]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create function [dbo].[fn_KhungGio](@giobd_ngay time, @khoangcachgio int)
returns @bangkhunggio table (khunggio varchar(15))
as
	begin
		declare @giokt_ngay time = '23:00'
		declare @giotemp time = @giobd_ngay
		while CONVERT(time, @giotemp) < CONVERT(time, @giokt_ngay)
			begin
				set @giotemp = DATEADD(HOUR, @khoangcachgio, CONVERT(time, @giotemp))
				insert into @bangkhunggio
				select CONVERT(VARCHAR(15), LEFT(@giobd_ngay, 5)) + ' - ' + CONVERT(VARCHAR(15),LEFT(@giotemp, 5))
				set @giobd_ngay = DATEADD(HOUR, @khoangcachgio, CONVERT(time, @giobd_ngay))
			end
		return
	end
GO
/****** Object:  UserDefinedFunction [dbo].[fn_MaKH]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-----------------------HAM GOI LAY MA_KH 
create function [dbo].[fn_MaKH]
(@ID_TV nvarchar(16)) 
returns nvarchar(12) as
begin
		DECLARE @MA_LENGHT INT = 12;
		DECLARE @ID_TV_SUB nchar(3) = right(@ID_TV,3)
		DECLARE @CHARACTER NVARCHAR(Max) = 'ABCDEFGHJKLIMNOPQRSTUWXYZ0123456789'
		DECLARE @MAKH NVARCHAR(12) = ''
		DECLARE @TIMES INT = @MA_LENGHT - LEN(@ID_TV_SUB)
		DECLARE @CHAR NCHAR(1) = ''
		DECLARE @MaDaRand nvarchar(50) = (select Value from vw_rand)
		DECLARE @isin nvarchar(50) = (select count(*) from THANHVIEN where MAKH like @MaDaRand)
		
		SET @MAKH = (SELECT DBO.FN_GENERATE_MATV(@MA_LENGHT, @ID_TV, @CHARACTER,3))
		-- D130
		--SELECT COUNT(*) FROM THANHVIEN  WHERE MA_KH = @MATV
		--IF TRUNG SET @MAKH LAI
		while @isin != 0
		begin
			SET @MAKH = (SELECT DBO.FN_GENERATE_MATV(@MA_LENGHT, @ID_TV, @CHARACTER,3))
			set @isin = (select count(MAKH) from THANHVIEN where MAKH like @MaDaRand)
		end

		RETURN @MAKH
end
GO
/****** Object:  UserDefinedFunction [dbo].[fn_PickRandomChar]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

--------------------LAY MOT KY TU BAT KY TRONG 1 CHUOI
CREATE FUNCTION [dbo].[fn_PickRandomChar]
(
  @chars VARCHAR(MAX)
)
RETURNS CHAR(1)
AS
BEGIN
  DECLARE @result CHAR(1) = NULL;
  DECLARE @resultIndex INT = NULL;
    SET @resultIndex = [dbo].[fn_RandIntBetween](1, LEN(@chars));
    SET @result = SUBSTRING(@chars, @resultIndex, 1);
  RETURN @result;
END

GO
/****** Object:  UserDefinedFunction [dbo].[fn_RandIntBetween]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--------------LAY 1 SO TRONG KHOANG TU MIN-MAX
	CREATE FUNCTION [dbo].[fn_RandIntBetween]
(
  @lower  INT,
  @upper  INT
)
RETURNS INT
AS
BEGIN
  DECLARE @result INT
  DECLARE @RAND FLOAT = (SELECT * FROM vw_rand)
  DECLARE @range INT = @upper - @lower + 1;
  SET @result = FLOOR(@rand * @range + @lower);
  RETURN @result;
END

GO
/****** Object:  Table [dbo].[DOAN]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DOAN](
	[ID_DOAN] [nvarchar](16) NOT NULL,
	[TEN] [nvarchar](100) NULL,
	[ID_LOAI] [nvarchar](4) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_DOAN] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DOANCT]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DOANCT](
	[ID_DOANCT] [int] IDENTITY(1,1) NOT NULL,
	[ID_KCDA] [int] NULL,
	[ID_DONDA] [nvarchar](20) NULL,
	[SOLUONG] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_DOANCT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DONDOAN]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DONDOAN](
	[ID_DONDA] [nvarchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_DONDA] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DONTHANHTOAN]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DONTHANHTOAN](
	[ID_DONTT] [int] IDENTITY(1,1) NOT NULL,
	[ID_DONDA] [nvarchar](20) NULL,
	[ID_TV] [nvarchar](16) NULL,
	[ID_NV] [nvarchar](10) NULL,
	[ID_DONVE] [nvarchar](20) NULL,
	[NGAYDAT] [datetime] NOT NULL,
	[TONG] [decimal](9, 3) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_DONTT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DONVE]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DONVE](
	[ID_DONVE] [nvarchar](20) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_DONVE] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[GHE]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[GHE](
	[ID_GHE] [nvarchar](12) NOT NULL,
	[VITRIDAY] [tinyint] NOT NULL,
	[VITRICOT] [tinyint] NOT NULL,
	[ID_PHONGCHIEU] [nchar](4) NOT NULL,
	[ID_LOAIGHE] [nchar](2) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_GHE] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KCDAHISTORY]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KCDAHISTORY](
	[id_DOANCT] [int] NOT NULL,
	[id_DOAN] [nvarchar](16) NULL,
	[id_LOAI] [nvarchar](4) NULL,
	[TENLOAI] [nvarchar](100) NULL,
	[TENDA] [nvarchar](100) NULL,
	[id_KICHCO] [nchar](1) NULL,
	[GIA] [decimal](6, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[id_DOANCT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KICHCO]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KICHCO](
	[ID] [nchar](1) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KICHCODOAN]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KICHCODOAN](
	[ID_KCDA] [int] IDENTITY(1,1) NOT NULL,
	[ID_DOAN] [nvarchar](16) NOT NULL,
	[ID_KICHCO] [nchar](1) NOT NULL,
	[GIA] [decimal](6, 2) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_KCDA] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LOAIDOAN]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LOAIDOAN](
	[ID] [nvarchar](4) NOT NULL,
	[TEN] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LOAIGHE]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LOAIGHE](
	[ID_LOAIGHE] [nchar](2) NOT NULL,
	[PHUTHU] [tinyint] NOT NULL,
	[TENLOAI] [nvarchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_LOAIGHE] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LOAIVE]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LOAIVE](
	[ID_LOAIVE] [nchar](2) NOT NULL,
	[TEN] [nvarchar](30) NULL,
	[GIA] [decimal](6, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_LOAIVE] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NHANVIEN]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NHANVIEN](
	[ID_NV] [nvarchar](10) NOT NULL,
	[HOTEN] [nvarchar](50) NULL,
	[NGAYSINH] [date] NULL,
	[GIOITINH] [bit] NULL,
	[SODT] [nvarchar](15) NULL,
	[EMAIL] [nvarchar](50) NOT NULL,
	[TENTK] [nvarchar](30) NOT NULL,
	[MATKHAU] [nvarchar](15) NULL,
	[ANH] [nvarchar](100) NULL,
	[VAITRO] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_NV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PHIM]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PHIM](
	[ID_PHIM] [nvarchar](15) NOT NULL,
	[TEN] [nvarchar](255) NULL,
	[POSTER] [nvarchar](100) NULL,
	[THOILUONG] [smallint] NULL,
	[THELOAI] [nvarchar](50) NULL,
	[NGONNGU] [nvarchar](20) NULL,
	[NGAY_KC] [date] NULL,
	[TOMTAT] [nvarchar](max) NULL,
	[TRANGTHAI] [bit] NULL,
	[ID_NV] [nvarchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_PHIM] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PHONGCHIEU]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PHONGCHIEU](
	[ID_PHONG] [nchar](4) NOT NULL,
	[SOLUONGDAY] [int] NOT NULL,
	[SOLUONGCOT] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_PHONG] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SUATCHIEU]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SUATCHIEU](
	[ID_SUAT] [nvarchar](30) NOT NULL,
	[NGAYCHIEU] [date] NOT NULL,
	[ID_PHONG] [nchar](4) NOT NULL,
	[ID_PHIM] [nvarchar](15) NOT NULL,
	[GIOBATDAU] [time](7) NOT NULL,
	[GIOKETTHUC] [time](7) NOT NULL,
	[ID_NV] [nvarchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_SUAT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[THANHVIEN]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[THANHVIEN](
	[ID_TV] [nvarchar](16) NOT NULL,
	[HOTEN] [nvarchar](50) NULL,
	[NGAYSINH] [date] NULL,
	[GIOITINH] [bit] NULL,
	[SODT] [nvarchar](15) NOT NULL,
	[EMAIL] [nvarchar](50) NULL,
	[MAKH] [nvarchar](12) NULL,
 CONSTRAINT [PK__THANHVIE__8B63B1A4337D0345] PRIMARY KEY CLUSTERED 
(
	[ID_TV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[VEDAT]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[VEDAT](
	[ID_VEDAT] [int] IDENTITY(1,1) NOT NULL,
	[ID_LOAIVE] [nchar](2) NOT NULL,
	[ID_GHE] [nvarchar](12) NOT NULL,
	[ID_DONVE] [nvarchar](20) NOT NULL,
	[ID_SUAT] [nvarchar](30) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID_VEDAT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  View [dbo].[vw_rand]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

----- TAO MAKH CHO THANH VIEN
------------------------THAY THE RAND() TRONG USER-FUNCTION
create view [dbo].[vw_rand] 
as 
	SELECT RAND() AS Value

GO
INSERT [dbo].[DOAN] ([ID_DOAN], [TEN], [ID_LOAI]) VALUES (N'DU00001', N'Nước lọc', N'DU')
INSERT [dbo].[DOAN] ([ID_DOAN], [TEN], [ID_LOAI]) VALUES (N'DU00002', N'Pepsi', N'DU')
INSERT [dbo].[DOAN] ([ID_DOAN], [TEN], [ID_LOAI]) VALUES (N'DU00003', N'Cocacola', N'DU')
INSERT [dbo].[DOAN] ([ID_DOAN], [TEN], [ID_LOAI]) VALUES (N'DU00004', N'Trà sữa', N'DU')
INSERT [dbo].[DOAN] ([ID_DOAN], [TEN], [ID_LOAI]) VALUES (N'DU00005', N'Cà phê', N'DU')
INSERT [dbo].[DOAN] ([ID_DOAN], [TEN], [ID_LOAI]) VALUES (N'TA00001', N'Pizza', N'TA')
INSERT [dbo].[DOAN] ([ID_DOAN], [TEN], [ID_LOAI]) VALUES (N'TA00002', N'Bắp rang bơ', N'TA')
INSERT [dbo].[DOAN] ([ID_DOAN], [TEN], [ID_LOAI]) VALUES (N'TA00003', N'Sushi', N'TA')
INSERT [dbo].[DOAN] ([ID_DOAN], [TEN], [ID_LOAI]) VALUES (N'TA00004', N'Bánh tráng trộn', N'TA')
INSERT [dbo].[DOAN] ([ID_DOAN], [TEN], [ID_LOAI]) VALUES (N'TA00005', N'Bánh ngọt', N'TA')
GO
SET IDENTITY_INSERT [dbo].[DOANCT] ON 

INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (1, 11, N'DA00001', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (2, 24, N'DA00001', 2)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (3, 5, N'DA00002', 2)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (4, 30, N'DA00002', 2)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (5, 8, N'DA00003', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (6, 5, N'DA00004', 3)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (7, 24, N'DA00004', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (8, 13, N'DA00005', 4)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (9, 2, N'DA00005', 4)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (10, 21, N'DA00005', 2)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (11, 11, N'DA00006', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (12, 7, N'DA00007', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (13, 11, N'DA00008', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (14, 17, N'DA00008', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (15, 10, N'DA00009', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (16, 8, N'DA00010', 2)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (17, 18, N'DA00010', 3)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (18, 2, N'DA00011', 5)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (19, 3, N'DA00011', 2)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (20, 5, N'DA00011', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (21, 7, N'DA00011', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (22, 2, N'DA00012', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (23, 1, N'DA00012', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (24, 17, N'DA00012', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (25, 18, N'DA00012', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (26, 29, N'DA00012', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (27, 2, N'DA00013', 5)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (28, 29, N'DA00014', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (29, 30, N'DA00014', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (30, 28, N'DA00014', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (31, 2, N'DA00015', 3)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (32, 2, N'DA00016', 19)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (33, 2, N'DA00017', 9)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (34, 1, N'DA00018', 4)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (35, 17, N'DA00018', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (36, 28, N'DA00018', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (37, 2, N'DA00019', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (38, 2, N'DA00020', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (39, 3, N'DA00020', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (40, 3, N'DA00021', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (41, 1, N'DA00021', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (42, 2, N'DA00021', 4)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (43, 17, N'DA00021', 2)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (44, 17, N'DA00022', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (45, 2, N'DA00023', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (46, 1, N'DA00023', 3)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (47, 2, N'DA00024', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (48, 1, N'DA00024', 4)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (49, 17, N'DA00025', 4)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (50, 1, N'DA00026', 5)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (51, 17, N'DA00026', 4)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (52, 16, N'DA00027', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (53, 26, N'DA00027', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (54, 27, N'DA00027', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (55, 2, N'DA00028', 4)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (56, 1, N'DA00028', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (57, 2, N'DA00029', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (58, 1, N'DA00029', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (59, 3, N'DA00029', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (60, 2, N'DA00030', 4)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (61, 1, N'DA00030', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (62, 2, N'DA00031', 4)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (63, 2, N'DA00032', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (64, 3, N'DA00032', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (65, 2, N'DA00033', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (66, 1, N'DA00033', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (67, 3, N'DA00033', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (68, 5, N'DA00033', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (70, 17, N'DA00035', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (71, 18, N'DA00035', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (72, 30, N'DA00035', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (73, 29, N'DA00035', 13)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (74, 2, N'DA00036', 6)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (75, 2, N'DA00037', 5)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (76, 2, N'DA00038', 1)
INSERT [dbo].[DOANCT] ([ID_DOANCT], [ID_KCDA], [ID_DONDA], [SOLUONG]) VALUES (77, 3, N'DA00038', 1)
SET IDENTITY_INSERT [dbo].[DOANCT] OFF
GO
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00001')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00002')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00003')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00004')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00005')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00006')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00007')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00008')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00009')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00010')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00011')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00012')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00013')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00014')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00015')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00016')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00017')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00018')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00019')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00020')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00021')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00022')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00023')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00024')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00025')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00026')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00027')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00028')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00029')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00030')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00031')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00032')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00033')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00035')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00036')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00037')
INSERT [dbo].[DONDOAN] ([ID_DONDA]) VALUES (N'DA00038')
GO
SET IDENTITY_INSERT [dbo].[DONTHANHTOAN] ON 

INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (1, N'DA00001', N'TV00001', N'NV00001', N'DV00001', CAST(N'2021-03-16T10:00:00.000' AS DateTime), CAST(650.000 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (2, N'DA00002', N'TV00002', N'NV00001', N'DV00002', CAST(N'2021-03-16T11:00:00.000' AS DateTime), CAST(280.000 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (3, N'DA00003', N'TV00003', N'NV00003', N'DV00003', CAST(N'2021-03-17T16:30:00.000' AS DateTime), CAST(350.000 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (4, N'DA00004', N'TV00005', N'NV00005', N'DV00004', CAST(N'2021-03-17T12:00:00.000' AS DateTime), CAST(500.000 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (5, N'DA00005', N'TV00008', N'NV00002', N'DV00005', CAST(N'2021-03-17T08:30:00.000' AS DateTime), CAST(520.000 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (6, N'DA00006', N'TV00011', N'NV00001', N'DV00006', CAST(N'2021-03-18T07:00:00.000' AS DateTime), CAST(360.000 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (7, N'DA00007', N'TV00021', N'NV00012', N'DV00007', CAST(N'2021-03-18T14:30:00.000' AS DateTime), CAST(525.000 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (8, N'DA00008', N'TV00015', N'NV00012', N'DV00008', CAST(N'2021-03-19T10:00:00.000' AS DateTime), CAST(620.000 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (9, N'DA00009', N'TV00022', N'NV00011', N'DV00009', CAST(N'2021-03-19T11:00:00.000' AS DateTime), CAST(130.000 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (10, N'DA00010', N'TV00019', N'NV00015', N'DV00010', CAST(N'2021-03-19T17:00:00.000' AS DateTime), CAST(760.000 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (11, NULL, NULL, N'QL00006', N'DV00013', CAST(N'2022-04-16T21:47:04.967' AS DateTime), CAST(76.000 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (12, N'DA00011', NULL, N'QL00006', N'DV00014', CAST(N'2022-04-17T22:02:49.190' AS DateTime), CAST(503.500 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (13, NULL, NULL, N'QL00006', N'DV00015', CAST(N'2022-04-17T23:48:19.440' AS DateTime), CAST(504.000 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (14, N'DA00012', NULL, N'QL00006', N'DV00016', CAST(N'2022-04-17T23:53:22.093' AS DateTime), CAST(760.500 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (15, N'DA00013', NULL, N'QL00006', N'DV00017', CAST(N'2022-04-18T00:14:15.307' AS DateTime), CAST(337.500 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (16, N'DA00014', NULL, N'QL00006', NULL, CAST(N'2022-04-18T00:16:58.597' AS DateTime), CAST(142.500 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (17, NULL, NULL, N'QL00006', N'DV00019', CAST(N'2022-04-18T01:01:48.250' AS DateTime), CAST(544.500 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (19, NULL, N'TV00007', N'QL00006', N'DV00020', CAST(N'2022-04-18T07:41:51.637' AS DateTime), CAST(252.000 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (20, NULL, N'TV00001', N'QL00006', N'DV00021', CAST(N'2022-04-19T23:41:15.277' AS DateTime), CAST(108.000 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (21, N'DA00016', NULL, N'QL00006', NULL, CAST(N'2022-04-19T23:59:46.853' AS DateTime), CAST(285.000 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (22, N'DA00017', NULL, N'QL00006', N'DV00022', CAST(N'2022-04-20T00:00:23.880' AS DateTime), CAST(193.500 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (23, N'DA00018', NULL, N'QL00006', N'DV00023', CAST(N'2022-04-20T01:04:18.903' AS DateTime), CAST(549.000 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (24, NULL, N'TV00012', N'QL00006', N'DV00024', CAST(N'2022-04-20T02:07:25.823' AS DateTime), CAST(180.000 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (25, N'DA00019', NULL, N'QL00006', N'DV00025', CAST(N'2022-04-20T15:33:25.870' AS DateTime), CAST(391.500 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (26, NULL, NULL, N'QL00006', N'DV00026', CAST(N'2022-04-20T15:33:59.717' AS DateTime), CAST(360.000 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (27, NULL, N'TV00007', N'QL00006', N'DV00027', CAST(N'2022-04-20T15:34:49.943' AS DateTime), CAST(288.000 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (28, N'DA00020', N'TV00014', N'QL00006', N'DV00028', CAST(N'2022-04-20T15:35:40.423' AS DateTime), CAST(499.500 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (29, N'DA00021', N'TV00006', N'QL00006', NULL, CAST(N'2022-04-20T15:36:36.557' AS DateTime), CAST(370.500 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (30, N'DA00022', NULL, N'QL00006', N'DV00029', CAST(N'2022-04-20T15:37:17.143' AS DateTime), CAST(891.000 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (31, N'DA00023', N'TV00010', N'QL00006', N'DV00030', CAST(N'2022-04-20T22:44:00.977' AS DateTime), CAST(346.500 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (32, NULL, NULL, N'QL00006', N'DV00031', CAST(N'2022-04-21T16:47:54.913' AS DateTime), CAST(432.000 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (33, N'DA00024', NULL, N'QL00006', N'DV00032', CAST(N'2022-04-21T16:48:28.320' AS DateTime), CAST(283.500 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (34, NULL, NULL, N'QL00006', N'DV00033', CAST(N'2022-04-21T16:49:16.917' AS DateTime), CAST(414.000 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (35, NULL, NULL, N'QL00006', N'DV00034', CAST(N'2022-04-21T16:58:45.030' AS DateTime), CAST(342.000 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (36, NULL, NULL, N'QL00006', N'DV00035', CAST(N'2022-04-21T16:59:12.930' AS DateTime), CAST(72.000 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (37, N'DA00025', NULL, N'QL00006', NULL, CAST(N'2022-04-21T17:08:23.010' AS DateTime), CAST(600.000 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (38, N'DA00026', NULL, N'QL00006', N'DV00036', CAST(N'2022-04-21T17:12:14.430' AS DateTime), CAST(549.000 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (39, N'DA00027', NULL, N'QL00006', NULL, CAST(N'2022-04-21T17:12:41.367' AS DateTime), CAST(147.250 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (40, N'DA00028', NULL, N'QL00006', NULL, CAST(N'2022-04-21T17:41:51.977' AS DateTime), CAST(66.500 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (41, N'DA00029', NULL, N'QL00006', N'DV00037', CAST(N'2022-04-21T17:43:26.607' AS DateTime), CAST(232.750 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (42, N'DA00030', NULL, N'QL00006', N'DV00038', CAST(N'2022-04-21T17:56:54.747' AS DateTime), CAST(513.000 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (43, NULL, N'TV00011', N'QL00006', N'DV00039', CAST(N'2022-04-21T18:05:36.903' AS DateTime), CAST(418.000 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (44, N'DA00031', NULL, N'QL00006', N'DV00040', CAST(N'2022-04-21T18:29:19.607' AS DateTime), CAST(513.000 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (45, N'DA00032', N'TV00026', N'QL00006', N'DV00041', CAST(N'2022-04-21T18:30:17.537' AS DateTime), CAST(229.500 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (46, N'DA00033', NULL, N'QL00006', N'DV00042', CAST(N'2022-04-21T19:50:09.967' AS DateTime), CAST(400.500 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (48, N'DA00035', NULL, N'QL00006', NULL, CAST(N'2022-04-21T20:03:03.880' AS DateTime), CAST(1007.000 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (49, N'DA00036', NULL, N'QL00006', N'DV00043', CAST(N'2022-04-21T20:17:40.587' AS DateTime), CAST(729.000 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (50, N'DA00037', N'TV00009', N'QL00006', N'DV00044', CAST(N'2022-04-21T20:36:37.723' AS DateTime), CAST(622.250 AS Decimal(9, 3)))
INSERT [dbo].[DONTHANHTOAN] ([ID_DONTT], [ID_DONDA], [ID_TV], [ID_NV], [ID_DONVE], [NGAYDAT], [TONG]) VALUES (51, N'DA00038', N'TV00021', N'QL00006', N'DV00045', CAST(N'2022-04-21T20:42:35.487' AS DateTime), CAST(385.000 AS Decimal(9, 3)))
SET IDENTITY_INSERT [dbo].[DONTHANHTOAN] OFF
GO
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00001')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00002')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00003')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00004')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00005')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00006')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00007')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00008')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00009')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00010')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00012')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00013')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00014')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00015')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00016')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00017')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00018')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00019')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00020')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00021')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00022')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00023')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00024')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00025')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00026')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00027')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00028')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00029')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00030')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00031')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00032')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00033')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00034')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00035')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00036')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00037')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00038')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00039')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00040')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00041')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00042')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00043')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00044')
INSERT [dbo].[DONVE] ([ID_DONVE]) VALUES (N'DV00045')
GO
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01A01', 1, 1, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01A02', 1, 2, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01A03', 1, 3, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01A04', 1, 4, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01A05', 1, 5, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01A06', 1, 6, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01A07', 1, 7, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01A08', 1, 8, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01A09', 1, 9, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01A10', 1, 10, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01A11', 1, 11, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01A12', 1, 12, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01B01', 2, 1, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01B02', 2, 2, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01B03', 2, 3, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01B04', 2, 4, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01B05', 2, 5, N'P001', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01B06', 2, 6, N'P001', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01B07', 2, 7, N'P001', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01B08', 2, 8, N'P001', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01B09', 2, 9, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01B10', 2, 10, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01B11', 2, 11, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01B12', 2, 12, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01C01', 3, 1, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01C02', 3, 2, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01C03', 3, 3, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01C04', 3, 4, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01C05', 3, 5, N'P001', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01C06', 3, 6, N'P001', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01C07', 3, 7, N'P001', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01C08', 3, 8, N'P001', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01C09', 3, 9, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01C10', 3, 10, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01C11', 3, 11, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01C12', 3, 12, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01D01', 4, 1, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01D02', 4, 2, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01D03', 4, 3, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01D04', 4, 4, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01D05', 4, 5, N'P001', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01D06', 4, 6, N'P001', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01D07', 4, 7, N'P001', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01D08', 4, 8, N'P001', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01D09', 4, 9, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01D10', 4, 10, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01D11', 4, 11, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01D12', 4, 12, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01E01', 5, 1, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01E02', 5, 2, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01E03', 5, 3, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01E04', 5, 4, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01E05', 5, 5, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01E06', 5, 6, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01E07', 5, 7, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01E08', 5, 8, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01E09', 5, 9, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01E10', 5, 10, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01E11', 5, 11, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS01E12', 5, 12, N'P001', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02A01', 1, 1, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02A02', 1, 2, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02A03', 1, 3, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02A04', 1, 4, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02A05', 1, 5, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02A06', 1, 6, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02A07', 1, 7, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02A08', 1, 8, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02A09', 1, 9, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02A10', 1, 10, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02A11', 1, 11, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02A12', 1, 12, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02B01', 2, 1, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02B02', 2, 2, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02B03', 2, 3, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02B04', 2, 4, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02B05', 2, 5, N'P002', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02B06', 2, 6, N'P002', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02B07', 2, 7, N'P002', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02B08', 2, 8, N'P002', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02B09', 2, 9, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02B10', 2, 10, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02B11', 2, 11, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02B12', 2, 12, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02C01', 3, 1, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02C02', 3, 2, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02C03', 3, 3, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02C04', 3, 4, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02C05', 3, 5, N'P002', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02C06', 3, 6, N'P002', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02C07', 3, 7, N'P002', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02C08', 3, 8, N'P002', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02C09', 3, 9, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02C10', 3, 10, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02C11', 3, 11, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02C12', 3, 12, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02D01', 4, 1, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02D02', 4, 2, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02D03', 4, 3, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02D04', 4, 4, N'P002', N'TH')
GO
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02D05', 4, 5, N'P002', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02D06', 4, 6, N'P002', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02D07', 4, 7, N'P002', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02D08', 4, 8, N'P002', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02D09', 4, 9, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02D10', 4, 10, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02D11', 4, 11, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02D12', 4, 12, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02E01', 5, 1, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02E02', 5, 2, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02E03', 5, 3, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02E04', 5, 4, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02E05', 5, 5, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02E06', 5, 6, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02E07', 5, 7, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02E08', 5, 8, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02E09', 5, 9, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02E10', 5, 10, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02E11', 5, 11, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS02E12', 5, 12, N'P002', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03A01', 1, 1, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03A02', 1, 2, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03A03', 1, 3, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03A04', 1, 4, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03A05', 1, 5, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03A06', 1, 6, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03A07', 1, 7, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03A08', 1, 8, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03A09', 1, 9, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03A10', 1, 10, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03A11', 1, 11, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03A12', 1, 12, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03B01', 2, 1, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03B02', 2, 2, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03B03', 2, 3, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03B04', 2, 4, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03B05', 2, 5, N'P003', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03B06', 2, 6, N'P003', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03B07', 2, 7, N'P003', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03B08', 2, 8, N'P003', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03B09', 2, 9, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03B10', 2, 10, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03B11', 2, 11, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03B12', 2, 12, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03C01', 3, 1, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03C02', 3, 2, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03C03', 3, 3, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03C04', 3, 4, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03C05', 3, 5, N'P003', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03C06', 3, 6, N'P003', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03C07', 3, 7, N'P003', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03C08', 3, 8, N'P003', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03C09', 3, 9, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03C10', 3, 10, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03C11', 3, 11, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03C12', 3, 12, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03D01', 4, 1, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03D02', 4, 2, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03D03', 4, 3, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03D04', 4, 4, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03D05', 4, 5, N'P003', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03D06', 4, 6, N'P003', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03D07', 4, 7, N'P003', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03D08', 4, 8, N'P003', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03D09', 4, 9, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03D10', 4, 10, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03D11', 4, 11, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03D12', 4, 12, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03E01', 5, 1, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03E02', 5, 2, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03E03', 5, 3, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03E04', 5, 4, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03E05', 5, 5, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03E06', 5, 6, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03E07', 5, 7, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03E08', 5, 8, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03E09', 5, 9, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03E10', 5, 10, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03E11', 5, 11, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS03E12', 5, 12, N'P003', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04A01', 1, 1, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04A02', 1, 2, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04A03', 1, 3, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04A04', 1, 4, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04A05', 1, 5, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04A06', 1, 6, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04A07', 1, 7, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04A08', 1, 8, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04A09', 1, 9, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04A10', 1, 10, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04A11', 1, 11, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04A12', 1, 12, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04B01', 2, 1, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04B02', 2, 2, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04B03', 2, 3, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04B04', 2, 4, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04B05', 2, 5, N'P004', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04B06', 2, 6, N'P004', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04B07', 2, 7, N'P004', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04B08', 2, 8, N'P004', N'VP')
GO
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04B09', 2, 9, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04B10', 2, 10, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04B11', 2, 11, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04B12', 2, 12, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04C01', 3, 1, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04C02', 3, 2, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04C03', 3, 3, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04C04', 3, 4, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04C05', 3, 5, N'P004', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04C06', 3, 6, N'P004', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04C07', 3, 7, N'P004', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04C08', 3, 8, N'P004', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04C09', 3, 9, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04C10', 3, 10, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04C11', 3, 11, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04C12', 3, 12, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04D01', 4, 1, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04D02', 4, 2, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04D03', 4, 3, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04D04', 4, 4, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04D05', 4, 5, N'P004', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04D06', 4, 6, N'P004', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04D07', 4, 7, N'P004', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04D08', 4, 8, N'P004', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04D09', 4, 9, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04D10', 4, 10, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04D11', 4, 11, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04D12', 4, 12, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04E01', 5, 1, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04E02', 5, 2, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04E03', 5, 3, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04E04', 5, 4, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04E05', 5, 5, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04E06', 5, 6, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04E07', 5, 7, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04E08', 5, 8, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04E09', 5, 9, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04E10', 5, 10, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04E11', 5, 11, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS04E12', 5, 12, N'P004', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05A01', 1, 1, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05A02', 1, 2, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05A03', 1, 3, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05A04', 1, 4, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05A05', 1, 5, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05A06', 1, 6, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05A07', 1, 7, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05A08', 1, 8, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05A09', 1, 9, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05A10', 1, 10, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05A11', 1, 11, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05A12', 1, 12, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05B01', 2, 1, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05B02', 2, 2, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05B03', 2, 3, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05B04', 2, 4, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05B05', 2, 5, N'P005', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05B06', 2, 6, N'P005', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05B07', 2, 7, N'P005', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05B08', 2, 8, N'P005', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05B09', 2, 9, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05B10', 2, 10, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05B11', 2, 11, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05B12', 2, 12, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05C01', 3, 1, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05C02', 3, 2, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05C03', 3, 3, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05C04', 3, 4, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05C05', 3, 5, N'P005', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05C06', 3, 6, N'P005', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05C07', 3, 7, N'P005', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05C08', 3, 8, N'P005', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05C09', 3, 9, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05C10', 3, 10, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05C11', 3, 11, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05C12', 3, 12, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05D01', 4, 1, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05D02', 4, 2, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05D03', 4, 3, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05D04', 4, 4, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05D05', 4, 5, N'P005', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05D06', 4, 6, N'P005', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05D07', 4, 7, N'P005', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05D08', 4, 8, N'P005', N'VP')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05D09', 4, 9, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05D10', 4, 10, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05D11', 4, 11, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05D12', 4, 12, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05E01', 5, 1, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05E02', 5, 2, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05E03', 5, 3, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05E04', 5, 4, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05E05', 5, 5, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05E06', 5, 6, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05E07', 5, 7, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05E08', 5, 8, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05E09', 5, 9, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05E10', 5, 10, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05E11', 5, 11, N'P005', N'TH')
INSERT [dbo].[GHE] ([ID_GHE], [VITRIDAY], [VITRICOT], [ID_PHONGCHIEU], [ID_LOAIGHE]) VALUES (N'GS05E12', 5, 12, N'P005', N'TH')
GO
INSERT [dbo].[KICHCO] ([ID]) VALUES (N'L')
INSERT [dbo].[KICHCO] ([ID]) VALUES (N'M')
INSERT [dbo].[KICHCO] ([ID]) VALUES (N'S')
GO
SET IDENTITY_INSERT [dbo].[KICHCODOAN] ON 

INSERT [dbo].[KICHCODOAN] ([ID_KCDA], [ID_DOAN], [ID_KICHCO], [GIA]) VALUES (1, N'DU00001', N'S', CAST(10.00 AS Decimal(6, 2)))
INSERT [dbo].[KICHCODOAN] ([ID_KCDA], [ID_DOAN], [ID_KICHCO], [GIA]) VALUES (2, N'DU00001', N'M', CAST(15.00 AS Decimal(6, 2)))
INSERT [dbo].[KICHCODOAN] ([ID_KCDA], [ID_DOAN], [ID_KICHCO], [GIA]) VALUES (3, N'DU00001', N'L', CAST(20.00 AS Decimal(6, 2)))
INSERT [dbo].[KICHCODOAN] ([ID_KCDA], [ID_DOAN], [ID_KICHCO], [GIA]) VALUES (4, N'DU00002', N'S', CAST(15.00 AS Decimal(6, 2)))
INSERT [dbo].[KICHCODOAN] ([ID_KCDA], [ID_DOAN], [ID_KICHCO], [GIA]) VALUES (5, N'DU00002', N'M', CAST(20.00 AS Decimal(6, 2)))
INSERT [dbo].[KICHCODOAN] ([ID_KCDA], [ID_DOAN], [ID_KICHCO], [GIA]) VALUES (6, N'DU00002', N'L', CAST(25.00 AS Decimal(6, 2)))
INSERT [dbo].[KICHCODOAN] ([ID_KCDA], [ID_DOAN], [ID_KICHCO], [GIA]) VALUES (7, N'DU00003', N'S', CAST(15.00 AS Decimal(6, 2)))
INSERT [dbo].[KICHCODOAN] ([ID_KCDA], [ID_DOAN], [ID_KICHCO], [GIA]) VALUES (8, N'DU00003', N'M', CAST(20.00 AS Decimal(6, 2)))
INSERT [dbo].[KICHCODOAN] ([ID_KCDA], [ID_DOAN], [ID_KICHCO], [GIA]) VALUES (9, N'DU00003', N'L', CAST(25.00 AS Decimal(6, 2)))
INSERT [dbo].[KICHCODOAN] ([ID_KCDA], [ID_DOAN], [ID_KICHCO], [GIA]) VALUES (10, N'DU00004', N'S', CAST(20.00 AS Decimal(6, 2)))
INSERT [dbo].[KICHCODOAN] ([ID_KCDA], [ID_DOAN], [ID_KICHCO], [GIA]) VALUES (11, N'DU00004', N'M', CAST(30.00 AS Decimal(6, 2)))
INSERT [dbo].[KICHCODOAN] ([ID_KCDA], [ID_DOAN], [ID_KICHCO], [GIA]) VALUES (12, N'DU00004', N'L', CAST(40.00 AS Decimal(6, 2)))
INSERT [dbo].[KICHCODOAN] ([ID_KCDA], [ID_DOAN], [ID_KICHCO], [GIA]) VALUES (13, N'DU00005', N'S', CAST(30.00 AS Decimal(6, 2)))
INSERT [dbo].[KICHCODOAN] ([ID_KCDA], [ID_DOAN], [ID_KICHCO], [GIA]) VALUES (14, N'DU00005', N'M', CAST(35.00 AS Decimal(6, 2)))
INSERT [dbo].[KICHCODOAN] ([ID_KCDA], [ID_DOAN], [ID_KICHCO], [GIA]) VALUES (15, N'DU00005', N'L', CAST(40.00 AS Decimal(6, 2)))
INSERT [dbo].[KICHCODOAN] ([ID_KCDA], [ID_DOAN], [ID_KICHCO], [GIA]) VALUES (16, N'TA00001', N'S', CAST(100.00 AS Decimal(6, 2)))
INSERT [dbo].[KICHCODOAN] ([ID_KCDA], [ID_DOAN], [ID_KICHCO], [GIA]) VALUES (17, N'TA00001', N'M', CAST(150.00 AS Decimal(6, 2)))
INSERT [dbo].[KICHCODOAN] ([ID_KCDA], [ID_DOAN], [ID_KICHCO], [GIA]) VALUES (18, N'TA00001', N'L', CAST(200.00 AS Decimal(6, 2)))
INSERT [dbo].[KICHCODOAN] ([ID_KCDA], [ID_DOAN], [ID_KICHCO], [GIA]) VALUES (19, N'TA00002', N'S', CAST(20.00 AS Decimal(6, 2)))
INSERT [dbo].[KICHCODOAN] ([ID_KCDA], [ID_DOAN], [ID_KICHCO], [GIA]) VALUES (20, N'TA00002', N'M', CAST(35.00 AS Decimal(6, 2)))
INSERT [dbo].[KICHCODOAN] ([ID_KCDA], [ID_DOAN], [ID_KICHCO], [GIA]) VALUES (21, N'TA00002', N'L', CAST(50.00 AS Decimal(6, 2)))
INSERT [dbo].[KICHCODOAN] ([ID_KCDA], [ID_DOAN], [ID_KICHCO], [GIA]) VALUES (22, N'TA00003', N'S', CAST(120.00 AS Decimal(6, 2)))
INSERT [dbo].[KICHCODOAN] ([ID_KCDA], [ID_DOAN], [ID_KICHCO], [GIA]) VALUES (23, N'TA00003', N'M', CAST(150.00 AS Decimal(6, 2)))
INSERT [dbo].[KICHCODOAN] ([ID_KCDA], [ID_DOAN], [ID_KICHCO], [GIA]) VALUES (24, N'TA00003', N'L', CAST(200.00 AS Decimal(6, 2)))
INSERT [dbo].[KICHCODOAN] ([ID_KCDA], [ID_DOAN], [ID_KICHCO], [GIA]) VALUES (25, N'TA00004', N'S', CAST(20.00 AS Decimal(6, 2)))
INSERT [dbo].[KICHCODOAN] ([ID_KCDA], [ID_DOAN], [ID_KICHCO], [GIA]) VALUES (26, N'TA00004', N'M', CAST(25.00 AS Decimal(6, 2)))
INSERT [dbo].[KICHCODOAN] ([ID_KCDA], [ID_DOAN], [ID_KICHCO], [GIA]) VALUES (27, N'TA00004', N'L', CAST(30.00 AS Decimal(6, 2)))
INSERT [dbo].[KICHCODOAN] ([ID_KCDA], [ID_DOAN], [ID_KICHCO], [GIA]) VALUES (28, N'TA00005', N'S', CAST(40.00 AS Decimal(6, 2)))
INSERT [dbo].[KICHCODOAN] ([ID_KCDA], [ID_DOAN], [ID_KICHCO], [GIA]) VALUES (29, N'TA00005', N'M', CAST(50.00 AS Decimal(6, 2)))
INSERT [dbo].[KICHCODOAN] ([ID_KCDA], [ID_DOAN], [ID_KICHCO], [GIA]) VALUES (30, N'TA00005', N'L', CAST(60.00 AS Decimal(6, 2)))
SET IDENTITY_INSERT [dbo].[KICHCODOAN] OFF
GO
INSERT [dbo].[LOAIDOAN] ([ID], [TEN]) VALUES (N'DU', N'Đồ uống')
INSERT [dbo].[LOAIDOAN] ([ID], [TEN]) VALUES (N'TA', N'Thức ăn')
GO
INSERT [dbo].[LOAIGHE] ([ID_LOAIGHE], [PHUTHU], [TENLOAI]) VALUES (N'TH', 30, N'Ghế thường')
INSERT [dbo].[LOAIGHE] ([ID_LOAIGHE], [PHUTHU], [TENLOAI]) VALUES (N'VP', 40, N'Ghế vip')
GO
INSERT [dbo].[LOAIVE] ([ID_LOAIVE], [TEN], [GIA]) VALUES (N'NL', N'Vé người lớn', CAST(80.00 AS Decimal(6, 2)))
INSERT [dbo].[LOAIVE] ([ID_LOAIVE], [TEN], [GIA]) VALUES (N'TE', N'Vé trẻ em', CAST(60.00 AS Decimal(6, 2)))
GO
INSERT [dbo].[NHANVIEN] ([ID_NV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [TENTK], [MATKHAU], [ANH], [VAITRO]) VALUES (N'NV00001', N'Tống Thanh Hàn', CAST(N'1998-10-07' AS Date), 0, N'0883882943', N'thanhhan@gmail.com', N'tonghan98', N'Thanhan123', N'user12.png', 0)
INSERT [dbo].[NHANVIEN] ([ID_NV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [TENTK], [MATKHAU], [ANH], [VAITRO]) VALUES (N'NV00002', N'Nguyễn Tường Vy', CAST(N'1994-01-30' AS Date), 1, N'0988392403', N'tuongvynguyen@gmail.com', N'vynguyen97', N'Tuongvy123', N'user2.png', 0)
INSERT [dbo].[NHANVIEN] ([ID_NV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [TENTK], [MATKHAU], [ANH], [VAITRO]) VALUES (N'NV00003', N'Nguyễn Ánh', CAST(N'1998-09-28' AS Date), 1, N'0964011088', N'anhnguyen@gmail.com', N'nguyenanh9x', N'ngAnh123', N'user12.png', 0)
INSERT [dbo].[NHANVIEN] ([ID_NV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [TENTK], [MATKHAU], [ANH], [VAITRO]) VALUES (N'NV00004', N'Phùng Thái Linh', CAST(N'1998-07-09' AS Date), 1, N'0364444043', N'phunglinh@gmail.com', N'thailinh98', N'Linh98', N'user1.png', 0)
INSERT [dbo].[NHANVIEN] ([ID_NV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [TENTK], [MATKHAU], [ANH], [VAITRO]) VALUES (N'NV00005', N'Hoàng Xuân Vinh', CAST(N'1990-05-15' AS Date), 0, N'093229239', N'hoangxuan@gmail.com', N'hoangvinh9x', N'vinhH1505', N'user1.png', 0)
INSERT [dbo].[NHANVIEN] ([ID_NV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [TENTK], [MATKHAU], [ANH], [VAITRO]) VALUES (N'NV00006', N'Nguyễn Lê Phương', CAST(N'1999-01-04' AS Date), 1, N'0963324252', N'phuongnguyen@gmail.com', N'phuong99', N'Phuong0401', N'user1.png', 0)
INSERT [dbo].[NHANVIEN] ([ID_NV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [TENTK], [MATKHAU], [ANH], [VAITRO]) VALUES (N'NV00007', N'Mai Nam Hải', CAST(N'1989-10-15' AS Date), 0, N'0917229979', N'maihai@gmail.com', N'namhai89', N'Hai1089', N'user1.png', 0)
INSERT [dbo].[NHANVIEN] ([ID_NV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [TENTK], [MATKHAU], [ANH], [VAITRO]) VALUES (N'NV00008', N'Trần Trà My', CAST(N'1996-12-07' AS Date), 1, N'093942561', N'mytra@gmail.com', N'tramy96', N'Tramy1996', N'user1.png', 0)
INSERT [dbo].[NHANVIEN] ([ID_NV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [TENTK], [MATKHAU], [ANH], [VAITRO]) VALUES (N'NV00009', N'Đoàn Nguyên Hải', CAST(N'1993-01-12' AS Date), 0, N'0394040503', N'haidoan@gmail.com', N'haidoan9x', N'Hai0193', N'user1.png', 0)
INSERT [dbo].[NHANVIEN] ([ID_NV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [TENTK], [MATKHAU], [ANH], [VAITRO]) VALUES (N'NV00010', N'Phùng Thanh Độ', CAST(N'1995-07-30' AS Date), 0, N'0364461650', N'dophung@gmail.com', N'thanhdo95', N'Thanh9507', N'user1.png', 0)
INSERT [dbo].[NHANVIEN] ([ID_NV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [TENTK], [MATKHAU], [ANH], [VAITRO]) VALUES (N'NV00011', N'Mai Ánh Thi', CAST(N'1993-03-03' AS Date), 1, N'0708178910', N'thimai@gmail.com', N'anhthi9x', N'Anhthi03', N'user1.png', 0)
INSERT [dbo].[NHANVIEN] ([ID_NV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [TENTK], [MATKHAU], [ANH], [VAITRO]) VALUES (N'NV00012', N'Nguyễn Ngọc Trang', CAST(N'1993-03-06' AS Date), 1, N'0977783120', N'ngoctrang@gmail.com', N'nguyentrang93', N'Trang0306', N'user1.png', 0)
INSERT [dbo].[NHANVIEN] ([ID_NV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [TENTK], [MATKHAU], [ANH], [VAITRO]) VALUES (N'NV00013', N'Nguyễn Ngọc Linh', CAST(N'1993-03-09' AS Date), 1, N'0977123450', N'ngoclinh@gmail.com', N'ngoclinh', N'Linh9x03', N'user1.png', 0)
INSERT [dbo].[NHANVIEN] ([ID_NV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [TENTK], [MATKHAU], [ANH], [VAITRO]) VALUES (N'NV00014', N'Lê Kim Tuấn', CAST(N'1999-03-01' AS Date), 0, N'0976009889', N'kimtuan@gmail.com', N'tuanle05', N'Tuanle9702', N'user1.png', 0)
INSERT [dbo].[NHANVIEN] ([ID_NV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [TENTK], [MATKHAU], [ANH], [VAITRO]) VALUES (N'NV00015', N'Hoàng Thuỳ Linh', CAST(N'1999-03-01' AS Date), 1, N'0974678320', N'thuylinh@gmail.com', N'hoangthuy999', N'Linh1999', N'user1.png', 0)
INSERT [dbo].[NHANVIEN] ([ID_NV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [TENTK], [MATKHAU], [ANH], [VAITRO]) VALUES (N'QL00001', N'Phạm Hoàng Long', CAST(N'1998-10-18' AS Date), 0, N'0985645231', N'hoanglong@gmail.com', N'hoanglong98', N'Longh9x', N'user1.png', 1)
INSERT [dbo].[NHANVIEN] ([ID_NV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [TENTK], [MATKHAU], [ANH], [VAITRO]) VALUES (N'QL00002', N'Nguyễn Thị Nga', CAST(N'1997-07-28' AS Date), 1, N'0977485234', N'nganguyen@gmail.com', N'nganguyen97', N'Nga0797', N'user1.png', 1)
INSERT [dbo].[NHANVIEN] ([ID_NV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [TENTK], [MATKHAU], [ANH], [VAITRO]) VALUES (N'QL00003', N'Nguyễn Thanh Loan', CAST(N'1996-05-15' AS Date), 1, N'0995343221', N'loannguyen@gmail.com', N'thanhloan96', N'Loan1505', N'user1.png', 1)
INSERT [dbo].[NHANVIEN] ([ID_NV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [TENTK], [MATKHAU], [ANH], [VAITRO]) VALUES (N'QL00004', N'Trương Thế Thành', CAST(N'1997-02-05' AS Date), 0, N'0755315241', N'thanhtruong@gmail.com', N'thethanh97', N'Thanhtr97', N'user1.png', 1)
INSERT [dbo].[NHANVIEN] ([ID_NV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [TENTK], [MATKHAU], [ANH], [VAITRO]) VALUES (N'QL00005', N'Trần Hoàng Kha', CAST(N'1989-05-25' AS Date), 0, N'0884245251', N'khahoang@gmail.com', N'hoangkha8x', N'Khatr25', N'user1.png', 1)
INSERT [dbo].[NHANVIEN] ([ID_NV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [TENTK], [MATKHAU], [ANH], [VAITRO]) VALUES (N'QL00006', N'Trần Vân', CAST(N'1998-02-23' AS Date), 1, N'0329436123', N'vantht@gmail.com', N'vantht', N'Ps12345', N'user7.png', 1)
GO
INSERT [dbo].[PHIM] ([ID_PHIM], [TEN], [POSTER], [THOILUONG], [THELOAI], [NGONNGU], [NGAY_KC], [TOMTAT], [TRANGTHAI], [ID_NV]) VALUES (N'FI00001', N'Bóng đè', N'bongde_poster.jpg', 101, N'Kinh dị', N'Tiếng Việt', CAST(N'2022-03-18' AS Date), N'Bóng đè là trải nghiệm xảy ra khi tâm trí đã thức giấc nhưng cơ thể vẫn còn trong giấc ngủ. Đa số trường hợp không thể phân biệt được giữa thực và mơ.', 1, N'NV00001')
INSERT [dbo].[PHIM] ([ID_PHIM], [TEN], [POSTER], [THOILUONG], [THELOAI], [NGONNGU], [NGAY_KC], [TOMTAT], [TRANGTHAI], [ID_NV]) VALUES (N'FI00002', N'BATMAN', N'bat_man_poster.jpg', 183, N'Hành động', N'Tiếng Anh', CAST(N'2022-03-04' AS Date), N'Bộ phim đưa khán giả dõi theo hành trình phá án và diệt trừ tội phạm của chàng Hiệp sĩ Bóng đêm Batman, với một câu chuyện hoàn toàn khác biệt với những phần phim đã ra mắt trước đây.', 1, N'QL00006')
INSERT [dbo].[PHIM] ([ID_PHIM], [TEN], [POSTER], [THOILUONG], [THELOAI], [NGONNGU], [NGAY_KC], [TOMTAT], [TRANGTHAI], [ID_NV]) VALUES (N'FI00003', N'TURNING RED: Gấu đỏ biến hình', N'turning_red_poster.jpg', 99, N'Hoạt hình', N'Tiếng Anh', CAST(N'2022-03-13' AS Date), N'Turning Red - Gấu Đỏ Biến Hình từ Disney và Pixar kể về Mei Lee, cô bé 13 tuổi tự tin và ngổ ngáo với những sự hỗn loạn của tuổi mới lớn.', 1, N'QL00006')
INSERT [dbo].[PHIM] ([ID_PHIM], [TEN], [POSTER], [THOILUONG], [THELOAI], [NGONNGU], [NGAY_KC], [TOMTAT], [TRANGTHAI], [ID_NV]) VALUES (N'FI00004', N'Gã điên báo thù', N'ga_dien_bao_thu_poster.jpg', 118, N'Hành động', N'Tiếng Anh', CAST(N'2022-03-11' AS Date), N'Gã Điên Báo Thù xoay quanh H – tên thật là Patrick Hill (Jason Statham đóng), một nhân viên vừa được Fortico Securities thuê vào đội bảo an sau khi chiếc xe bọc thép…', 1, N'QL00006')
INSERT [dbo].[PHIM] ([ID_PHIM], [TEN], [POSTER], [THOILUONG], [THELOAI], [NGONNGU], [NGAY_KC], [TOMTAT], [TRANGTHAI], [ID_NV]) VALUES (N'FI00005', N'Bẫy ngọt ngào', N'bay_ngot_ngao_poster.jpg', 90, N'Tâm lý', N'Tiếng Việt', CAST(N'2022-02-11' AS Date), N'Sau một thời gian dài không gặp, cuộc sống của mỗi thành viên trong hội ế đều có nhiều thay đổi. Camy là người duy nhất “thoát ế” với cuộc sống đáng mơ ước bên người chồng tài hoa Đăng Minh.', 0, N'QL00006')
INSERT [dbo].[PHIM] ([ID_PHIM], [TEN], [POSTER], [THOILUONG], [THELOAI], [NGONNGU], [NGAY_KC], [TOMTAT], [TRANGTHAI], [ID_NV]) VALUES (N'FI00006', N'Người lắng nghe', N'nguoi_lang_nghe_poster.jpg', 119, N'Tâm lý', N'Tiếng Anh', CAST(N'2022-03-04' AS Date), N'Một nữ nhà văn, một chuyên gia tâm lý, một bác sĩ tâm thần và một nữ doanh nhân cùng nhau bị cuốn vào những ám ảnh không hồi kết của người phụ nữ với gương mặt mờ ảo.', 1, N'QL00006')
INSERT [dbo].[PHIM] ([ID_PHIM], [TEN], [POSTER], [THOILUONG], [THELOAI], [NGONNGU], [NGAY_KC], [TOMTAT], [TRANGTHAI], [ID_NV]) VALUES (N'FI00007', N'Giả danh anh hùng', N'poster1.png', 82, N'Hài hước', N'Tiếng Pháp', CAST(N'2022-03-11' AS Date), N'Cậu học sinh trung học mồ côi - Rick Riker bị một con chuồn chuồn phóng xạ cắn, phát triển siêu năng lực (ngoại trừ khả năng bay), và trở thành một anh hùng bất đắc dĩ.', 1, N'NV00001')
INSERT [dbo].[PHIM] ([ID_PHIM], [TEN], [POSTER], [THOILUONG], [THELOAI], [NGONNGU], [NGAY_KC], [TOMTAT], [TRANGTHAI], [ID_NV]) VALUES (N'FI00008', N'BELLE: Rồng và công chúa tàn nhang', N'belle_poster.jpg', 122, N'Hoạt hình', N'Tiếng Nhật', CAST(N'2022-02-25' AS Date), N'Suzu là một học sinh trung học bình thường, nhút nhát sống ở một ngôi làng nông thôn. Trong nhiều năm, cô ấy chỉ là cái bóng của chính mình.', 1, N'QL00006')
INSERT [dbo].[PHIM] ([ID_PHIM], [TEN], [POSTER], [THOILUONG], [THELOAI], [NGONNGU], [NGAY_KC], [TOMTAT], [TRANGTHAI], [ID_NV]) VALUES (N'FI00009', N'Trăng rơi', N'poster1.png', 130, N'Khoa học viễn tưởng', N'Tiếng Anh', CAST(N'2022-02-18' AS Date), N'Năm 2011, một tai nạn ngoài vũ trụ khiến một phi hành gia tử vong đầy bí ẩn. Đúng 10 năm sau, Mặt Trăng đột nhiên rời khỏi quỹ đạo và dần trên đường va chạm với Trái Đất.', 1, N'NV00001')
INSERT [dbo].[PHIM] ([ID_PHIM], [TEN], [POSTER], [THOILUONG], [THELOAI], [NGONNGU], [NGAY_KC], [TOMTAT], [TRANGTHAI], [ID_NV]) VALUES (N'FI00010', N'Cô dâu ma', N'bo_cu_poster.jpg', 90, N'Kinh dị', N'Tiếng Nga', CAST(N'2022-02-25' AS Date), N'Khi còn ở tuổi thiếu niên, Sasha (Konstantin Beloshapka) từng yêu say đắm một cô gái rất xinh đẹp. Hàng loạt những tấm ảnh tình tứ được Sasha đăng lên mạng như một sự khoe khoang với bạn bè.', 1, N'QL00006')
INSERT [dbo].[PHIM] ([ID_PHIM], [TEN], [POSTER], [THOILUONG], [THELOAI], [NGONNGU], [NGAY_KC], [TOMTAT], [TRANGTHAI], [ID_NV]) VALUES (N'FI00011', N'Chuyện ma gần nhà', N'chuyen_ma_gan_nha_poster.jpg', 108, N'Kinh dị', N'Tiếng Việt', CAST(N'2022-02-11' AS Date), N'Là tác phẩm đầu tiên trong vũ trụ kinh dị Việt Nam được lấy cảm hứng từ những truyền thuyết đô thị và các câu chuyện ma được đồn thổi trong dân gian.', 1, N'QL00006')
INSERT [dbo].[PHIM] ([ID_PHIM], [TEN], [POSTER], [THOILUONG], [THELOAI], [NGONNGU], [NGAY_KC], [TOMTAT], [TRANGTHAI], [ID_NV]) VALUES (N'FI00012', N'Nhện không nhà', N'nhen_khong_nha_poster.jpg', 149, N'Phiêu lưu', N'Tiếng Anh', CAST(N'2021-12-17' AS Date), N'Lần đầu tiên trong lịch sử điện ảnh của Người Nhện, thân phận người hàng xóm thân thiện bị lật mở, khiến trách nhiệm làm một Siêu Anh Hùng xung đột với cuộc sống bình thường và đặt người anh quan tâm', 1, N'QL00006')
INSERT [dbo].[PHIM] ([ID_PHIM], [TEN], [POSTER], [THOILUONG], [THELOAI], [NGONNGU], [NGAY_KC], [TOMTAT], [TRANGTHAI], [ID_NV]) VALUES (N'FI00013', N'Án mạng trên sông nile', N'minions_3_poster.jpg', 126, N'Tâm lý', N'Tiếng Anh', CAST(N'2022-02-11' AS Date), N'Án Mạng Trên Sông Nile xoay quanh chuyến đi tham quan Ai Cập của thám tử Poirot. Trên chiếc du thuyền nhỏ, ông bắt gặp một cặp nam thanh nữ tú: nàng triệu phú trẻ Linnet Doyle và người chồng mới cưới Simon Doyle.', 0, N'QL00006')
INSERT [dbo].[PHIM] ([ID_PHIM], [TEN], [POSTER], [THOILUONG], [THELOAI], [NGONNGU], [NGAY_KC], [TOMTAT], [TRANGTHAI], [ID_NV]) VALUES (N'FI00014', N'Năm, Mười, Mười Lăm', N'nam_muoi_muoilam_poster.jpg', 120, N'Kinh dị', N'Tiếng Anh', CAST(N'2022-03-25' AS Date), N'Khi trò trốn tìm biến tướng thành cuộc truy đuổi rùng rợn của “bà ba bị”. Sofía là một vú nuôi trẻ tuổi vừa bắt đầu công việc trông trẻ trong một gia đình giàu có.', 0, N'QL00006')
INSERT [dbo].[PHIM] ([ID_PHIM], [TEN], [POSTER], [THOILUONG], [THELOAI], [NGONNGU], [NGAY_KC], [TOMTAT], [TRANGTHAI], [ID_NV]) VALUES (N'FI00015', N'Phù thủy tối thượng trong đa vũ trụ hỗn loạn', N'nhung_ke_xau_xa_poster.jpg', 120, N'Hành động', N'Tiếng Anh', CAST(N'2022-05-20' AS Date), N'Sau các sự kiện của Avengers: Endgame, Tiến sĩ Stephen Strange tiếp tục nghiên cứu về Viên đá Thời gian. Nhưng một người bạn cũ đã trở thành kẻ thù tìm cách tiêu diệt mọi phù thủy', 0, N'QL00006')
INSERT [dbo].[PHIM] ([ID_PHIM], [TEN], [POSTER], [THOILUONG], [THELOAI], [NGONNGU], [NGAY_KC], [TOMTAT], [TRANGTHAI], [ID_NV]) VALUES (N'FI00016', N'Minions: sự trỗi dậy của gru', N'minions_3_poster.jpg', 110, N'Hoạt hình', N'Tiếng Anh', CAST(N'2022-04-30' AS Date), N'Hành trình phiêu lưu của Gru song hành cùng với Otto và viên đá của ác nhân MINIONS: SỰ TRỖI DẬY CỦA GRU - DCKC: 2021', 0, N'QL00006')
INSERT [dbo].[PHIM] ([ID_PHIM], [TEN], [POSTER], [THOILUONG], [THELOAI], [NGONNGU], [NGAY_KC], [TOMTAT], [TRANGTHAI], [ID_NV]) VALUES (N'FI00017', N'Em và Trịnh', N'poster1.png', 101, N'Tình cảm', N'Tiếng Việt', CAST(N'2022-04-30' AS Date), N'Phim điện ảnh Em và Trịnh kể về cuộc đời của nhạc sĩ Trịnh Công Sơn từ khi còn trẻ đến tuổi trung niên, khi ông hoài niệm về những nàng thơ trong cuộc đời mình: Bích Diễm, Dao Ánh, Khánh Ly, Hồng Nhung...', 0, N'NV00002')
INSERT [dbo].[PHIM] ([ID_PHIM], [TEN], [POSTER], [THOILUONG], [THELOAI], [NGONNGU], [NGAY_KC], [TOMTAT], [TRANGTHAI], [ID_NV]) VALUES (N'FI00018', N'Những kẻ xấu xa', N'poster1.png', 100, N'Hoạt hình', N'Tiếng Anh', CAST(N'2022-03-25' AS Date), N'Hành trình trở thành người tốt đầy gay cấn và nhiều tiếng cười của băng đảng những loài thú xấu xa.', 0, N'NV00002')
INSERT [dbo].[PHIM] ([ID_PHIM], [TEN], [POSTER], [THOILUONG], [THELOAI], [NGONNGU], [NGAY_KC], [TOMTAT], [TRANGTHAI], [ID_NV]) VALUES (N'FI00020', N'Avatar2', N'avata2_poster.jpg', 140, N'Hoạt hình', N'Tiếng Anh', CAST(N'2022-01-09' AS Date), N'', 1, N'QL00006')
INSERT [dbo].[PHIM] ([ID_PHIM], [TEN], [POSTER], [THOILUONG], [THELOAI], [NGONNGU], [NGAY_KC], [TOMTAT], [TRANGTHAI], [ID_NV]) VALUES (N'FI00021', N'Ma sói', N'masoi_poster.jpg', 180, N'Tâm lý', N'Tiếng Việt', CAST(N'2022-04-20' AS Date), N'', 1, N'QL00006')
GO
INSERT [dbo].[PHONGCHIEU] ([ID_PHONG], [SOLUONGDAY], [SOLUONGCOT]) VALUES (N'P001', 5, 12)
INSERT [dbo].[PHONGCHIEU] ([ID_PHONG], [SOLUONGDAY], [SOLUONGCOT]) VALUES (N'P002', 5, 12)
INSERT [dbo].[PHONGCHIEU] ([ID_PHONG], [SOLUONGDAY], [SOLUONGCOT]) VALUES (N'P003', 5, 12)
INSERT [dbo].[PHONGCHIEU] ([ID_PHONG], [SOLUONGDAY], [SOLUONGCOT]) VALUES (N'P004', 5, 12)
INSERT [dbo].[PHONGCHIEU] ([ID_PHONG], [SOLUONGDAY], [SOLUONGCOT]) VALUES (N'P005', 5, 12)
GO
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00001', CAST(N'2022-03-01' AS Date), N'P003', N'FI00012', CAST(N'23:00:00' AS Time), CAST(N'01:29:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00002', CAST(N'2022-03-18' AS Date), N'P001', N'FI00001', CAST(N'08:50:00' AS Time), CAST(N'10:31:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00003', CAST(N'2022-03-18' AS Date), N'P001', N'FI00009', CAST(N'10:40:00' AS Time), CAST(N'12:50:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00004', CAST(N'2022-03-18' AS Date), N'P001', N'FI00002', CAST(N'13:00:00' AS Time), CAST(N'16:03:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00005', CAST(N'2022-03-18' AS Date), N'P001', N'FI00004', CAST(N'16:10:00' AS Time), CAST(N'18:02:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00006', CAST(N'2022-03-18' AS Date), N'P001', N'FI00003', CAST(N'18:10:00' AS Time), CAST(N'19:49:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00007', CAST(N'2022-03-18' AS Date), N'P001', N'FI00001', CAST(N'20:00:00' AS Time), CAST(N'21:41:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00008', CAST(N'2022-03-18' AS Date), N'P001', N'FI00009', CAST(N'21:50:00' AS Time), CAST(N'00:00:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00009', CAST(N'2022-03-18' AS Date), N'P002', N'FI00002', CAST(N'07:00:00' AS Time), CAST(N'10:03:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00010', CAST(N'2022-03-18' AS Date), N'P002', N'FI00004', CAST(N'10:10:00' AS Time), CAST(N'12:02:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00011', CAST(N'2022-03-18' AS Date), N'P002', N'FI00001', CAST(N'12:10:00' AS Time), CAST(N'13:51:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00012', CAST(N'2022-03-18' AS Date), N'P002', N'FI00009', CAST(N'14:00:00' AS Time), CAST(N'16:10:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00013', CAST(N'2022-03-18' AS Date), N'P002', N'FI00003', CAST(N'16:20:00' AS Time), CAST(N'17:59:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00014', CAST(N'2022-03-18' AS Date), N'P002', N'FI00002', CAST(N'18:10:00' AS Time), CAST(N'21:13:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00015', CAST(N'2022-03-18' AS Date), N'P002', N'FI00004', CAST(N'21:20:00' AS Time), CAST(N'23:12:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00016', CAST(N'2022-03-18' AS Date), N'P002', N'FI00001', CAST(N'23:20:00' AS Time), CAST(N'01:01:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00017', CAST(N'2022-03-18' AS Date), N'P003', N'FI00003', CAST(N'07:00:00' AS Time), CAST(N'08:39:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00018', CAST(N'2022-03-18' AS Date), N'P003', N'FI00001', CAST(N'08:50:00' AS Time), CAST(N'10:31:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00019', CAST(N'2022-03-18' AS Date), N'P003', N'FI00009', CAST(N'10:40:00' AS Time), CAST(N'12:50:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00020', CAST(N'2022-03-18' AS Date), N'P003', N'FI00002', CAST(N'13:00:00' AS Time), CAST(N'16:03:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00021', CAST(N'2022-03-18' AS Date), N'P003', N'FI00004', CAST(N'16:10:00' AS Time), CAST(N'18:02:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00022', CAST(N'2022-03-18' AS Date), N'P003', N'FI00003', CAST(N'18:10:00' AS Time), CAST(N'19:49:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00023', CAST(N'2022-03-18' AS Date), N'P003', N'FI00001', CAST(N'20:00:00' AS Time), CAST(N'21:41:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00024', CAST(N'2022-03-18' AS Date), N'P003', N'FI00009', CAST(N'21:50:00' AS Time), CAST(N'00:00:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00025', CAST(N'2022-03-18' AS Date), N'P004', N'FI00002', CAST(N'07:00:00' AS Time), CAST(N'10:03:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00026', CAST(N'2022-03-18' AS Date), N'P004', N'FI00004', CAST(N'10:10:00' AS Time), CAST(N'12:02:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00027', CAST(N'2022-03-18' AS Date), N'P004', N'FI00001', CAST(N'12:10:00' AS Time), CAST(N'13:51:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00028', CAST(N'2022-03-18' AS Date), N'P004', N'FI00009', CAST(N'14:00:00' AS Time), CAST(N'16:10:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00029', CAST(N'2022-03-18' AS Date), N'P004', N'FI00003', CAST(N'16:20:00' AS Time), CAST(N'17:59:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00030', CAST(N'2022-03-18' AS Date), N'P004', N'FI00002', CAST(N'18:10:00' AS Time), CAST(N'21:13:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00031', CAST(N'2022-03-18' AS Date), N'P004', N'FI00004', CAST(N'21:20:00' AS Time), CAST(N'23:12:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00032', CAST(N'2022-03-18' AS Date), N'P004', N'FI00001', CAST(N'23:20:00' AS Time), CAST(N'01:01:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00033', CAST(N'2022-03-18' AS Date), N'P005', N'FI00003', CAST(N'07:00:00' AS Time), CAST(N'08:39:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00034', CAST(N'2022-03-18' AS Date), N'P005', N'FI00001', CAST(N'08:50:00' AS Time), CAST(N'10:31:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00035', CAST(N'2022-03-18' AS Date), N'P005', N'FI00009', CAST(N'10:40:00' AS Time), CAST(N'12:50:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00036', CAST(N'2022-03-18' AS Date), N'P005', N'FI00002', CAST(N'13:00:00' AS Time), CAST(N'16:03:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00037', CAST(N'2022-03-18' AS Date), N'P005', N'FI00004', CAST(N'16:10:00' AS Time), CAST(N'18:02:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00038', CAST(N'2022-03-18' AS Date), N'P005', N'FI00003', CAST(N'18:10:00' AS Time), CAST(N'19:49:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00039', CAST(N'2022-03-18' AS Date), N'P005', N'FI00001', CAST(N'20:00:00' AS Time), CAST(N'21:41:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00040', CAST(N'2022-03-18' AS Date), N'P005', N'FI00009', CAST(N'21:50:00' AS Time), CAST(N'00:00:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00041', CAST(N'2022-03-19' AS Date), N'P001', N'FI00003', CAST(N'07:00:00' AS Time), CAST(N'08:39:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00042', CAST(N'2022-03-19' AS Date), N'P001', N'FI00001', CAST(N'08:50:00' AS Time), CAST(N'10:31:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00043', CAST(N'2022-03-19' AS Date), N'P001', N'FI00009', CAST(N'10:40:00' AS Time), CAST(N'12:50:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00044', CAST(N'2022-03-19' AS Date), N'P001', N'FI00002', CAST(N'13:00:00' AS Time), CAST(N'16:03:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00045', CAST(N'2022-03-19' AS Date), N'P001', N'FI00004', CAST(N'16:10:00' AS Time), CAST(N'18:02:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00046', CAST(N'2022-03-19' AS Date), N'P001', N'FI00003', CAST(N'18:10:00' AS Time), CAST(N'19:49:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00047', CAST(N'2022-03-19' AS Date), N'P001', N'FI00001', CAST(N'20:00:00' AS Time), CAST(N'21:41:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00048', CAST(N'2022-03-19' AS Date), N'P001', N'FI00009', CAST(N'21:50:00' AS Time), CAST(N'00:00:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00049', CAST(N'2022-03-19' AS Date), N'P002', N'FI00002', CAST(N'07:00:00' AS Time), CAST(N'10:03:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00050', CAST(N'2022-03-19' AS Date), N'P002', N'FI00004', CAST(N'10:10:00' AS Time), CAST(N'12:02:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00051', CAST(N'2022-03-19' AS Date), N'P002', N'FI00001', CAST(N'12:10:00' AS Time), CAST(N'13:51:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00052', CAST(N'2022-03-19' AS Date), N'P002', N'FI00009', CAST(N'14:00:00' AS Time), CAST(N'16:10:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00053', CAST(N'2022-03-19' AS Date), N'P002', N'FI00003', CAST(N'16:20:00' AS Time), CAST(N'17:59:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00054', CAST(N'2022-03-19' AS Date), N'P002', N'FI00002', CAST(N'18:10:00' AS Time), CAST(N'21:13:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00055', CAST(N'2022-03-19' AS Date), N'P002', N'FI00004', CAST(N'21:20:00' AS Time), CAST(N'23:12:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00056', CAST(N'2022-03-19' AS Date), N'P002', N'FI00001', CAST(N'23:20:00' AS Time), CAST(N'01:01:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00057', CAST(N'2022-03-19' AS Date), N'P003', N'FI00003', CAST(N'07:00:00' AS Time), CAST(N'08:39:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00058', CAST(N'2022-03-19' AS Date), N'P003', N'FI00001', CAST(N'08:50:00' AS Time), CAST(N'10:31:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00059', CAST(N'2022-03-19' AS Date), N'P003', N'FI00009', CAST(N'10:40:00' AS Time), CAST(N'12:50:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00060', CAST(N'2022-03-19' AS Date), N'P003', N'FI00002', CAST(N'13:00:00' AS Time), CAST(N'16:03:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00061', CAST(N'2022-03-19' AS Date), N'P003', N'FI00004', CAST(N'16:10:00' AS Time), CAST(N'18:02:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00062', CAST(N'2022-03-19' AS Date), N'P003', N'FI00003', CAST(N'18:10:00' AS Time), CAST(N'19:49:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00063', CAST(N'2022-03-19' AS Date), N'P003', N'FI00001', CAST(N'20:00:00' AS Time), CAST(N'21:41:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00064', CAST(N'2022-03-19' AS Date), N'P003', N'FI00009', CAST(N'21:50:00' AS Time), CAST(N'00:00:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00065', CAST(N'2022-03-19' AS Date), N'P004', N'FI00002', CAST(N'07:00:00' AS Time), CAST(N'10:03:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00066', CAST(N'2022-03-19' AS Date), N'P004', N'FI00004', CAST(N'10:10:00' AS Time), CAST(N'12:02:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00067', CAST(N'2022-03-19' AS Date), N'P004', N'FI00001', CAST(N'12:10:00' AS Time), CAST(N'13:51:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00068', CAST(N'2022-03-19' AS Date), N'P004', N'FI00009', CAST(N'14:00:00' AS Time), CAST(N'16:10:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00069', CAST(N'2022-03-19' AS Date), N'P004', N'FI00003', CAST(N'16:20:00' AS Time), CAST(N'17:59:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00070', CAST(N'2022-03-19' AS Date), N'P004', N'FI00002', CAST(N'18:10:00' AS Time), CAST(N'21:13:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00071', CAST(N'2022-03-19' AS Date), N'P004', N'FI00004', CAST(N'21:20:00' AS Time), CAST(N'23:12:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00072', CAST(N'2022-03-19' AS Date), N'P004', N'FI00001', CAST(N'23:20:00' AS Time), CAST(N'01:01:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00073', CAST(N'2022-03-19' AS Date), N'P005', N'FI00003', CAST(N'07:00:00' AS Time), CAST(N'08:39:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00074', CAST(N'2022-03-19' AS Date), N'P005', N'FI00001', CAST(N'08:50:00' AS Time), CAST(N'10:31:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00075', CAST(N'2022-03-19' AS Date), N'P005', N'FI00009', CAST(N'10:40:00' AS Time), CAST(N'12:50:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00076', CAST(N'2022-03-19' AS Date), N'P005', N'FI00002', CAST(N'13:00:00' AS Time), CAST(N'16:03:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00077', CAST(N'2022-03-19' AS Date), N'P005', N'FI00004', CAST(N'16:10:00' AS Time), CAST(N'18:02:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00078', CAST(N'2022-03-19' AS Date), N'P005', N'FI00003', CAST(N'18:10:00' AS Time), CAST(N'19:49:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00079', CAST(N'2022-03-19' AS Date), N'P005', N'FI00001', CAST(N'20:00:00' AS Time), CAST(N'21:41:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00080', CAST(N'2022-03-19' AS Date), N'P005', N'FI00009', CAST(N'21:50:00' AS Time), CAST(N'00:00:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00081', CAST(N'2022-03-20' AS Date), N'P001', N'FI00003', CAST(N'07:00:00' AS Time), CAST(N'08:39:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00082', CAST(N'2022-03-20' AS Date), N'P001', N'FI00001', CAST(N'08:50:00' AS Time), CAST(N'10:31:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00083', CAST(N'2022-03-20' AS Date), N'P001', N'FI00009', CAST(N'10:40:00' AS Time), CAST(N'12:50:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00084', CAST(N'2022-03-20' AS Date), N'P001', N'FI00002', CAST(N'13:00:00' AS Time), CAST(N'16:03:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00085', CAST(N'2022-03-20' AS Date), N'P001', N'FI00004', CAST(N'16:10:00' AS Time), CAST(N'18:02:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00086', CAST(N'2022-03-20' AS Date), N'P001', N'FI00003', CAST(N'18:10:00' AS Time), CAST(N'19:49:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00087', CAST(N'2022-03-20' AS Date), N'P001', N'FI00001', CAST(N'20:00:00' AS Time), CAST(N'21:41:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00088', CAST(N'2022-03-20' AS Date), N'P001', N'FI00009', CAST(N'21:50:00' AS Time), CAST(N'00:00:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00089', CAST(N'2022-03-20' AS Date), N'P002', N'FI00002', CAST(N'07:00:00' AS Time), CAST(N'10:03:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00090', CAST(N'2022-03-20' AS Date), N'P002', N'FI00004', CAST(N'10:10:00' AS Time), CAST(N'12:02:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00091', CAST(N'2022-03-20' AS Date), N'P002', N'FI00001', CAST(N'12:10:00' AS Time), CAST(N'13:51:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00092', CAST(N'2022-03-20' AS Date), N'P002', N'FI00009', CAST(N'14:00:00' AS Time), CAST(N'16:10:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00093', CAST(N'2022-03-20' AS Date), N'P002', N'FI00003', CAST(N'16:20:00' AS Time), CAST(N'17:59:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00094', CAST(N'2022-03-20' AS Date), N'P002', N'FI00002', CAST(N'18:10:00' AS Time), CAST(N'21:13:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00095', CAST(N'2022-03-20' AS Date), N'P002', N'FI00004', CAST(N'21:20:00' AS Time), CAST(N'23:12:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00096', CAST(N'2022-03-20' AS Date), N'P002', N'FI00001', CAST(N'23:20:00' AS Time), CAST(N'01:01:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00097', CAST(N'2022-03-20' AS Date), N'P003', N'FI00003', CAST(N'07:00:00' AS Time), CAST(N'08:39:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00098', CAST(N'2022-03-20' AS Date), N'P003', N'FI00001', CAST(N'08:50:00' AS Time), CAST(N'10:31:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00099', CAST(N'2022-03-20' AS Date), N'P003', N'FI00009', CAST(N'10:40:00' AS Time), CAST(N'12:50:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00100', CAST(N'2022-03-20' AS Date), N'P003', N'FI00002', CAST(N'13:00:00' AS Time), CAST(N'16:03:00' AS Time), N'NV00001')
GO
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00101', CAST(N'2022-03-20' AS Date), N'P003', N'FI00004', CAST(N'16:10:00' AS Time), CAST(N'18:02:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00102', CAST(N'2022-03-20' AS Date), N'P003', N'FI00003', CAST(N'18:10:00' AS Time), CAST(N'19:49:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00103', CAST(N'2022-03-20' AS Date), N'P003', N'FI00001', CAST(N'20:00:00' AS Time), CAST(N'21:41:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00104', CAST(N'2022-03-20' AS Date), N'P003', N'FI00009', CAST(N'21:50:00' AS Time), CAST(N'00:00:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00105', CAST(N'2022-03-20' AS Date), N'P004', N'FI00002', CAST(N'07:00:00' AS Time), CAST(N'10:03:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00106', CAST(N'2022-03-20' AS Date), N'P004', N'FI00004', CAST(N'10:10:00' AS Time), CAST(N'12:02:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00107', CAST(N'2022-03-20' AS Date), N'P004', N'FI00001', CAST(N'12:10:00' AS Time), CAST(N'13:51:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00108', CAST(N'2022-03-20' AS Date), N'P004', N'FI00009', CAST(N'14:00:00' AS Time), CAST(N'16:10:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00109', CAST(N'2022-03-20' AS Date), N'P004', N'FI00003', CAST(N'16:20:00' AS Time), CAST(N'17:59:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00110', CAST(N'2022-03-20' AS Date), N'P004', N'FI00002', CAST(N'18:10:00' AS Time), CAST(N'21:13:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00111', CAST(N'2022-03-20' AS Date), N'P004', N'FI00004', CAST(N'21:20:00' AS Time), CAST(N'23:12:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00112', CAST(N'2022-03-20' AS Date), N'P004', N'FI00001', CAST(N'23:20:00' AS Time), CAST(N'01:01:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00113', CAST(N'2022-03-20' AS Date), N'P005', N'FI00003', CAST(N'07:00:00' AS Time), CAST(N'08:39:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00114', CAST(N'2022-03-20' AS Date), N'P005', N'FI00001', CAST(N'08:50:00' AS Time), CAST(N'10:31:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00115', CAST(N'2022-03-20' AS Date), N'P005', N'FI00009', CAST(N'10:40:00' AS Time), CAST(N'12:50:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00116', CAST(N'2022-03-20' AS Date), N'P005', N'FI00002', CAST(N'13:00:00' AS Time), CAST(N'16:03:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00117', CAST(N'2022-03-20' AS Date), N'P005', N'FI00004', CAST(N'16:10:00' AS Time), CAST(N'18:02:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00118', CAST(N'2022-03-20' AS Date), N'P005', N'FI00003', CAST(N'18:10:00' AS Time), CAST(N'19:49:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00119', CAST(N'2022-03-20' AS Date), N'P005', N'FI00001', CAST(N'20:00:00' AS Time), CAST(N'21:41:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00120', CAST(N'2022-03-20' AS Date), N'P005', N'FI00009', CAST(N'21:50:00' AS Time), CAST(N'00:00:00' AS Time), N'NV00001')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00121', CAST(N'2022-04-14' AS Date), N'P001', N'FI00003', CAST(N'07:00:00' AS Time), CAST(N'08:39:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00122', CAST(N'2022-04-14' AS Date), N'P001', N'FI00003', CAST(N'11:00:00' AS Time), CAST(N'12:39:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00124', CAST(N'2022-04-14' AS Date), N'P002', N'FI00008', CAST(N'07:00:00' AS Time), CAST(N'09:02:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00125', CAST(N'2022-04-14' AS Date), N'P002', N'FI00002', CAST(N'07:00:00' AS Time), CAST(N'10:03:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00126', CAST(N'2022-04-14' AS Date), N'P005', N'FI00010', CAST(N'10:00:00' AS Time), CAST(N'11:30:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00127', CAST(N'2022-04-15' AS Date), N'P002', N'FI00007', CAST(N'07:00:00' AS Time), CAST(N'08:22:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00128', CAST(N'2022-04-15' AS Date), N'P002', N'FI00007', CAST(N'10:00:00' AS Time), CAST(N'11:22:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00129', CAST(N'2022-04-14' AS Date), N'P004', N'FI00003', CAST(N'21:35:00' AS Time), CAST(N'23:14:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00130', CAST(N'2022-04-15' AS Date), N'P002', N'FI00012', CAST(N'12:00:00' AS Time), CAST(N'14:29:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00131', CAST(N'2022-04-15' AS Date), N'P005', N'FI00001', CAST(N'10:00:00' AS Time), CAST(N'11:41:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00132', CAST(N'2022-04-18' AS Date), N'P005', N'FI00011', CAST(N'07:00:00' AS Time), CAST(N'08:48:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00133', CAST(N'2022-04-15' AS Date), N'P003', N'FI00001', CAST(N'11:00:00' AS Time), CAST(N'12:41:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00134', CAST(N'2022-04-15' AS Date), N'P003', N'FI00002', CAST(N'14:00:00' AS Time), CAST(N'17:03:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00135', CAST(N'2022-04-15' AS Date), N'P003', N'FI00002', CAST(N'18:00:00' AS Time), CAST(N'21:03:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00136', CAST(N'2022-04-16' AS Date), N'P004', N'FI00012', CAST(N'21:00:00' AS Time), CAST(N'23:29:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00137', CAST(N'2022-04-16' AS Date), N'P002', N'FI00011', CAST(N'11:00:00' AS Time), CAST(N'12:48:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00138', CAST(N'2022-04-16' AS Date), N'P001', N'FI00006', CAST(N'18:00:00' AS Time), CAST(N'19:59:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00139', CAST(N'2022-04-16' AS Date), N'P004', N'FI00001', CAST(N'12:00:00' AS Time), CAST(N'13:41:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00140', CAST(N'2022-04-16' AS Date), N'P004', N'FI00006', CAST(N'18:00:00' AS Time), CAST(N'19:59:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00141', CAST(N'2022-04-16' AS Date), N'P005', N'FI00006', CAST(N'18:00:00' AS Time), CAST(N'19:59:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00142', CAST(N'2022-04-16' AS Date), N'P003', N'FI00012', CAST(N'21:00:00' AS Time), CAST(N'23:29:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00143', CAST(N'2022-04-15' AS Date), N'P004', N'FI00001', CAST(N'20:00:00' AS Time), CAST(N'21:41:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00144', CAST(N'2022-04-16' AS Date), N'P004', N'FI00012', CAST(N'23:00:00' AS Time), CAST(N'01:29:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00145', CAST(N'2022-04-17' AS Date), N'P001', N'FI00012', CAST(N'07:00:00' AS Time), CAST(N'09:29:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00146', CAST(N'2022-04-17' AS Date), N'P001', N'FI00012', CAST(N'10:00:00' AS Time), CAST(N'12:29:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00147', CAST(N'2022-04-17' AS Date), N'P003', N'FI00012', CAST(N'10:00:00' AS Time), CAST(N'12:29:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00148', CAST(N'2022-04-17' AS Date), N'P005', N'FI00012', CAST(N'10:00:00' AS Time), CAST(N'12:29:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00149', CAST(N'2022-04-17' AS Date), N'P004', N'FI00012', CAST(N'07:00:00' AS Time), CAST(N'09:29:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00150', CAST(N'2022-04-18' AS Date), N'P002', N'FI00011', CAST(N'07:00:00' AS Time), CAST(N'08:48:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00151', CAST(N'2022-04-18' AS Date), N'P002', N'FI00011', CAST(N'11:00:00' AS Time), CAST(N'12:48:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00152', CAST(N'2022-04-18' AS Date), N'P002', N'FI00011', CAST(N'13:00:00' AS Time), CAST(N'14:48:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00153', CAST(N'2022-04-18' AS Date), N'P002', N'FI00011', CAST(N'18:00:00' AS Time), CAST(N'19:48:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00154', CAST(N'2022-04-18' AS Date), N'P004', N'FI00011', CAST(N'07:00:00' AS Time), CAST(N'08:48:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00155', CAST(N'2022-04-18' AS Date), N'P005', N'FI00008', CAST(N'17:00:00' AS Time), CAST(N'19:02:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00156', CAST(N'2022-04-17' AS Date), N'P005', N'FI00009', CAST(N'23:00:00' AS Time), CAST(N'01:10:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00157', CAST(N'2022-04-17' AS Date), N'P004', N'FI00007', CAST(N'22:00:00' AS Time), CAST(N'23:22:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00158', CAST(N'2022-04-17' AS Date), N'P003', N'FI00010', CAST(N'07:00:00' AS Time), CAST(N'08:30:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00159', CAST(N'2022-04-17' AS Date), N'P002', N'FI00008', CAST(N'23:00:00' AS Time), CAST(N'01:02:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00160', CAST(N'2022-04-20' AS Date), N'P001', N'FI00002', CAST(N'07:00:00' AS Time), CAST(N'10:03:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00161', CAST(N'2022-04-22' AS Date), N'P002', N'FI00002', CAST(N'11:45:00' AS Time), CAST(N'14:48:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00162', CAST(N'2022-04-22' AS Date), N'P003', N'FI00007', CAST(N'07:45:00' AS Time), CAST(N'09:07:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00163', CAST(N'2022-04-22' AS Date), N'P003', N'FI00007', CAST(N'10:00:00' AS Time), CAST(N'11:22:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00164', CAST(N'2022-04-22' AS Date), N'P003', N'FI00001', CAST(N'13:00:00' AS Time), CAST(N'14:41:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00166', CAST(N'2022-04-21' AS Date), N'P001', N'FI00006', CAST(N'07:00:00' AS Time), CAST(N'08:59:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00167', CAST(N'2022-04-21' AS Date), N'P001', N'FI00008', CAST(N'11:00:00' AS Time), CAST(N'13:02:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00168', CAST(N'2022-04-20' AS Date), N'P001', N'FI00021', CAST(N'17:00:00' AS Time), CAST(N'20:00:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00169', CAST(N'2022-04-20' AS Date), N'P001', N'FI00003', CAST(N'23:00:00' AS Time), CAST(N'00:39:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00170', CAST(N'2022-04-22' AS Date), N'P005', N'FI00009', CAST(N'09:00:00' AS Time), CAST(N'11:10:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00171', CAST(N'2022-04-22' AS Date), N'P005', N'FI00009', CAST(N'19:00:00' AS Time), CAST(N'21:10:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00172', CAST(N'2022-04-22' AS Date), N'P004', N'FI00003', CAST(N'12:30:00' AS Time), CAST(N'14:09:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00173', CAST(N'2022-04-22' AS Date), N'P005', N'FI00009', CAST(N'15:00:00' AS Time), CAST(N'17:10:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00174', CAST(N'2022-04-21' AS Date), N'P004', N'FI00001', CAST(N'07:00:00' AS Time), CAST(N'08:41:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00175', CAST(N'2022-04-21' AS Date), N'P004', N'FI00008', CAST(N'12:00:00' AS Time), CAST(N'14:02:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00177', CAST(N'2022-04-21' AS Date), N'P004', N'FI00004', CAST(N'17:00:00' AS Time), CAST(N'18:58:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00178', CAST(N'2022-04-20' AS Date), N'P004', N'FI00011', CAST(N'17:00:00' AS Time), CAST(N'18:48:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00179', CAST(N'2022-04-20' AS Date), N'P004', N'FI00004', CAST(N'19:00:00' AS Time), CAST(N'20:58:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00180', CAST(N'2022-04-20' AS Date), N'P003', N'FI00009', CAST(N'15:00:00' AS Time), CAST(N'17:10:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00181', CAST(N'2022-04-20' AS Date), N'P003', N'FI00012', CAST(N'19:50:00' AS Time), CAST(N'22:19:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00182', CAST(N'2022-04-21' AS Date), N'P001', N'FI00003', CAST(N'16:15:00' AS Time), CAST(N'17:54:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00183', CAST(N'2022-04-21' AS Date), N'P005', N'FI00002', CAST(N'17:00:00' AS Time), CAST(N'20:03:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00184', CAST(N'2022-04-21' AS Date), N'P005', N'FI00006', CAST(N'20:20:00' AS Time), CAST(N'22:19:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00185', CAST(N'2022-04-21' AS Date), N'P005', N'FI00010', CAST(N'22:35:00' AS Time), CAST(N'00:05:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00186', CAST(N'2022-04-21' AS Date), N'P004', N'FI00004', CAST(N'19:10:00' AS Time), CAST(N'21:08:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00187', CAST(N'2022-04-21' AS Date), N'P004', N'FI00008', CAST(N'21:30:00' AS Time), CAST(N'23:32:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00188', CAST(N'2022-04-21' AS Date), N'P003', N'FI00021', CAST(N'17:30:00' AS Time), CAST(N'20:30:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00189', CAST(N'2022-04-21' AS Date), N'P003', N'FI00006', CAST(N'20:55:00' AS Time), CAST(N'22:54:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00190', CAST(N'2022-04-23' AS Date), N'P001', N'FI00010', CAST(N'07:00:00' AS Time), CAST(N'08:30:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00191', CAST(N'2022-04-23' AS Date), N'P001', N'FI00021', CAST(N'09:00:00' AS Time), CAST(N'12:00:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00192', CAST(N'2022-04-23' AS Date), N'P001', N'FI00004', CAST(N'13:00:00' AS Time), CAST(N'14:58:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00193', CAST(N'2022-04-23' AS Date), N'P001', N'FI00008', CAST(N'16:00:00' AS Time), CAST(N'18:02:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00194', CAST(N'2022-04-23' AS Date), N'P004', N'FI00010', CAST(N'10:00:00' AS Time), CAST(N'11:30:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00195', CAST(N'2022-04-23' AS Date), N'P004', N'FI00007', CAST(N'13:00:00' AS Time), CAST(N'14:22:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00196', CAST(N'2022-04-23' AS Date), N'P005', N'FI00002', CAST(N'16:00:00' AS Time), CAST(N'19:03:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00197', CAST(N'2022-04-23' AS Date), N'P005', N'FI00008', CAST(N'12:00:00' AS Time), CAST(N'14:02:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00198', CAST(N'2022-04-23' AS Date), N'P003', N'FI00002', CAST(N'15:00:00' AS Time), CAST(N'18:03:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00199', CAST(N'2022-04-23' AS Date), N'P003', N'FI00011', CAST(N'07:00:00' AS Time), CAST(N'08:48:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00200', CAST(N'2022-04-24' AS Date), N'P004', N'FI00011', CAST(N'09:00:00' AS Time), CAST(N'10:48:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00201', CAST(N'2022-04-24' AS Date), N'P002', N'FI00011', CAST(N'09:00:00' AS Time), CAST(N'10:48:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00202', CAST(N'2022-04-24' AS Date), N'P004', N'FI00002', CAST(N'11:00:00' AS Time), CAST(N'14:03:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00203', CAST(N'2022-04-23' AS Date), N'P003', N'FI00021', CAST(N'19:00:00' AS Time), CAST(N'22:00:00' AS Time), N'QL00006')
GO
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00204', CAST(N'2022-04-22' AS Date), N'P003', N'FI00021', CAST(N'18:10:00' AS Time), CAST(N'21:10:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00205', CAST(N'2022-04-22' AS Date), N'P004', N'FI00006', CAST(N'16:35:00' AS Time), CAST(N'18:34:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00206', CAST(N'2022-04-22' AS Date), N'P002', N'FI00009', CAST(N'15:00:00' AS Time), CAST(N'17:10:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00207', CAST(N'2022-04-22' AS Date), N'P003', N'FI00003', CAST(N'07:45:00' AS Time), CAST(N'09:24:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00208', CAST(N'2022-04-22' AS Date), N'P004', N'FI00003', CAST(N'08:45:00' AS Time), CAST(N'10:24:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00209', CAST(N'2022-04-22' AS Date), N'P002', N'FI00011', CAST(N'18:00:00' AS Time), CAST(N'19:48:00' AS Time), N'QL00006')
INSERT [dbo].[SUATCHIEU] ([ID_SUAT], [NGAYCHIEU], [ID_PHONG], [ID_PHIM], [GIOBATDAU], [GIOKETTHUC], [ID_NV]) VALUES (N'SC00210', CAST(N'2022-04-22' AS Date), N'P002', N'FI00003', CAST(N'21:00:00' AS Time), CAST(N'22:39:00' AS Time), N'QL00006')
GO
INSERT [dbo].[THANHVIEN] ([ID_TV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [MAKH]) VALUES (N'TV00001', N'Hoàng Linh Trang', CAST(N'1999-03-01' AS Date), 1, N'0922344112', N'linhtrang@gmail.com', N'F3CX39ACA001')
INSERT [dbo].[THANHVIEN] ([ID_TV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [MAKH]) VALUES (N'TV00002', N'Võ Như Ngọc', CAST(N'1999-07-13' AS Date), 1, N'0982990002', N'nhungoc@gmail.com', N'5836D6RJ4002')
INSERT [dbo].[THANHVIEN] ([ID_TV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [MAKH]) VALUES (N'TV00003', N'Ánh Linh Nhi', CAST(N'1999-03-03' AS Date), 1, N'0906916666', N'linhnhi@gmail.com', N'C7D65JRXC003')
INSERT [dbo].[THANHVIEN] ([ID_TV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [MAKH]) VALUES (N'TV00004', N'Đường Hoàng Tam', CAST(N'1999-09-03' AS Date), 0, N'0909220109', N'hoangtam@gmail.com', N'A6JRCFTPQ004')
INSERT [dbo].[THANHVIEN] ([ID_TV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [MAKH]) VALUES (N'TV00005', N'Ngọc Linh Đường', CAST(N'1999-04-08' AS Date), 1, N'0945678120', N'linhduong@gmail.com', N'HC0O9WIDC005')
INSERT [dbo].[THANHVIEN] ([ID_TV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [MAKH]) VALUES (N'TV00006', N'Nguyễn Tuấn Linh', CAST(N'1992-03-03' AS Date), 0, N'0912880220', N'tuanlinh@gmail.com', N'X3E93W0L2006')
INSERT [dbo].[THANHVIEN] ([ID_TV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [MAKH]) VALUES (N'TV00007', N'Nguyễn Quang Tuấn', CAST(N'1995-05-03' AS Date), 0, N'0982778456', N'quangtuan@gmail.com', N'4QCU2BH0H007')
INSERT [dbo].[THANHVIEN] ([ID_TV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [MAKH]) VALUES (N'TV00008', N'Nguyễn Quang Minh', CAST(N'1998-03-03' AS Date), 0, N'0921589987', N'quangminh@gmail.com', N'GLQ6ZF91U008')
INSERT [dbo].[THANHVIEN] ([ID_TV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [MAKH]) VALUES (N'TV00009', N'Trần Nhật Hào', CAST(N'2001-05-24' AS Date), 0, N'0975155532', N'haotn@gmail.com', N'8OW8DGBDG009')
INSERT [dbo].[THANHVIEN] ([ID_TV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [MAKH]) VALUES (N'TV00010', N'Lê Kim Kim', CAST(N'1999-04-24' AS Date), 1, N'0977783123', N'kimkim99@gmail.com', N'1PAY85LMR010')
INSERT [dbo].[THANHVIEN] ([ID_TV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [MAKH]) VALUES (N'TV00011', N'Hoàng Thu Trang', CAST(N'1993-07-13' AS Date), 1, N'0977456720', N'thutrang@gmail.com', N'IYOB0I36E011')
INSERT [dbo].[THANHVIEN] ([ID_TV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [MAKH]) VALUES (N'TV00012', N'Mai Ánh Linh', CAST(N'1993-03-09' AS Date), 1, N'0989779666', N'maianhlinh@gmail.com', N'X51T8YMRL012')
INSERT [dbo].[THANHVIEN] ([ID_TV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [MAKH]) VALUES (N'TV00013', N'Phạm Thành Đô', CAST(N'2001-12-01' AS Date), 0, N'0334718723', N'phamdo@gmail.com', N'E5S9QAT91013')
INSERT [dbo].[THANHVIEN] ([ID_TV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [MAKH]) VALUES (N'TV00014', N'Phan Thanh Phương', CAST(N'2001-03-24' AS Date), 0, N'0337034588', N'phuongphan@gmail.com', N'CJ3BKM3WC014')
INSERT [dbo].[THANHVIEN] ([ID_TV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [MAKH]) VALUES (N'TV00015', N'Lê Bửu Điền', CAST(N'2001-08-03' AS Date), 0, N'0967141140', N'dienle@gmail.com', N'ACXNXHSS3015')
INSERT [dbo].[THANHVIEN] ([ID_TV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [MAKH]) VALUES (N'TV00016', N'Nguyễn Đức Huy', CAST(N'2000-04-22' AS Date), 0, N'0342389488', N'duchuy@gmail.com', N'WYOPZTKIU016')
INSERT [dbo].[THANHVIEN] ([ID_TV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [MAKH]) VALUES (N'TV00017', N'Phan Thanh Châu', CAST(N'2001-10-04' AS Date), 0, N'0866735557', N'chauthanh@gmail.com', N'Y4IUE2EAH017')
INSERT [dbo].[THANHVIEN] ([ID_TV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [MAKH]) VALUES (N'TV00018', N'Nguyễn Nhật Vy', CAST(N'2001-03-25' AS Date), 1, N'0353447899', N'vynguyen@gmail.com', N'HA3HX9CJB018')
INSERT [dbo].[THANHVIEN] ([ID_TV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [MAKH]) VALUES (N'TV00019', N'Lê Thanh Tâm', CAST(N'2001-09-27' AS Date), 1, N'0357837264', N'thanhtam@gmail.com', N'YB9UY1WRI019')
INSERT [dbo].[THANHVIEN] ([ID_TV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [MAKH]) VALUES (N'TV00020', N'Cao Minh Khang', CAST(N'2001-09-20' AS Date), 0, N'0974101817', N'minhkhang@gmail.com', N'L64642KUS020')
INSERT [dbo].[THANHVIEN] ([ID_TV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [MAKH]) VALUES (N'TV00021', N'Phạm Chí Thanh', CAST(N'1999-02-13' AS Date), 0, N'0984875769', N'thanhpham@gmail.com', N'C99IN334B021')
INSERT [dbo].[THANHVIEN] ([ID_TV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [MAKH]) VALUES (N'TV00022', N'Trần Nhật Nam', CAST(N'2003-01-21' AS Date), 0, N'0387371168', N'nhatnam@gmail.com', N'JJ4SQPZF5022')
INSERT [dbo].[THANHVIEN] ([ID_TV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [MAKH]) VALUES (N'TV00023', N'Nguyễn Nhật Bản', CAST(N'1998-02-14' AS Date), 0, N'0378372332', N'nguyennhat@gmail.com', N'FDZ19BFMX023')
INSERT [dbo].[THANHVIEN] ([ID_TV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [MAKH]) VALUES (N'TV00024', N'Phạm Lê Yến Vy', CAST(N'2001-08-25' AS Date), 1, N'03578294637', N'vypham@gmail.com', N'SKXZ0BXJ4024')
INSERT [dbo].[THANHVIEN] ([ID_TV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [MAKH]) VALUES (N'TV00025', N'Trần Thị Như Yên', CAST(N'2000-03-27' AS Date), 1, N'0993728495', N'nhuyen@gmail.com', N'G43KIGJKO025')
INSERT [dbo].[THANHVIEN] ([ID_TV], [HOTEN], [NGAYSINH], [GIOITINH], [SODT], [EMAIL], [MAKH]) VALUES (N'TV00026', N'Trần Vân', CAST(N'1998-04-21' AS Date), 0, N'8088487994', N'van@gmail.com', N'EBR93O0TX026')
GO
SET IDENTITY_INSERT [dbo].[VEDAT] ON 

INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (1, N'NL', N'GS01A01', N'DV00001', N'SC00001')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (2, N'NL', N'GS01A02', N'DV00001', N'SC00001')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (3, N'NL', N'GS01B05', N'DV00002', N'SC00001')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (4, N'NL', N'GS01A03', N'DV00003', N'SC00002')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (5, N'NL', N'GS01A04', N'DV00003', N'SC00002')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (6, N'NL', N'GS01A05', N'DV00003', N'SC00002')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (7, N'NL', N'GS01B06', N'DV00004', N'SC00003')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (8, N'NL', N'GS01B07', N'DV00004', N'SC00003')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (9, N'NL', N'GS01C05', N'DV00005', N'SC00001')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (10, N'NL', N'GS01C06', N'DV00005', N'SC00001')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (11, N'NL', N'GS01C10', N'DV00006', N'SC00002')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (12, N'NL', N'GS01C11', N'DV00006', N'SC00002')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (13, N'NL', N'GS01C12', N'DV00006', N'SC00002')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (14, N'NL', N'GS01D01', N'DV00007', N'SC00005')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (15, N'NL', N'GS01D02', N'DV00007', N'SC00005')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (16, N'NL', N'GS01D03', N'DV00007', N'SC00005')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (17, N'TE', N'GS01D04', N'DV00007', N'SC00005')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (18, N'TE', N'GS01D10', N'DV00007', N'SC00005')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (19, N'NL', N'GS01D05', N'DV00008', N'SC00005')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (20, N'NL', N'GS01D06', N'DV00008', N'SC00005')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (21, N'TE', N'GS01D07', N'DV00008', N'SC00005')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (22, N'TE', N'GS01D08', N'DV00008', N'SC00005')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (23, N'NL', N'GS01D09', N'DV00009', N'SC00005')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (24, N'NL', N'GS02B08', N'DV00010', N'SC00005')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (37, N'NL', N'GS01A05', N'DV00012', N'SC00005')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (60, N'NL', N'GS04B10', N'DV00013', N'SC00157')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (61, N'NL', N'GS05C05', N'DV00014', N'SC00156')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (62, N'TE', N'GS05C06', N'DV00014', N'SC00156')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (63, N'TE', N'GS05C07', N'DV00014', N'SC00156')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (64, N'TE', N'GS05C09', N'DV00014', N'SC00156')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (65, N'NL', N'GS05B06', N'DV00015', N'SC00155')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (66, N'NL', N'GS05C08', N'DV00015', N'SC00155')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (67, N'NL', N'GS05C02', N'DV00015', N'SC00155')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (68, N'NL', N'GS05C05', N'DV00015', N'SC00155')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (69, N'NL', N'GS05C06', N'DV00015', N'SC00155')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (70, N'NL', N'GS05D09', N'DV00016', N'SC00155')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (71, N'NL', N'GS05D02', N'DV00016', N'SC00155')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (72, N'TE', N'GS05D04', N'DV00016', N'SC00155')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (73, N'TE', N'GS05D06', N'DV00016', N'SC00155')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (74, N'TE', N'GS05D08', N'DV00016', N'SC00155')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (75, N'NL', N'GS05B08', N'DV00017', N'SC00155')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (76, N'TE', N'GS05A05', N'DV00017', N'SC00155')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (77, N'TE', N'GS05A06', N'DV00017', N'SC00155')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (78, N'TE', N'GS05A07', N'DV00017', N'SC00155')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (79, N'NL', N'GS05B07', N'DV00018', N'SC00132')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (80, N'TE', N'GS05C01', N'DV00018', N'SC00132')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (81, N'TE', N'GS05C02', N'DV00018', N'SC00132')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (82, N'TE', N'GS05C03', N'DV00018', N'SC00132')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (83, N'TE', N'GS05C04', N'DV00018', N'SC00132')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (84, N'TE', N'GS05C05', N'DV00018', N'SC00132')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (85, N'TE', N'GS05C06', N'DV00018', N'SC00132')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (86, N'NL', N'GS02B07', N'DV00020', N'SC00151')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (87, N'TE', N'GS02C03', N'DV00020', N'SC00151')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (88, N'TE', N'GS02C05', N'DV00020', N'SC00151')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (89, N'NL', N'GS02B08', N'DV00021', N'SC00151')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (90, N'NL', N'GS05B09', N'DV00022', N'SC00155')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (91, N'NL', N'GS02B09', N'DV00023', N'SC00151')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (92, N'TE', N'GS02C06', N'DV00023', N'SC00151')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (93, N'TE', N'GS02C07', N'DV00023', N'SC00151')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (94, N'TE', N'GS02C08', N'DV00023', N'SC00151')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (95, N'NL', N'GS02C07', N'DV00024', N'SC00152')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (96, N'NL', N'GS02B09', N'DV00024', N'SC00152')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (97, N'NL', N'GS01B07', N'DV00025', N'SC00168')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (98, N'TE', N'GS01C08', N'DV00025', N'SC00168')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (99, N'TE', N'GS01D05', N'DV00025', N'SC00168')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (100, N'TE', N'GS01D06', N'DV00025', N'SC00168')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (101, N'NL', N'GS03B07', N'DV00026', N'SC00181')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (102, N'TE', N'GS03B05', N'DV00026', N'SC00181')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (103, N'TE', N'GS03B09', N'DV00026', N'SC00181')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (104, N'TE', N'GS03C10', N'DV00026', N'SC00181')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (105, N'NL', N'GS01C08', N'DV00027', N'SC00169')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (106, N'NL', N'GS01A07', N'DV00027', N'SC00169')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (107, N'NL', N'GS01B07', N'DV00027', N'SC00169')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (108, N'TE', N'GS04C06', N'DV00028', N'SC00179')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (109, N'TE', N'GS04C07', N'DV00028', N'SC00179')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (110, N'NL', N'GS04C02', N'DV00029', N'SC00178')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (111, N'NL', N'GS04C03', N'DV00029', N'SC00178')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (112, N'NL', N'GS04C04', N'DV00029', N'SC00178')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (113, N'NL', N'GS04C05', N'DV00029', N'SC00178')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (114, N'NL', N'GS04C06', N'DV00029', N'SC00178')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (115, N'TE', N'GS04C07', N'DV00029', N'SC00178')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (116, N'NL', N'GS04C08', N'DV00029', N'SC00178')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (117, N'TE', N'GS04C09', N'DV00029', N'SC00178')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (118, N'NL', N'GS01C06', N'DV00030', N'SC00169')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (119, N'TE', N'GS01A04', N'DV00030', N'SC00169')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (120, N'TE', N'GS01B03', N'DV00030', N'SC00169')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (121, N'TE', N'GS01D06', N'DV00030', N'SC00169')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (122, N'NL', N'GS03B04', N'DV00031', N'SC00189')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (123, N'NL', N'GS03B05', N'DV00031', N'SC00189')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (124, N'NL', N'GS03B06', N'DV00031', N'SC00189')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (125, N'NL', N'GS03B03', N'DV00031', N'SC00189')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (126, N'NL', N'GS03C03', N'DV00032', N'SC00188')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (127, N'TE', N'GS03D03', N'DV00032', N'SC00188')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (128, N'TE', N'GS03E03', N'DV00032', N'SC00188')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (129, N'TE', N'GS03B03', N'DV00032', N'SC00188')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (130, N'NL', N'GS01B08', N'DV00033', N'SC00193')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (131, N'TE', N'GS01B05', N'DV00033', N'SC00193')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (132, N'TE', N'GS01B04', N'DV00033', N'SC00193')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (133, N'TE', N'GS01B03', N'DV00033', N'SC00193')
GO
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (134, N'TE', N'GS01B02', N'DV00033', N'SC00193')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (135, N'TE', N'GS01C02', N'DV00033', N'SC00193')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (136, N'NL', N'GS04B07', N'DV00034', N'SC00177')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (137, N'TE', N'GS04B04', N'DV00034', N'SC00177')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (138, N'TE', N'GS04B06', N'DV00034', N'SC00177')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (139, N'TE', N'GS04B05', N'DV00034', N'SC00177')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (140, N'NL', N'GS04A05', N'DV00035', N'SC00177')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (141, N'NL', N'GS04D06', N'DV00036', N'SC00186')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (142, N'NL', N'GS04D04', N'DV00036', N'SC00186')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (143, N'NL', N'GS04D05', N'DV00036', N'SC00186')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (144, N'NL', N'GS04D07', N'DV00036', N'SC00186')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (145, N'NL', N'GS04D08', N'DV00036', N'SC00186')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (146, N'NL', N'GS05A05', N'DV00037', N'SC00170')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (147, N'TE', N'GS05A06', N'DV00037', N'SC00170')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (148, N'TE', N'GS05A07', N'DV00037', N'SC00170')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (149, N'NL', N'GS03B04', N'DV00038', N'SC00204')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (150, N'TE', N'GS03B05', N'DV00038', N'SC00204')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (151, N'TE', N'GS03B09', N'DV00038', N'SC00204')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (152, N'TE', N'GS03B10', N'DV00038', N'SC00204')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (153, N'TE', N'GS03B06', N'DV00038', N'SC00204')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (154, N'NL', N'GS05C03', N'DV00039', N'SC00173')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (155, N'NL', N'GS05C05', N'DV00039', N'SC00173')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (156, N'NL', N'GS05C08', N'DV00039', N'SC00173')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (157, N'NL', N'GS05C06', N'DV00039', N'SC00173')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (158, N'NL', N'GS02B05', N'DV00040', N'SC00161')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (159, N'TE', N'GS02B06', N'DV00040', N'SC00161')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (160, N'TE', N'GS02B07', N'DV00040', N'SC00161')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (161, N'TE', N'GS02B08', N'DV00040', N'SC00161')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (162, N'TE', N'GS02B09', N'DV00040', N'SC00161')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (163, N'NL', N'GS03D06', N'DV00041', N'SC00204')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (164, N'TE', N'GS03D07', N'DV00041', N'SC00204')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (165, N'NL', N'GS04B06', N'DV00042', N'SC00205')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (166, N'TE', N'GS04C06', N'DV00042', N'SC00205')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (167, N'TE', N'GS04C08', N'DV00042', N'SC00205')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (168, N'TE', N'GS04A09', N'DV00042', N'SC00205')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (169, N'NL', N'GS03B06', N'DV00043', N'SC00164')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (170, N'TE', N'GS03C01', N'DV00043', N'SC00164')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (171, N'TE', N'GS03C03', N'DV00043', N'SC00164')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (172, N'TE', N'GS03E05', N'DV00043', N'SC00164')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (173, N'NL', N'GS03C10', N'DV00043', N'SC00164')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (174, N'NL', N'GS03C11', N'DV00043', N'SC00164')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (175, N'NL', N'GS04B07', N'DV00044', N'SC00187')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (176, N'TE', N'GS04C05', N'DV00044', N'SC00187')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (177, N'TE', N'GS04C07', N'DV00044', N'SC00187')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (178, N'TE', N'GS04C09', N'DV00044', N'SC00187')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (179, N'TE', N'GS04C08', N'DV00044', N'SC00187')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (180, N'TE', N'GS04D06', N'DV00044', N'SC00187')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (181, N'NL', N'GS04B06', N'DV00045', N'SC00208')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (182, N'NL', N'GS04D04', N'DV00045', N'SC00208')
INSERT [dbo].[VEDAT] ([ID_VEDAT], [ID_LOAIVE], [ID_GHE], [ID_DONVE], [ID_SUAT]) VALUES (183, N'NL', N'GS04D05', N'DV00045', N'SC00208')
SET IDENTITY_INSERT [dbo].[VEDAT] OFF
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__NHANVIEN__161CF724FBF2B69B]    Script Date: 21/04/2022 21:32:26 ******/
ALTER TABLE [dbo].[NHANVIEN] ADD UNIQUE NONCLUSTERED 
(
	[EMAIL] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__NHANVIEN__A58DF1B81C18C60B]    Script Date: 21/04/2022 21:32:26 ******/
ALTER TABLE [dbo].[NHANVIEN] ADD UNIQUE NONCLUSTERED 
(
	[TENTK] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ_SoDT_TV]    Script Date: 21/04/2022 21:32:26 ******/
ALTER TABLE [dbo].[THANHVIEN] ADD  CONSTRAINT [UQ_SoDT_TV] UNIQUE NONCLUSTERED 
(
	[SODT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [U_G_S]    Script Date: 21/04/2022 21:32:26 ******/
ALTER TABLE [dbo].[VEDAT] ADD  CONSTRAINT [U_G_S] UNIQUE NONCLUSTERED 
(
	[ID_GHE] ASC,
	[ID_SUAT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[DOAN]  WITH CHECK ADD  CONSTRAINT [FK_DOAN_LOAI] FOREIGN KEY([ID_LOAI])
REFERENCES [dbo].[LOAIDOAN] ([ID])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[DOAN] CHECK CONSTRAINT [FK_DOAN_LOAI]
GO
ALTER TABLE [dbo].[DOANCT]  WITH CHECK ADD  CONSTRAINT [FK_CT_DONDA] FOREIGN KEY([ID_DONDA])
REFERENCES [dbo].[DONDOAN] ([ID_DONDA])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[DOANCT] CHECK CONSTRAINT [FK_CT_DONDA]
GO
ALTER TABLE [dbo].[DOANCT]  WITH CHECK ADD  CONSTRAINT [FK_CT_KCDA] FOREIGN KEY([ID_KCDA])
REFERENCES [dbo].[KICHCODOAN] ([ID_KCDA])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[DOANCT] CHECK CONSTRAINT [FK_CT_KCDA]
GO
ALTER TABLE [dbo].[DONTHANHTOAN]  WITH CHECK ADD  CONSTRAINT [FK_DON_DA] FOREIGN KEY([ID_DONDA])
REFERENCES [dbo].[DONDOAN] ([ID_DONDA])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[DONTHANHTOAN] CHECK CONSTRAINT [FK_DON_DA]
GO
ALTER TABLE [dbo].[DONTHANHTOAN]  WITH CHECK ADD  CONSTRAINT [FK_DON_NV] FOREIGN KEY([ID_NV])
REFERENCES [dbo].[NHANVIEN] ([ID_NV])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[DONTHANHTOAN] CHECK CONSTRAINT [FK_DON_NV]
GO
ALTER TABLE [dbo].[DONTHANHTOAN]  WITH CHECK ADD  CONSTRAINT [FK_DON_TV] FOREIGN KEY([ID_TV])
REFERENCES [dbo].[THANHVIEN] ([ID_TV])
ON UPDATE CASCADE
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[DONTHANHTOAN] CHECK CONSTRAINT [FK_DON_TV]
GO
ALTER TABLE [dbo].[DONTHANHTOAN]  WITH CHECK ADD  CONSTRAINT [FK_DON_VE] FOREIGN KEY([ID_DONVE])
REFERENCES [dbo].[DONVE] ([ID_DONVE])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[DONTHANHTOAN] CHECK CONSTRAINT [FK_DON_VE]
GO
ALTER TABLE [dbo].[GHE]  WITH CHECK ADD  CONSTRAINT [FK_GHE_LOAI] FOREIGN KEY([ID_LOAIGHE])
REFERENCES [dbo].[LOAIGHE] ([ID_LOAIGHE])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[GHE] CHECK CONSTRAINT [FK_GHE_LOAI]
GO
ALTER TABLE [dbo].[GHE]  WITH CHECK ADD  CONSTRAINT [FK_GHE_PHONG] FOREIGN KEY([ID_PHONGCHIEU])
REFERENCES [dbo].[PHONGCHIEU] ([ID_PHONG])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[GHE] CHECK CONSTRAINT [FK_GHE_PHONG]
GO
ALTER TABLE [dbo].[KICHCODOAN]  WITH CHECK ADD  CONSTRAINT [FK_KC_KCDA] FOREIGN KEY([ID_KICHCO])
REFERENCES [dbo].[KICHCO] ([ID])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[KICHCODOAN] CHECK CONSTRAINT [FK_KC_KCDA]
GO
ALTER TABLE [dbo].[KICHCODOAN]  WITH CHECK ADD  CONSTRAINT [FK_KICHCO_DOAN] FOREIGN KEY([ID_DOAN])
REFERENCES [dbo].[DOAN] ([ID_DOAN])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[KICHCODOAN] CHECK CONSTRAINT [FK_KICHCO_DOAN]
GO
ALTER TABLE [dbo].[PHIM]  WITH CHECK ADD  CONSTRAINT [FK_PHIM_NV] FOREIGN KEY([ID_NV])
REFERENCES [dbo].[NHANVIEN] ([ID_NV])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[PHIM] CHECK CONSTRAINT [FK_PHIM_NV]
GO
ALTER TABLE [dbo].[SUATCHIEU]  WITH CHECK ADD  CONSTRAINT [FK_SC_NV] FOREIGN KEY([ID_NV])
REFERENCES [dbo].[NHANVIEN] ([ID_NV])
ON DELETE SET NULL
GO
ALTER TABLE [dbo].[SUATCHIEU] CHECK CONSTRAINT [FK_SC_NV]
GO
ALTER TABLE [dbo].[SUATCHIEU]  WITH CHECK ADD  CONSTRAINT [FK_SC_PHIM] FOREIGN KEY([ID_PHIM])
REFERENCES [dbo].[PHIM] ([ID_PHIM])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[SUATCHIEU] CHECK CONSTRAINT [FK_SC_PHIM]
GO
ALTER TABLE [dbo].[SUATCHIEU]  WITH CHECK ADD  CONSTRAINT [FK_SC_PHONG] FOREIGN KEY([ID_PHONG])
REFERENCES [dbo].[PHONGCHIEU] ([ID_PHONG])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[SUATCHIEU] CHECK CONSTRAINT [FK_SC_PHONG]
GO
ALTER TABLE [dbo].[VEDAT]  WITH CHECK ADD  CONSTRAINT [FK_VEDAT_DONDATVE] FOREIGN KEY([ID_DONVE])
REFERENCES [dbo].[DONVE] ([ID_DONVE])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[VEDAT] CHECK CONSTRAINT [FK_VEDAT_DONDATVE]
GO
ALTER TABLE [dbo].[VEDAT]  WITH CHECK ADD  CONSTRAINT [FK_VEDAT_GHE] FOREIGN KEY([ID_GHE])
REFERENCES [dbo].[GHE] ([ID_GHE])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[VEDAT] CHECK CONSTRAINT [FK_VEDAT_GHE]
GO
ALTER TABLE [dbo].[VEDAT]  WITH CHECK ADD  CONSTRAINT [FK_VEDAT_LOAIVE] FOREIGN KEY([ID_LOAIVE])
REFERENCES [dbo].[LOAIVE] ([ID_LOAIVE])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[VEDAT] CHECK CONSTRAINT [FK_VEDAT_LOAIVE]
GO
ALTER TABLE [dbo].[VEDAT]  WITH CHECK ADD  CONSTRAINT [FK_VEDAT_SUAT] FOREIGN KEY([ID_SUAT])
REFERENCES [dbo].[SUATCHIEU] ([ID_SUAT])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[VEDAT] CHECK CONSTRAINT [FK_VEDAT_SUAT]
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthudoan]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_doanhthudoan]
as 
	begin
		select DOAN.TEN, SUM(DOANCT.SOLUONG) AS SOLUONG, KICHCODOAN.ID_KICHCO, (SUM(DOANCT.SOLUONG) * KICHCODOAN.GIA) AS THANHTIEN
	from KICHCODOAN join DOAN on KICHCODOAN.ID_DOAN = DOAN.ID_DOAN
					join DOANCT on DOANCT.ID_KCDA = KICHCODOAN.ID_KCDA
GROUP BY  DOAN.TEN,KICHCODOAN.ID_KICHCO, KICHCODOAN.GIA
end
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthudoan_theonam]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_doanhthudoan_theonam] (@Year int)
as 
	begin
		select DOAN.TEN, SUM(DOANCT.SOLUONG) AS SOLUONG, KICHCODOAN.ID_KICHCO, (SUM(DOANCT.SOLUONG) * KICHCODOAN.GIA) AS THANHTIEN
	from KICHCODOAN join DOAN on KICHCODOAN.ID_DOAN = DOAN.ID_DOAN
					join DOANCT on DOANCT.ID_KCDA = KICHCODOAN.ID_KCDA
					join DONTHANHTOAN on DONTHANHTOAN.ID_DONDA = DOANCT.ID_DONDA
	WHERE YEAR(NGAYDAT) = @Year
GROUP BY  DOAN.TEN,KICHCODOAN.ID_KICHCO, KICHCODOAN.GIA
end
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthudoan_theongay]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_doanhthudoan_theongay] (@Ngay date)
as 
	begin
		select DOAN.TEN, SUM(DOANCT.SOLUONG) AS SOLUONG, KICHCODOAN.ID_KICHCO, (SUM(DOANCT.SOLUONG) * KICHCODOAN.GIA) AS THANHTIEN
	from KICHCODOAN join DOAN on KICHCODOAN.ID_DOAN = DOAN.ID_DOAN
					join DOANCT on DOANCT.ID_KCDA = KICHCODOAN.ID_KCDA
					join DONTHANHTOAN on DONTHANHTOAN.ID_DONDA = DOANCT.ID_DONDA
		where NGAYDAT BETWEEN (convert(varchar, @Ngay) +' 00:00:00') AND (convert(varchar, @Ngay) + ' 23:59:59')
GROUP BY  DOAN.TEN,KICHCODOAN.ID_KICHCO, KICHCODOAN.GIA
end
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthudoan_theosize]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_doanhthudoan_theosize] (@size nchar(1))
as 
	begin
		select DOAN.TEN, SUM(DOANCT.SOLUONG) AS SOLUONG, KICHCODOAN.ID_KICHCO, (SUM(DOANCT.SOLUONG) * KICHCODOAN.GIA) AS THANHTIEN
	from KICHCODOAN join DOAN on KICHCODOAN.ID_DOAN = DOAN.ID_DOAN
					join DOANCT on DOANCT.ID_KCDA = KICHCODOAN.ID_KCDA
					join DONTHANHTOAN on DONTHANHTOAN.ID_DONDA = DOANCT.ID_DONDA
	WHERE KICHCODOAN.ID_KICHCO like @size
GROUP BY  DOAN.TEN,KICHCODOAN.ID_KICHCO, KICHCODOAN.GIA
end
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthudoan_theoten]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_doanhthudoan_theoten] (@ten nvarchar(100))
as 
	begin
		select DOAN.TEN, SUM(DOANCT.SOLUONG) AS SOLUONG, KICHCODOAN.ID_KICHCO, (SUM(DOANCT.SOLUONG) * KICHCODOAN.GIA) AS THANHTIEN
	from KICHCODOAN join DOAN on KICHCODOAN.ID_DOAN = DOAN.ID_DOAN
					join DOANCT on DOANCT.ID_KCDA = KICHCODOAN.ID_KCDA
					join DONTHANHTOAN on DONTHANHTOAN.ID_DONDA = DOANCT.ID_DONDA
	WHERE DOAN.TEN like @ten
GROUP BY  DOAN.TEN,KICHCODOAN.ID_KICHCO, KICHCODOAN.GIA
end
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthudoan_theothangnam]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_doanhthudoan_theothangnam] (@Thang int, @Year int)
as 
	begin
		select DOAN.TEN, SUM(DOANCT.SOLUONG) AS SOLUONG, KICHCODOAN.ID_KICHCO, (SUM(DOANCT.SOLUONG) * KICHCODOAN.GIA) AS THANHTIEN
	from KICHCODOAN join DOAN on KICHCODOAN.ID_DOAN = DOAN.ID_DOAN
					join DOANCT on DOANCT.ID_KCDA = KICHCODOAN.ID_KCDA
					join DONTHANHTOAN on DONTHANHTOAN.ID_DONDA = DOANCT.ID_DONDA
	WHERE MONTH(NGAYDAT) = @Thang and YEAR(NGAYDAT) = @Year 
GROUP BY  DOAN.TEN,KICHCODOAN.ID_KICHCO, KICHCODOAN.GIA
end
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthuphim]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_doanhthuphim]
as 
	begin
		declare @SoSuatChieu_Phim table(id nvarchar(20), sosuatchieu int)
		insert into @SoSuatChieu_Phim
		select PHIM.ID_PHIM, count(ID_SUAT) from SUATCHIEU join PHIM 
		on SUATCHIEU.ID_PHIM = PHIM.ID_PHIM group by PHIM.ID_PHIM

		select P.TEN, sosuatchieu, COUNT(VD.ID_VEDAT) as sovedat, SUM(GIA + PHUTHU) as tonggia 
		from PHIM P join SUATCHIEU SC on P.ID_PHIM = SC.ID_PHIM 
					left join VEDAT VD on VD.ID_SUAT = SC.ID_SUAT 
					join @SoSuatChieu_Phim SSC on SSC.id = P.ID_PHIM

					join LOAIVE on VD.ID_LOAIVE = LOAIVE.ID_LOAIVE
					join GHE on VD.ID_GHE = GHE.ID_GHE
					join LOAIGHE  on GHE.ID_LOAIGHE = LOAIGHE.ID_LOAIGHE

					group by P.TEN, sosuatchieu
	end
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthuphim_sapxep]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_doanhthuphim_sapxep]
as 
	begin
		declare @SoSuatChieu_Phim table(id nvarchar(20), sosuatchieu int)
		insert into @SoSuatChieu_Phim
		select PHIM.ID_PHIM, count(ID_SUAT) from SUATCHIEU join PHIM 
		on SUATCHIEU.ID_PHIM = PHIM.ID_PHIM group by PHIM.ID_PHIM

		select P.TEN, sosuatchieu, COUNT(VD.ID_VEDAT) as sovedat, SUM(GIA + PHUTHU) as tonggia 
		from PHIM P join SUATCHIEU SC on P.ID_PHIM = SC.ID_PHIM 
					left join VEDAT VD on VD.ID_SUAT = SC.ID_SUAT 
					join @SoSuatChieu_Phim SSC on SSC.id = P.ID_PHIM

					join LOAIVE on VD.ID_LOAIVE = LOAIVE.ID_LOAIVE
					join GHE on VD.ID_GHE = GHE.ID_GHE
					join LOAIGHE  on GHE.ID_LOAIGHE = LOAIGHE.ID_LOAIGHE

					group by P.TEN, sosuatchieu
					order by sovedat desc
	end
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthuphim_theonam]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_doanhthuphim_theonam] (@Year int)
as 
	begin
		declare @SoSuatChieu_Phim table(id nvarchar(20), sosuatchieu int)
		insert into @SoSuatChieu_Phim
		select PHIM.ID_PHIM, count(ID_SUAT) from SUATCHIEU join PHIM 
		on SUATCHIEU.ID_PHIM = PHIM.ID_PHIM group by PHIM.ID_PHIM

		select P.TEN, sosuatchieu, COUNT(VD.ID_VEDAT) as sovedat, SUM(GIA + PHUTHU) as tonggia 
		from PHIM P join SUATCHIEU SC on P.ID_PHIM = SC.ID_PHIM 
					left join VEDAT VD on VD.ID_SUAT = SC.ID_SUAT 
					join @SoSuatChieu_Phim SSC on SSC.id = P.ID_PHIM

					join LOAIVE on VD.ID_LOAIVE = LOAIVE.ID_LOAIVE
					join GHE on VD.ID_GHE = GHE.ID_GHE
					join LOAIGHE  on GHE.ID_LOAIGHE = LOAIGHE.ID_LOAIGHE
		where YEAR(sc.NGAYCHIEU) like @Year
		group by P.TEN, sosuatchieu
	end
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthuphim_theongay]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_doanhthuphim_theongay] (@Ngay date)
as 
	begin
		declare @SoSuatChieu_Phim table(id nvarchar(20), sosuatchieu int)
		insert into @SoSuatChieu_Phim
		select PHIM.ID_PHIM, count(ID_SUAT) from SUATCHIEU join PHIM 
		on SUATCHIEU.ID_PHIM = PHIM.ID_PHIM group by PHIM.ID_PHIM

		select P.TEN, sosuatchieu, COUNT(VD.ID_VEDAT) as sovedat, SUM(GIA + PHUTHU) as tonggia 
		from PHIM P join SUATCHIEU SC on P.ID_PHIM = SC.ID_PHIM 
					left join VEDAT VD on VD.ID_SUAT = SC.ID_SUAT 
					join @SoSuatChieu_Phim SSC on SSC.id = P.ID_PHIM

					join LOAIVE on VD.ID_LOAIVE = LOAIVE.ID_LOAIVE
					join GHE on VD.ID_GHE = GHE.ID_GHE
					join LOAIGHE  on GHE.ID_LOAIGHE = LOAIGHE.ID_LOAIGHE
		where sc.NGAYCHIEU BETWEEN (convert(varchar, @Ngay) +' 00:00:00') AND (convert(varchar, @Ngay) + ' 23:59:59')
		group by P.TEN, sosuatchieu
	end
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthuphim_theothangnam]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_doanhthuphim_theothangnam] (@Thang int, @Year int)
as 
	begin
		declare @SoSuatChieu_Phim table(id nvarchar(20), sosuatchieu int)
		insert into @SoSuatChieu_Phim
		select PHIM.ID_PHIM, count(ID_SUAT) from SUATCHIEU join PHIM 
		on SUATCHIEU.ID_PHIM = PHIM.ID_PHIM group by PHIM.ID_PHIM

		select P.TEN, sosuatchieu, COUNT(VD.ID_VEDAT) as sovedat, SUM(GIA + PHUTHU) as tonggia 
		from PHIM P join SUATCHIEU SC on P.ID_PHIM = SC.ID_PHIM 
					left join VEDAT VD on VD.ID_SUAT = SC.ID_SUAT 
					join @SoSuatChieu_Phim SSC on SSC.id = P.ID_PHIM

					join LOAIVE on VD.ID_LOAIVE = LOAIVE.ID_LOAIVE
					join GHE on VD.ID_GHE = GHE.ID_GHE
					join LOAIGHE  on GHE.ID_LOAIGHE = LOAIGHE.ID_LOAIGHE
		where MONTH(sc.NGAYCHIEU) like @Thang and YEAR(sc.NGAYCHIEU) like @Year
		group by P.TEN, sosuatchieu
	end
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthuphim_top]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_doanhthuphim_top]
as 
	begin
		declare @SoSuatChieu_Phim table(id nvarchar(20), sosuatchieu int)
		insert into @SoSuatChieu_Phim
		select PHIM.ID_PHIM, count(ID_SUAT) from SUATCHIEU join PHIM 
		on SUATCHIEU.ID_PHIM = PHIM.ID_PHIM group by PHIM.ID_PHIM

		select top (5) P.TEN, sosuatchieu, COUNT(VD.ID_VEDAT) as sovedat, SUM(GIA + PHUTHU) as tonggia 
		from PHIM P join SUATCHIEU SC on P.ID_PHIM = SC.ID_PHIM 
					left join VEDAT VD on VD.ID_SUAT = SC.ID_SUAT 
					join @SoSuatChieu_Phim SSC on SSC.id = P.ID_PHIM

					join LOAIVE on VD.ID_LOAIVE = LOAIVE.ID_LOAIVE
					join GHE on VD.ID_GHE = GHE.ID_GHE
					join LOAIGHE  on GHE.ID_LOAIGHE = LOAIGHE.ID_LOAIGHE

					group by P.TEN, sosuatchieu
					order by tonggia desc
	end
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthuphongve]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_doanhthuphongve]
as 
	begin
		declare @SoSuatChieu_Thang table (thang int, tongsosuatchieu int)
		insert into @SoSuatChieu_Thang
		select DATEPART(MM, NGAYCHIEU) ,count(ID_SUAT) from SUATCHIEU group by DATEPART(MM, NGAYCHIEU)

		SELECT DATEPART(MM, NGAYCHIEU) as thang, tongsosuatchieu, COUNT(ID_VEDAT) as sovedaban, sum(GIA + PHUTHU) as doanhthu
		from SUATCHIEU SC left join VEDAT VD on SC.ID_SUAT = VD.ID_SUAT 
						join @SoSuatChieu_Thang SSC on SSC.thang = MONTH(SC.NGAYCHIEU)

						join LOAIVE on VD.ID_LOAIVE = LOAIVE.ID_LOAIVE
						join GHE on VD.ID_GHE = GHE.ID_GHE
						join LOAIGHE  on GHE.ID_LOAIGHE = LOAIGHE.ID_LOAIGHE

		group by DATEPART(MM, NGAYCHIEU), tongsosuatchieu
	end
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthuphongve_sapxep]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_doanhthuphongve_sapxep]
as 
	begin
		declare @SoSuatChieu_Thang table (thang int, tongsosuatchieu int)
		insert into @SoSuatChieu_Thang
		select DATEPART(MM, NGAYCHIEU) ,count(ID_SUAT) from SUATCHIEU group by DATEPART(MM, NGAYCHIEU)

		SELECT DATEPART(MM, NGAYCHIEU) as thang, tongsosuatchieu, COUNT(ID_VEDAT) as sovedaban, sum(GIA + PHUTHU) as doanhthu
		from SUATCHIEU SC left join VEDAT VD on SC.ID_SUAT = VD.ID_SUAT 
						join @SoSuatChieu_Thang SSC on SSC.thang = MONTH(SC.NGAYCHIEU)

						join LOAIVE on VD.ID_LOAIVE = LOAIVE.ID_LOAIVE
						join GHE on VD.ID_GHE = GHE.ID_GHE
						join LOAIGHE  on GHE.ID_LOAIGHE = LOAIGHE.ID_LOAIGHE

		group by DATEPART(MM, NGAYCHIEU), tongsosuatchieu
		order by sovedaban desc
	end
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthuphongve_theonam]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_doanhthuphongve_theonam] (@Year int)
as 
	begin
		declare @SoSuatChieu_Thang table (thang int, tongsosuatchieu int)
		insert into @SoSuatChieu_Thang
		select DATEPART(MM, NGAYCHIEU) ,count(ID_SUAT) from SUATCHIEU group by DATEPART(MM, NGAYCHIEU)

		SELECT DATEPART(MM, NGAYCHIEU) as thang, tongsosuatchieu, COUNT(ID_VEDAT) as sovedaban, sum(GIA + PHUTHU) as doanhthu
		from SUATCHIEU SC left join VEDAT VD on SC.ID_SUAT = VD.ID_SUAT 
						join @SoSuatChieu_Thang SSC on SSC.thang = MONTH(SC.NGAYCHIEU)

						join LOAIVE on VD.ID_LOAIVE = LOAIVE.ID_LOAIVE
						join GHE on VD.ID_GHE = GHE.ID_GHE
						join LOAIGHE  on GHE.ID_LOAIGHE = LOAIGHE.ID_LOAIGHE
		where YEAR(NGAYCHIEU) like @Year
		group by DATEPART(MM, NGAYCHIEU), tongsosuatchieu
	end
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthuphongve_theongay]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_doanhthuphongve_theongay] (@Ngay date)
as 
	begin
		declare @SoSuatChieu_Thang table (thang int, tongsosuatchieu int)
		insert into @SoSuatChieu_Thang
		select DATEPART(MM, NGAYCHIEU) ,count(ID_SUAT) from SUATCHIEU group by DATEPART(MM, NGAYCHIEU)

		SELECT DATEPART(MM, NGAYCHIEU) as thang, tongsosuatchieu, COUNT(ID_VEDAT) as sovedaban, sum(GIA + PHUTHU) as doanhthu
		from SUATCHIEU SC left join VEDAT VD on SC.ID_SUAT = VD.ID_SUAT 
						join @SoSuatChieu_Thang SSC on SSC.thang = MONTH(SC.NGAYCHIEU)

						join LOAIVE on VD.ID_LOAIVE = LOAIVE.ID_LOAIVE
						join GHE on VD.ID_GHE = GHE.ID_GHE
						join LOAIGHE  on GHE.ID_LOAIGHE = LOAIGHE.ID_LOAIGHE
		where NGAYCHIEU BETWEEN (convert(varchar, @Ngay) +' 00:00:00') AND (convert(varchar, @Ngay) + ' 23:59:59')
		group by DATEPART(MM, NGAYCHIEU), tongsosuatchieu
	end
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthuphongve_theothangnam]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_doanhthuphongve_theothangnam] (@Thang int, @Year int)
as 
	begin
		declare @SoSuatChieu_Thang table (thang int, tongsosuatchieu int)
		insert into @SoSuatChieu_Thang
		select DATEPART(MM, NGAYCHIEU) ,count(ID_SUAT) from SUATCHIEU group by DATEPART(MM, NGAYCHIEU)

		SELECT DATEPART(MM, NGAYCHIEU) as thang, tongsosuatchieu, COUNT(ID_VEDAT) as sovedaban, sum(GIA + PHUTHU) as doanhthu
		from SUATCHIEU SC left join VEDAT VD on SC.ID_SUAT = VD.ID_SUAT 
						join @SoSuatChieu_Thang SSC on SSC.thang = MONTH(SC.NGAYCHIEU)

						join LOAIVE on VD.ID_LOAIVE = LOAIVE.ID_LOAIVE
						join GHE on VD.ID_GHE = GHE.ID_GHE
						join LOAIGHE  on GHE.ID_LOAIGHE = LOAIGHE.ID_LOAIGHE
		where MONTH(NGAYCHIEU) like @Thang and YEAR(NGAYCHIEU) like @Year
		group by DATEPART(MM, NGAYCHIEU), tongsosuatchieu
	end
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthutong]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE proc [dbo].[sp_doanhthutong]
as 
	begin
		-- DOANH THU DO AN
		declare @doanhthuDA table (id int identity(1,1), doanhthuDA decimal(15, 2))
		insert into @doanhthuDA
		select SUM(SOLUONG*GIA) as DT_DOAN 
		from DOANCT DACT join KICHCODOAN KCDA on DACT.ID_KCDA = KCDA.ID_KCDA
						 join DONTHANHTOAN DTT on DTT.ID_DONDA = DACT.ID_DONDA

		-- DOANH THU PHONG VE
		declare @doanhthuPV table (id int identity(1,1), doanhthuPV decimal(15, 2))
		insert into @doanhthuPV
		select SUM(GIA + PHUTHU) as DT_PHIM
		from VEDAT VD join LOAIVE LV on VD.ID_LOAIVE = LV.ID_LOAIVE
					  join GHE G on G.ID_GHE = VD.ID_GHE
					  join LOAIGHE LG on LG.ID_LOAIGHE = G.ID_LOAIGHE
					  join PHONGCHIEU PC on PC.ID_PHONG = G.ID_PHONGCHIEU
					  join SUATCHIEU SC on SC.ID_SUAT = VD.ID_SUAT
		select iif(doanhthuPV is NULL, 0, doanhthuPV) as DTPV, 
			   iif(doanhthuDA is NULL, 0, doanhthuDA) as DTDA, 
			  (iif(doanhthuDA is NULL, 0, doanhthuDA) + iif(doanhthuPV is NULL, 0, doanhthuPV)) as tongDoanhThu
		from @doanhthuDA DTDA join @doanhthuPV DTPV on DTDA.id = DTPV.id
	end
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthutong_nam]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE proc [dbo].[sp_doanhthutong_nam] (@Nam int)
as 
	begin
		declare @doanhthuDA table (id int identity(1,1), doanhthuDA decimal(15, 2))
		insert into @doanhthuDA
		select SUM(SOLUONG*GIA) as DT_DOAN 
		from DOANCT DACT join KICHCODOAN KCDA on DACT.ID_KCDA = KCDA.ID_KCDA
						 join DONTHANHTOAN DTT on DTT.ID_DONDA = DACT.ID_DONDA
		where YEAR(NGAYDAT) like @Nam

		-- DOANH THU PV
		declare @doanhthuPV table (id int identity(1,1), doanhthuPV decimal(15, 2))
		insert into @doanhthuPV
		select SUM(GIA + PHUTHU) as DT_PHIM
		from VEDAT VD join LOAIVE LV on VD.ID_LOAIVE = LV.ID_LOAIVE
					  join GHE G on G.ID_GHE = VD.ID_GHE
					  join LOAIGHE LG on LG.ID_LOAIGHE = G.ID_LOAIGHE
					  join PHONGCHIEU PC on PC.ID_PHONG = G.ID_PHONGCHIEU
					  join SUATCHIEU SC on SC.ID_SUAT = VD.ID_SUAT
		where YEAR(NGAYCHIEU) like @Nam
		select iif(doanhthuPV is NULL, 0, doanhthuPV) as DTPV, 
			   iif(doanhthuDA is NULL, 0, doanhthuDA) as DTDA, 
			   (iif(doanhthuDA is NULL, 0, doanhthuDA) + iif(doanhthuPV is NULL, 0, doanhthuPV)) as tongDoanhThu
		from @doanhthuDA DTDA join @doanhthuPV DTPV on DTDA.id = DTPV.id
end
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthutong_ngay]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE proc [dbo].[sp_doanhthutong_ngay] (@Ngay date)
as 
	begin
		declare @doanhthuDA table (id int identity(1,1), doanhthuDA decimal(15, 2))
		insert into @doanhthuDA
		select SUM(SOLUONG*GIA) as DT_DOAN 
		from DOANCT DACT join KICHCODOAN KCDA on DACT.ID_KCDA = KCDA.ID_KCDA
						 join DONTHANHTOAN DTT on DTT.ID_DONDA = DACT.ID_DONDA
		where NGAYDAT like @Ngay

		-- DOANH THU PV
		declare @doanhthuPV table (id int identity(1,1), doanhthuPV decimal(15, 2))
		insert into @doanhthuPV
		select SUM(GIA + PHUTHU) as DT_PHIM
		from VEDAT VD join LOAIVE LV on VD.ID_LOAIVE = LV.ID_LOAIVE
					  join GHE G on G.ID_GHE = VD.ID_GHE
					  join LOAIGHE LG on LG.ID_LOAIGHE = G.ID_LOAIGHE
					  join PHONGCHIEU PC on PC.ID_PHONG = G.ID_PHONGCHIEU
					  join SUATCHIEU SC on SC.ID_SUAT = VD.ID_SUAT
		where NGAYCHIEU like @Ngay
		select iif(doanhthuPV is NULL, 0, doanhthuPV) as DTPV, 
			   iif(doanhthuDA is NULL, 0, doanhthuDA) as DTDA, 
			   (iif(doanhthuDA is NULL, 0, doanhthuDA) + iif(doanhthuPV is NULL, 0, doanhthuPV)) as tongDoanhThu
		from @doanhthuDA DTDA join @doanhthuPV DTPV on DTDA.id = DTPV.id
end
GO
/****** Object:  StoredProcedure [dbo].[sp_doanhthutong_thang]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE proc [dbo].[sp_doanhthutong_thang] (@Thang int, @Nam int)
as 
	begin
				declare @doanhthuDA table (id int identity(1,1), doanhthuDA decimal(15, 2))
		insert into @doanhthuDA
		select SUM(SOLUONG*GIA) as DT_DOAN 
		from DOANCT DACT join KICHCODOAN KCDA on DACT.ID_KCDA = KCDA.ID_KCDA
						 join DONTHANHTOAN DTT on DTT.ID_DONDA = DACT.ID_DONDA
		where MONTH(NGAYDAT) like @Thang and YEAR(NGAYDAT) like @Nam

		-- DOANH THU PV
		declare @doanhthuPV table (id int identity(1,1), doanhthuPV decimal(15, 2))
		insert into @doanhthuPV
		select SUM(GIA + PHUTHU) as DT_PHIM
		from VEDAT VD join LOAIVE LV on VD.ID_LOAIVE = LV.ID_LOAIVE
					  join GHE G on G.ID_GHE = VD.ID_GHE
					  join LOAIGHE LG on LG.ID_LOAIGHE = G.ID_LOAIGHE
					  join PHONGCHIEU PC on PC.ID_PHONG = G.ID_PHONGCHIEU
					  join SUATCHIEU SC on SC.ID_SUAT = VD.ID_SUAT
		where MONTH(NGAYCHIEU) like @Thang and YEAR(NGAYCHIEU) like @Nam
		select iif(doanhthuPV is NULL, 0, doanhthuPV) as DTPV, 
			   iif(doanhthuDA is NULL, 0, doanhthuDA) as DTDA, 
			   (iif(doanhthuDA is NULL, 0, doanhthuDA) + iif(doanhthuPV is NULL, 0, doanhthuPV)) as tongDoanhThu
		from @doanhthuDA DTDA join @doanhthuPV DTPV on DTDA.id = DTPV.id
	end
GO
/****** Object:  StoredProcedure [dbo].[sp_donDoAn]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_donDoAn] (@id_dontt int)
as
begin
	select TEN, SOLUONG, ID_KICHCO, convert(float,sum(GIA)) as TONGTIEN
	from DONTHANHTOAN DTT join DONDOAN DDA on DTT.ID_DONDA = DDA.ID_DONDA
						  join DOANCT DACT on DACT.ID_DONDA = DDA.ID_DONDA
						  join KICHCODOAN KCDA on KCDA.ID_KCDA = DACT.ID_KCDA
						  join DOAN DA on DA.ID_DOAN = KCDA.ID_DOAN
	where ID_DONTT = @id_dontt
	group by TEN, SOLUONG, ID_KICHCO
end
GO
/****** Object:  StoredProcedure [dbo].[sp_donthanhtoan]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[sp_donthanhtoan] @NGAYBD DATE = NULL, @NGAYKT DATE = NULL, @THANG INT = NULL, @NAM INT = NULL, @ISKHACHHANG INT = -1, @KEYWORD NVARCHAR(100) = NULL
as
begin
	DECLARE @TABLE TABLE(ID_DONTT INT,NGAYDAT DATE, ID_TV NVARCHAR(16), TONG DECIMAL(15,3));
	insert @TABLE select ID_DONTT, NGAYDAT, TV.ID_TV, ROUND(TONG, 0) 
				  from DONTHANHTOAN DTT LEFT JOIN THANHVIEN TV ON DTT.ID_TV = TV.ID_TV
	WHERE ((@NGAYBD IS NULL AND @NGAYKT IS NULL) OR (
	cast(NGAYDAT as date) >= @NGAYBD AND cast(NGAYDAT as date) <= @NGAYKT))
		  AND (@THANG IS NULL OR MONTH(cast(NGAYDAT as date)) like @THANG) AND (@NAM IS NULL OR YEAR(cast(NGAYDAT as date)) like @NAM)
		  AND (@KEYWORD IS NULL OR (SODT LIKE @KEYWORD OR HOTEN LIKE @KEYWORD))
	ORDER BY ID_DONTT ASC

	IF(@ISKHACHHANG != -1)
	BEGIN
			declare @now date = getdate();
			if(@ISKHACHHANG = 0)	
			select * from @TABLE where ((NGAYDAT < @now ) or (NGAYDAT = @now)) and ID_TV IS NOT NULL order by NGAYDAT desc, ID_DONTT desc
			else select * from @TABLE where (NGAYDAT > @now) or (NGAYDAT = @now) and ID_TV IS NULL order by NGAYDAT desc, ID_DONTT desc
	END
	else select * from @TABLE
end
GO
/****** Object:  StoredProcedure [dbo].[sp_donThanhToan_KhachHang]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_donThanhToan_KhachHang](@loaiKH nvarchar(10))
as
begin
	declare @loaiKH_search varchar
	if @loaiKH like N'Thành viên'
		set @loaiKH_search = '%TV%'
	else
		set @loaiKH_search = 'NULL'
	select ID_DONTT, GIOBATDAU as SUATCHIEU, convert(varchar, NGAYDAT, 105) as NGAYDAT, THANHVIEN.ID_TV, TONG
	from DONTHANHTOAN JOIN DONVE ON DONTHANHTOAN.ID_DONVE = DONVE.ID_DONVE
					JOIN THANHVIEN ON THANHVIEN.ID_TV = DONTHANHTOAN.ID_TV
					JOIN VEDAT ON VEDAT.ID_DONVE = DONVE.ID_DONVE
					JOIN SUATCHIEU ON SUATCHIEU.ID_SUAT = VEDAT.ID_SUAT
	where THANHVIEN.ID_TV like @loaiKH_search
	GROUP BY  ID_DONTT, SUATCHIEU.GIOBATDAU,SUATCHIEU.NGAYCHIEU, NGAYDAT, TONG, THANHVIEN.ID_TV
end
GO
/****** Object:  StoredProcedure [dbo].[sp_donThanhToan_NgayMua]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_donThanhToan_NgayMua] (@ngayMua date)
as
begin
	select ID_DONTT, GIOBATDAU as SUATCHIEU, convert(varchar, NGAYDAT, 105) as NGAYDAT, THANHVIEN.ID_TV, TONG
	from DONTHANHTOAN JOIN DONVE ON DONTHANHTOAN.ID_DONVE = DONVE.ID_DONVE
					JOIN THANHVIEN ON THANHVIEN.ID_TV = DONTHANHTOAN.ID_TV
					JOIN VEDAT ON VEDAT.ID_DONVE = DONVE.ID_DONVE
					JOIN SUATCHIEU ON SUATCHIEU.ID_SUAT = VEDAT.ID_SUAT
	where NGAYDAT BETWEEN (convert(varchar, @ngayMua) +' 00:00:00') AND (convert(varchar, @ngayMua) + ' 23:59:59')
	GROUP BY  ID_DONTT, SUATCHIEU.GIOBATDAU,SUATCHIEU.NGAYCHIEU, NGAYDAT, TONG, THANHVIEN.ID_TV
end
GO
/****** Object:  StoredProcedure [dbo].[sp_donVe]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_donVe] (@id_dontt int)
as
begin
	select P.TEN, 
		   convert(varchar, GIOBATDAU, 0) + ' ' + convert(varchar, NGAYCHIEU, 105) as SUATCHIEU,
		   VDAT.ID_LOAIVE,
		   convert(float,(GIA + PHUTHU)) as GIA
	from DONTHANHTOAN DTT join DONVE DV on DTT.ID_DONVE = DV.ID_DONVE
						  join VEDAT VDAT on VDAT.ID_DONVE = DV.ID_DONVE
						  join SUATCHIEU SC on SC.ID_SUAT = VDAT.ID_SUAT
						  join PHIM P on P.ID_PHIM = SC.ID_PHIM
						  join LOAIVE LV on LV.ID_LOAIVE = VDAT.ID_LOAIVE
						  join GHE G on G.ID_GHE = VDAT.ID_GHE
						  join LOAIGHE LG on LG.ID_LOAIGHE = G.ID_LOAIGHE
	where ID_DONTT = @id_dontt
	group by ID_VEDAT, P.TEN, GIOBATDAU, NGAYCHIEU, VDAT.ID_LOAIVE, GIA, PHUTHU
end
GO
/****** Object:  StoredProcedure [dbo].[SP_FINDTIMESLOT]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROC [dbo].[SP_FINDTIMESLOT]  @NGAY DATE, @PHONG NCHAR(4), @GIOBD TIME, @GIOKT TIME
AS BEGIN
	select * from SUATCHIEU where ID_PHONG = @PHONG and ngaychieu = @ngay 
and (( GIOBATDAU between convert(time, @giobd) and convert(time, @gioKT)) or ( GIOBATDAU <= @gioKT ) AND  (GIOKETTHUC >= @giobd ))
END
GO
/****** Object:  StoredProcedure [dbo].[SP_GETTONGDON]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_GETTONGDON] @ID_DONTT INT, @TONGTIEN DECIMAL(9,3) OUTPUT
AS BEGIN
	DECLARE @ID_VE NVARCHAR(20) = (SELECT ID_DONVE FROM DONTHANHTOAN WHERE @ID_DONTT = ID_DONTT)
	DECLARE @ID_DONDA  NVARCHAR(20) = (SELECT ID_DONDA FROM DONTHANHTOAN WHERE @ID_DONTT = ID_DONTT)
	DECLARE @TONG DECIMAL(9,3) = 0

	IF(@ID_VE is NOT null) 
	BEGIN
		EXEC SP_TINHTONGDONVE @ID_VE, @TONG OUTPUT
	END
	IF(@ID_DONDA is NOT null) 
	BEGIN
		DECLARE @SUB DECIMAL(9,3) = 0
		EXEC SP_TINHTONGDONDA @ID_DONDA, @SUB OUTPUT
		SET @TONG = @TONG + @SUB
	END
	select @TONGTIEN =  @Tong
END
GO
/****** Object:  StoredProcedure [dbo].[SP_LICHCHIEUNGAY]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
  CREATE PROC [dbo].[SP_LICHCHIEUNGAY]  @NGAY DATE, @GIO TIME = NULL, @CONDITION TINYINT = 1, @PHIM NVARCHAR(15) = NULL
  AS BEGIN
  DECLARE @Lichchieu TABLE(
  ID_SUAT NVARCHAR(30),
  NGAYCHIEU DATE,
  ID_PHONG NCHAR(4),
  ID_PHIM NVARCHAR(15),
  GIOBATDAU TIME,
  GIOKETTHUC TIME,
  ID_NV NVARCHAR(10)
  )
  declare @maxNgay date =  DATEADD(day, 2, @NGAY)
	IF(@CONDITION = 1) BEGIN
		INSERT INTO @Lichchieu
					SELECT SC.* 
					 FROM PHIM P
					 JOIN SUATCHIEU SC ON SC.ID_PHIM = P.ID_PHIM
					 WHERE TRANGTHAI = 1 AND ( NGAYCHIEU >= @NGAY AND NGAYCHIEU <= @maxNgay)
					 GROUP BY SC.ID_PHIM, TEN,  SC.ID_SUAT, SC.NGAYCHIEU, SC.ID_PHONG, SC.GIOBATDAU, SC.GIOKETTHUC, SC.ID_NV
					 ORDER BY NGAYCHIEU, GIOBATDAU OFFSET 0 ROWS
					 END
	IF(@CONDITION = 0) BEGIN
	INSERT INTO @Lichchieu
				SELECT SC.* FROM PHIM P
				 JOIN SUATCHIEU SC ON SC.ID_PHIM = P.ID_PHIM
				 WHERE TRANGTHAI = 1 AND NGAYCHIEU = @NGAY
				 GROUP BY SC.ID_PHIM, TEN,  SC.ID_SUAT, SC.NGAYCHIEU, SC.ID_PHONG, SC.GIOBATDAU, SC.GIOKETTHUC, SC.ID_NV
				 ORDER BY NGAYCHIEU, GIOBATDAU OFFSET 0 ROWS
				 END
	IF(@CONDITION = -1) BEGIN
	INSERT INTO @Lichchieu
				SELECT SC.* FROM PHIM P
				 JOIN SUATCHIEU SC ON SC.ID_PHIM = P.ID_PHIM
				 WHERE TRANGTHAI = 1 AND NGAYCHIEU < @NGAY
				 GROUP BY SC.ID_PHIM, TEN,  SC.ID_SUAT, SC.NGAYCHIEU, SC.ID_PHONG, SC.GIOBATDAU, SC.GIOKETTHUC, SC.ID_NV
				 ORDER BY NGAYCHIEU, GIOBATDAU OFFSET 0 ROWS			 
				END

	IF(@GIO IS NOT NULL)
	BEGIN 
		IF(@CONDITION = 1)
		SELECT * FROM @Lichchieu WHERE (NGAYCHIEU = @NGAY and GIOBATDAU > @GIO) or(NGAYCHIEU > @NGAY)
		ELSE 
		SELECT * FROM @Lichchieu WHERE  GIOBATDAU > @GIO
	END
	ELSE IF(@PHIM IS NOT NULL)
	BEGIN
		SELECT * FROM @Lichchieu WHERE ID_PHIM = @PHIM
	END
	ELSE SELECT * FROM @Lichchieu
  END
GO
/****** Object:  StoredProcedure [dbo].[SP_LUONGVEBANCUASUAT]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROC [dbo].[SP_LUONGVEBANCUASUAT] @ID_SUAT NVARCHAR(30) 
AS BEGIN
	select count(*) LUONGVE from suatchieu sc join VEDAT vd on sc.ID_SUAT = vd.ID_SUAT where sc.ID_SUAT = @Id_suat
END
GO
/****** Object:  StoredProcedure [dbo].[sp_luongvedaban]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_luongvedaban]
as 
	begin
		declare @SoSuatChieu_Ve table(ngaychieu date, tongsuatchieu int)
		insert into @SoSuatChieu_Ve
		select NGAYCHIEU ,count(ID_SUAT) from SUATCHIEU group by NGAYCHIEU

		select SC.NGAYCHIEU, COUNT(ID_VEDAT) as sovedaban, tongsuatchieu from SUATCHIEU SC 
		left join VEDAT VD on SC.ID_SUAT = VD.ID_SUAT join @SoSuatChieu_Ve SSC on SSC.ngaychieu = SC.NGAYCHIEU
		group by SC.NGAYCHIEU, tongsuatchieu
	end
GO
/****** Object:  StoredProcedure [dbo].[sp_luongvedaban_sapxep]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_luongvedaban_sapxep]
as 
	begin
		declare @SoSuatChieu_Ve table(ngaychieu date, tongsuatchieu int)
		insert into @SoSuatChieu_Ve
		select NGAYCHIEU ,count(ID_SUAT) from SUATCHIEU group by NGAYCHIEU

		select SC.NGAYCHIEU, COUNT(ID_VEDAT) as sovedaban, tongsuatchieu from SUATCHIEU SC 
		left join VEDAT VD on SC.ID_SUAT = VD.ID_SUAT join @SoSuatChieu_Ve SSC on SSC.ngaychieu = SC.NGAYCHIEU
		group by SC.NGAYCHIEU, tongsuatchieu
		order by sovedaban desc
	end
GO
/****** Object:  StoredProcedure [dbo].[sp_luongvedaban_theonam]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_luongvedaban_theonam] (@Year int)
as 
	begin
		declare @SoSuatChieu_Ve table(ngaychieu date, tongsuatchieu int)
		insert into @SoSuatChieu_Ve
		select NGAYCHIEU ,count(ID_SUAT) from SUATCHIEU group by NGAYCHIEU

		select SC.NGAYCHIEU, COUNT(ID_VEDAT) as sovedaban, tongsuatchieu
		from SUATCHIEU SC left join VEDAT VD on SC.ID_SUAT = VD.ID_SUAT 
		join @SoSuatChieu_Ve SSC on SSC.ngaychieu = SC.NGAYCHIEU
		where YEAR(sc.NGAYCHIEU) like @Year
		group by SC.NGAYCHIEU, tongsuatchieu
	end
GO
/****** Object:  StoredProcedure [dbo].[sp_luongvedaban_theongay]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_luongvedaban_theongay] (@ngay varchar(20))
as 
	begin
		declare @SoSuatChieu_Ve table(ngaychieu date, tongsuatchieu int)
		insert into @SoSuatChieu_Ve
		select NGAYCHIEU ,count(ID_SUAT) from SUATCHIEU group by NGAYCHIEU

		select SC.NGAYCHIEU, COUNT(ID_VEDAT) as sovedaban, tongsuatchieu from SUATCHIEU SC 
		left join VEDAT VD on SC.ID_SUAT = VD.ID_SUAT 
		join @SoSuatChieu_Ve SSC on SSC.ngaychieu = SC.NGAYCHIEU
		where @ngay like SC.NGAYCHIEU
		group by SC.NGAYCHIEU, tongsuatchieu
	end
GO
/****** Object:  StoredProcedure [dbo].[sp_luongvedaban_theothangnam]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_luongvedaban_theothangnam] (@Thang int, @Year int)
as 
	begin
		declare @SoSuatChieu_Ve table(ngaychieu date, tongsuatchieu int)
		insert into @SoSuatChieu_Ve
		select NGAYCHIEU ,count(ID_SUAT) from SUATCHIEU group by NGAYCHIEU

		select SC.NGAYCHIEU, COUNT(ID_VEDAT) as sovedaban, tongsuatchieu
		from SUATCHIEU SC left join VEDAT VD on SC.ID_SUAT = VD.ID_SUAT 
		join @SoSuatChieu_Ve SSC on SSC.ngaychieu = SC.NGAYCHIEU
		where MONTH(SC.NGAYCHIEU) like @Thang and YEAR(sc.NGAYCHIEU) like @Year
		group by SC.NGAYCHIEU, tongsuatchieu
	end
GO
/****** Object:  StoredProcedure [dbo].[sp_qldoan]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_qldoan] 
as
begin
	select DOAN.ID_DOAN, DOAN.TEN, DOAN.ID_LOAI, KICHCODOAN.ID_KICHCO, KICHCODOAN.GIA
	from KICHCODOAN join DOAN on KICHCODOAN.ID_DOAN = DOAN.ID_DOAN
 end
GO
/****** Object:  StoredProcedure [dbo].[sp_qldonthanhtoan]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_qldonthanhtoan] 
as
begin
	select ID_DONTT, GIOBATDAU as SUATCHIEU, convert(varchar, NGAYDAT, 105) as NGAYDAT, THANHVIEN.ID_TV, TONG
	from DONTHANHTOAN JOIN DONVE ON DONTHANHTOAN.ID_DONVE = DONVE.ID_DONVE
					JOIN THANHVIEN ON THANHVIEN.ID_TV = DONTHANHTOAN.ID_TV
					JOIN VEDAT ON VEDAT.ID_DONVE = DONVE.ID_DONVE
					JOIN SUATCHIEU ON SUATCHIEU.ID_SUAT = VEDAT.ID_SUAT
	 GROUP BY  ID_DONTT, SUATCHIEU.GIOBATDAU,SUATCHIEU.NGAYCHIEU, NGAYDAT, TONG, THANHVIEN.ID_TV
end
GO
/****** Object:  StoredProcedure [dbo].[sp_qlsuatchieu]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_qlsuatchieu]
as 
	begin
SELECT PHONGCHIEU.ID_PHONG, PHIM.TEN, CONCAT(convert(varchar, GIOBATDAU, 0), '     ', NGAYCHIEU) as NgayGio
FROM PHONGCHIEU JOIN SUATCHIEU ON PHONGCHIEU.ID_PHONG = SUATCHIEU.ID_PHONG
				JOIN PHIM ON PHIM.ID_PHIM = SUATCHIEU.ID_PHIM
END
GO
/****** Object:  StoredProcedure [dbo].[sp_sapxepdoan]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_sapxepdoan]
as 
	begin
		select DOAN.TEN, SUM(DOANCT.SOLUONG) AS SOLUONG, KICHCODOAN.ID_KICHCO, (SUM(DOANCT.SOLUONG) * KICHCODOAN.GIA) AS THANHTIEN
	from KICHCODOAN join DOAN on KICHCODOAN.ID_DOAN = DOAN.ID_DOAN
					join DOANCT on DOANCT.ID_KCDA = KICHCODOAN.ID_KCDA				
GROUP BY  DOAN.TEN,KICHCODOAN.ID_KICHCO, KICHCODOAN.GIA
order by SOLUONG desc
end
GO
/****** Object:  StoredProcedure [dbo].[SP_SELECTLICH]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_SELECTLICH] @ID_PHIM nvarchar(15) = Null, @NGAYBD DATE = NULL, @NGAYKT DATE = NULL, @Phong nchar(4) = NULL, @ISCHUACHIEU INT = -1
as begin
	
	 DECLARE @TABLE TABLE(ID_SUAT NVARCHAR(30),NGAYCHIEU DATE, TEN NVARCHAR(MAX), PHONG NCHAR(4), THOILUONG SMALLINT, GIOBATDAU TIME, GIOKETTHUC TIME);
	insert @TABLE  select ID_SUAT, ngaychieu, p.TEN TENPHIM, ID_PHONG PHONG, THOILUONG , GIOBATDAU, GIOKETTHUC from SUATCHIEU sc join PHIM p on sc.ID_PHIM = p.ID_PHIM
	WHERE( @ID_PHIM IS NULL OR @ID_PHIM = SC.ID_PHIM) 
	AND ((@NGAYBD IS NULL AND @NGAYKT IS NULL) OR (NGAYCHIEU >= @NGAYBD AND NGAYCHIEU <= @NGAYKT) )
	AND (@PHONG IS NULL OR ID_PHONG = @Phong)
	ORDER BY sc.NGAYCHIEU ASC, TENPHIM asc

	IF(@ISCHUACHIEU != -1)
	BEGIN
			declare @now date = getdate();
			declare @timeNow time = CAST(GETdATE() AS TIME)
			if(@ISCHUACHIEU = 0)	
			select * from @TABLE where (NGAYCHIEU < @now ) or (NGAYCHIEU = @now and GIOBATDAU < @timeNow) order by NGAYCHIEU desc, TEN asc
			else select * from @TABLE where (NGAYCHIEU > @now) or (NGAYCHIEU = @now and GIOBATDAU > @timeNow) order by NGAYCHIEU desc, ten asc
	END
	else select * from @TABLE
end
GO
/****** Object:  StoredProcedure [dbo].[SP_SELECTLICHBYTRANGTHAI]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_SELECTLICHBYTRANGTHAI] @ID_PHIM NVARCHAR(15) NULL, @PHONG NCHAR(4) = NULL, @ISCHUACHIEU BIT = 1
as begin
	DECLARE @TABLE TABLE(ID_SUAT NVARCHAR(30),NGAYCHIEU DATE, TEN NVARCHAR(MAX), PHONG NCHAR(4), THOILUONG SMALLINT, GIOBATDAU TIME, GIOKETTHUC TIME);
	insert @TABLE EXEC SP_SELECTLICH @id_phim = @ID_PHIM, @PHONG = @PHONG

	declare @now date = getdate();
	declare @timeNow time = CAST(GETdATE() AS TIME)
	
	if(@ISCHUACHIEU = 0)	
	select * from @TABLE where (NGAYCHIEU < @now ) or (NGAYCHIEU = @now and GIOBATDAU < @timeNow)
	else select * from @TABLE where (NGAYCHIEU > @now) or (NGAYCHIEU = @now and GIOBATDAU > @timeNow)
end
GO
/****** Object:  StoredProcedure [dbo].[SP_SELECTLICHPHONG]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_SELECTLICHPHONG] @ID_PHONG NCHAR(4), @NGAY DATE, @GIO TIME 
as begin

	DECLARE @SUATCHUACHIEU INT = (SELECT COUNT(SC.ID_SUAT) from PHONGCHIEU pc join SUATCHIEU sc on pc.ID_PHONG = sc.ID_PHONG  join PHIM p on p.ID_PHIM = sc.ID_PHIM  where sc.ID_PHONG = @ID_PHONG and NGAYCHIEU = @NGAY and GIOBATDAU > @GIO)
	
	IF(@SUATCHUACHIEU IS NULL OR @SUATCHUACHIEU = 0) 
	begin
		select top 3 pc.ID_PHONG, TEN, GIOBATDAU, GIOKETTHUC from PHONGCHIEU pc join SUATCHIEU sc on pc.ID_PHONG = sc.ID_PHONG  join PHIM p on p.ID_PHIM = sc.ID_PHIM  where sc.ID_PHONG = @ID_PHONG and NGAYCHIEU = @NGAY and GIOBATDAU < @GIO order by GIOBATDAU desc
	end
	else
	begin
	select top 3 pc.ID_PHONG, TEN, GIOBATDAU, GIOKETTHUC from PHONGCHIEU pc join SUATCHIEU sc on pc.ID_PHONG = sc.ID_PHONG  join PHIM p on p.ID_PHIM = sc.ID_PHIM  where sc.ID_PHONG = @ID_PHONG and NGAYCHIEU = @NGAY and GIOBATDAU > @GIO order by GIOBATDAU 
	end
	
END
GO
/****** Object:  StoredProcedure [dbo].[sp_suaDoAn]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_suaDoAn] (@tenDA nvarchar(50), @id_loaiDA varchar(2), @id_kichCo varchar(1), @gia float, @id_DoAn varchar(10), @id_KCDA int)
as
	begin
		update DOAN		
		set TEN = @tenDA, ID_LOAI = @id_loaiDA
		where ID_DOAN like @id_DoAn
		update KICHCODOAN
		set ID_KICHCO = @id_kichCo, GIA = @gia
		where ID_KCDA like @id_KCDA
	end
GO
/****** Object:  StoredProcedure [dbo].[SP_SUATDACHIEUCUAPHONG]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROC [dbo].[SP_SUATDACHIEUCUAPHONG] @ID_PHONG NCHAR(4), @NGAY DATE, @GIO TIME 
AS BEGIN
	DECLARE @TONG INT = (select  count(*) from PHONGCHIEU pc join SUATCHIEU sc on pc.ID_PHONG = sc.ID_PHONG where NGAYCHIEU = @NGAY and sc.ID_PHONG = @ID_PHONG)
	DECLARE @DACHIEU INT = (select count(*)  from PHONGCHIEU pc join SUATCHIEU sc on pc.ID_PHONG = sc.ID_PHONG where NGAYCHIEU = @NGAY and sc.ID_PHONG = @ID_PHONG and GIOBATDAU <= @GIO)

	select  @DACHIEU as dachieu, @tong as tongsuat
END
GO
/****** Object:  StoredProcedure [dbo].[sp_themDoAn]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_themDoAn] (@tenDA nvarchar(50), @id_loaiDA varchar(2), @id_kichCo varchar(1), @gia float)
as
	begin
		if (select count(*) from DOAN where TEN like @tenDA and ID_LOAI like @id_loaiDA) = 0
		begin
			insert into DOAN(TEN, ID_LOAI)
			values (@tenDA, @id_loaiDA)
		end

		declare @id_da varchar(10)

		select @id_da = (select ID_DOAN from DOAN where TEN like @tenDA and ID_LOAI like @id_loaiDA)

		insert into KICHCODOAN(ID_DOAN, ID_KICHCO, GIA)
		values (@id_da, @id_kichCo, @gia)
	end
GO
/****** Object:  StoredProcedure [dbo].[SP_THEMDON]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROC [dbo].[SP_THEMDON] @ISDONVE BIT = 1
AS BEGIN
	IF(@ISDONVE = 1)
	INSERT INTO DONVE VALUES('')
	ELSE INSERT INTO DONDOAN VALUES('')
END
GO
/****** Object:  StoredProcedure [dbo].[sp_themdonOf]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_themdonOf] @id_donthanhtoan INT, @ISDONVE BIT, 
	@ID_Don nvarchar(20) output
as begin
	IF(@ISDONVE = 1) 
	BEGIN
		EXEC SP_THEMDON 1
		DECLARE @ID_DONVE NVARCHAR(20) = (select TOP 1 * from DONVE ORDER BY ID_DONVE DESC)
		select @ID_don = @ID_DONVE
	END
	ELSE
	BEGIN
		EXEC SP_THEMDON 0
		DECLARE @ID_DONDA NVARCHAR(20) = (select TOP 1 * from DONDOAN ORDER BY ID_DONDA DESC)
		select @ID_don = @ID_DONDA
	END
end
GO
/****** Object:  StoredProcedure [dbo].[sp_themdontt]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
  create proc [dbo].[sp_themdontt] @loaiDon int = 0, @id_tv nvarchar(16) = null, @id_nv nvarchar(10) = null
as begin
	DECLARE @MYDON TABLE (IDDONTT INT ,DONDA NVARCHAR(20), DONVE NVARCHAR(20))
	 insert into DONTHANHTOAN (ID_TV, ID_NV, NGAYDAT,TONG) VALUES
     (@id_tv,@id_nv,GETDATE(), 0)

	 declare @ID_DONTT INT
	 declare @id_ve nvarchar(20) = NULL
	 declare @id_da nvarchar(20) = NULL

	 SET @ID_DONTT = ( SELECT top 1 ID_DONTT from DONTHANHTOAN order by ID_DONTT desc)
	 INSERT INTO @MYDON(IDDONTT) VALUES(@ID_DONTT)
	 ---
	 ---don ve
	 IF(@loaiDon = 0)
	 BEGIN
		exec sp_themdonOf @ID_DONTT,1, @id_ve output
	 END
	 --don do an
	 ELSE IF(@loaiDon = 1)
	 BEGIN 
		exec sp_themdonOf @ID_DONTT,0, @id_da output
	 END
	 --ca 2
	 ELSE
	 BEGIN
		 exec sp_themdonOf @ID_DONTT,1, @id_ve output
		 exec sp_themdonOf @ID_DONTT,0, @id_da output 
		
	 END
	 UPDATE @MYDON SET DONVE = @ID_ve, DONDA = @id_da WHERE IDDONTT = @ID_DONTT
	 
	 update DONTHANHTOAN set ID_DONDA = @id_da, ID_DONVE = @id_ve where ID_DONTT = @ID_DONTT
	 SELECT * FROM @MYDON
end
GO
/****** Object:  StoredProcedure [dbo].[sp_thongke_khunggio]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_thongke_khunggio] (@khoangcach int, @ngay date)
as
	begin
		declare @table1 table (stt int identity(1,1), khunggio varchar(15))
		insert into @table1
		select * from dbo.fn_KhungGio('7:00', @khoangcach)

		declare @table2 table (stt int identity(1,1), sosuatchieu int, sovedat int)
		
		declare @dem int = 1
		while @dem <= (select count(*) from dbo.fn_KhungGio('7:00', @khoangcach))
		begin
			declare @khunggio varchar(15) = (select khunggio from @table1 where stt = @dem)
			insert into @table2
			select (select count(ID_SUAT) from SUATCHIEU
					where (GIOBATDAU >= convert(time, left(@khunggio,5)) and GIOBATDAU < convert(time, right(@khunggio,5))) and NGAYCHIEU like @ngay) as SOSUATCHIEU,
					count(ID_VEDAT) as SOVEDAT from SUATCHIEU SC join VEDAT VD on SC.ID_SUAT = VD.ID_SUAT
			where (GIOBATDAU >= convert(time, left(@khunggio,5)) and GIOBATDAU < convert(time, right(@khunggio,5))) and NGAYCHIEU like @ngay
			set @dem = @dem + 1
		end
		select khunggio, sosuatchieu, sovedat from @table1 TB1 join @table2 TB2 on TB1.stt = TB2.stt
	end
GO
/****** Object:  StoredProcedure [dbo].[SP_TINHTONGDONDA]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROC [dbo].[SP_TINHTONGDONDA] @ID_DONDA NVARCHAR(20), @TONG DECIMAL(9,3) OUTPUT
AS BEGIN
	DECLARE @TONGDON DECIMAL(9,3) = 0
	set @TONGDON = (select sum(sub.tong) as tong from (SELECT DDA.ID_DONDA, CT.ID_KCDA, KC.ID_DOAN, KC.GIA, CT.SOLUONG, (KC.GIA * CT.SOLUONG)TONG FROM DONDOAN DDA JOIN DOANCT CT ON DDA.ID_DONDA = CT.ID_DONDA JOIN KICHCODOAN KC ON KC.ID_KCDA = CT.ID_KCDA where DDA.ID_DONDA = @ID_DONDA ) as sub)
	select @TONG = @TONGDON
END
GO
/****** Object:  StoredProcedure [dbo].[SP_TINHTONGDONVE]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROC [dbo].[SP_TINHTONGDONVE] @ID_DONVE NVARCHAR(20), @TONG DECIMAL(9,3) OUTPUT
AS BEGIN
	DECLARE @TONGDON DECIMAL(9,3) = 0
	---TINH TONG CAC GHE TRONG DONVE
	SET @TONGDON = (SELECT SUM(GHEDON.TONGTIEN) TONG FROM 
					(SELECT V.ID_GHE,V.ID_LOAIVE,GIA, PHUTHU, (GIA+PHUTHU)TONGTIEN from VEDAT V
						JOIN LOAIVE LV ON V.ID_LOAIVE = LV.ID_LOAIVE  JOIN GHE G ON V.ID_GHE = G.ID_GHE
						JOIN LOAIGHE LG ON LG.ID_LOAIGHE = G.ID_LOAIGHE WHERE ID_DONVE = @ID_DONVE) AS GHEDON)
	---
	SELECT @TONG = @TONGDON
END
GO
/****** Object:  StoredProcedure [dbo].[sp_tracuulichchieu]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create proc [dbo].[sp_tracuulichchieu] 
as
begin
	declare @stt table (stt int identity(1,1), idsuat nvarchar(50) )
	insert into @stt
	select ID_SUAT from SUATCHIEU

	select stt, sc.ID_SUAT,p.TEN,sc.ID_PHONG,sc.GIOBATDAU,p.TRANGTHAI,sc.NGAYCHIEU
	from SUATCHIEU sc join PHIM p on sc.ID_PHIM=p.ID_PHIM join @stt s on s.idsuat = sc.ID_SUAT
 end
GO
/****** Object:  StoredProcedure [dbo].[SP_UPDATETONGDON]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE PROC [dbo].[SP_UPDATETONGDON] @ID_DONTT INT
AS BEGIN
	DECLARE @T DECIMAL(9,3)
	EXEC SP_GETTONGDON @ID_DONTT, @T OUTPUT
	UPDATE DONTHANHTOAN SET TONG = @T WHERE ID_DONTT =  @ID_DONTT
END
GO
/****** Object:  Trigger [dbo].[tg_doan]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- create trigger before insert

create trigger [dbo].[tg_doan] on [dbo].[DOAN]
INSTEAD OF INSERT 
AS BEGIN
	SET NOCOUNT ON;
	DECLARE @LDA NVARCHAR(50) = (SELECT ID_LOAI  FROM INSERTED)
	DECLARE @TENDA NVARCHAR(100) = (SELECT TEN  FROM INSERTED)
	--SO ID GAN NHAT DUOC THEM VAO TRONG BANG
	DECLARE @PRE_IDDA NVARCHAR(16) = (SELECT TOP 1 CAST(REPLACE(ID_DOAN,left(ID_DOAN,2),'') AS INT)  IDoan FROM DOAN WHERE ID_LOAI LIKE @LDA ORDER BY IDoan desc)
	DECLARE @NEXT INT

	-- NEU LA RECORD DAU TIEN SO LA 1 , NEU KHONG THI ++
	IF(@PRE_IDDA IS NULL) SET @NEXT = 1 
	ELSE SET @NEXT = @PRE_IDDA + 1 
			
	-- NEXT ID
	DECLARE @DA_ID NVARCHAR(16) =  @LDA + RIGHT('0000'+CONVERT(NVARCHAR(10),@NEXT),5)
										
	-- INSERT														
	INSERT INTO DOAN VALUES(@DA_ID, @TENDA, @LDA)

END
GO
ALTER TABLE [dbo].[DOAN] ENABLE TRIGGER [tg_doan]
GO
/****** Object:  Trigger [dbo].[tg_xoaDoAn]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create trigger [dbo].[tg_xoaDoAn] on [dbo].[DOAN] instead of delete
as
	begin
		delete from KICHCODOAN where ID_DOAN like (select ID_DOAN from deleted)
		delete from DOAN where ID_DOAN like (select ID_DOAN from deleted)		
	end
GO
ALTER TABLE [dbo].[DOAN] ENABLE TRIGGER [tg_xoaDoAn]
GO
/****** Object:  Trigger [dbo].[tg_DonDoan]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- create trigger before insert

create trigger [dbo].[tg_DonDoan] on [dbo].[DONDOAN]
INSTEAD OF INSERT 
AS BEGIN
	SET NOCOUNT ON;
	DECLARE @ID_DONDA NVARCHAR(50) = (SELECT ID_DONDA FROM INSERTED)


	--SO ID GAN NHAT DUOC THEM VAO TRONG BANG
	DECLARE @PRE_IDDA NVARCHAR(16) = (SELECT TOP 1 CAST(REPLACE(ID_DONDA,left(ID_DONDA,2),'') AS INT) IDDoan FROM DONDOAN ORDER BY IDDoan desc)
	DECLARE @NEXT INT

	-- NEU LA RECORD DAU TIEN SO LA 1 , NEU KHONG THI ++
	IF(@PRE_IDDA IS NULL) SET @NEXT = 1 
	ELSE SET @NEXT = @PRE_IDDA + 1 
			
	-- NEXT ID
	DECLARE @DA_ID NVARCHAR(50) = 'DA'+ RIGHT('0000'+CONVERT(NVARCHAR(10),@NEXT),5)
										
	-- INSERT														
	INSERT INTO DONDOAN VALUES(@DA_ID)

END
GO
ALTER TABLE [dbo].[DONDOAN] ENABLE TRIGGER [tg_DonDoan]
GO
/****** Object:  Trigger [dbo].[tg_xoa_donThanhToan]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create trigger [dbo].[tg_xoa_donThanhToan] on [dbo].[DONTHANHTOAN] instead of delete
as
	begin
		delete from DONTHANHTOAN where ID_DONTT like (select ID_DONTT from deleted)
		delete from VEDAT where ID_DONVE like (select ID_DONVE from deleted)
		delete from DONVE where ID_DONVE like (select ID_DONVE from deleted)
		delete from DOANCT where ID_DONDA like (select ID_DONDA from deleted)
		delete from DONDOAN where ID_DONDA like (select ID_DONDA from deleted)	
	end
GO
ALTER TABLE [dbo].[DONTHANHTOAN] ENABLE TRIGGER [tg_xoa_donThanhToan]
GO
/****** Object:  Trigger [dbo].[tg_donve]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TRIGGER [dbo].[tg_donve] ON [dbo].[DONVE]
INSTEAD OF INSERT
AS BEGIN
	SET NOCOUNT ON

	--SO ID GAN NHAT DUOC THEM VAO TRONG BANG
	DECLARE @PRE_ID NVARCHAR(16) = (SELECT TOP 1 CAST(REPLACE(ID_DONVE,left(ID_DONVE,2),'') AS INT)  IDN FROM DONVE ORDER BY IDN DESC)
	DECLARE @NEXT INT

	-- NEU LA RECORD DAU TIEN SO LA 1 HOAC @PRE_ID + 1
	IF(@PRE_ID IS NULL) SET @NEXT = 1 
	ELSE SET @NEXT = @PRE_ID + 1

	--TAO ID TU TANG CHO HANG CHUAN BI THEM 
	 DECLARE @CUR_ID NVARCHAR(16) = ('DV' + RIGHT('00000'+CAST((@NEXT) AS NVARCHAR(5)),5))

	 	 --INSERT
	  INSERT INTO DONVE VALUES(@CUR_ID)

END
GO
ALTER TABLE [dbo].[DONVE] ENABLE TRIGGER [tg_donve]
GO
/****** Object:  Trigger [dbo].[tg_ghe]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- create trigger before insert GHẾ
create trigger [dbo].[tg_ghe] on [dbo].[GHE]
INSTEAD OF INSERT 
AS BEGIN
	SET NOCOUNT ON;
	DECLARE @VITRIDAY TINYINT = (SELECT VITRIDAY FROM INSERTED)
	DECLARE @VITRIDAY_CHAR NCHAR(1) = CHAR((SELECT VITRIDAY FROM INSERTED) + 64)
	DECLARE @VITRICOT NCHAR(2) = (SELECT VITRICOT FROM INSERTED)
	DECLARE @ID_PHONGCHIEU NCHAR(4) = (SELECT ID_PHONGCHIEU FROM INSERTED)
	DECLARE @ID_LOAIGHE NCHAR(2) = (SELECT ID_LOAIGHE FROM INSERTED)
	--SO ID GAN NHAT DUOC THEM VAO TRONG BANG
	DECLARE @PRE_ID NVARCHAR(16) = (SELECT TOP 1 CAST(REPLACE(ID_GHE, left(ID_GHE,5),'') AS INT) IDN FROM GHE ORDER BY IDN desc)
	DECLARE @NEXT INT

	-- NEU LA RECORD DAU TIEN SO LA 1 HOAC @PRE_ID + 1
	IF(@PRE_ID IS NULL) SET @NEXT = 1
	ELSE SET @NEXT = @PRE_ID + 1
	-- NEXT ID 
	IF LEN(@VITRICOT) <= 1
		SET @VITRICOT = '0' + @VITRICOT
	DECLARE @CUR_ID NVARCHAR(16) = ('GS' + RIGHT(@ID_PHONGCHIEU,2) + @VITRIDAY_CHAR + @VITRICOT)
	-- INSERT										
	INSERT INTO GHE VALUES(@CUR_ID, @VITRIDAY, @VITRICOT, @ID_PHONGCHIEU, @ID_LOAIGHE)
END
GO
ALTER TABLE [dbo].[GHE] ENABLE TRIGGER [tg_ghe]
GO
/****** Object:  Trigger [dbo].[tg_xoaKCDA]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- create trigger before insert phim
create trigger [dbo].[tg_xoaKCDA] on [dbo].[KICHCODOAN] instead of delete
as
	begin
		declare @idKC int = (select ID_KCDA from deleted)
		declare @table table (id_doanct int, id_doan nvarchar(16), id_loai nvarchar(4), 
								tenloai nvarchar(100), tenda nvarchar(100), id_kichco nchar(1), gia decimal(6,2))

		insert @table select ID_DOANCT, DA.ID_DOAN, ID_LOAI, LDA.TEN, DA.TEN, KCDA.ID_KICHCO, GIA
		from DOANCT DACT left join KICHCODOAN KCDA on DACT.ID_KCDA = KCDA.ID_KCDA
				 left join DOAN DA on DA.ID_DOAN = KCDA.ID_DOAN
				 left join LOAIDOAN LDA on LDA.ID = DA.ID_LOAI
		where DACT.ID_KCDA like @idKC
		-- them do an vao bang tam
		insert into KCDAHISTORY select * from @table
		-- xoa do an
		delete from KICHCODOAN where ID_KCDA like @idKC
		--if (select count(*) from KICHCODOAN where ID_DOAN like (select ID_DOAN from deleted where ID_KCDA like @idKC)) = 0
		--	delete from DOAN where ID_DOAN like (select ID_DOAN from deleted where ID_KCDA like @idKC)
	end
GO
ALTER TABLE [dbo].[KICHCODOAN] ENABLE TRIGGER [tg_xoaKCDA]
GO
/****** Object:  Trigger [dbo].[tg_nv]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
create trigger [dbo].[tg_nv] on [dbo].[NHANVIEN]
INSTEAD OF INSERT 
AS BEGIN
	SET NOCOUNT ON;
	DECLARE @HOTEN NVARCHAR(50) = (SELECT HOTEN FROM inserted)
	DECLARE @NGAYSINH DATE =(SELECT NGAYSINH FROM inserted)
	DECLARE @GIOITINH BIT = (SELECT GIOITINH FROM inserted)
	DECLARE @SODT NVARCHAR(15) = (SELECT SODT FROM inserted)
	DECLARE @EMAIL NVARCHAR(50) = (SELECT EMAIL FROM INSERTED)
	DECLARE @TENTK NVARCHAR(30) = (SELECT TENTK FROM INSERTED)
	DECLARE @MATKHAU NVARCHAR(15) = (SELECT MATKHAU FROM INSERTED)
	DECLARE @VT BIT = (SELECT VAITRO FROM INSERTED)
	DECLARE @ANH NVARCHAR(100) = (SELECT ANH FROM inserted)
	--SO ID GAN NHAT DUOC THEM VAO TRONG BANG
	DECLARE @PRE_ID NVARCHAR(16) = (SELECT TOP 1 CAST(REPLACE(ID_NV,left(ID_NV,2),'') AS INT)  IDN FROM NHANVIEN WHERE VAITRO LIKE @VT ORDER BY IDN DESC)
	DECLARE @NEXT INT

	-- NEU LA RECORD DAU TIEN SO LA 1 HOAC @PRE_ID + 1
	IF(@PRE_ID IS NULL) SET @NEXT = 1 
	ELSE SET @NEXT = @PRE_ID + 1 
	-- TAO ID TU TANG CHO HANG CHUAN BI THEM
	DECLARE @CUR_ID NVARCHAR(16) = CASE WHEN @VT = 1 THEN ('QL' + RIGHT('0000'+CONVERT(NVARCHAR(10),@NEXT),5)) 
										WHEN @VT = 0 THEN ('NV'+ RIGHT('0000'+CONVERT(NVARCHAR(10),@NEXT),5)) END

	-- INSERT														
	INSERT INTO NHANVIEN VALUES(@CUR_ID, @HOTEN, @NGAYSINH, @GIOITINH, @SODT, @EMAIL,@TENTK,@MATKHAU,@ANH,@VT)

END
GO
ALTER TABLE [dbo].[NHANVIEN] ENABLE TRIGGER [tg_nv]
GO
/****** Object:  Trigger [dbo].[tg_xoa_nv]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create trigger [dbo].[tg_xoa_nv] on [dbo].[NHANVIEN] instead of delete
as
	begin		
		update PHIM set ID_NV = NULL where ID_NV like (select ID_NV from deleted)
		update DONTHANHTOAN set ID_NV = NULL where ID_NV like (select ID_NV from deleted)
		delete from NHANVIEN where ID_NV like  (select ID_NV from deleted)
	end
GO
ALTER TABLE [dbo].[NHANVIEN] ENABLE TRIGGER [tg_xoa_nv]
GO
/****** Object:  Trigger [dbo].[tg_phim]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- create trigger before insert phim
create trigger [dbo].[tg_phim] on [dbo].[PHIM]
INSTEAD OF INSERT 
AS BEGIN
	SET NOCOUNT ON;
	DECLARE @TEN NVARCHAR(50) = (SELECT TEN FROM INSERTED)
	DECLARE @THOILUONG SMALLINT = (SELECT THOILUONG FROM INSERTED)
	DECLARE @THELOAI NVARCHAR(50) = (SELECT THELOAI FROM INSERTED)
	DECLARE @NGONNGU NVARCHAR(20) = (SELECT NGONNGU FROM INSERTED)
	DECLARE @NGAY_KC DATE = (SELECT NGAY_KC FROM INSERTED)
	DECLARE @TOMTAT NVARCHAR(1024) = (SELECT TOMTAT FROM INSERTED)
	DECLARE @TRANGTHAI BIT = (SELECT TRANGTHAI FROM INSERTED)
	DECLARE @ID_NV NVARCHAR(10) = (SELECT ID_NV FROM INSERTED)
	DECLARE @POSTER NVARCHAR(100) = (SELECT POSTER FROM inserted)
	--SO ID GAN NHAT DUOC THEM VAO TRONG BANG
	DECLARE @PRE_ID NVARCHAR(16) = (SELECT TOP 1 CAST(REPLACE(ID_PHIM,left(ID_PHIM,2),'') AS INT) IDN FROM PHIM ORDER BY IDN desc)
	DECLARE @NEXT INT

	-- NEU LA RECORD DAU TIEN SO LA 1 HOAC @PRE_ID + 1
	IF(@PRE_ID IS NULL) SET @NEXT = 1
	ELSE SET @NEXT = @PRE_ID + 1
	-- NEXT ID
	DECLARE @CUR_ID NVARCHAR(16) = ('FI' + RIGHT('0000'+CONVERT(NVARCHAR(10),@NEXT),5))
	-- INSERT										
	INSERT INTO PHIM VALUES(@CUR_ID, @TEN, @POSTER ,@THOILUONG, @THELOAI, @NGONNGU, @NGAY_KC, @TOMTAT, @TRANGTHAI, @ID_NV)
END
GO
ALTER TABLE [dbo].[PHIM] ENABLE TRIGGER [tg_phim]
GO
/****** Object:  Trigger [dbo].[tg_pc]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

create trigger [dbo].[tg_pc] on [dbo].[PHONGCHIEU]
INSTEAD OF  INSERT
AS BEGIN
	SET NOCOUNT ON
	DECLARE @DAY INT = (SELECT SOLUONGDAY FROM inserted)
	DECLARE @COT INT = (SELECT SOLUONGCOT FROM inserted)
	DECLARE @PRE_ID NVARCHAR(16) = (SELECT TOP 1 CAST(REPLACE(ID_PHONG,LEFT(ID_PHONG,1),'') AS INT) ID  FROM PHONGCHIEU ORDER BY ID DESC)
	-- NEU LA RECORD DAU TIEN SO LA 1 HOAC @PRE_ID + 1
	DECLARE @NEXT NVARCHAR(16)
	IF(@PRE_ID IS NULL) SET @NEXT = 1 
	ELSE SET @NEXT = @PRE_ID + 1 
	  
	--TAO ID TU TANG CHO HANG CHUAN BI THEM
	  DECLARE @CUR_ID NVARCHAR(16) = ('P' + RIGHT('000'+CAST((@NEXT) AS NVARCHAR(5)),3))
	 --INSERT
	  INSERT INTO PHONGCHIEU VALUES(@CUR_ID,@DAY,@COT)
END
GO
ALTER TABLE [dbo].[PHONGCHIEU] ENABLE TRIGGER [tg_pc]
GO
/****** Object:  Trigger [dbo].[tg_sc]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO

CREATE TRIGGER [dbo].[tg_sc] ON [dbo].[SUATCHIEU]
INSTEAD OF INSERT
AS BEGIN
	SET NOCOUNT ON
	DECLARE @NGAYCHIEU DATE = (SELECT NGAYCHIEU FROM inserted)
	DECLARE @ID_PHONG NCHAR(4) = (SELECT ID_PHONG FROM inserted)
	DECLARE @ID_PHIM NVARCHAR(15) = (SELECT ID_PHIM FROM inserted)
	DECLARE @GIOBATDAU TIME = (SELECT GIOBATDAU FROM inserted)
	DECLARE @GIOKETTHUC TIME = (SELECT GIOKETTHUC FROM inserted)
	DECLARE @ID_NV NVARCHAR(10) = (SELECT ID_NV FROM inserted)

		--SO ID GAN NHAT DUOC THEM VAO TRONG BANG
	DECLARE @PRE_ID NVARCHAR(16) = (SELECT TOP 1 CAST(REPLACE(ID_SUAT,left(ID_SUAT,2),'') AS INT)  IDN FROM SUATCHIEU ORDER BY IDN DESC)
	DECLARE @NEXT INT

	-- NEU LA RECORD DAU TIEN SO LA 1 HOAC @PRE_ID + 1
	IF(@PRE_ID IS NULL) SET @NEXT = 1 
	ELSE SET @NEXT = @PRE_ID + 1

	--TAO ID TU TANG CHO HANG CHUAN BI THEM 
	 DECLARE @CUR_ID NVARCHAR(16) = ('SC' + RIGHT('00000'+CAST((@NEXT) AS NVARCHAR(5)),5))

	 	 --INSERT
	  INSERT INTO SUATCHIEU VALUES(@CUR_ID,@NGAYCHIEU,@ID_PHONG,@ID_PHIM,@GIOBATDAU,@GIOKETTHUC,@ID_NV)

END
GO
ALTER TABLE [dbo].[SUATCHIEU] ENABLE TRIGGER [tg_sc]
GO
/****** Object:  Trigger [dbo].[tg_tv]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[tg_tv] ON [dbo].[THANHVIEN]
INSTEAD OF INSERT
AS BEGIN
	SET NOCOUNT ON
	DECLARE @HOTEN NVARCHAR(50) = (SELECT HOTEN FROM inserted)
	DECLARE @NGAYSINH DATE =(SELECT NGAYSINH FROM inserted)
	DECLARE @GIOITINH BIT = (SELECT GIOITINH FROM inserted)
	DECLARE @SODT NVARCHAR(15) = (SELECT SODT FROM inserted)
	DECLARE @EMAIL NVARCHAR(50) = (SELECT EMAIL FROM inserted)
	DECLARE @MAKH NVARCHAR(50)
	
	--SO ID GAN NHAT DUOC THEM VAO TRONG BANG
	DECLARE @PRE_ID NVARCHAR(16) = (SELECT TOP 1 CAST(REPLACE(ID_TV,left(ID_TV,2),'') AS INT)  IDN FROM THANHVIEN ORDER BY IDN DESC)
	DECLARE @NEXT INT

	-- NEU LA RECORD DAU TIEN SO LA 1 HOAC @PRE_ID + 1
	IF(@PRE_ID IS NULL) SET @NEXT = 1 
	ELSE SET @NEXT = @PRE_ID + 1

	--TAO ID TU TANG CHO HANG CHUAN BI THEM 
	 DECLARE @CUR_ID NVARCHAR(16) = ('TV' + RIGHT('00000'+CAST((@NEXT) AS NVARCHAR(5)),5))
	 SET @MAKH = (SELECT DBO.FN_MAKH(@CUR_ID))
	 	 --INSERT
	 INSERT INTO THANHVIEN VALUES(@CUR_ID,@HOTEN,@NGAYSINH,@GIOITINH,@SODT,@EMAIL, @MAKH)

END
GO
ALTER TABLE [dbo].[THANHVIEN] ENABLE TRIGGER [tg_tv]
GO
/****** Object:  Trigger [dbo].[tg_xoavedat]    Script Date: 21/04/2022 21:32:26 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- create trigger before insert phim
create trigger [dbo].[tg_xoavedat] on [dbo].[VEDAT]
AFTER DELETE 
AS BEGIN
	DECLARE @T TABLE(ID_DONVE NVARCHAR(20), LUONGVE INT )
	-- DECLARE @ID_DONVE NVARCHAR(20) = (SELECT TOP 1 ID_DONVE FROM deleted)
	INSERT @T(ID_DONVE) SELECT ID_DONVE FROM deleted group by ID_DONVE;
	--DECLARE @VEDAT_NUM INT = (SELECT COUNT(*) FROM VEDAT WHERE ID_DONVE = @ID_DONVE)
	
	UPDATE @T SET T1.LUONGVE = T2.LUONGVE
	from @T AS T1 INNER JOIN 
	(SELECT ID_DONVE, COUNT(ID_VEDAT) AS LUONGVE FROM VEDAT WHERE ID_DONVE IN (SELECT ID_DONVE FROM @T) GROUP BY ID_DONVE)  AS T2
	ON T1.ID_DONVE = T2.ID_DONVE

	DELETE FROM DONVE WHERE ID_DONVE IN (SELECT ID_DONVE FROM @T WHERE LUONGVE is null or LUONGVE = 0)
	SELECT * FROM @T
	--PRINT 'LUONG VE TRONG DON: ' + CAST(@VEdAT_NUM AS NVARCHAR(40)) + ' DONVE: ' + @ID_DONVE
END
GO
ALTER TABLE [dbo].[VEDAT] ENABLE TRIGGER [tg_xoavedat]
GO
USE [master]
GO
ALTER DATABASE [CINEBOOST] SET  READ_WRITE 
GO
