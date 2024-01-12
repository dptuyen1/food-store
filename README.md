## I. Công cụ

NetBeans: 15

JDK: 17

Apache Tomcat: 9

MySQL: 8.0 trở lên

## II. Cấu hình

### 1. Cơ sở dữ liệu

Mở MySQL, sau đó chạy file db.sql trong thư mục database

Chọn Refresh All, kiểm tra xem đã có cơ sở dữ liệu tên food-store hay chưa

Nếu chưa, tạo cơ sở dữ liệu rỗng, đặt tên là food-store, làm lại bước 1

### 2. Kết nối cơ sở dữ liệu

Có 2 cách

C1. Windows Explorer:

Thư mục dự án (food-store) > server > src > main > resources > databases.properties

C2. NetBeans:

Thiết lập thông số như sau:

    hibernate.dialect=org.hibernate.dialect.MySQLDialect

    hibernate.showSql=true

    hibernate.connection.driverClass=com.mysql.cj.jdbc.Driver

    hibernate.connection.url=jdbc:mysql://localhost:3306/food-store

    --- Quan trọng! ---

    hibernate.connection.username=<username>

    hibernate.connection.password=<password>

## III. Tổng quan

### 1. Sử dụng

Server (**bắt buộc chạy đầu tiên**):

Mở dự án trong folder **"server"** và nhấn vào nút build project để tải xuống những modules cần thiết

Cho đến khi output hiện thông báo thành công

Tiếp theo đó, nhấn vào nút run project để chạy

> **<Tài khoản - mật khẩu>**

```
admin - 123 (quản trị viên)

staff - 123 (nhân viên)
```

**Dùng 2 tài khoản trên để quản lý dữ liệu, xem thống kê báo cáo, xác nhận hóa đơn, xem doanh thu ngày...**

Client:

Mở dự án trong folder **"client"** và gõ vào lệnh **"npm i"** để tải xuống những modules cần thiết

Sau khi hiển thị thông báo tải xuống thành công những modules cần thiết, gõ tiếp lệnh **"npm start"** để chạy

```
customer - 123 (khách hàng)
```

**Dùng tài khoản trên để đặt hàng trực tuyến, xem lịch sử mua hàng, bình luận sản phẩm...**
