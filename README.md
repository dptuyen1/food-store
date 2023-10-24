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

thư mục dự án (food-store) > server > src > main > resources > databases.properties

![git-tutorial](https://res.cloudinary.com/dzbcst18v/image/upload/v1698088708/food-store/sc1apq3pwytf1qjruijt.png)

C2. NetBeans:

![git-tutorial](https://res.cloudinary.com/dzbcst18v/image/upload/v1698088228/food-store/ubq37pekr079bl7io8lh.png)

Thiết lập thông số như sau:

    hibernate.dialect=org.hibernate.dialect.MySQLDialect

    hibernate.showSql=true

    hibernate.connection.driverClass=com.mysql.cj.jdbc.Driver

    hibernate.connection.url=jdbc:mysql://localhost:3306/food-store

    --- Quan trọng! ---

    hibernate.connection.username=<username>

    hibernate.connection.password=<password>

## III. Tổng quan

### 1. Chức năng

![git-tutorial](https://res.cloudinary.com/dzbcst18v/image/upload/v1698089612/food-store/bwmazyxktckcvo59r9ti.png)

### 2. Sử dụng

Server (**bắt buộc chạy đầu tiên**):

Mở dự án trong folder <server> và nhấn vào nút run project (như hình) để chạy.

![git-tutorial](https://res.cloudinary.com/dzbcst18v/image/upload/v1698111224/food-store/kc54ict4mx4y8crakipx.png)

> **<Tài khoản - mật khẩu>**

```
admin - 123 (quản trị viên)

staff - 123 (nhân viên)
```

**Dùng 2 tài khoản trên để quản lý dữ liệu, xem thống kê báo cáo, xác nhận hóa đơn, xem doanh thu ngày...**

Client:

Mở dự án trong folder <client> và gõ vào lệnh <npm start> (như hình) để chạy.

![git-tutorial](https://res.cloudinary.com/dzbcst18v/image/upload/v1698111464/food-store/nf2axlylthjq4eiddisk.png)

```
customer - 123 (khách hàng)
```

**Dùng tài khoản trên để đặt hàng trực tuyến, xem lịch sử mua hàng, bình luận sản phẩm...**

http://localhost:3000/ (đường dẫn đến giao diện mua hàng trực tuyến).

![git-tutorial](https://res.cloudinary.com/dzbcst18v/image/upload/v1698113074/food-store/b6lfhiia10c5lvz68cgx.png)

http://localhost:3000/in-store (đường dẫn đến giao diện mua hàng tại quán (pos)).

![git-tutorial](https://res.cloudinary.com/dzbcst18v/image/upload/v1698113155/food-store/h5t5ukeyb85tprm97sdu.png)
