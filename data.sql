USE [CovidApp]
GO
INSERT [dbo].[con_nguoi] ([cmnd], [dia_chi], [gioi_tinh], [ho_ten], [ngay_sinh], [sdt]) VALUES (N'123', N'25 đường số 7, Linh Tây, Thủ Đức, HCM', N'Nam', N'Nguyễn Văn A', N'03/04/2022', N'0376749')
INSERT [dbo].[con_nguoi] ([cmnd], [dia_chi], [gioi_tinh], [ho_ten], [ngay_sinh], [sdt]) VALUES (N'1234', N'19 đường số 7, Linh Tây, Thủ Đức, HCM', N'Nam', N'Phạm Tiến D', N'03/04/2022', N'0376749')
INSERT [dbo].[con_nguoi] ([cmnd], [dia_chi], [gioi_tinh], [ho_ten], [ngay_sinh], [sdt]) VALUES (N'12345', N'25 đường số 8, Linh Trung, Thủ Đức, Hồ Chí Minh', N'Nam', N'Nguyễn B', N'03/04/2022', N'0376749')
INSERT [dbo].[con_nguoi] ([cmnd], [dia_chi], [gioi_tinh], [ho_ten], [ngay_sinh], [sdt]) VALUES (N'123456789', N'20 đường số 7, Linh Tây, Thủ Đức, HCM', N'Nam', N'Nguyễn Văn H', N'29/4/2019', N'036451')
INSERT [dbo].[con_nguoi] ([cmnd], [dia_chi], [gioi_tinh], [ho_ten], [ngay_sinh], [sdt]) VALUES (N'251212694', N'19 đường số 7, Linh Tây, Thử Đức, Hồ Chí Minh', N'Nam', N'Nguyễn Văn Duy', N'05/11/2001', N'0367145454')
INSERT [dbo].[con_nguoi] ([cmnd], [dia_chi], [gioi_tinh], [ho_ten], [ngay_sinh], [sdt]) VALUES (N'333', N'21 đường số 7, Linh Tây, Thủ Đức, HCM', N'Nam', N'Trần Thị H', N'29/4/2019', N'555333')
INSERT [dbo].[con_nguoi] ([cmnd], [dia_chi], [gioi_tinh], [ho_ten], [ngay_sinh], [sdt]) VALUES (N'55446', N'26 đường số 7, Linh Tây, Thủ Đức, HCM', N'Nam', N'Trần Thị A', N'28/4/2022', N'84546')
INSERT [dbo].[con_nguoi] ([cmnd], [dia_chi], [gioi_tinh], [ho_ten], [ngay_sinh], [sdt]) VALUES (N'854548', N'19 đường số 7, Linh Tây, Thủ Đức, Hồ Chí Minh', N'Nam', N'Nguyễn Phạm T', N'06/04/2022', N'80000000')
GO
SET IDENTITY_INSERT [dbo].[co_soyte] ON 

INSERT [dbo].[co_soyte] ([macsyt], [dia_chi], [sdt], [tencsyt]) VALUES (1, N'123, Linh Tây, Thủ Đức, HCM', N'213221', N'Hoàng Hoa Thám')
INSERT [dbo].[co_soyte] ([macsyt], [dia_chi], [sdt], [tencsyt]) VALUES (4, N'12, Linh Tây, Thủ Đức, HCM', N'078754', N'Bệnh viện quân y 175')
INSERT [dbo].[co_soyte] ([macsyt], [dia_chi], [sdt], [tencsyt]) VALUES (6, N'111, Linh Tây, Thủ Đức, HCM', N'00000885', N'Trạm y tế Linh Tây')
SET IDENTITY_INSERT [dbo].[co_soyte] OFF
GO
SET IDENTITY_INSERT [dbo].[benh_nhan] ON 

INSERT [dbo].[benh_nhan] ([mabn], [ngay_phat_hien], [so_lan_mac], [so_mui_vacin], [cmnd_con_nguoi], [macsyt], [tt]) VALUES (1, N'09/04/2022', 1, 2, N'123', 1, NULL)
INSERT [dbo].[benh_nhan] ([mabn], [ngay_phat_hien], [so_lan_mac], [so_mui_vacin], [cmnd_con_nguoi], [macsyt], [tt]) VALUES (2, N'09/04/2022', 1, 3, N'1234', 1, NULL)
INSERT [dbo].[benh_nhan] ([mabn], [ngay_phat_hien], [so_lan_mac], [so_mui_vacin], [cmnd_con_nguoi], [macsyt], [tt]) VALUES (3, N'09/04/2022', 1, 2, N'12345', 1, NULL)
INSERT [dbo].[benh_nhan] ([mabn], [ngay_phat_hien], [so_lan_mac], [so_mui_vacin], [cmnd_con_nguoi], [macsyt], [tt]) VALUES (4, N'10/04/2022', 2, 2, N'123', 1, NULL)
INSERT [dbo].[benh_nhan] ([mabn], [ngay_phat_hien], [so_lan_mac], [so_mui_vacin], [cmnd_con_nguoi], [macsyt], [tt]) VALUES (6, N'10/04/2022', 2, 3, N'1234', 1, NULL)
INSERT [dbo].[benh_nhan] ([mabn], [ngay_phat_hien], [so_lan_mac], [so_mui_vacin], [cmnd_con_nguoi], [macsyt], [tt]) VALUES (7, N'28/04/2022', 2, 1, N'55446', 1, N'Dương tính')
INSERT [dbo].[benh_nhan] ([mabn], [ngay_phat_hien], [so_lan_mac], [so_mui_vacin], [cmnd_con_nguoi], [macsyt], [tt]) VALUES (8, N'29/04/2022', 1, 1, N'123456789', 4, N'Dương tính')
SET IDENTITY_INSERT [dbo].[benh_nhan] OFF
GO
INSERT [dbo].[nguoi_dung] ([uid], [cmnd_con_nguoi]) VALUES (N'CnygpFc8OSZPKpSp7BfTYmF6uRt2', N'251212694')
GO
INSERT [dbo].[nv_yte] ([uid], [chuc_vu], [cmnd_con_nguoi], [ma_csyt]) VALUES (N'AJSIwprGovQyrlZ7lmXbRuQrA9y1', N'Quản lý', N'123', 1)
GO
SET IDENTITY_INSERT [dbo].[phieu_khai_baoyte] ON 

INSERT [dbo].[phieu_khai_baoyte] ([ma_phieu_khai_bao], [cau_hoi1], [cau_hoi2], [cau_hoi3_1], [cau_hoi3_2], [cau_hoi3_3], [cmnd_nguoi_khai_bao], [date_time], [cmnd_con_nguoi]) VALUES (5, 0, 0, 0, 0, 0, N'123', N'03-04-2022 17:14:14', N'123')
INSERT [dbo].[phieu_khai_baoyte] ([ma_phieu_khai_bao], [cau_hoi1], [cau_hoi2], [cau_hoi3_1], [cau_hoi3_2], [cau_hoi3_3], [cmnd_nguoi_khai_bao], [date_time], [cmnd_con_nguoi]) VALUES (6, 0, 0, 0, 0, 0, N'123', N'03-04-2022 17:14:24', N'12345')
INSERT [dbo].[phieu_khai_baoyte] ([ma_phieu_khai_bao], [cau_hoi1], [cau_hoi2], [cau_hoi3_1], [cau_hoi3_2], [cau_hoi3_3], [cmnd_nguoi_khai_bao], [date_time], [cmnd_con_nguoi]) VALUES (7, 0, 0, 0, 0, 0, N'123', N'03-04-2022 17:18:42', N'123')
INSERT [dbo].[phieu_khai_baoyte] ([ma_phieu_khai_bao], [cau_hoi1], [cau_hoi2], [cau_hoi3_1], [cau_hoi3_2], [cau_hoi3_3], [cmnd_nguoi_khai_bao], [date_time], [cmnd_con_nguoi]) VALUES (8, 0, 0, 0, 0, 0, N'123', N'03-04-2022 17:18:51', N'123')
INSERT [dbo].[phieu_khai_baoyte] ([ma_phieu_khai_bao], [cau_hoi1], [cau_hoi2], [cau_hoi3_1], [cau_hoi3_2], [cau_hoi3_3], [cmnd_nguoi_khai_bao], [date_time], [cmnd_con_nguoi]) VALUES (9, 0, 0, 0, 0, 0, N'123', N'03-04-2022 17:25:42', N'123')
INSERT [dbo].[phieu_khai_baoyte] ([ma_phieu_khai_bao], [cau_hoi1], [cau_hoi2], [cau_hoi3_1], [cau_hoi3_2], [cau_hoi3_3], [cmnd_nguoi_khai_bao], [date_time], [cmnd_con_nguoi]) VALUES (10, 0, 0, 0, 0, 0, N'123', N'03-04-2022 17:43:49', N'123')
INSERT [dbo].[phieu_khai_baoyte] ([ma_phieu_khai_bao], [cau_hoi1], [cau_hoi2], [cau_hoi3_1], [cau_hoi3_2], [cau_hoi3_3], [cmnd_nguoi_khai_bao], [date_time], [cmnd_con_nguoi]) VALUES (11, 0, 0, 0, 0, 0, N'854548', N'10-04-2022 10:24:12', N'854548')
INSERT [dbo].[phieu_khai_baoyte] ([ma_phieu_khai_bao], [cau_hoi1], [cau_hoi2], [cau_hoi3_1], [cau_hoi3_2], [cau_hoi3_3], [cmnd_nguoi_khai_bao], [date_time], [cmnd_con_nguoi]) VALUES (12, 0, 0, 0, 0, 0, N'251212694', N'10-04-2022 11:34:35', N'251212694')
INSERT [dbo].[phieu_khai_baoyte] ([ma_phieu_khai_bao], [cau_hoi1], [cau_hoi2], [cau_hoi3_1], [cau_hoi3_2], [cau_hoi3_3], [cmnd_nguoi_khai_bao], [date_time], [cmnd_con_nguoi]) VALUES (13, 0, 0, 0, 0, 0, N'251212694', N'10-04-2022 11:35:00', N'251212694')
INSERT [dbo].[phieu_khai_baoyte] ([ma_phieu_khai_bao], [cau_hoi1], [cau_hoi2], [cau_hoi3_1], [cau_hoi3_2], [cau_hoi3_3], [cmnd_nguoi_khai_bao], [date_time], [cmnd_con_nguoi]) VALUES (14, 0, 0, 0, 0, 0, N'251212694', N'29-04-2022 19:50:31', N'251212694')
SET IDENTITY_INSERT [dbo].[phieu_khai_baoyte] OFF
GO
