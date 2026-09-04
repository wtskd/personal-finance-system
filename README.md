# 基于 Spring Cloud 的个人财务管理系统

> 课程设计项目 ｜ Spring Cloud Alibaba 微服务架构 ｜ 管理员 / 普通用户双角色

一个用于个人收支管理的系统，覆盖账户、交易流水、收支分类、预算类型、商品订单等 8 大业务模块，并通过 ECharts 提供月度收支趋势与支出占比分析。

## 功能一览

| 模块 | 说明 |
|---|---|
| 用户管理 | 用户增删改查、批量删除（仅管理员） |
| 角色管理 | 角色维护，登录时下拉选择角色 |
| 账户管理 | 账户名称 / 余额 / 状态 / 预算类型，模糊搜索 + 状态筛选 |
| 交易记录 | 收入支出流水，按类型与账户筛选，收入绿色 / 支出红色 |
| 收支分类 | 分类增删改查，分类数据用于支出占比统计 |
| 预算类型 | 账户的预算分类管理 |
| 商品管理 / 订单管理 | 商品维护与订单列表查看 |
| 数据分析 | ECharts 月度收支趋势折线图 + 支出占比饼图 |

**权限设计**：基于角色的 Session 登录鉴权，管理员可查看管理全部数据；普通用户仅能查看自己的数据；批量删除按钮仅管理员可见。

## 技术栈

- **微服务**：Spring Cloud Alibaba（Nacos 服务注册与发现、OpenFeign 远程调用、LoadBalancer）
- **网关**：Spring Cloud Gateway（统一路由 + 跨域处理）
- **持久层**：MyBatis-Plus
- **数据库**：MySQL 8
- **前端**：Layui 2.5.4 + LayuiAdmin 模板、ECharts、Thymeleaf

## 模块结构

```
personal-finance-system
├── common-api          # 公共模块：实体类、DTO、统一返回、Knife4j 配置
├── project-serivce     # 业务服务（9001）：核心业务接口与数据访问
├── project-web         # Web 服务（8001）：页面渲染与前端资源
├── project-gateway     # 网关服务（9999）：路由转发、跨域
└── sql/personal_finance.sql   # 数据库建表脚本
```

## 快速开始

### 环境要求

- JDK 17+、Maven 3.6+
- MySQL 8.0
- Nacos 2.x（需先启动）

### 步骤

1. 创建数据库并导入表结构

```sql
CREATE DATABASE personal_finance DEFAULT CHARSET utf8mb4;
USE personal_finance;
SOURCE sql/personal_finance.sql;
```

2. 修改数据库连接

`project-serivce/src/main/resources/application.yml`：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/personal_finance?serverTimezone=Asia/Shanghai
    username: root
    password: your_mysql_password   # ← 改成你的密码
  cloud:
    nacos:
      discovery:
        server-addr: 127.0.0.1:8848   # ← 改成你的 Nacos 地址
```

3. 启动 Nacos，然后依次启动

```bash
mvn clean install                       # 根目录安装公共模块
# 依次启动：project-serivce(9001) → project-web(8001) → project-gateway(9999)
```

4. 访问 `http://localhost:9999`，选择角色后登录

## 说明

- 本项目为课程设计作品，用于学习 Spring Cloud 微服务开发全流程
- 前端静态资源包含 LayuiAdmin 模板文件（第三方，体积较大），仅用于项目运行
