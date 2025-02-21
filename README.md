# Xây dựng chương trình quản lý sinh viên sử dụng kỹ thuật phân tán RMI

## Deployment

### Bước 1: Kết nối database  
Đảm bảo database đã được thiết lập và có đầy đủ dữ liệu cần thiết.

### Bước 2: Biên dịch toàn bộ chương trình  
```sh
javac *.java
```

### Bước 3: Bắt đầu với registry  
```sh
start rmiregistry
```

### Bước 4: Chạy Server  
```sh
java RMIServer
```

### Bước 5: Chạy Client  
```sh
java RMIClient
