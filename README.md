# 🐍 贪吃蛇游戏（Snake Game）

这是一个使用 Java Swing 开发的简易贪吃蛇游戏，具备图形界面、键盘控制、糖果碰撞、自动移动、游戏胜利与结束判定等功能。

---

## 📁 项目结构

```
SnakeGame/
├── Candy.java       // 控制糖果生成与碰撞
├── GamePanel.java   // 主界面与游戏逻辑线程
├── Snake.java       // 蛇的模型与逻辑
└── Test.java        // 启动类，窗口初始化与线程启动
```

---

## 🔧 功能说明

- **Snake.java**：管理蛇的方向、移动、身体增长、自撞检测。
- **Candy.java**：管理糖果的生成位置与碰撞检测。
- **GamePanel.java**：游戏逻辑主类，负责绘图、监听键盘、处理多线程事件（吃糖、撞墙、刷新等）。
- **Test.java**：游戏入口类，负责创建窗口并启动游戏相关线程。

---

## ▶️ 如何运行

### ✅ 方法一：使用 IntelliJ IDEA（推荐）

1. 打开 IntelliJ IDEA，选择 **"Open"** 并导入包含四个 `.java` 文件的文件夹。
2. 确保 Project SDK 设置为 **JDK 21 或以上**。
   - 可在 `File > Project Structure > Project` 中配置。
3. 右键点击 `Test.java`，选择 **Run 'Test'** 即可启动游戏。

### ✅ 方法二：命令行运行

1. 安装 JDK 21+ 并配置环境变量。
2. 在终端中进入文件目录：

   ```bash
   javac Snake.java Candy.java GamePanel.java Test.java
   java SnakeGame.Test
   ```

---

## ⌨️ 操作方式

- 使用 **方向键**（↑ ↓ ← →）或者 **WASD** 控制蛇的移动。
- 按下 **空格** 加速,松开 **空格** 恢复默认速度
- 每吃到一个糖果，蛇会增长并加分。
- 游戏胜利条件：吃到设定数量糖果。
- 游戏失败条件：撞墙或撞到自己。

---

## 🧱 技术要点

- 使用 `JPanel` 绘制游戏图形。
- 多线程并发控制游戏逻辑：
  - 自动移动线程
  - 吃糖检测线程
  - 撞墙检测线程
  - 撞自身检测线程
  - 胜利判定线程
  - 时间显示线程
  - 界面刷新线程

---

## 🧑‍💻 作者

- **Github**:**Helloqiyuan**
- **Gitee**:**Helloqiyuan**