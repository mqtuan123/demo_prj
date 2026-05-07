# 🌍 Wild-Life Eco Simulation

Một dự án mô phỏng hệ sinh thái hoang dã được viết hoàn toàn bằng **Java**, sử dụng **Swing** cho giao diện đồ họa (GUI). Dự án áp dụng chặt chẽ các nguyên tắc Lập trình hướng đối tượng (OOP), mô hình MVC và **Strategy Design Pattern** để tạo ra "trí thông minh" cho các loài động vật.

## ✨ Tính năng nổi bật

- **🌱 Sinh thái tự nhiên:** Bao gồm các loài thực vật (Cỏ, Cây ăn quả tự sinh sôi) và Động vật (Thỏ, Nai, Sói, Hổ, Voi, Người).
- **🧠 Trí thông minh nhân tạo (Strategy Pattern):**
  - `HunterStrategy`: Thú ăn thịt tự động quét bán kính xung quanh để săn mồi và tăng tốc khi phát hiện mục tiêu.
  - `ScaredStrategy`: Động vật ăn cỏ biết tính toán góc độ và bỏ chạy ngược hướng an toàn khi thú dữ đến gần.
  - `PassiveStrategy`: Động vật tự do đi dạo và tự động tìm đến nguồn thức ăn khi đói.
- **🎨 Giao diện tương tác 2 chế độ:**
  - **Basic Mode:** Render hình học cơ bản (vuông, tròn) để tối ưu hiệu năng.
  - **Graphic Mode:** Render avatar sinh động lấy tự động từ API qua Internet.
- **🗺️ Bản đồ tương tác (MVC):** Người chơi có thể nhấp chuột gieo mầm cây hoặc tạo vách đá cản đường, và chọn 4 môi trường: Đồng cỏ, Rừng rậm, Hồ nước, Bản đồ hỗn hợp.

## 🛠️ Cấu trúc dự án

Dự án được phân chia rạch ròi theo mô hình **MVC** (Model-View-Controller) để đảm bảo không bị lẫn lộn giữa logic game và logic vẽ đồ họa:

```text
wildlife-sim/
├── src/main/java/com/wildlife/
│   ├── model/         # (BioLogic) Quản lý sinh tồn, đói khát, tính toán thuật toán di chuyển
│   │   ├── entity/    # Cây cỏ, Động vật (Rabbit, Tiger,...)
│   │   ├── strategy/  # "Bộ não" đưa ra quyết định di chuyển (Strategy Pattern)
│   │   └── engine/    # Game Loop giả lập thời gian thực
│   ├── view/          # (ViewLogic) Vẽ lên màn hình bằng Java Swing
│   ├── controller/    # Quản lý tương tác chuột của người dùng
│   └── Main.java      # Điểm khởi chạy của dự án
└── pom.xml            # Cấu hình dự án Maven
```

## 🚀 Hướng dẫn Cài đặt & Chạy dự án

### Yêu cầu hệ thống:
- Cài đặt sẵn **Java JDK 11** trở lên.
- Có cài đặt **Maven** (hoặc chạy trực tiếp từ IDE).

### Cách 1: Chạy bằng Maven (Dòng lệnh)
Mở terminal, di chuyển vào thư mục `wildlife-sim` (nơi chứa file `pom.xml`) và chạy lệnh sau:
```bash
mvn clean compile exec:java
```

### Cách 2: Chạy trực tiếp qua IDE (VS Code, IntelliJ, Eclipse)
1. Mở thư mục `wildlife-sim` vào IDE của bạn.
2. Mở file `src/main/java/com/wildlife/Main.java`.
3. Nhấn nút **Run** (Play) trong IDE của bạn. Cửa sổ ứng dụng sẽ tự động hiện lên.

### Cách 3: Chạy bằng Javac thuần túy
Nếu bạn không dùng Maven, bạn có thể tự biên dịch bằng tay:
```bash
cd wildlife-sim/src/main/java
javac com/wildlife/model/environment/*.java com/wildlife/model/entity/*.java com/wildlife/model/strategy/*.java com/wildlife/model/engine/*.java com/wildlife/view/*.java com/wildlife/controller/*.java com/wildlife/*.java
java com.wildlife.Main
```

## 🎮 Cách sử dụng

- Ở giao diện chính, chọn thẻ **Environment** để thay đổi địa hình.
- Chuyển thẻ **View Mode** sang **Graphic** để xem ảnh trực quan của các loài động vật.
- Trong thẻ **Click Action**, chọn `Plant Grass` (Gieo cỏ) và bấm chuột liên tục xuống bản đồ để dụ Thỏ/Nai chạy đến ăn!

---